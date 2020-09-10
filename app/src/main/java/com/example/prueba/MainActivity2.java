package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity2 extends AppCompatActivity {
    CheckBox OpcionUno, OpcionDos, OpcionTres;
    RadioButton RadioUno, RadioDos, RadioTres;
    ToggleButton Btn;
    TextView Estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        OpcionUno = (CheckBox) findViewById(R.id.Opcion1);
        OpcionDos = (CheckBox) findViewById(R.id.Opcion2);
        OpcionTres = (CheckBox) findViewById(R.id.Opcion3);

        RadioUno = (RadioButton) findViewById(R.id.Radio1);
        RadioDos = (RadioButton) findViewById(R.id.Radio2);
        RadioTres = (RadioButton) findViewById(R.id.Radio3);

        Btn= findViewById(R.id.BotonToggle);
        Estado=findViewById(R.id.TextoToggle);
    }

    private String validar() {
        String texto = "Selecionado: \n";
        if (OpcionUno.isChecked()) {
            texto += "Opción 1 \n";
        }
        if (OpcionDos.isChecked()) {
            texto += "Opción 2 \n";
        }
        if (OpcionTres.isChecked()) {
            texto += "Opción 3 \n";
        }
        if (RadioUno.isChecked()) {
            texto += "Radio 1 \n";
        }
        if (RadioDos.isChecked()) {
            texto += "Radio 2 \n";
        }
        if (RadioTres.isChecked()) {
            texto += "Radio 3 \n";
        }
        return texto;
    }

    public void comprobar(View view) {
        Toast.makeText(getApplicationContext(), validar(), Toast.LENGTH_LONG).show();
    }

    public void ComprobarActivacion(View view) {
        if (view.getId() == R.id.BotonToggle) {
            if (Btn.isChecked()) {
                Estado.setText("Botón On");
            } else {
                Estado.setText("Botón Off");
            }
        }
    }
}