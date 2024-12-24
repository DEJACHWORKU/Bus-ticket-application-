package com.example.abs_app.trafic;

public class DataClass1 {
    private String date;
    private String ocar;
    private String ocity;
    private String okm;
    private String oname;
    private String ophone;
    private String oprice;
    private String keyss;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOcar() {
        return ocar;
    }

    public void setOcar(String ocar) {
        this.ocar = ocar;
    }

    public String getOcity() {
        return ocity;
    }

    public void setOcity(String ocity) {
        this.ocity = ocity;
    }

    public String getOkm() {
        return okm;
    }

    public void setOkm(String okm) {
        this.okm = okm;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public String getOphone() {
        return ophone;
    }

    public void setOphone(String ophone) {
        this.ophone = ophone;
    }

    public String getOprice() {
        return oprice;
    }

    public void setOprice(String oprice) {
        this.oprice = oprice;
    }

    public String getKeyss() {
        return keyss;
    }

    public void setKeyss(String key) {
        this.keyss = key;
    }

    public DataClass1(String date, String ocar, String ocity, String okm, String oname, String ophone, String oprice) {
        this.date = date;
        this.ocar = ocar;
        this.ocity = ocity;
        this.okm = okm;
        this.oname = oname;
        this.ophone = ophone;
        this.oprice = oprice;


    }

    public DataClass1() {
    }
}