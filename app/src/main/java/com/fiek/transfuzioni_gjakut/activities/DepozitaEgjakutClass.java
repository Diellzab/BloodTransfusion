package com.fiek.transfuzioni_gjakut.activities;

public class DepozitaEgjakutClass {
    private Integer sasia;
    private String tipiGjakut;

    public DepozitaEgjakutClass() {}

    public DepozitaEgjakutClass(String tipiGjakut,Integer sasia) {
        this.tipiGjakut = tipiGjakut;
        this.sasia = sasia;
    }

    public Integer getSasia() {
        return sasia;
    }

    public void setSasia(Integer sasia) {
        this.sasia = sasia;
    }

    public String getTipiGjakut() {
        return tipiGjakut;
    }

    public void setTipiGjakut(String tipiGjakut) {
        this.tipiGjakut = tipiGjakut;
    }
}
