package com.example.gestoralmacenes.activities;

import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoProducto;
import com.example.gestoralmacenes.dao.DaoTransaccion;
import com.example.gestoralmacenes.models.almacen.Producto;
import com.example.gestoralmacenes.models.transaccion.Transaccion;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import java.util.List;

public class GraficosIMPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos_impactivity);
        switch(getIntent().getIntExtra("Tipo",0))
        {
            case 1:
                DaoProducto connector = new DaoProducto(this);
                List<Producto> productos = connector.getProductos();
                PieChart grafico = new PieChart(this);
                int perecederos=0;
                int noPerecederos=0;
                for(Producto producto:productos)
                {
                    if(producto.getTipo().equals("Perecedero"))
                    {
                        perecederos++;
                    }
                    else
                    {
                        noPerecederos++;
                    }
                }
                PieData data= new PieData();
                IPieDataSet dataSet= new PieDataSet(List.of(new PieEntry(100f*perecederos/(perecederos+noPerecederos),"Perecederos"),new PieEntry(100f*noPerecederos/(perecederos+noPerecederos),"No Perecederos")),"Productos");
                data.setDataSet(dataSet);
                grafico.setData(data);
                grafico.setHoleColor(R.color.purple_200);
                setContentView(grafico);
                break;
            case 2:
                DaoTransaccion connector2 = new DaoTransaccion(this);
                List<Transaccion> transaccions=connector2.getTransacciones();
                PieChart grafico2 = new PieChart(this);
                int externas=0;
                int internas=0;
                for(Transaccion transaccion:transaccions)
                {
                    if(transaccion.getTipoTransaccion().equals("Externa"))
                    {
                        externas++;
                    }
                    else
                    {
                        internas++;
                    }
                }
                PieData data2= new PieData();
                IPieDataSet dataSet2= new PieDataSet(List.of(new PieEntry(100.0f*externas/(externas+internas),"Externas"),new PieEntry(100.0f*internas/(externas+internas),"Internas")),"Transacciones");
                data2.setDataSet(dataSet2);
                grafico2.setData(data2);
                grafico2.setHoleColor(R.color.purple_200);
                setContentView(grafico2);
                break;
        }
    }
}