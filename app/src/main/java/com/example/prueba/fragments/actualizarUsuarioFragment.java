package com.example.prueba.fragments;

import android.content.ContentValues;
import android.database.Cursor;
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
 * Use the {@link actualizarUsuarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class actualizarUsuarioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View visto;
    EditText idUsuario, nombreUsuario, telefonoUsuario;
    ConexionSQLiteHelper conn;
    Button buscar, actualizar, eliminar;


    public actualizarUsuarioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment actualizarUsuarioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static actualizarUsuarioFragment newInstance(String param1, String param2) {
        actualizarUsuarioFragment fragment = new actualizarUsuarioFragment();
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
        visto = inflater.inflate(R.layout.fragment_actualizar_usuario, container, false);
        idUsuario = visto.findViewById(R.id.id_usuarioFActualizar);
        nombreUsuario = visto.findViewById(R.id.nombre_usuarioFActualizar);
        telefonoUsuario = visto.findViewById(R.id.telefono_usuarioFActualizar);
        buscar = visto.findViewById(R.id.buscarUsuarioFActualizar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buscarUsuario();
            }
        });
        actualizar = visto.findViewById(R.id.actualizarUsuarioFActualizar);
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarUsuario();
            }
        });
        eliminar = visto.findViewById(R.id.eliminarUsuarioFActualizar);
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUsuario();
            }
        });
       conn= new ConexionSQLiteHelper(visto.getContext(), "bd_usuarios", null, 1);
        return visto;
    }

    public void buscarUsuario() {
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] params = {idUsuario.getText().toString()};
        String[] camposV = {Utilidades.CAMPO_NOMBRE, Utilidades.CAMPO_TELEFONO};
        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO, camposV, Utilidades.CAMPO_ID + "=?", params, null, null, null);
            cursor.moveToFirst();
            nombreUsuario.setText(cursor.getString(0));
            telefonoUsuario.setText(cursor.getString(1));
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(visto.getContext(), "El documento no existe", Toast.LENGTH_LONG).show();
            limpiar(false);
        }
        db.close();
    }

    public void actualizarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] params = {idUsuario.getText().toString()};
        ContentValues conval = new ContentValues();
        conval.put(Utilidades.CAMPO_NOMBRE, nombreUsuario.getText().toString());
        conval.put(Utilidades.CAMPO_TELEFONO, telefonoUsuario.getText().toString());
        db.update(Utilidades.TABLA_USUARIO, conval, Utilidades.CAMPO_ID + "=?", params);
        Toast.makeText(visto.getContext(), "Usuario actualizado", Toast.LENGTH_LONG).show();
        db.close();
    }

    private void limpiar(boolean idLimpiar) {
        if (idLimpiar) {
            idUsuario.setText("");
        }
        nombreUsuario.setText("");
        telefonoUsuario.setText("");
    }

    public void eliminarUsuario() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] params = {idUsuario.getText().toString()};
        db.delete(Utilidades.TABLA_USUARIO, Utilidades.CAMPO_ID + "=?", params);
        Toast.makeText(visto.getContext(), "Usuario Eliminado", Toast.LENGTH_LONG).show();

        limpiar(true);
        db.close();

    }
}