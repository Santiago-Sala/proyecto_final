package com.example.Login.Clases;

import com.example.Login.Enums.Rol;
import com.example.Login.Exceptions.UsuarioRepetidoException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.TreeSet;

public class LoginManager {

    private TreeSet<Usuario> listaUsuarios;

    public LoginManager(){

        this.listaUsuarios = new TreeSet<>();
    }


    public String agregarUsuario(Usuario usuario){

        for(Usuario u: listaUsuarios){

            if(u.getUsername().equals(usuario.getUsername())){
                throw new UsuarioRepetidoException("Usuario repetido");
            }
        }
        listaUsuarios.add(usuario);

        return "Usuario agregado exitósamente";
    }


    public String modificarUsuario(Usuario usuario, String newPassword, Rol newRol, String nombreApellidoNuevo, char sexo){

        if(!listaUsuarios.contains(usuario)){
            throw new NoSuchElementException("Usuario no encontrado");
        }

        usuario.setPasswordHash(newPassword);
        usuario.setTipoIngreso(newRol);
        usuario.setNombreApellido(nombreApellidoNuevo);
        usuario.setSexo(sexo);

        return "Usuario modificado exitosamente";
    }


    public String eliminarUsuario(Usuario usuario){

        if(!listaUsuarios.contains(usuario)){
            throw new NoSuchElementException("Usuario no encontrado");
        }
        listaUsuarios.remove(usuario);

        return "Usuario eliminado exitosamente";
    }

    public JSONArray convertirAJson(){

        JSONArray jsonArray = new JSONArray();

        for(Usuario usuario: listaUsuarios){

            JSONObject json = new JSONObject();

            json.put("username", usuario.getUsername());
            json.put("password", usuario.getPasswordHash());
            json.put("tipoIngreso", usuario.getTipoIngreso());
            json.put("nombreApellido", usuario.getNombreApellido());
            json.put("sexo", usuario.getSexo());
            jsonArray.put(json);

        }

        return jsonArray;
    }

    public boolean aniadirArchivo(JSONArray jsonArray) {

        if (jsonArray.isEmpty()) {
            System.out.println("El arreglo está vacio");
            return false;
        }

        try {
            FileWriter file = new FileWriter("arregloUsuarios.json");
            file.write(jsonArray.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo");
        } catch (JSONException e) {
            System.out.println("Error al escribir el archivo");
        }
        
        return true;
    }


    public TreeSet<Usuario> getListaUsuarios() {return listaUsuarios;}

    
}
