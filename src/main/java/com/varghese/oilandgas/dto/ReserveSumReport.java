package com.varghese.oilandgas.dto;

/**
 * Created by newuser on 12/14/16.
 */
public class ReserveSumReport {

    private int totalExistingOil;
    private int totalOil;
    private int totalExistingGas;
    private int totalNaturalGas;


    public int getTotalExistingOil() {
        return totalExistingOil;
    }

    public void setTotalExistingOil(int totalExistingOil) {
        this.totalExistingOil = totalExistingOil;
    }

    public int getTotalOil() {
        return totalOil;
    }

    public void setTotalOil(int totalOil) {
        this.totalOil = totalOil;
    }

    public int getTotalExistingGas() {
        return totalExistingGas;
    }

    public void setTotalExistingGas(int totalExistingGas) {
        this.totalExistingGas = totalExistingGas;
    }

    public int getTotalNaturalGas() {
        return totalNaturalGas;
    }

    public void setTotalNaturalGas(int totalNaturalGas) {
        this.totalNaturalGas = totalNaturalGas;
    }
}
