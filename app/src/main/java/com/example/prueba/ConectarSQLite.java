package com.example.prueba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prueba.utilidades.Utilidades;

public class ConectarSQLite extends AppCompatActivity {
    EditText id,nombre,telefono;
    Button btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conectar_s_q_lite);

        id= findViewById(R.id.id_usuario);
        nombre=findViewById(R.id.nombre_usuario);
        telefono=findViewById(R.id.telefono_usuario);
       // btn_guardar=findViewById(R.id.buttonGuardar);
//        ConexionSQLiteHelper conector= new ConexionSQLiteHelper(getApplicationContext(),"db_usuario",null,1);
    }

    public void GuardarDatos(View view) {
       // registrarUsuarios();
        registrarUsuariosSQL();

    }

    private void registrarUsuariosSQL() {
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);

        //Abrir base de datos para poder editarla

        SQLiteDatabase db = conexion.getWritableDatabase();
        //insert into usuario (id,nombre,telefono) values(1234,pablito alimaña,3122175449)
        String insert="INSERT INTO "+Utilidades.TABLA_USUARIO
                +" ( "+
                Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO
                +" )"+
                "VALUES"
                +" ( "+
                id.getText().toString()+",'"+nombre.getText().toString()+"', '"+telefono.getText().toString()
                +"' )" ;
                db.execSQL(insert);
                db.close();
    }

    private void registrarUsuarios() {
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(getApplicationContext(), "bd_usuarios", null, 1);

        //Abrir base de datos para poder editarla
        SQLiteDatabase db = conexion.getWritableDatabase();

        //Android nos da la forma de realizar el registro por medio de ContentValues
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, id.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, nombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, telefono.getText().toString());

        //Ahora insertar todos los valores a la DB.
        //Vamos  a usar el metodo insert de nuestro SQLiteDatabase el cual nos
        // retorna un long dependiendo de los datos que le enviemos.
       // Toast.makeText(getApplicationContext(), "Id Registro "+ id.getText().toString(), Toast.LENGTH_LONG).show();
        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);

        Toast.makeText(getApplicationContext(), "Id Registro "+ idResultante, Toast.LENGTH_LONG).show();
        db.close();
    }
}