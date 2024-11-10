package com.example.Login.Clases;

import com.example.Login.Enums.Rol;
import com.example.Login.Exceptions.UsuarioRepetidoException;

import java.util.TreeSet;

public class LoginManager {

    private TreeSet<Usuario> listaUsuarios;

    public LoginManager(){

        this.listaUsuarios = new TreeSet<>();
    }

    public String agregarUsuario(Usuario usuario){

        if(listaUsuarios.contains(usuario)){
            throw new UsuarioRepetidoException("Usuario repetido");
        }
        listaUsuarios.add(usuario);

        return "Usuario agregado exitósamente";
    }

    public String modificarUsuario(String userName,String password, Rol rol){

        if(!listaUsuarios.contains(userName)){}
    }
    public TreeSet<Usuario> getListaUsuarios() {return listaUsuarios;}

    
}
