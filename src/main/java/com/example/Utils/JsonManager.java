package com.example.Utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import org.json.JSONTokener;

public static class JsonManager extends Collections, Map{

    public static JSONArray listaToJson(ArrayList<Habitacion> listaHabitaciones) {
        JSONArray jsonArray = new JSONArray();
        for (Habitacion elemento : listaHabitaciones) {

            jsonArray.put(new JSONObject(elemento));
        }
        return jsonArray;
    }

    public static JSONArray setToJson() {
        JSONArray jsonArray = new JSONArray();
        for (T elemento : setDatos) {
            jsonArray.put(new JSONObject(elemento));
        }
        return jsonArray;
    }

    public static JSONObject mapToJson() {
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, T> entry : mapDatos.entrySet()) {
            jsonObject.put(entry.getKey(), new JSONObject(entry.getValue()));
        }
        return jsonObject;
    }

    public static void guardarListaEnArchivo(String nombreArchivo) {
        try (FileWriter file = new FileWriter(nombreArchivo)) {
            file.write(listaToJson().toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarSetEnArchivo(String nombreArchivo) {
        try (FileWriter file = new FileWriter(nombreArchivo)) {
            file.write(setToJson().toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void guardarMapEnArchivo(String nombreArchivo) {
        try (FileWriter file = new FileWriter(nombreArchivo)) {
            file.write(mapToJson().toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarListaDesdeArchivo(String nombreArchivo) {
        try (FileReader reader = new FileReader(nombreArchivo)) {
            JSONArray jsonArray = new JSONArray(new JSONTokener(reader));
            listaDatos.clear();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                T elemento = (T) new JSONObject(jsonObject.toString()); // Transformar a objeto T
                listaDatos.add(elemento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<T> obtenerListaDatos() {
        return listaDatos;
    }

    public Set<T> obtenerSetDatos() {
        return setDatos;
    }

    public Map<String, T> obtenerMapDatos() {
        return mapDatos;
    }

    public void modificarElementoLista(int indice, T nuevoElemento) {
        if (indice >= 0 && indice < listaDatos.size()) {
            listaDatos.set(indice, nuevoElemento);
        }
    }

    public void modificarElementoSet(T elementoExistente, T nuevoElemento) {
        if (setDatos.contains(elementoExistente)) {
            setDatos.remove(elementoExistente);
            setDatos.add(nuevoElemento);
        }
    }

    public void modificarElementoMap(String clave, T nuevoElemento) {
        if (mapDatos.containsKey(clave)) {
            mapDatos.put(clave, nuevoElemento);
        }
    }
}
