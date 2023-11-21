package com.example.gestoralmacenes.activities;

import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoProducto;
import com.example.gestoralmacenes.models.almacen.Producto;

public class ProductoUnitarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_unitario);
        DaoProducto connector=new DaoProducto(this);
        LinearLayout tabla=findViewById(R.id.productoUnitarioTabla);
        Producto producto=connector.getProductoById(getIntent().getLongExtra("Id",0));
        TextView text=new TextView(this);
        text.setText(producto.toString());
        tabla.addView(text);
    }
}