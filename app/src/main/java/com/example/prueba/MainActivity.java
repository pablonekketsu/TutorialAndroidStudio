package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void mensaje(View view){
        Toast.makeText(this,"Hola mundo",Toast.LENGTH_SHORT).show();
    }

    public void pasala(View view) {
        Intent intent= new Intent(this,MainActivity2.class);
        startActivity(intent);
    }

    public void ScrollView(View view) {
        Intent intent= new Intent(this,PruebaScollView.class);
        startActivity(intent);
    }

    public void IrShared(View view) {
        Intent intent= new Intent(this,SharedPreferenceClase.class);
        startActivity(intent);
    }

    public void conectarSQLite(View view) {
        Intent intent= new Intent(this,ConectarSQLite.class);
        startActivity(intent);
    }
}