package com.example.practicahotel.modelo;

import java.util.Date;

public class ReservaVO {

    int id_reserva;
    Date fecha_entrada;
    Date fecha_salida;
    int num_habitaciones;
    String tipo_habitacion;
    boolean fumador;
    String regimen;
    String id_cliente;

    //Constructor por defecto

    public ReservaVO(int id_reserva, Date fecha_entrada, Date fecha_salida, int num_habitaciones, String tipo_habitacion, boolean fumador, String regimen, String id_cliente) {
        this.id_reserva = id_reserva;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.num_habitaciones = num_habitaciones;
        this.tipo_habitacion = tipo_habitacion;
        this.fumador = fumador;
        this.regimen = regimen;
        this.id_cliente = id_cliente;
    }


    //Getters y setters

    public int getId_reserva() {return id_reserva;}
    public Date getFecha_entrada() {return fecha_entrada;}
    public Date getFecha_salida() {return fecha_salida;}
    public int getNum_habitaciones() {return num_habitaciones;}
    public String getTipo_habitacion() {return tipo_habitacion;}
    public boolean isFumador() {return fumador;}
    public String getRegimen() {return regimen;}
    public String getId_cliente() {return id_cliente;}

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public void setFecha_entrada(Date fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public void setNum_habitaciones(int num_habitaciones) {
        this.num_habitaciones = num_habitaciones;
    }

    public void setTipo_habitacion(String tipo_habitacion) {
        this.tipo_habitacion = tipo_habitacion;
    }

    public void setFumador(boolean fumador) {
        this.fumador = fumador;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }
}
