package com.example.gestoralmacenes.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoUsuario;

import java.sql.SQLException;

public class UsuarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        try {
            DaoUsuario connector=new DaoUsuario(this);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}