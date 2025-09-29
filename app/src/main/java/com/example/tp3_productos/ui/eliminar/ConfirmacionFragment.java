package com.example.tp3_productos.ui.eliminar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tp3_productos.R;
import com.example.tp3_productos.databinding.FragmentConfirmacionBinding;
import com.example.tp3_productos.databinding.FragmentEliminarBinding;
import com.example.tp3_productos.ui.modelo.Producto;

public class ConfirmacionFragment extends Fragment {

    private ConfirmacionViewModel confirmacionViewModel;
    private FragmentConfirmacionBinding binding;

    public static ConfirmacionFragment newInstance() {
        return new ConfirmacionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        confirmacionViewModel = new ViewModelProvider(this).get(ConfirmacionViewModel.class);
        binding = FragmentConfirmacionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        confirmacionViewModel.getMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                binding.tvConfirmacionId.setText("");
                binding.tvConfirmacionDescripcion.setText("");
                binding.tvConfirmacionPrecio.setText("");
                binding.tvConfirmacion.setText(s);
                binding.btnConfirmar.setEnabled(false);
            }
        });

        confirmacionViewModel.getProducto().observe(getViewLifecycleOwner(), new Observer<Producto>() {
            @Override
            public void onChanged(Producto producto) {
                binding.tvConfirmacionId.setText("Código: " + producto.getCodigo());
                binding.tvConfirmacionDescripcion.setText("Descripción: " + producto.getDescripcion());
                binding.tvConfirmacionPrecio.setText("Precio: " + producto.getPrecio());
                binding.tvConfirmacion.setText("¿Seguro que quiere eliminar el producto?");
                binding.btnConfirmar.setEnabled(true);
            }
        });



        Bundle bundle = getArguments();
        String codigo = bundle.getString("codigo");
        confirmacionViewModel.buscarCodigo(codigo);

        binding.btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmacionViewModel.eliminarProducto();
            }
        });


        return root;
    }

}