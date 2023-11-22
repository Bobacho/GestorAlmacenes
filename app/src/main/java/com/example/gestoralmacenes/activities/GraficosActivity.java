package com.example.gestoralmacenes.activities;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoContenedor;
import com.example.gestoralmacenes.dao.DaoProducto;
import com.example.gestoralmacenes.models.almacen.Producto;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.*;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import java.util.ArrayList;
import java.util.List;

public class GraficosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);

    }
    public void moveToProductosGrafico(View view)
    {
        Intent i=new Intent(this,GraficosIMPActivity.class);
        i.putExtra("Tipo",1);
        startActivity(i);
    }
    public void moveToTransacciones(View view)
    {
        Intent i=new Intent(this,GraficosIMPActivity.class);
        i.putExtra("Tipo",2);
        startActivity(i);
    }
}