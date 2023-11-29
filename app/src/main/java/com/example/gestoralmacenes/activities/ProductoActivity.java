package com.example.gestoralmacenes.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoProducto;
import com.example.gestoralmacenes.models.almacen.Contenedor;
import com.example.gestoralmacenes.models.almacen.Producto;

import java.util.List;

public class ProductoActivity extends AppCompatActivity {
    SearchView searchView;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        searchView=(SearchView) findViewById(R.id.productosSearchView);
        searchView.setQueryHint("Buscar por nombre de producto");
        DaoProducto connector=new DaoProducto(this);
        LinearLayout tabla=(LinearLayout) findViewById(R.id.productosTabla);
        List<Producto> productos=connector.getProductos();
        for(Producto producto:productos)
        {
            Button button=new Button(this);
            button.setText("Producto "+producto.getNombre()+":"+producto.getId());
            Log.d("Producto ",producto.toString());
            button.setOnClickListener(view -> {
               Intent i=new Intent(this,ProductoUnitarioActivity.class);
               i.putExtra("Id",producto.getId());
               startActivity(i);
            });
            tabla.addView(button);
        }
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                tabla.removeAllViews();
                boolean resultado=false;
                List<Producto> productosList=connector.getProductos();
                if(productosList==null)
                {
                    return false;
                }
                for(Producto producto:productosList)
                {
                    if(producto.getNombre().contains(s))
                    {
                        Button button=new Button(ProductoActivity.this);
                        button.setText("Producto "+producto.getNombre()+":"+producto.getId());
                        button.setTextSize(15);
                        button.setOnClickListener(v -> {
                            Intent i=new Intent(ProductoActivity.this,ProductoUnitarioActivity.class);
                            i.putExtra("Producto",producto.getId());
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

}