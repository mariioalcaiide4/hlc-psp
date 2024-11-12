package com.example.practicahotel.modelo;

public class ClienteVO {

    String dni;
    String nombre;
    String apellido;
    String direccion;
    String localidad;
    String provincia;

    //Constructor por defecto

    public ClienteVO(String dni, String nombre, String apellido, String direccion, String localidad, String province) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.localidad = localidad;
        this.provincia = province;
    }


    //Getters y setters

    public String getDni() {return dni;}
    public void setDni(String dni) {this.dni = dni;}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getApellido() {return apellido;}
    public void setApellido(String apellido) {this.apellido = apellido;}

    public String getDireccion() {return direccion;}
    public void setDireccion(String direccion) {this.direccion = direccion;}

    public String getLocalidad() {return localidad;}
    public void setLocalidad(String localidad) {this.localidad = localidad;}

    public String getProvincia() {return provincia;}
    public void setProvincia(String province) {this.provincia = province;}

    public String toString() {
        return "Cliente{Nombre Completo = " + this.nombre  + " " + this.apellido + ", DNI = " + this.dni + ", Direccion = " + this.direccion + ", Localidad = " + this.localidad + ", Provincia = " + '}';
    }

}
