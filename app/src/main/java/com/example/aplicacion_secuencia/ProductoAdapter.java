package com.example.aplicacion_secuencia;

// ✅ IMPORTS
import android.content.Intent;
import android.widget.ImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ViewHolder> {

    private List<Producto> listaProductos;

    public ProductoAdapter(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_producto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Producto producto = listaProductos.get(position);

        holder.tvNombre.setText(producto.getNombre());
        holder.tvPrecio.setText("Precio: $" + producto.getPrecio());
        holder.tvCantidad.setText("Cantidad: 10");

        // ✅ BOTÓN EDITAR
        holder.btnEditar.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();

            if (pos != RecyclerView.NO_POSITION) {

                Producto productoSeleccionado = listaProductos.get(pos);

                Intent intent = new Intent(v.getContext(), EditarProductoActivity.class);
                intent.putExtra("position", pos);
                intent.putExtra("nombre", productoSeleccionado.getNombre());
                intent.putExtra("precio", productoSeleccionado.getPrecio());

                v.getContext().startActivity(intent);
            }
        });

        // ✅ BOTÓN ELIMINAR (ACTUALIZADO)
        holder.btnEliminar.setOnClickListener(v -> {
            int pos = holder.getAdapterPosition();

            if (pos != RecyclerView.NO_POSITION) {

                DataRepository.eliminarProducto(pos);

                // 🔥 MEJOR ACTUALIZACIÓN
                notifyDataSetChanged();

                Toast.makeText(v.getContext(), "Producto eliminado", Toast.LENGTH_SHORT).show();
            }
        });

        // ❌ ELIMINADO: setOnLongClickListener
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNombre, tvPrecio, tvCantidad;
        ImageView btnEditar, btnEliminar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
            tvCantidad = itemView.findViewById(R.id.tvCantidad);

            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}