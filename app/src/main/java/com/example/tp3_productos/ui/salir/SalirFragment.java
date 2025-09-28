package com.example.tp3_productos.ui.salir;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp3_productos.R;
import com.example.tp3_productos.databinding.FragmentSalirBinding;

import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;

public class SalirFragment extends Fragment {

    private FragmentSalirBinding binding;
    private SalirViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSalirBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(SalirViewModel.class);
        viewModel.cancelarSalir();

        viewModel.getSalir().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean salir) {
                if (salir != null && salir) {
                    requireActivity().finishAffinity(); // cerrar app
                }
            }
        });

        mostrarDialogo();

        return root;
    }

    private void mostrarDialogo() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Salir")
                .setMessage("¿Seguro que quieres cerrar la aplicación?")
                .setPositiveButton("Sí", (dialog, which) -> viewModel.confirmarSalir())
                .setNegativeButton("Cancelar", (dialog, which) ->{
                        viewModel.cancelarSalir();
                        Navigation.findNavController(requireView()).popBackStack();
                })
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
