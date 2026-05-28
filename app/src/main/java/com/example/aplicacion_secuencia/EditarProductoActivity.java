package com.example.aplicacion_secuencia;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditarProductoActivity extends AppCompatActivity {

    EditText etNombre, etPrecio;
    Button btnActualizar;

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);

        etNombre = findViewById(R.id.etNombre);
        etPrecio = findViewById(R.id.etPrecio);
        btnActualizar = findViewById(R.id.btnActualizar);


        position = getIntent().getIntExtra("position", -1);
        String nombre = getIntent().getStringExtra("nombre");
        double precio = getIntent().getDoubleExtra("precio", 0);

        // 👉 MOSTRAR DATOS
        etNombre.setText(nombre);
        etPrecio.setText(String.valueOf(precio));

        // 👉 ACTUALIZAR
        btnActualizar.setOnClickListener(v -> {

            String nuevoNombre = etNombre.getText().toString();
            double nuevoPrecio = Double.parseDouble(etPrecio.getText().toString());

            Producto actualizado = new Producto(nuevoNombre, nuevoPrecio);

            DataRepository.actualizarProducto(position, actualizado);

            finish(); // regresar
        });
    }
}