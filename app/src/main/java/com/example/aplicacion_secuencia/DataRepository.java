package com.example.aplicacion_secuencia;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    public static List<Producto> listaProductos = new ArrayList<>();

    public static void agregarProducto(Producto p){
        listaProductos.add(p);
    }

    public static List<Producto> obtenerProductos(){
        return listaProductos;
    }

    public static void eliminarProducto(int position){
        listaProductos.remove(position);
    }

    public static void actualizarProducto(int position, Producto nuevo){
        listaProductos.set(position, nuevo);
    }
}