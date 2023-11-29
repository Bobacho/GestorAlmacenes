package com.example.gestoralmacenes.activities;

import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoContenedor;
import com.example.gestoralmacenes.models.almacen.Contenedor;

import java.util.ArrayList;
import java.util.List;
public class ContenedorActivity extends AppCompatActivity{
    SearchView searchView;
    DaoContenedor connector;
    LinearLayout tabla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor);
        searchView=(SearchView) findViewById(R.id.contenedorSearchView);
        searchView.setQueryHint("Buscar por nombre de producto");
        tabla=(LinearLayout) findViewById(R.id.contenedorTabla);
        connector=new DaoContenedor(this);
        switch(getIntent().getIntExtra("Filtros",0)) {
            case 0 -> generarContenedores();
            case 1 -> generarContenedoresByUbicacion();
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s){
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s){
                tabla.removeAllViews();
                boolean resultado=false;
                List<Contenedor> contenedorList=connector.getContenedores();
                if(contenedorList==null)
                {
                    return false;
                }
                for(Contenedor contenedor:contenedorList)
                {
                    if(contenedor.getProductos().stream().anyMatch(producto -> producto.getNombre().contains(s)))
                    {
                        Button button=new Button(ContenedorActivity.this);
                        button.setText("Contendor Nro "+contenedor.getId());
                        button.setTextSize(15);
                        button.setOnClickListener(v -> {
                            Intent i=new Intent(ContenedorActivity.this,ContenedorDetalleActivity.class);
                            i.putExtra("IdContenedor",contenedor.getId());
                            startActivity(i);
                        });
                        tabla.addView(button);
                        resultado=true;
                    }
                }
                return resultado;
            }
        });
    }

    private void generarContenedoresByUbicacion() {
        List<Contenedor> contenedores=connector.getContenedores();
        List<Contenedor> contenedoresByUbicacion=new ArrayList<>();
        Log.d("Fila",getIntent().getIntExtra("Fila",0)+"");
        Log.d("Columna",getIntent().getIntExtra("Columna",0)+"");
        Log.d("Orientacion",getIntent().getIntExtra("Orientacion",0)+"");
        for(Contenedor contenedor:contenedores)
        {
            if(contenedor.getBloqueEstanteria().stream().anyMatch(bloqueEstanteria -> bloqueEstanteria.getFila()==getIntent().getIntExtra("Fila",0) && bloqueEstanteria.getColumna()==getIntent().getIntExtra("Columna",0) && bloqueEstanteria.getCara()==getIntent().getIntExtra("Orientacion",0)))
            {
                contenedoresByUbicacion.add(contenedor);
            }
        }
        for(Contenedor contenedor:contenedoresByUbicacion)
        {
            Button button = new Button(this);
            button.setText("Contendor Nro " + contenedor.getId());
            button.setTextSize(15);
            button.setOnClickListener(v -> {
                Intent i = new Intent(this, ContenedorDetalleActivity.class);
                i.putExtra("IdContenedor", contenedor.getId());
                startActivity(i);
            });
            tabla.addView(button);
        }
    }

    public void moveToBuscadorUbicacion(android.view.View view)
    {
        Intent i=new Intent(this,BuscadorUbicacion.class);
        startActivity(i);
    }
    public void generarContenedores()
    {
        List<Contenedor> contenedores=connector.getContenedores();
        for(Contenedor contenedor:contenedores)
        {
            Button button = new Button(this);
            button.setText("Contendor Nro " + contenedor.getId());
            button.setTextSize(15);
            button.setOnClickListener(v -> {
                Intent i = new Intent(this, ContenedorDetalleActivity.class);
                i.putExtra("IdContenedor", contenedor.getId());
                startActivity(i);
            });
            tabla.addView(button);
        }
    }
}