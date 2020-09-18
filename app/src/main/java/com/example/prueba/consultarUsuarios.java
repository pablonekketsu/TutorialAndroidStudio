package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prueba.utilidades.Utilidades;

public class consultarUsuarios extends AppCompatActivity {
    EditText id, nombre, telefono;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);
        conn = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);
        id = findViewById(R.id.id_usuarioC);
        nombre = findViewById(R.id.nombre_usuarioC);
        telefono = findViewById(R.id.telefono_usuarioC);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buscarUsuario:
                buscarUsuario();
                break;
            case R.id.actualizarUsuario:
                actualizarUsuario();
                break;
            case R.id.eliminarUsuario:
                eliminarUsuario();
                break;
        }
    }

    public void buscarUsuario() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] params = {id.getText().toString()};
        String[] camposV = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};
        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, camposV, Utilidades.CAMPO_ID + "=?", params, null, null, null);
            cursor.moveToFirst();
            nombre.setText(cursor.getString(0));
            telefono.setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_LONG).show();
            limpiar(false);
        }
        db.close();
    }

    public void actualizarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] params = {id.getText().toString()};
        ContentValues conval = new ContentValues();
        conval.put(Utilidades.CAMPO_NOMBRE, nombre.getText().toString());
        conval.put(Utilidades.CAMPO_TELEFONO, telefono.getText().toString());
        db.update(Utilidades.TABLA_USUARIO, conval, Utilidades.CAMPO_ID + "=?", params);
        Toast.makeText(getApplicationContext(), "Usuario actualizado", Toast.LENGTH_LONG).show();
        db.close();
    }

    private void limpiar(boolean idLimpiar) {
        if (idLimpiar) {
            id.setText("");
        }
        nombre.setText("");
        telefono.setText("");
    }

    public void eliminarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] params = {id.getText().toString()};
        db.delete(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID + "=?", params);
        Toast.makeText(getApplicationContext(), "Usuario Eliminado", Toast.LENGTH_LONG).show();

        limpiar(true);
        db.close();

    }
}