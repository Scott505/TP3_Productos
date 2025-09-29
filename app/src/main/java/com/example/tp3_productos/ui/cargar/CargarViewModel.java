package com.example.tp3_productos.ui.cargar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp3_productos.MainActivity;
import com.example.tp3_productos.databinding.FragmentCargarBinding;
import com.example.tp3_productos.ui.modelo.Producto;

public class CargarViewModel extends AndroidViewModel {
    private MutableLiveData<String> mensaje;
    private FragmentCargarBinding binding;

    public CargarViewModel(@NonNull Application application) {
        super(application);
    }


    public LiveData<String> getMensaje() {
        if (mensaje == null) {
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }

    public void cargarProducto(String codigo, String descripcion, String precio) {
        boolean valido = validarCampos(codigo, descripcion, precio);
        if (valido) {
            int codigoInt = Integer.parseInt(codigo);
            double precioDb = Double.parseDouble(precio);
            MainActivity.listaProductos.add(new Producto(codigoInt, descripcion, precioDb));
            mensaje.setValue("Producto agregado");
        }
    }

    private boolean validarCampos(String codigo, String descripcion, String precio) {
        boolean duplicado = false;
        boolean valido = true;

        if (codigo.isBlank() || descripcion.isBlank() || precio.isBlank()) {
            mensaje.setValue("Complete todos los campos");
            valido = false;
            return valido;
        }

        try{
            int codigoInt = Integer.parseInt(codigo);
            double precioDb = Double.parseDouble(precio);
            duplicado = MainActivity.listaProductos.contains(new Producto(codigoInt, descripcion, precioDb));
        } catch (NumberFormatException e){
            mensaje.setValue("Código o precio inválidos");
            valido = false;
            return valido;
        }

        if (duplicado){
            mensaje.setValue("El código ya existe");
            valido = false;
        }
        return valido;
    }


}
