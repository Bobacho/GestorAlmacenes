package com.example.gestoralmacenes.activities;

import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoUsuario;
import com.example.gestoralmacenes.models.personas.Usuario;

import java.sql.SQLException;

public class UsuarioDetalleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_detalle);
        LinearLayout layout=(LinearLayout) findViewById(R.id.usuarioDetalleLayout);
        TextView nombreUsuario=new TextView(this);
        try {
            DaoUsuario connector=new DaoUsuario(this);
            for(Usuario usuario:connector.obtenerUsuariosSQLite())
            {
                if(usuario.getId()==getIntent().getLongExtra("IdUsuario",0))
                {
                    nombreUsuario.setText(usuario.toString());
                    nombreUsuario.setTextSize(15);
                    layout.addView(nombreUsuario);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}