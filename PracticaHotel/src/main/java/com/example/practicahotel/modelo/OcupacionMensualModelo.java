package com.example.practicahotel.modelo;

public class OcupacionMensualModelo {
    private String mes;
    private double porcentajeOcupacion;

    public OcupacionMensualModelo(String mes, double porcentajeOcupacion) {
        this.mes = mes;
        this.porcentajeOcupacion = porcentajeOcupacion;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getPorcentajeOcupacion() {
        return porcentajeOcupacion;
    }

    public void setPorcentajeOcupacion(double porcentajeOcupacion) {
        this.porcentajeOcupacion = porcentajeOcupacion;
    }
}

