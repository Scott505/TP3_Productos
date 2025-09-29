package com.example.tp3_productos.ui.listar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tp3_productos.R;
import com.example.tp3_productos.ui.modelo.Producto;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolderProducto> {
    private List<Producto> listaProductos;
    private Context context;
    private LayoutInflater li;

    public ProductoAdapter(List<Producto> listaProductos, Context context, LayoutInflater li) {
        this.context=context;
        this.listaProductos = listaProductos;
        this.context = context;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderProducto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=li.inflate(R.layout.item,parent,false);
        return new ViewHolderProducto(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProducto holder, int position) {
        Producto producto = listaProductos.get(position);
        holder.tvId.setText("ID: " + producto.getCodigo());
        holder.tvDescripcion.setText("Descripción: " + producto.getDescripcion());
        holder.tvPrecio.setText("Precio: " + producto.getPrecio());
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ViewHolderProducto extends RecyclerView.ViewHolder {
        TextView tvId, tvDescripcion, tvPrecio;
        public ViewHolderProducto(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}
