package com.example.gestoralmacenes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.gestoralmacenes.models.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoUsuario {
    private SQLiteDatabase db;
    private DaoSQLite connector;
    public DaoUsuario(@Nullable Context context) throws SQLException {
        //conectar();
        connector=new DaoSQLite(context);

    }
    public void agregarUsuarioSQLite(Usuario usuario)
    {
        db=connector.getWritableDatabase();
        ContentValues values=new ContentValues(4);
        values.put("Id",usuario.getId());
        values.put("Nombre",usuario.getNombre());
        values.put("Contraseña",usuario.getContraseña());
        values.put("IdEmpleado",usuario.getIdEmpleado());
        db.insert("Usuario",
                null,
                values);
        db.close();
    }
    public void showDatabase()
    {
        db=connector.getReadableDatabase();
        Log.d("Tabla",db.toString());
        db.close();
    }
    public List<Usuario> obtenerUsuariosSQLite()
    {
        db=connector.getReadableDatabase();
        List<Usuario> retornar = new ArrayList<>();
        try {
            Cursor cursor = db.rawQuery("select * from Usuario", null);
            if (cursor.moveToFirst()) {
                do {
                    Usuario usuario = new Usuario(
                            cursor.getLong(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getLong(3)
                    );
                    retornar.add(usuario);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        }catch (Exception e)
        {
            Log.e("Error",e.toString());
        }
        return retornar;
    }
    public void limpiarTabla()
    {
        db=connector.getWritableDatabase();
        db.execSQL("delete from Usuario");
        db.close();
    }
    /*public void agregarUsuarioMySQL(Usuario usuario) throws SQLException {
        String query="call insertarUsuario(?,?,?,?)";
        PreparedStatement statement=conexion.prepareStatement(query);
        statement.setLong(1,usuario.getId());
        statement.setLong(2,usuario.getIdEmpleado());
        statement.setString(3,usuario.getNombre());
        statement.setString(4,usuario.getContraseña());
    }*/
}
