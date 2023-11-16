package com.example.gestoralmacenes.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.gestoralmacenes.models.personas.Usuario;

import java.sql.SQLException;
import java.time.LocalDate;
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
        ContentValues values=new ContentValues(7);
        values.put("Id",usuario.getId());
        values.put("NombreUsuario",usuario.getNombreUsuario());
        values.put("Contraseña",usuario.getContraseña());
        values.put("TipoUsuario",usuario.getTipoUsuario());
        values.put("Nombre",usuario.getNombre());
        values.put("Fecha Registro",usuario.getFechaRegistro().toString());
        values.put("TipoActividad",usuario.getTipoActividad());
        values.put("DireccionIp",usuario.getDireccionIp());
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
                            cursor.getString(3),
                            cursor.getString(4),
                            LocalDate.parse(cursor.getString(5)),
                            cursor.getString(6),
                            cursor.getString(7)
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
