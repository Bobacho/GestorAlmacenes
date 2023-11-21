package com.example.gestoralmacenes.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import com.example.gestoralmacenes.models.almacen.Producto;
import com.example.gestoralmacenes.models.documentos.Tarifario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DaoProducto{
    private SQLiteDatabase db;
    private DaoSQLite connector;
    public DaoProducto(@Nullable Context context) {
        connector=new DaoSQLite(context);
        db=connector.getReadableDatabase();
    }
    public Producto getProductoById(Long Id)
    {
        Cursor cursor=db.rawQuery("SELECT * FROM Producto WHERE Id="+Id,null);
        if(cursor.moveToFirst())
        {
            Producto producto=new Producto(
                    cursor.getLong(0),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(1),
                    cursor.getString(2),
                    Boolean.parseBoolean(cursor.getString(3)),
                    getTarifariosByProductoId(cursor.getLong(0)),
                    getTarifarioActual(getTarifariosByProductoId(cursor.getLong(0))));
            return producto;
        }
        else {
            return null;
        }
    }
    public List<Producto> getProductos()
    {
        Cursor cursor=db.rawQuery("SELECT * FROM Producto",null);
        if(cursor.moveToFirst())
        {
            List<Producto> productos=new ArrayList<>();
            do{
                Producto producto=new Producto(
                  cursor.getLong(0),
                  cursor.getString(7),
                  cursor.getString(8),
                  cursor.getString(1),
                  cursor.getString(2),
                  Boolean.parseBoolean(cursor.getString(3)),
                  getTarifariosByProductoId(cursor.getLong(0)),
                  getTarifarioActual(getTarifariosByProductoId(cursor.getLong(0)))
                );
                productos.add(producto);
            }while(cursor.moveToNext());
            return productos;
        }
        else
        {
            return null;
        }
    }
    public List<Tarifario> getTarifariosByProductoId(Long Id)
    {
        Cursor cursor=db.rawQuery("select * from Tarifario where IdProducto="+Id,null);
        if (cursor.moveToFirst())
        {
            List<Tarifario> tarifarios=new ArrayList<>();
            do {
                Tarifario tarifario=new Tarifario(
                        cursor.getLong(0),
                        cursor.getFloat(2),
                        cursor.getInt(3),
                        cursor.getFloat(4),
                        cursor.getFloat(5),
                        LocalDate.parse(cursor.getString(6))
                );
                tarifarios.add(tarifario);
            }while (cursor.moveToNext());
            return tarifarios;
        }
        else
        {
            return null;
        }
    }
    public Tarifario getTarifarioActual(List<Tarifario> tarifarios)
    {
        Tarifario tarifarioActual=tarifarios.get(0);
        for (Tarifario tarifario:tarifarios)
        {
            if(tarifario.getFechaVencimiento().isAfter(tarifarioActual.getFechaVencimiento()))
            {
                tarifarioActual=tarifario;
            }
        }
        return tarifarioActual;
    }
}
