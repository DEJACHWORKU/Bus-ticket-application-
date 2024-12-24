package com.example.abs_app;

public class dtaclass {
    private String ocity;
    private String oprice;
    private String oname;
    private String ophone;
    private String ocar;
    private String okm;
    private String date;
    private String keyy;


    public String getDate() {
        return date;
    }

    public String getOcity() {
        return ocity;
    }

    public String getOprice() {
        return oprice;
    }

    public String getOname() {
        return oname;
    }

    public String getOphone() {
        return ophone;
    }

    public String getOcar() {
        return ocar;
    }

    public String getOkm() {
        return okm;
    }

    public void setKeyy(String key) {
        this.keyy = key;
    }

    public String getKeyy() {
        return keyy;
    }

    public dtaclass(String ocity, String oprice, String oname, String ophone, String ocar, String okm, String date) {
        this.ocity = ocity;
        this.oprice = oprice;
        this.oname = oname;
        this.ophone = ophone;
        this.ocar = ocar;
        this.okm = okm;
        this.date = date;
    }
}
