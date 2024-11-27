package com.example.practicahotel.view;


import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;

public class Reserva {

    //Atributos de la clase

    private final IntegerProperty id_reserva;
    private final ObjectProperty<LocalDate> fecha_entrada;
    private final ObjectProperty<LocalDate> fecha_salida;
    private final IntegerProperty num_habitaciones;
    private final StringProperty tipo_habitacion;
    private final BooleanProperty fumador;
    private final StringProperty regimen;
    private final StringProperty id_cliente;


    //Constructores

    public Reserva() {
        this.id_reserva = new SimpleIntegerProperty();
        this.fecha_entrada = new SimpleObjectProperty();
        this.fecha_salida = new SimpleObjectProperty();
        this.num_habitaciones = new SimpleIntegerProperty();
        this.tipo_habitacion = new SimpleStringProperty();
        this.fumador = new SimpleBooleanProperty();
        this.regimen = new SimpleStringProperty();
        this.id_cliente = new SimpleStringProperty();

    }

    public Reserva(int id_reserva, LocalDate fecha_entrada, LocalDate fecha_salida, int num_habitaciones, String tipo_habitacion, boolean fumador, String regimen, String id_cliente) {
        this.id_reserva = new SimpleIntegerProperty(id_reserva);
        this.fecha_entrada = new SimpleObjectProperty(fecha_entrada);
        this.fecha_salida = new SimpleObjectProperty(fecha_salida);
        this.num_habitaciones = new SimpleIntegerProperty(num_habitaciones);
        this.tipo_habitacion = new SimpleStringProperty(tipo_habitacion);
        this.fumador = new SimpleBooleanProperty(fumador);
        this.regimen = new SimpleStringProperty(regimen);
        this.id_cliente = new SimpleStringProperty(id_cliente);


    }

    //Getters y setters

    public int getId_Reserva() {return id_reserva.getValue();}
    public void setId_Reserva(int id_reserva) {this.id_reserva.setValue(id_reserva);}
    public IntegerProperty id_reservaProperty(){
        return id_reserva;
    }

    public LocalDate getFecha_Entrada() {
        return fecha_entrada.get();
    }
    public void setFecha_Entrada(LocalDate fecha_entrada) {
        this.fecha_entrada.set(fecha_entrada);}
    public ObjectProperty<LocalDate> fecha_entradaProperty() {
        return fecha_entrada;
    }

    public LocalDate getFecha_Salida() {
        return fecha_salida.get();
    }
    public void setFecha_Salida(LocalDate fecha_salida) {
        this.fecha_salida.set(fecha_salida);}
    public ObjectProperty<LocalDate> fecha_salidaProperty() {
        return fecha_salida;
    }

    public int getNumHabitaciones() {return num_habitaciones.getValue();}
    public void setNum_Habitaciones(int num_habitaciones) {this.num_habitaciones.setValue(num_habitaciones);}
    public IntegerProperty numHabitacionesProperty() {
        return num_habitaciones;
    }

    public String getTipoHabitacion() {return tipo_habitacion.getValue();}
    public void setTipoHabitacion(String tipo_habitacion) {this.tipo_habitacion.setValue(tipo_habitacion);}
    public StringProperty tipoHabitacionProperty(){
        return tipo_habitacion;
    }

    public boolean isFumador() {return fumador.get();}
    public void setFumador(boolean fumador) {this.fumador.set(fumador);}
    public BooleanProperty fumadorProperty() {
        return fumador;
    }

    public String getRegimen() {return regimen.getValue();}
    public void setRegimen(String regimen) {this.regimen.setValue(regimen);}
    public StringProperty RegimenProperty() {
        return regimen;
    }

    public String getId_Cliente() {return id_cliente.getValue();}
    public void setId_Cliente(String id_cliente) {this.id_cliente.setValue(id_cliente);}
    public StringProperty Id_ClienteProperty() {
        return id_cliente;
    }

    @Override
    public String toString() {
        return "Reserva { " +
                "Fecha Llegada =" + fecha_entrada +
                ", Fecha Salida =" + fecha_salida +
                ", Tipo Habitacion =" + tipo_habitacion +
                ", Fumador =" + fumador +
                ", Regimen =" + regimen +
                ", Número de Habitaciones =" + num_habitaciones.get() +
                ", Dni del cliente =" + id_cliente.get() +
                '}';
    }

}



