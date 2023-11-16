package com.example.gestoralmacenes.activities;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoTransaccion;
import com.example.gestoralmacenes.dao.DaoUsuario;
import com.example.gestoralmacenes.models.personas.Usuario;
import com.example.gestoralmacenes.models.transaccion.Transaccion;
import com.example.gestoralmacenes.models.transaccion.TransaccionInterna;
import com.google.android.gms.vision.text.Line;
import com.google.android.material.navigation.NavigationView;

import java.sql.SQLException;
import java.util.List;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        try {
            //generarUsuarios();
            generarTransacciones();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generarTransacciones() throws SQLException{
        LinearLayout tabla=(LinearLayout)findViewById(R.id.transaccionesTabla);
        DaoTransaccion connector=new DaoTransaccion(this);
        List<com.example.gestoralmacenes.models.transaccion.Transaccion> transacciones=connector.getTransacciones();
        for(Transaccion transaccion:transacciones)
        {
            Button boton=new Button(this);
            boton.setText("Transaccion del :"+transaccion.getFechaInicio().toString()+"/"+transaccion.getFechaFin());
            boton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(Menu.this, Transaccion.class);
                    i.putExtra("Id",transaccion.getId());
                    i.putExtra("TipoTransaccion",(transaccion.getClass()== TransaccionInterna.class)?"Interna":"Externa");
                    startActivity(i);
                }
            });
            tabla.addView(boton);
        }
    }

    public void startNavigate(View view)
    {
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigationView);
        navigationView.setVisibility(View.VISIBLE);
        TextView usuario=(TextView)findViewById(R.id.usuario);
        usuario.setText("Bienvenido a tu cuenta "+getIntent().getStringExtra("usuario"));
        TextView correo=(TextView)findViewById(R.id.tipoUsuario);
        correo.setText(getIntent().getStringExtra("tipoUsuario"));
    }
    public void returnMenu(View view)
    {
        NavigationView navigationView=(NavigationView)findViewById(R.id.navigationView);
        navigationView.setVisibility(View.INVISIBLE);
    }
    private void generarUsuarios() throws SQLException {
        LinearLayout tabla=(LinearLayout)findViewById(R.id.transaccionesTabla);
        DaoUsuario connector=new DaoUsuario(this);
        List<Usuario> usuarios=connector.obtenerUsuariosSQLite();
        for(Usuario usuario:usuarios)
        {
            TextView nombre=new TextView(this);
            String espaciado="     ";
            nombre.setText(usuario.getNombre());
            nombre.setSelected(false);
            TextView contraseña=new TextView(this);
            contraseña.setText(usuario.getContraseña());
            contraseña.setSelected(false);
            TextView id=new TextView(this);
            id.setText(String.valueOf(usuario.getId()));
            id.setSelected(false);
            TextView nombreUsuario=new TextView(this);
            nombreUsuario.setText(String.valueOf(usuario.getNombreUsuario()));
            nombreUsuario.setSelected(false);
            TextView tipoUsuario=new TextView(this);
            tipoUsuario.setText(String.valueOf(usuario.getTipoUsuario()));
            tipoUsuario.setSelected(false);
            TextView fechaRegistro=new TextView(this);
            fechaRegistro.setText(String.valueOf(usuario.getFechaRegistro()));
            TextView tipoActividad=new TextView(this);
            tipoActividad.setText(String.valueOf(usuario.getTipoActividad()));
            tipoActividad.setSelected(false);
            fechaRegistro.setSelected(false);
            tabla.addView(nombre);
            tabla.addView(contraseña);
            tabla.addView(id);
            tabla.addView(nombreUsuario);
            tabla.addView(tipoUsuario);
            tabla.addView(fechaRegistro);
            tabla.addView(tipoActividad);
        }
    }

    public void moveToProductos(View v)
    {
        Intent i=new Intent(this, ProductosActivity.class);
        startActivity(i);
    }
}