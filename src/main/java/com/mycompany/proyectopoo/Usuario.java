
package com.mycompany.proyectopoo;

public abstract class Usuario {
    private String id;
    private String nombre;
    private String user;
    private String password;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String user, String password) {
        this.id = id;
        this.nombre = nombre;
        this.user = user;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    public abstract String mostrarMenuOpciones();
            
}
