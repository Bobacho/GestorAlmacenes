package com.example.gestoralmacenes.activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoProducto;
import com.example.gestoralmacenes.models.almacen.Producto;

import java.util.List;

public class ProductoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);
        DaoProducto connector=new DaoProducto(this);
        LinearLayout tabla=findViewById(R.id.productosTabla);
        List<Producto> productos=connector.getProductos();
        for(Producto producto:productos)
        {
            Button button=new Button(this);
            button.setText(producto.getNombre()+":"+producto.getId());
            button.setOnClickListener(view -> {
               Intent i=new Intent(this,ProductoUnitarioActivity.class);
               i.putExtra("Id",producto.getId());
               startActivity(i);
            });
            tabla.addView(button);
        }
    }
}