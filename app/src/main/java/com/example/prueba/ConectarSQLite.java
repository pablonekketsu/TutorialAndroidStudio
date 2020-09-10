package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ConectarSQLite extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conectar_s_q_lite);
        ConexionSQLiteHelper conector= new ConexionSQLiteHelper(getApplicationContext(),"db.usuario",null,1);
    }

    public void GuardarDatos(View view) {

    }
}