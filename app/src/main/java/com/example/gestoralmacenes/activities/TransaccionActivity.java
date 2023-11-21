package com.example.gestoralmacenes.activities;

import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoTransaccion;
import com.example.gestoralmacenes.models.transaccion.Transaccion;
import com.example.gestoralmacenes.models.transaccion.TransaccionExterna;
import com.example.gestoralmacenes.models.transaccion.TransaccionInterna;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TransaccionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_transaccion);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.tablaTransaccionU);
            TextView text = new TextView(this);
            text.setTextSize(16);
            DaoTransaccion daoTransaccion = new DaoTransaccion(this);
            List<TransaccionInterna> transaccionInternaList = new ArrayList<>();
            List<TransaccionExterna> transaccionExternaList = new ArrayList<>();
            if (getIntent().getStringExtra("TipoTransaccion").equals("Interna")) {
                transaccionInternaList = daoTransaccion.getTransaccionInterna();
                for (TransaccionInterna interna : transaccionInternaList) {
                    if (interna.getId() == getIntent().getLongExtra("Id", 0)) {
                        text.setText(interna.toString());
                        break;
                    }
                }
            } else {
                transaccionExternaList = daoTransaccion.getTransaccionesExternas();
                for (TransaccionExterna externa : transaccionExternaList) {
                    if (externa.getId() == getIntent().getLongExtra("Id", 0)) {
                        text.setText(externa.toString());
                        break;
                    }
                }
            }
            linearLayout.addView(text);
        }
        catch (Exception e)
        {
            Log.e("Error",e.getMessage()+"\n"+ Arrays.toString(e.getStackTrace()));
        }
    }
    
}