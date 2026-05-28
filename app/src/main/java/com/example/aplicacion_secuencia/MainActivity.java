package com.example.aplicacion_secuencia;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottomNavigation);

        loadFragment(new ProductosFragment());

        bottomNavigation.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.nav_productos) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new ProductosFragment())
                        .commit();
                return true;
            }

            if (item.getItemId() == R.id.nav_ventas) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new VentasFragment())
                        .commit();
                return true;
            }

            if (item.getItemId() == R.id.nav_config) {
                Intent intent = new Intent(this, ConfigActivity.class);
                startActivity(intent);
                return true;
            }

            return false;
        });
    }

    private boolean loadFragment(Fragment fragment){
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
