package com.example.aplicacion_secuencia;

// ✅ IMPORTS CORRECTOS
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductosFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProductoAdapter adapter;
    private List<Producto> listaProductos;

    public ProductosFragment() {
        // Constructor vacío obligatorio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_productos_fragment, container, false);

        // RecyclerView
        recyclerView = view.findViewById(R.id.recyclerProductos);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 🔥 USAR DataRepository
        listaProductos = DataRepository.obtenerProductos();

        // 👉 Solo agrega datos si está vacío
        if (listaProductos.isEmpty()) {
            DataRepository.agregarProducto(new Producto("Laptop", 15000));
            DataRepository.agregarProducto(new Producto("Mouse", 300));
            DataRepository.agregarProducto(new Producto("Teclado", 600));
            DataRepository.agregarProducto(new Producto("Monitor", 4000));
        }

        // Adapter
        adapter = new ProductoAdapter(listaProductos);
        recyclerView.setAdapter(adapter);

        // 👉 BOTÓN AGREGAR
        Button btnAgregar = view.findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(v -> {
            startActivity(new Intent(getContext(), AgregarProductoActivity.class));
        });

        return view;
    }

    // 🔥 ESTE MÉTODO VA FUERA de onCreateView (YA BIEN COLOCADO)
    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
