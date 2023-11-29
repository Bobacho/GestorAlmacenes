package com.example.gestoralmacenes.activities;

import android.content.Intent;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoUsuario;
import com.example.gestoralmacenes.models.personas.Usuario;
import android.widget.Button;

import java.sql.SQLException;
import java.util.List;

public class UsuarioActivity extends AppCompatActivity {
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        searchView=(SearchView) findViewById(R.id.buscadorUsuario);
        searchView.setQueryHint("Buscar por nombre de usuario");
        LinearLayout tabla=(LinearLayout) findViewById(R.id.usuarioTabla);
        try {
            DaoUsuario connector=new DaoUsuario(this);
            List<Usuario> usuarios=connector.obtenerUsuariosSQLite();
            for(Usuario usuario:usuarios)
            {
                Button boton=new Button(this);
                boton.setText(usuario.getNombreUsuario());
                boton.setTextSize(15);
                boton.setOnClickListener(v -> {
                    Intent i=new Intent(this,UsuarioDetalleActivity.class);
                    i.putExtra("IdUsuario",usuario.getId());
                    setResult(RESULT_OK,i);
                    startActivity(i);
                });
                tabla.addView(boton);
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
                    List<Usuario> usuarioList=connector.obtenerUsuariosSQLite();
                    if(usuarioList==null)
                    {
                        return false;
                    }
                    for(Usuario usuario:usuarioList)
                    {
                        if(usuario.getNombreUsuario().contains(s))
                        {
                            Button button=new Button(UsuarioActivity.this);
                            button.setText(usuario.getNombreUsuario());
                            button.setTextSize(15);
                            button.setOnClickListener(v -> {
                                Intent i=new Intent(UsuarioActivity.this,UsuarioDetalleActivity.class);
                                i.putExtra("IdUsuario",usuario.getId());
                                setResult(RESULT_OK,i);
                                startActivity(i);
                            });
                            tabla.addView(button);
                            resultado=true;
                        }
                    }
                    return resultado;
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}