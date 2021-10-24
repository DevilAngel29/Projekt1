package com.company;

public class Stat {
    public String zkratkaStatu;
    public String nazevStatu;
    public double zakladniSazba;
    public double snizenaSazba;
    public boolean parkingRate;

    public Stat(String zkratkaStatu, String nazevStatu, double zakladniSazba, double snizenaSazba, boolean parkingRate) {
        this.zkratkaStatu = zkratkaStatu;
        this.nazevStatu = nazevStatu;
        this.zakladniSazba = zakladniSazba;
        this.snizenaSazba = snizenaSazba;
        this.parkingRate = parkingRate;
    }

    public Stat(String zkratkaStatuStr, String nazevStatuStr, String zakladniSazbaStr, String snizenaSazbaStr, String parkingRateStr) throws ModelException {
        this.zkratkaStatu = zkratkaStatuStr;
        this.nazevStatu = nazevStatuStr;
        try {
            this.zakladniSazba = Double.parseDouble(zakladniSazbaStr);
        }catch (NumberFormatException ex) {
            throw new ModelException("Spatne zadane cislo zakladni sazby:"+ex.getLocalizedMessage());
        }
        try {
            this.snizenaSazba = Double.parseDouble(snizenaSazbaStr);
        } catch (NumberFormatException ex) {
            throw new ModelException("Spatne zadane cislo snizene sazby:"+ex.getLocalizedMessage());
        }

        this.parkingRate = Boolean.parseBoolean(parkingRateStr);
    }


    public String getZkratkaStatu() {
        return zkratkaStatu;
    }

    public void setZkratkaStatu(String zkratkaStatu) {
        this.zkratkaStatu = zkratkaStatu;
    }

    public String getNazevStatu() {
        return nazevStatu;
    }

    public void setNazevStatu(String nazevStatu) {
        this.nazevStatu = nazevStatu;
    }

    public double getZakladniSazba() {
        return zakladniSazba;
    }

    public void setZakladniSazba(double zakladniSazba) {
        this.zakladniSazba = zakladniSazba;
    }

    public double getSnizenaSazba() {
        return snizenaSazba;
    }

    public void setSnizenaSazba(double snizenaSazba) {
        this.snizenaSazba = snizenaSazba;
    }

    public boolean isParkingRate() {
        return parkingRate;
    }

    public void setParkingRate(boolean parkingRate) {
        this.parkingRate = parkingRate;
    }

    @Override
    public String toString() {
        return "Sazba-"+zkratkaStatu+nazevStatu+zakladniSazba+snizenaSazba+parkingRate;
    }
}
