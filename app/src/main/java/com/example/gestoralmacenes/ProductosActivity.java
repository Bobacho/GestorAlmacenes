package com.example.gestoralmacenes;

import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.dao.DaoUsuario;

import java.sql.SQLException;

public class ProductosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        ScrollView scroll=(ScrollView) findViewById(R.id.tabla);
        try {
            DaoUsuario connector=new DaoUsuario(ProductosActivity.this);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}