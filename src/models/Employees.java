package models;

import java.util.Date;
import models.Sesiones;

public class Employees {
    private String idEmpleados;
    private String nombreEmpleado;
    private String rol;
    private Date fechaContratacion;
    private float salario;
    private String telefono;
    private String direccion;
    private String email;
    private String usuario;
    private int edad;
    
    // Relación con la clase Sesiones
    Sesiones sesion = new Sesiones();
    
    
    public Employees() {
    }

    public Employees(String idEmpleados, String nombreEmpleado, String rol, Date fechaContratacion, float salario, String telefono, String direccion, String email, String usuario, int edad) {
        this.idEmpleados = idEmpleados;
        this.nombreEmpleado = nombreEmpleado;
        this.rol = rol;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
        this.usuario = usuario;
        this.edad = edad;
    }

    public String getIdEmpleados() {
        return idEmpleados;
    }

    public void setIdEmpleados(String idEmpleados) {
        this.idEmpleados = idEmpleados;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    // Constructor y otros métodos

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    
    
    
    // Getters y Setters de los campos anteriores

    public Sesiones getSesion() {
        return sesion;
    }

    public void setSesion(Sesiones sesion) {
        this.sesion = sesion;
    }
    
    
    
}   

