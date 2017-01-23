package com.example.slafuente.imc;

/**
 * Created by slafuente on 22/01/2017.
 */

/**
 * Clase Usuario con con email y password
 */
public class Usuario {

    private String email;
    private String password;

    public Usuario() {
    }

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
   }

}
