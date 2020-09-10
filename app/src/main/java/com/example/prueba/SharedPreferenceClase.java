package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferenceClase extends AppCompatActivity {
    EditText nombre, contraseña;
    TextView campo1,campo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference_clase);
        nombre=(EditText)findViewById(R.id.nombrePersona);
        contraseña= (EditText)findViewById(R.id.PasswordPersona);
        campo1=findViewById(R.id.impresion1);
        campo2=findViewById(R.id.impresion2);
    }

    public void GuardarDatos(View view) {
        guardarPreferencias();
        cargarPreferencias();
    }

    private void cargarPreferencias() {
        SharedPreferences preferences=getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String nombre= preferences.getString("user","No existe la info");
        String contraseña= preferences.getString("password","No existe la info");
        campo1.setText(nombre);
        campo2.setText(contraseña);
    }

    private void guardarPreferencias() {
        SharedPreferences preferences=getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String nombreU=nombre.getText().toString();
        String contraseñaU= contraseña.getText().toString();
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("user",nombreU);
        editor.putString("password",contraseñaU);
        editor.commit();

    }
}