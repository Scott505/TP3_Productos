package com.example.tp3_productos.ui.modelo;

import androidx.annotation.Nullable;

public class Producto {
    private int codigo;
    private String descripcion;
    private double precio;

    public Producto(int codigo, String descripcion, double precio) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Producto)){
            return false;
        }
        Producto p = (Producto) obj;
        if (this.codigo == p.getCodigo()){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return codigo;
    }
}
