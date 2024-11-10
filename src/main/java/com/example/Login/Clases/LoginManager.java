package com.example.Login.Clases;

import java.util.TreeSet;

public class LoginManager {

    private TreeSet<Usuario> listaUsuarios;

    public LoginManager(){
        this.listaUsuarios = new TreeSet<>();
    }

    public TreeSet<Usuario> getListaUsuarios() {return listaUsuarios;}

    
}
