package com.mitradayakreasi.backend.modules.ahp;

import com.mitradayakreasi.backend.modules.project.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AhpResult {
    private Double wBahan;
    private Double wBudget;
    private Double wKategori;
    private Double consistencyRatio;
    private List<ProjectRank> rankings;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ProjectRank {
        private Project project;
        private Double score;
    }
}
