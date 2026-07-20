package com.mitradayakreasi.backend.modules.ahp;

import com.mitradayakreasi.backend.modules.project.Project;
import com.mitradayakreasi.backend.modules.project.ProjectRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AhpService {

    private final ProjectRepository projectRepository;

    public AhpService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public AhpResult calculate(AhpRequest request) {
        // 1. Validasi input agar tidak bernilai kosong atau <= 0
        if (request.getBahanVsBudget() == null || request.getBahanVsBudget() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bahanVsBudget harus lebih besar dari 0");
        }
        if (request.getBahanVsKategori() == null || request.getBahanVsKategori() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bahanVsKategori harus lebih besar dari 0");
        }
        if (request.getBudgetVsKategori() == null || request.getBudgetVsKategori() <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "budgetVsKategori harus lebih besar dari 0");
        }

        // 2. Bentuk matriks perbandingan berpasangan 3x3 untuk Kriteria (Bahan, Budget, Kategori)
        // Indeks: 0 = Bahan, 1 = Budget, 2 = Kategori
        double[][] matrix = new double[3][3];
        matrix[0][0] = 1.0;
        matrix[0][1] = request.getBahanVsBudget();
        matrix[0][2] = request.getBahanVsKategori();

        matrix[1][0] = 1.0 / request.getBahanVsBudget();
        matrix[1][1] = 1.0;
        matrix[1][2] = request.getBudgetVsKategori();

        matrix[2][0] = 1.0 / request.getBahanVsKategori();
        matrix[2][1] = 1.0 / request.getBudgetVsKategori();
        matrix[2][2] = 1.0;

        // 3. Hitung bobot kriteria (Eigenvector W) menggunakan metode rata-rata kolom ternormalisasi
        double[] colSums = new double[3];
        for (int j = 0; j < 3; j++) {
            colSums[j] = matrix[0][j] + matrix[1][j] + matrix[2][j];
        }

        double[] w = new double[3];
        for (int i = 0; i < 3; i++) {
            double sumNormalizedRow = 0;
            for (int j = 0; j < 3; j++) {
                sumNormalizedRow += matrix[i][j] / colSums[j];
            }
            w[i] = sumNormalizedRow / 3.0;
        }

        // 4. Hitung Lambda Max (Eigenvalue Terbesar)
        double[] aw = new double[3];
        for (int i = 0; i < 3; i++) {
            aw[i] = 0;
            for (int j = 0; j < 3; j++) {
                aw[i] += matrix[i][j] * w[j];
            }
        }

        double lambdaMax = 0;
        for (int i = 0; i < 3; i++) {
            lambdaMax += aw[i] / w[i];
        }
        lambdaMax = lambdaMax / 3.0;

        // 5. Hitung Consistency Index (CI) & Consistency Ratio (CR)
        // Saaty RI table untuk n=3 adalah 0.58
        double ci = (lambdaMax - 3.0) / 2.0;
        double ri = 0.58;
        double cr = ci / ri;

        // Validasi konsistensi matriks
        if (cr > 0.1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Matriks perbandingan tidak konsisten (CR = " + String.format("%.4f", cr) +
                    " > 0.1). Silakan masukkan kembali nilai perbandingan yang konsisten.");
        }

        // 6. Ambil seluruh data Project dari database
        List<Project> projects = projectRepository.findAll();
        if (projects.isEmpty()) {
            return new AhpResult(w[0], w[1], w[2], cr, new ArrayList<>());
        }

        // 7. Berikan skor otomatis pada masing-masing Project terhadap 3 kriteria
        List<Double> rawBudgets = new ArrayList<>();
        List<Double> rawBahans = new ArrayList<>();
        List<Double> rawKategoris = new ArrayList<>();

        for (Project project : projects) {
            // Jika budget kosong atau <= 0, berikan nilai default (misal 100 juta) agar perhitungan tidak error
            double budgetVal = (project.getBudget() != null && project.getBudget() > 0) ? project.getBudget() : 100000000.0;
            double bahanVal = evaluateBahanScore(project.getMaterials());
            double kategoriVal = evaluateKategoriScore(project.getKategori());

            rawBudgets.add(budgetVal);
            rawBahans.add(bahanVal);
            rawKategoris.add(kategoriVal);
        }

        // 7a. Dapatkan min/max untuk keperluan normalisasi
        double minBudget = rawBudgets.stream().min(Double::compare).orElse(1.0);
        if (minBudget <= 0) minBudget = 1.0;

        double maxBahan = rawBahans.stream().max(Double::compare).orElse(1.0);
        if (maxBahan <= 0) maxBahan = 1.0;

        double maxKategori = rawKategoris.stream().max(Double::compare).orElse(1.0);
        if (maxKategori <= 0) maxKategori = 1.0;

        // 7b. Normalisasi dan hitung skor akhir per alternatif
        List<AhpResult.ProjectRank> rankings = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            
            // Cost Criteria (Budget): Semakin murah semakin tinggi skornya -> min / val
            double normBudget = minBudget / rawBudgets.get(i);
            
            // Benefit Criteria (Bahan & Kategori): Semakin tinggi nilai kualitas semakin tinggi skornya -> val / max
            double normBahan = rawBahans.get(i) / maxBahan;
            double normKategori = rawKategoris.get(i) / maxKategori;

            // Total skor = penjumlahan (skor alternatif * bobot kriteria)
            double score = (w[0] * normBahan) + (w[1] * normBudget) + (w[2] * normKategori);
            rankings.add(new AhpResult.ProjectRank(project, score));
        }

        // 8. Urutkan hasil ranking dari skor terbesar ke terkecil (Desain Terbaik)
        rankings.sort((a, b) -> Double.compare(b.getScore(), a.getScore()));

        return new AhpResult(w[0], w[1], w[2], cr, rankings);
    }

    /**
     * Konversi string bahan (materials) ke nilai numerik (Benefit Criteria).
     * Skala 1 - 5 berdasarkan kompleksitas/kemewahan bahan.
     */
    private double evaluateBahanScore(String materials) {
        if (materials == null || materials.trim().isEmpty()) {
            return 1.0; // Default score terendah
        }
        String mats = materials.toLowerCase();
        if (mats.contains("marmer") || mats.contains("granit") || mats.contains("premium")) {
            return 5.0;
        } else if (mats.contains("baja") || mats.contains("besi") || mats.contains("kaca") || mats.contains("metal")) {
            return 4.0;
        } else if (mats.contains("beton") || mats.contains("semen") || mats.contains("bata")) {
            return 3.0;
        } else if (mats.contains("kayu") || mats.contains("wood")) {
            return 2.0;
        }
        return 1.0;
    }

    /**
     * Konversi list string kategori ke nilai numerik (Benefit Criteria).
     * Ambil skor maksimal dari kategori yang melekat pada proyek tersebut.
     */
    private double evaluateKategoriScore(List<String> kategori) {
        if (kategori == null || kategori.isEmpty()) {
            return 1.0; // Default score terendah
        }
        double maxScore = 1.0;
        for (String kat : kategori) {
            if (kat == null) continue;
            String k = kat.toLowerCase();
            double currentScore = 1.0;
            if (k.contains("luxury") || k.contains("klasik") || k.contains("classic") || k.contains("premium")) {
                currentScore = 5.0;
            } else if (k.contains("modern") || k.contains("contemporary")) {
                currentScore = 4.0;
            } else if (k.contains("minimalis") || k.contains("scandinavian") || k.contains("industrial") || k.contains("vintage")) {
                currentScore = 3.0;
            } else if (k.contains("interior") || k.contains("eksterior") || k.contains("outdoor") || k.contains("architect")) {
                currentScore = 2.0;
            }
            if (currentScore > maxScore) {
                maxScore = currentScore;
            }
        }
        return maxScore;
    }
}
