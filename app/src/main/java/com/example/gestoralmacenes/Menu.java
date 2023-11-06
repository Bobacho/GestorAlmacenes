package com.example.gestoralmacenes;

import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintSet;
import com.example.gestoralmacenes.dao.DaoUsuario;
import com.example.gestoralmacenes.models.Usuario;

import java.sql.SQLException;
import java.util.List;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        try {
            generarUsuarios();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generarUsuarios() throws SQLException {
        LinearLayout tabla=(LinearLayout)findViewById(R.id.usuariosTabla);
        DaoUsuario connector=new DaoUsuario(this);
        List<Usuario> usuarios=connector.obtenerUsuariosSQLite();
        for(Usuario usuario:usuarios)
        {
            TextView nombre=new EditText(this);
            nombre.setText(usuario.getNombre());
            nombre.setSelected(false);
            TextView contraseña=new EditText(this);
            contraseña.setText(usuario.getContraseña());
            contraseña.setSelected(false);
            TextView id=new EditText(this);
            id.setText(String.valueOf(usuario.getId()));
            id.setSelected(false);
            TextView idEmpleado=new EditText(this);
            idEmpleado.setText(String.valueOf(usuario.getIdEmpleado()));
            idEmpleado.setSelected(false);
            tabla.addView(nombre);
            tabla.addView(contraseña);
            tabla.addView(id);
            tabla.addView(idEmpleado);
        }
    }

    public void moveToProductos(View v)
    {
        Intent i=new Intent(this, ProductosActivity.class);
        startActivity(i);
    }
}