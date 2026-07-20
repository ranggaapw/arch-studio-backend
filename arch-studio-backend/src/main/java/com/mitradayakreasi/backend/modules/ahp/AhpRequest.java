package com.mitradayakreasi.backend.modules.ahp;

import lombok.Data;

@Data
public class AhpRequest {
    private Double bahanVsBudget;
    private Double bahanVsKategori;
    private Double budgetVsKategori;
}
