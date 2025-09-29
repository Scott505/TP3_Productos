package com.example.tp3_productos.ui.eliminar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tp3_productos.MainActivity;
import com.example.tp3_productos.ui.modelo.Producto;

public class ConfirmacionViewModel extends AndroidViewModel {

    private MutableLiveData<String> mensaje;
    private MutableLiveData<Producto> mutableProducto;
    private String codigoActual;

    public ConfirmacionViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Producto> getProducto() {
        if (mutableProducto == null) {
            mutableProducto = new MutableLiveData<>();
        }
        return mutableProducto;
    }
    public LiveData<String> getMensaje() {
        if (mensaje == null) {
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }


    public void buscarCodigo(String codigo) {
        boolean encontrado = validarDatos(codigo);
        if (encontrado) {
            int codigoInt = Integer.parseInt(codigo);
            for (Producto producto : MainActivity.listaProductos) {
                if (producto.getCodigo() == codigoInt) {
                    mutableProducto.setValue(producto);
                    this.codigoActual = codigo;
                    break;
                }
            };
        }
    }


    public boolean validarDatos(String codigo) {
        boolean encontrado = false;
        boolean valido = true;

        if (codigo.isBlank()) {
            mensaje.setValue("Complete todos los campos");
            valido = false;
            return valido;
        }
        try{
            int codigoInt = Integer.parseInt(codigo);
            encontrado = MainActivity.listaProductos.contains(new Producto(codigoInt));
        } catch (NumberFormatException e){
            mensaje.setValue("Código o precio inválidos");
            valido = false;
            return valido;
        }
        if (!encontrado){
            mensaje.setValue("No existe el producto");
            valido = false;
        }
        return valido;

    }

    public void eliminarProducto() {
        int codigoInt = Integer.parseInt(codigoActual);
        MainActivity.listaProductos.remove(new Producto(codigoInt));
        mensaje.setValue("Producto eliminado correctamente");
    }
}