package com.example.gestoralmacenes.activities;

import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoContenedor;
import com.example.gestoralmacenes.models.almacen.Contenedor;
import java.util.List;
public class ContenedorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenedor);
        LinearLayout tabla=(LinearLayout) findViewById(R.id.contenedorTabla);
        DaoContenedor connector=new DaoContenedor(this);
        List<Contenedor> contenedores=connector.getContenedores();
        for(Contenedor contenedor:contenedores)
        {
            Button button=new Button(this);
            button.setText("Contendor Nro "+contenedor.getId());
            button.setTextSize(15);
            button.setOnClickListener(v -> {
                Intent i=new Intent(this,ContenedorDetalleActivity.class);
                i.putExtra("IdContenedor",contenedor.getId());
                startActivity(i);
            });
            tabla.addView(button);
        }
    }
}