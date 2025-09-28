package com.example.tp3_productos.ui.cargar;

import androidx.lifecycle.ViewModel;

import com.example.tp3_productos.MainActivity;
import com.example.tp3_productos.ui.modelo.Producto;

public class CargarViewModel extends ViewModel {

    public boolean cargarProducto(Producto producto) {
        for (Producto p : MainActivity.listaProductos) {
            if (p.getCodigo() == producto.getCodigo()) {
                return false;
            }
        }
            MainActivity.listaProductos.add(producto);
    return true;
    }

}
