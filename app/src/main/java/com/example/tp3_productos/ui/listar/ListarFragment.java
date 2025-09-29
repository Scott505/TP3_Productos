package com.example.tp3_productos.ui.listar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.tp3_productos.databinding.FragmentListarBinding;
import com.example.tp3_productos.ui.modelo.Producto;

import java.util.List;

public class ListarFragment extends Fragment {

    private FragmentListarBinding binding;
    private Producto producto;
    private ListarViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentListarBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ListarViewModel.class);
        View root = binding.getRoot();

       viewModel.getListaProductos().observe(getViewLifecycleOwner(), new Observer<List<Producto>>() {
           @Override
           public void onChanged(List<Producto> productos) {
               ProductoAdapter adapter = new ProductoAdapter(productos, getContext(), getLayoutInflater());
               GridLayoutManager glm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
               binding.rvLista.setLayoutManager(glm);
               binding.rvLista.setAdapter(adapter);
           }
       });
        viewModel.cargarLista();
        return root;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}