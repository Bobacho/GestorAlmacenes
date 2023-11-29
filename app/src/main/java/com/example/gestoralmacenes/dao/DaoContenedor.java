package com.example.gestoralmacenes.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import com.example.gestoralmacenes.models.almacen.BloqueEstanteria;
import com.example.gestoralmacenes.models.almacen.Contenedor;
import com.example.gestoralmacenes.models.almacen.Producto;
import com.example.gestoralmacenes.models.documentos.Tarifario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoContenedor {
    private DaoSQLite connector;
    private SQLiteDatabase db;
    public DaoContenedor(@Nullable Context context) {
        this.connector = new DaoSQLite(context);
        this.db = connector.getReadableDatabase();
    }
    public List<Contenedor> getContenedoresByProductoNombre(String nombreProducto)
    {
        Cursor cursor=db.rawQuery("SELECT * FROM Contenedor c left join Producto p ON c.Id=p.IdContenedor where p.Nombre LIKE ?;", new String[]{"%" + nombreProducto + "%"});
        if(cursor.moveToFirst())
        {
            List<Contenedor> contenedores=new ArrayList<>();
            do{
                Contenedor contenedor=new Contenedor(
                        cursor.getLong(0),
                        cursor.getLong(1),
                        cursor.getLong(2),
                        cursor.getFloat(3),
                        cursor.getFloat(4),
                        cursor.getFloat(5),
                        cursor.getFloat(6),
                        getProductosByContenedorId(cursor.getLong(0)),
                        getBloquesEstanteriaByContenedorId(cursor.getLong(0),0)
                );
                contenedores.add(contenedor);
            }while(cursor.moveToNext());
            return contenedores;
        }
        else {
            return null;
        }
    }
    public List<Contenedor> getContenedores()
    {
        Cursor cursor=db.rawQuery("SELECT * FROM Contenedor",null);
        if(cursor.moveToFirst())
        {
            List<Contenedor> contenedores=new ArrayList<>();
            do{
                Contenedor contenedor=new Contenedor(
                        cursor.getLong(0),
                        cursor.getLong(1),
                        cursor.getLong(2),
                        cursor.getFloat(3),
                        cursor.getFloat(4),
                        cursor.getFloat(5),
                        cursor.getFloat(6),
                        getProductosByContenedorId(cursor.getLong(0)),
                        getBloquesEstanteriaByContenedorId(cursor.getLong(0),0)
                );
                contenedores.add(contenedor);
            }while(cursor.moveToNext());
            return contenedores;
        }
        else {
            return null;
        }

    }

    private List<BloqueEstanteria> getBloquesEstanteriaByContenedorId(Long Id,int mode) {
        Cursor cursor=db.rawQuery("select * from BloqueEstanteriaContenedor where IdContenedor="+Id+";",null);
        if(cursor.moveToFirst())
        {
            List<BloqueEstanteria> bloquesEstanteria=new ArrayList<>();
            do{
                 Cursor cursor1=db.rawQuery("select * from BloqueEstanteria where Id="+cursor.getLong(1)+";",null);
                 if(cursor1.moveToFirst())
                 {
                     do {
                         BloqueEstanteria bloqueEstanteria = new BloqueEstanteria(
                                 cursor1.getLong(0),
                                 cursor1.getFloat(1),
                                 cursor1.getFloat(2),
                                 cursor1.getFloat(3),
                                 cursor1.getInt(4),
                                 cursor1.getInt(5),
                                 cursor1.getInt(6),
                                 cursor1.getInt(7),
                                 cursor1.getInt(8),
                                 getContenedoresByBloqueEstanteriaId(cursor1.getLong(0),1)
                         );
                         bloquesEstanteria.add(bloqueEstanteria);
                     }while(cursor1.moveToNext());
                 }
                 cursor1.close();
            }while(cursor.moveToNext());
            return bloquesEstanteria;
        }
        else {
            return null;
        }
    }

    private List<Contenedor> getContenedoresByBloqueEstanteriaId(Long Id,int mode) {
        Cursor cursor=db.rawQuery("select IdContenedor from BloqueEstanteriaContenedor where IdBloqueEstanteria="+Id+";",null);
        if(cursor.moveToFirst())
        {
            List<Contenedor> contenedores=new ArrayList<>();
            do{
                Cursor cursor1=db.rawQuery("select * from Contenedor where Id="+cursor.getLong(0)+";",null);
                if(cursor1.moveToFirst())
                {
                    do {
                        Contenedor contenedor=new Contenedor(
                                cursor1.getLong(0),
                                cursor1.getLong(1),
                                cursor1.getLong(2),
                                cursor1.getFloat(3),
                                cursor1.getFloat(4),
                                cursor1.getFloat(5),
                                cursor1.getFloat(6),
                                getProductosByContenedorId(cursor1.getLong(0)),
                                (mode==0)?getBloquesEstanteriaByContenedorId(cursor1.getLong(0),1):Collections.EMPTY_LIST
                        );
                        contenedores.add(contenedor);
                    }while(cursor1.moveToNext());
                }
                cursor1.close();
            }while(cursor.moveToNext());
            return contenedores;
        }
        else
        {
            return null;
        }
    }

    private List<Producto> getProductosByContenedorId(Long Id) {
        Cursor cursor=db.rawQuery("select * from Producto where IdContenedor="+Id+";",null);
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
                            DaoProducto.getTarifarioActual(getTarifariosByProductoId(cursor.getLong(0)))
                    );
                    productos.add(producto);
            }while(cursor.moveToNext());
            return productos;
        }
        else {
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
            return List.of(new Tarifario(0L,0F,0,0F,0F, LocalDate.now()));
        }
    }

    public Contenedor getContenedorById(Long Id) {
        Cursor cursor=db.rawQuery("select * from Contenedor where Id="+Id+";",null);
        if(cursor.moveToFirst())
        {
            Contenedor contenedor=new Contenedor(
                    cursor.getLong(0),
                    cursor.getLong(1),
                    cursor.getLong(2),
                    cursor.getFloat(3),
                    cursor.getFloat(4),
                    cursor.getFloat(5),
                    cursor.getFloat(6),
                    getProductosByContenedorId(cursor.getLong(0)),
                    getBloquesEstanteriaByContenedorId(cursor.getLong(0),0)
            );
            return contenedor;
        }
        else {
            return null;
        }
    }
}
