package com.example.tp3_productos.ui.eliminar;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tp3_productos.R;
import com.example.tp3_productos.databinding.FragmentEliminarBinding;

public class EliminarFragment extends Fragment {

    private EliminarViewModel eliminarViewModel;
    private FragmentEliminarBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        eliminarViewModel = new ViewModelProvider(this).get(EliminarViewModel.class);
        binding = FragmentEliminarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = binding.etEliminar.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("codigo", codigo);
                Navigation.findNavController(getActivity(),R.id.nav_host_fragment_content_main).navigate(R.id.confirmacionFragment, bundle);
            }
        });
        return root;
        }


    }