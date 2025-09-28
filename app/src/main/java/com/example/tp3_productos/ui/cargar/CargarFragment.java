package com.example.tp3_productos.ui.cargar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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
        CargarViewModel cargarViewModel = new ViewModelProvider(this).get(CargarViewModel.class);

        binding = FragmentCargarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = binding.codigo.getText().toString();
                String descripcion = binding.descripcion.getText().toString();
                String precio = binding.precio.getText().toString();

                if (codigo.isEmpty() || descripcion.isEmpty() || precio.isEmpty()) {
                    Toast.makeText(getContext(), "Complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                int codigoInt;
                double precioDb;

                try {
                    codigoInt = Integer.parseInt(codigo);
                    precioDb = Double.parseDouble(precio);
                } catch (NumberFormatException e) {
                    Toast.makeText(requireContext(), "Código o precio inválidos", Toast.LENGTH_SHORT).show();
                    return;
                }

                Producto nuevo = new Producto(codigoInt, descripcion, precioDb);

                boolean agregado = cargarViewModel.cargarProducto(nuevo);

                if (agregado) {
                    Toast.makeText(requireContext(), "Producto agregado", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(v).navigate(R.id.nav_cargar); // o a la lista
                } else {
                    Toast.makeText(requireContext(), "El código ya existe", Toast.LENGTH_SHORT).show();
                }

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