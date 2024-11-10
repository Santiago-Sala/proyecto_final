package com.example.Login.Clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import com.example.Login.Enums.Rol;

public class Usuario implements Comparable<Usuario>{

    //Atributos
    private Sting username;
    private String passwordHash;
    private Rol tipoIngreso;

    //Constructor con parámetros
    public Usuario(String username, String password, Rol tipoIngreso){
        this.username = username;
        this.passwordHash = hashPassword(password);
        this.TipoIngreso = tipoIngreso;
    }

    //Constructor sin parametros
    public Ususario(){
        this.username = username;
        this.passwordHash = hashPassword(passwordHash);
        this.TipoIngreso = tipoIngreso;
    }

    //Metodo para verificar la contraseña ingresada.
    public boolean verificarPassword(String passwordIngresada){
        return(passwordIngresada.equals(hashPassword));
    }

    //GETTERS
    public Sting getUsername() {return username;}
    public String passwordHash() {return password;}
    public Rol getTipoIngreso() {return TipoIngreso;}

    //SETTERS
    public void setUsername(Sting username) {this.username = username;}
    public void setPasswordHash(String password) {this.password = password;}
    public void setTipoIngreso(Rol tipoIngreso) {TipoIngreso = tipoIngreso;}

    // Método para hashear la contraseña. Aquí se convertirá la contraseña en un arreglo de Strings Hasheado.
    //Para que cuando se abra el archivo json de texto, no se vea la contraseña real.
        private String hashPassword(String password) {
        try {
            // Crea una instancia de MessageDigest con el algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

             // Convierte la contraseña en un arreglo de bytes y calcula el hash
            byte[] hash = digest.digest(password.getBytes());

            // Convierte el arreglo de bytes a un formato hexadecimal
            StringBuilder hexString = new StringBuilder();

                //Convierte el hash en una representación hexadecimal convertida a cadena de texto
                //para que sea más facil guardarla en archivos de texto.
               for (byte b : hash) {
                //Se convierte a String
                String hex = Integer.toHexString(0xff & b);

                //Se le agrega un 0 si el valor hexadecimal tiene un solo carácter para su correcta representación.
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }

    // Método equals para username y tipoIngreso
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(username, usuario.username) &&
               tipoIngreso == usuario.tipoIngreso;
    }

    // Método hashCode para username y tipoIngreso
    @Override
    public int hashCode() {
        return Objects.hash(username, tipoIngreso);
    
    }
    
    public int compareTo(Usuario o) {
        return this.username.compareTo(o.username);
    };
}
