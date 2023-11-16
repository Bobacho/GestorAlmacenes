package com.example.gestoralmacenes;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.gestoralmacenes.activities.Camara;
import com.example.gestoralmacenes.activities.Menu;
import com.example.gestoralmacenes.dao.DaoUsuario;
import com.example.gestoralmacenes.models.personas.Usuario;
import org.signal.argon2.*;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText usuarioInput;
    private EditText contraseñaInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuarioInput=(EditText) findViewById(R.id.UsuarioInput);
        contraseñaInput=(EditText) findViewById(R.id.passwordInput);
    }
    public void inicioSesion(View view) throws SQLException, Argon2Exception {
        try {
            DaoUsuario connector = new DaoUsuario(MainActivity.this);
            List<Usuario> usuarios = connector.obtenerUsuariosSQLite();
            boolean encontrado = false;
            for (Usuario usuario : usuarios) {
                if (usuario.getNombre().equals(usuarioInput.getText().toString()) && usuario.getContraseña().equals(contraseñaInput.getText().toString())) {
                    encontrado=true;
                    Intent i = new Intent(this, Menu.class);
                    i.putExtra("usuario", usuario.getNombre());
                    i.putExtra("tipoUsuario", usuario.getTipoUsuario());
                    startActivity(i);
                }
            }
            Toast t=encontrado ? Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG) : Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_LONG);
            t.show();
        }
        catch (Exception e)
        {
            Log.e("Error",e.toString());
        }
    }
    public void registroSesion(View view)
    {
        Intent i=new Intent(this, Camara.class);
        startActivity(i);
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }
}