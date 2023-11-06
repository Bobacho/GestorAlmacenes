package com.example.gestoralmacenes.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class DaoSQLite extends SQLiteOpenHelper {
    private final String usuarioNombre="Nombre";
    private final String usuarioId="Id";
    private final String usuarioContraseña="Contraseña";
    private final String usuarioNroEmpleado="IdEmpleado";
    public DaoSQLite(@Nullable Context context)
    {
        super(context,"Usuario",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Usuario (" +
                usuarioId +" LONG PRIMARY KEY," +
                usuarioNombre + " TEXT,"+
                usuarioContraseña + " TEXT,"+
                usuarioNroEmpleado + " LONG"+
                ");"
        );
        sqLiteDatabase.execSQL("insert into Usuario values(1,'admin','admin',1)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Usuario");
        onCreate(sqLiteDatabase);
    }
}
