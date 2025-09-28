package com.example.tp3_productos.ui.listar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp3_productos.MainActivity;
import com.example.tp3_productos.ui.modelo.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListarViewModel extends ViewModel {

    private MutableLiveData<List<Producto>> listaProductos;

    public ListarViewModel() {
        listaProductos = new MutableLiveData<>(new ArrayList<>(MainActivity.listaProductos));
    }

    public LiveData<List<Producto>> getListaProductos() {
        if (listaProductos==null){
            listaProductos= new MutableLiveData<>();
        }
        return listaProductos;
    }

    public void cargarLista() {
        List<Producto> lista = new ArrayList<>(MainActivity.listaProductos);

        Collections.sort(lista, Comparator.comparing(Producto::getDescripcion, String.CASE_INSENSITIVE_ORDER));

        listaProductos.setValue(lista);
    }
}