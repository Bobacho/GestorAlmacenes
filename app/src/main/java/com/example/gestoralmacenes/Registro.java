package com.example.gestoralmacenes;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.dao.DaoMySQL;
import com.example.gestoralmacenes.dao.DaoUsuario;
import com.example.gestoralmacenes.models.Usuario;
import org.signal.argon2.*;


import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {
    private EditText nombreUsuarioView;
    private EditText contraseñaView;
    private EditText idView;
    private EditText idEmpleadoView;
    private Usuario usuario;
    private void procesarSentencia()
    {
        Intent i=getIntent();
        String sentencia=i.getStringExtra("Sentencia");
        sentencia=sentencia.replaceAll("\n","");
        sentencia=sentencia.replaceAll(" ","");
        sentencia=sentencia.replaceAll("Nombre:","*");
        sentencia=sentencia.replaceAll("Contraseña:","*");
        sentencia=sentencia.replaceAll("id:","*");
        sentencia=sentencia.replaceAll("idEmpleado:","*");
        sentencia+="*";
        String separador= Pattern.quote("*");
        String[] datos=sentencia.split(separador);
        nombreUsuarioView.setText(datos[1]);
        contraseñaView.setText(datos[2]);
        idView.setText(datos[3]);
        idEmpleadoView.setText(datos[4]);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        nombreUsuarioView=(EditText) findViewById(R.id.nombreUsuarioInput);
        contraseñaView=(EditText) findViewById(R.id.usuarioPasswordInput);
        idView=(EditText) findViewById(R.id.usuarioIdInput);
        idEmpleadoView=(EditText) findViewById(R.id.usuarioIdEmpleadoInput);
        Log.d("Sentencia",getIntent().getStringExtra("Sentencia"));
        procesarSentencia();
    }
    public String encriptacion() throws Argon2Exception {
        String password=contraseñaView.getText().toString();
        return password;
    }
    public void ingresarValues(View view) throws SQLException, Argon2Exception {
        DaoUsuario connector=new DaoUsuario(Registro.this);
        Log.d("primer",Long.parseLong(
                idView.getText().toString())+","
            +nombreUsuarioView.getText().toString()+","+
                        contraseñaView.getText().toString()
        +","+Long.parseLong(idEmpleadoView.getText().toString()));
        String password=encriptacion();
        usuario=new Usuario(
                Long.parseLong(idView.getText().toString()),
                nombreUsuarioView.getText().toString(),
                password,
                Long.parseLong(idEmpleadoView.getText().toString())
        );
        Log.d("valores",connector.obtenerUsuariosSQLite().toString());
        connector.agregarUsuarioSQLite(usuario);
        Intent i=new Intent(this, MainActivity.class);
        startActivity(i);
    }
}