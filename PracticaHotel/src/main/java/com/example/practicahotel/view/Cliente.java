package com.example.practicahotel.view;

import com.mysql.cj.conf.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cliente {

    //Atributos de la clase Cliente

    private final StringProperty dni;
    private final StringProperty nombre;
    private final StringProperty apellido;
    private final StringProperty direccion;
    private final StringProperty localidad;
    private final StringProperty provincia;

    //Constructor por defecto

    public Cliente(){
        this.dni = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.apellido = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.localidad = new SimpleStringProperty();
        this.provincia = new SimpleStringProperty();
    }

    //Constructor de la clase Cliente

    public Cliente(String dni, String nombre, String apellido, String direccion, String localidad, String provincia) {
        this.dni = new SimpleStringProperty(dni);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.direccion = new SimpleStringProperty(direccion);
        this.localidad = new SimpleStringProperty(localidad);
        this.provincia = new SimpleStringProperty(provincia);
    }

    public String getDni() {return dni.getValue();}
    public void setDni(String dni) {this.dni.setValue(dni);}
    public String getNombre() {return nombre.getValue();}
    public void setNombre(String nombre) {this.nombre.setValue(nombre);}
    public String getApellido() {return apellido.getValue();}
    public void setApellido(String apellido) {this.apellido.setValue(apellido);}
    public String getDireccion() {return direccion.getValue();}
    public void setDireccion(String direccion) {this.direccion.setValue(direccion);}
    public String getLocalidad() {return localidad.getValue();}
    public void setLocalidad(String localidad) {this.localidad.setValue(localidad);}
    public String getProvincia() {return provincia.getValue();}
    public void setProvincia(String provincia) {this.provincia.setValue(provincia);}




}
