package com.example.gestoralmacenes.activities;

import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gestoralmacenes.R;
import com.example.gestoralmacenes.dao.DaoTransaccion;
import com.example.gestoralmacenes.dao.DaoUsuario;
import com.example.gestoralmacenes.models.personas.Usuario;
import com.example.gestoralmacenes.models.transaccion.Transaccion;
import com.example.gestoralmacenes.models.transaccion.TransaccionInterna;
import com.google.android.gms.vision.text.Line;
import com.google.android.material.chip.Chip;
import com.google.android.material.navigation.NavigationView;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Menu extends AppCompatActivity{

    private LinearLayout tabla;
    private Chip chip1;
    private Chip chip2;
    private DaoTransaccion connector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder(StrictMode.getVmPolicy())
                .detectLeakedClosableObjects()
                .build());
        setContentView(R.layout.activity_menu);

        chip2=(Chip)findViewById(R.id.chip2);
        try {
            switch (getIntent().getIntExtra("Filtros", 0)) {
                case 0 -> generarTransacciones();
                case 1 -> generarTransaccionesPorFechas();
                case 2 -> generarTransacciones();
            }
            chip1=(Chip)findViewById(R.id.chip);
            chip1.setOnClickListener(view -> {
                view.setBackgroundColor(R.color.white);
                tabla.removeAllViews();
                DaoTransaccion connector2=new DaoTransaccion(this);
                List<Transaccion> transaccions=new ArrayList<>(connector2.getTransaccionInterna());
                for (Transaccion transaccion : transaccions) {
                    Button boton = new Button(this);
                    boton.setText("Transaccion del :" + transaccion.getFechaInicio().toString() + "/" + transaccion.getFechaFin());
                    boton.setOnClickListener(view2 -> {
                        Intent i = new Intent(this, TransaccionActivity.class);
                        i.putExtra("Id", transaccion.getId());
                        i.putExtra("TipoTransaccion", transaccion.getTipoTransaccion());
                        startActivity(i);
                    });
                    tabla.addView(boton);
                }
            });
            chip2.setOnClickListener(view -> {
                tabla.removeAllViews();
                view.setBackgroundColor(R.color.white);
                DaoTransaccion connector2=new DaoTransaccion(this);
                List<Transaccion> transaccions=new ArrayList<>(connector2.getTransaccionesExternas());
                for (Transaccion transaccion : transaccions) {
                    Button boton = new Button(this);
                    boton.setText("Transaccion del :" + transaccion.getFechaInicio().toString() + "/" + transaccion.getFechaFin());
                    boton.setOnClickListener(view2 -> {
                        Intent i = new Intent(this, TransaccionActivity.class);
                        i.putExtra("Id", transaccion.getId());
                        i.putExtra("TipoTransaccion", transaccion.getTipoTransaccion());
                        startActivity(i);
                    });
                    tabla.addView(boton);
                }
            });
        } catch (Exception e) {
            Log.e("Error",e.getMessage()+"\n"+ Arrays.toString(e.getStackTrace()));
        }
    }
    public void moveToFechas(View view)
    {
        Intent i=new Intent(this,FechaActivity.class);
        i.putExtra("Fecha",1);
        startActivity(i);
    }
    private void generarTransacciones() throws SQLException{
        try {

            tabla = (LinearLayout) findViewById(R.id.transaccionesTabla);
            connector = new DaoTransaccion(this);
            List<com.example.gestoralmacenes.models.transaccion.Transaccion> transacciones = connector.getTransacciones();
            for (Transaccion transaccion : transacciones) {
                Button boton = new Button(this);
                boton.setText("Transaccion del :" + transaccion.getFechaInicio().toString() + "/" + transaccion.getFechaFin());
                boton.setOnClickListener(view -> {
                    Intent i = new Intent(this, TransaccionActivity.class);
                    i.putExtra("Id", transaccion.getId());
                    i.putExtra("TipoTransaccion", transaccion.getTipoTransaccion());
                    startActivity(i);
                });
                tabla.addView(boton);
            }
        }catch (Exception e)
        {
            Log.e("Error",e.getMessage()+"\n"+ Arrays.toString(e.getStackTrace()));
        }
    }
    private void generarTransaccionesPorFechas()
    {
        try {
            tabla = (LinearLayout) findViewById(R.id.transaccionesTabla);
            connector = new DaoTransaccion(this);
            LocalDate fechaInicio = LocalDate.of(getIntent().getIntExtra("FechaInicioAño", 0), getIntent().getIntExtra("FechaInicioMes", 0), getIntent().getIntExtra("FechaInicioDia", 0));
            LocalDate fechaFin = LocalDate.of(getIntent().getIntExtra("FechaFinAño", 0), getIntent().getIntExtra("FechaFinMes", 0), getIntent().getIntExtra("FechaFinDia", 0));
            Log.d("FechaInicio", fechaInicio.toString());
            DaoTransaccion connector = new DaoTransaccion(this);
            List<Transaccion> transacciones=connector.getTransacciones();
            if(transacciones!=null) {
                for (Transaccion transaccion : transacciones) {
                    if(fechaInicio.isBefore(transaccion.getFechaInicio()) && fechaFin.isAfter(transaccion.getFechaInicio())){
                        Button boton = new Button(this);
                        boton.setText("Transaccion del :" + transaccion.getFechaInicio().toString() + "/" + transaccion.getFechaFin());
                        boton.setOnClickListener(view -> {
                        Intent i = new Intent(this, TransaccionActivity.class);
                        i.putExtra("Id", transaccion.getId());
                            i.putExtra("TipoTransaccion", transaccion.getTipoTransaccion());
                            startActivity(i);
                        });
                        tabla.addView(boton);
                    }
                }
            }
        }catch (Exception e)
        {
            Log.e("Error",e.getMessage()+"\n"+ Arrays.toString(e.getStackTrace()));
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
    public void movetoUsuarios(View view)
    {
        Intent i = new Intent(this, UsuarioActivity.class);
        startActivity(i);
    }
    public void movetoProductos(View view)
    {
        Intent i=new Intent(this,ProductoActivity.class);
        startActivity(i);
    }
    public void movetoContenedores(View view)
    {
        Intent i=new Intent(this,ContenedorActivity.class);
        i.putExtra("Filtros",0);
        startActivity(i);
    }
    public void moveToGraficos(View view)
    {
        Intent i=new Intent(this,GraficosActivity.class);
        startActivity(i);
    }
}