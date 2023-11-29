package com.example.gestoralmacenes.activities;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;

public class BuscadorUbicacion extends AppCompatActivity {
    private EditText fila;
    private EditText columna;
    private EditText orientacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscador_ubicacion);
        fila=(EditText) findViewById(R.id.filaInput);
        columna=(EditText) findViewById(R.id.columnaInput);
        orientacion=(EditText) findViewById(R.id.orientacionInput);
    }

    public void moveToContenedor(View view)
    {
        Intent i=new Intent(this,ContenedorActivity.class);
        i.putExtra("Fila",Integer.parseInt(fila.getText().toString()));
        i.putExtra("Columna",Integer.parseInt(columna.getText().toString()));
        i.putExtra("Orientacion",Integer.parseInt(orientacion.getText().toString()));
        i.putExtra("Filtros",1);
        startActivity(i);
    }
}