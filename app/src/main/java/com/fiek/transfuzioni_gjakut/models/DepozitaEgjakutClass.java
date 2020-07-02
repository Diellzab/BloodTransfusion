
package com.fiek.transfuzioni_gjakut.models;

public class DepozitaEgjakutClass {
    private Long sasiaGjakut;
    private String tipiGjakut;

    public DepozitaEgjakutClass(){}

    public DepozitaEgjakutClass(Long sasiaGjakut, String tipiGjakut) {
        this.sasiaGjakut = sasiaGjakut;
        this.tipiGjakut = tipiGjakut;
    }

    public Long getSasiaGjakut() {
        return sasiaGjakut;
    }

    public void setSasiaGjakut(Long sasiaGjakut) {
        this.sasiaGjakut = sasiaGjakut;
    }

    public String getTipiGjakut() {
        return tipiGjakut;
    }

    public void setTipiGjakut(String tipiGjakut) {
        this.tipiGjakut = tipiGjakut;
    }
}
