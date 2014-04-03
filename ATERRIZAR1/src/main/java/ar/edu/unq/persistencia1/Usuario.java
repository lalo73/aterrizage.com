package ar.edu.unq.persistencia1;

import java.util.Date;

public class Usuario {
    String nombre, apellido, nombreDeUsuario, email, password;
    Date birthday;

    public Usuario(String nombre, String apellido, String nombreDeUsuario, String email, Date birthday,String pass) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreDeUsuario = nombreDeUsuario;
        this.email = email;
        this.birthday = birthday;
        this.password= pass;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthday() {
        return birthday;
    }
}
