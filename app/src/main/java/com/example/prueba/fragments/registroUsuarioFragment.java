package com.example.prueba.fragments;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prueba.ConexionSQLiteHelper;
import com.example.prueba.R;
import com.example.prueba.utilidades.Utilidades;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link registroUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class registroUsuarioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View vista;
    EditText idUsuario, nombreUsuario, telefonoUsuario;
    Button btnGuardar;


    public registroUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment pruebaFragmentsView.
     */
    // TODO: Rename and change types and number of parameters
    public static registroUsuarioFragment newInstance(String param1, String param2) {
        registroUsuarioFragment fragment = new registroUsuarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_registro_usuario_fragment_view, container, false);
        idUsuario = vista.findViewById(R.id.id_usuarioF);
        nombreUsuario = vista.findViewById(R.id.nombre_usuarioF);
        telefonoUsuario = vista.findViewById(R.id.telefono_usuarioF);
        btnGuardar = vista.findViewById(R.id.buttonGuardarF);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuarios();
            }
        });
        return vista;
    }

    private void registrarUsuarios() {
        ConexionSQLiteHelper conexion = new ConexionSQLiteHelper(vista.getContext(), "bd_usuarios", null, 1);

        //Abrir base de datos para poder editarla
        SQLiteDatabase db = conexion.getWritableDatabase();

        //Android nos da la forma de realizar el registro por medio de ContentValues
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_ID, idUsuario.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE, nombreUsuario.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, telefonoUsuario.getText().toString());

        //Ahora insertar todos los valores a la DB.
        //Vamos  a usar el metodo insert de nuestro SQLiteDatabase el cual nos
        // retorna un long dependiendo de los datos que le enviemos.
        // Toast.makeText(getApplicationContext(), "Id Registro "+ id.getText().toString(), Toast.LENGTH_LONG).show();
        Long idResultante = db.insert(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID, values);

        Toast.makeText(vista.getContext(), "Id Registro " + idResultante, Toast.LENGTH_LONG).show();
        db.close();
    }
}