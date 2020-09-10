package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PruebaScollView extends AppCompatActivity {
    EditText usuario,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_scoll_view);
       usuario=(EditText)findViewById(R.id.usuario);
       password=(EditText)findViewById(R.id.password);

    }

    public void mostrar(View view) {
        String usu= usuario.getText().toString();//Usu para usuario
        String pass= password.getText().toString();
        Toast.makeText(getApplicationContext(), "Felicidades "+usu+" has logueado con exito.",Toast.LENGTH_LONG).show();
    }
}