package com.example.Login.Clases;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import com.example.Login.Enums.Rol;

public class Usuario implements Comparable<Usuario>{

    //Atributos
    private String username;
    private String passwordHash;
    private Rol tipoIngreso;
    private String nombreApellido;
    private int dni;
    private char sexo;

    //Constructor con parámetros

    public Usuario(String username, String passwordHash, Rol tipoIngreso, String nombreApellido, int dni, char sexo) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.tipoIngreso = tipoIngreso;
        this.nombreApellido = nombreApellido;
        this.dni = dni;
        this.sexo = sexo;
    }


    //Constructor sin parametros
    public Usuario() {
    }

    //Metodo para verificar la contraseña ingresada.
    public boolean verificarPassword(String passwordIngresada){

        hashPassword(passwordIngresada);
        return(passwordIngresada.equals(passwordHash));
    }


    // Metodo para hashear la contraseña. Aquí se convertirá la contraseña en un arreglo de Strings Hasheado.
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

    //GETTERS
    public String getUsername() {return username;}
    public String getPasswordHash() {return passwordHash;}
    public Rol getTipoIngreso() {return tipoIngreso;}
    public String getNombreApellido() {return nombreApellido;}
    public int getDni() {return dni;}
    public char getSexo() {return sexo;}

    //SETTERS
    //Los setters no van a tener Username ya que eso no se prodá modificar.
    public void setPasswordHash(String password) {this.passwordHash = hashPassword(password);}
    public void setTipoIngreso(Rol tipoIngreso) {tipoIngreso = tipoIngreso;}
    public void setNombreApellido(String nombreApellido) {this.nombreApellido = nombreApellido;}
    public void setSexo(char sexo) {this.sexo = sexo;}


    // Metodo equals para username y tipoIngreso
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return dni == usuario.dni && sexo == usuario.sexo && Objects.equals(username, usuario.username) && Objects.equals(passwordHash, usuario.passwordHash) && tipoIngreso == usuario.tipoIngreso && Objects.equals(nombreApellido, usuario.nombreApellido);
    }


    // Metodo hashCode para username y tipoIngreso
    @Override
    public int hashCode() {
        return Objects.hash(username, passwordHash, tipoIngreso, nombreApellido, dni, sexo);
    }

    
    public int compareTo(Usuario o) {
        return this.username.compareTo(o.username);
    };
}
