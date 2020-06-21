package com.fiek.transfuzioni_gjakut;

public class addDonorDataInsert {

    private String emri;
    private String mbiemri;
    private String email;
    private String tipiGjakut;
    private String telefoni;
    private Integer sasia;

    public addDonorDataInsert() {
    }

    public addDonorDataInsert(String emri,String mbiemri,String email,String tipiGjakut,String telefoni,int sasia) {
    this.emri = emri;
    this.mbiemri = mbiemri;
    this.email = email;
    this.tipiGjakut = tipiGjakut;
    this.telefoni = telefoni;
    this.sasia = sasia;

    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipiGjakut() {
        return tipiGjakut;
    }

    public void setTipiGjakut(String tipiGjakut) {
        this.tipiGjakut = tipiGjakut;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(String telefoni) {
        this.telefoni = telefoni;
    }

    public Integer getSasia() {
        return sasia;
    }

    public void setSasia(Integer sasia) {
        this.sasia = sasia;
    }
}
