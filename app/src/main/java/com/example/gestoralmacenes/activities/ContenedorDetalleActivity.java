package com.example.gestoralmacenes.activities;

import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoContenedor;
import com.google.android.gms.vision.text.Line;

public class ContenedorDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor_detalle);
        DaoContenedor connector=new DaoContenedor(this);
        LinearLayout tabla=(LinearLayout)findViewById(R.id.contenedorDetalleTabla);
        TextView text=new TextView(this);
        text.setText(connector.getContenedorById(getIntent().getLongExtra("IdContenedor",0)).toString());
        text.setTextSize(20);
        tabla.addView(text);
    }
}