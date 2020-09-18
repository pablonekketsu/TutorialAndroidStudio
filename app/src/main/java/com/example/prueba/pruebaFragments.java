package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.SurfaceControl;
import android.view.View;
import android.widget.Button;

import com.example.prueba.fragments.actualizarUsuarioFragment;
import com.example.prueba.fragments.registroUsuarioFragment;

public class pruebaFragments extends AppCompatActivity {
    Button btnRegistro, btnActualizar;
    Fragment fragmentR, fragmentA;
    FragmentTransaction cambiarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_fragments);
        btnRegistro = findViewById(R.id.registroFragment);
        btnActualizar = findViewById(R.id.actualizarFragment);
        fragmentR = new registroUsuarioFragment();
        fragmentA = new actualizarUsuarioFragment();
        //cambiarFragment = getSupportFragmentManager().beginTransaction();
        getSupportFragmentManager().beginTransaction().add(R.id.frameFramentLayout, fragmentR).commit();
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarFragment = getSupportFragmentManager().beginTransaction();
                cambiarFragment.replace(R.id.frameFramentLayout, fragmentA);
                cambiarFragment.addToBackStack(null);
                cambiarFragment.commit();
            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cambiarFragment = getSupportFragmentManager().beginTransaction();
                cambiarFragment.replace(R.id.frameFramentLayout, fragmentR);
                cambiarFragment.addToBackStack(null);
                cambiarFragment.commit();
            }
        });
    }


}