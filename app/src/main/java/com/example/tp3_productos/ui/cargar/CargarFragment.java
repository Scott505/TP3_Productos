package com.example.tp3_productos.ui.cargar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.tp3_productos.MainActivity;
import com.example.tp3_productos.R;
import com.example.tp3_productos.databinding.FragmentCargarBinding;
import com.example.tp3_productos.ui.modelo.Producto;

public class CargarFragment extends Fragment {

    private FragmentCargarBinding binding;
    private CargarViewModel cargarViewModel;;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        cargarViewModel = new ViewModelProvider(this).get(CargarViewModel.class);

        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        cargarViewModel.getMensaje().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String mensaje) {
                Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
                binding.codigo.setText("");
                binding.descripcion.setText("");
                binding.precio.setText("");
            }
        });

        binding.btCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = binding.codigo.getText().toString();
                String descripcion = binding.descripcion.getText().toString();
                String precio = binding.precio.getText().toString();

                cargarViewModel.cargarProducto(codigo, descripcion, precio);

                Navigation.findNavController(v).navigate(R.id.nav_cargar);

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}