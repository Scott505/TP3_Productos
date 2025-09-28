package com.example.tp3_productos.ui.salir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SalirViewModel extends ViewModel {

    private MutableLiveData<Boolean> salir;

    public SalirViewModel() {
        salir = new MutableLiveData<>(false);
    }

    public LiveData<Boolean> getSalir() {
        if (salir == null) {
            salir = new MutableLiveData<>(false);
        }
        return salir;
    }

    public void confirmarSalir() {
        salir.setValue(true);
    }

    public void cancelarSalir() {
        salir.setValue(false);
    }
}

