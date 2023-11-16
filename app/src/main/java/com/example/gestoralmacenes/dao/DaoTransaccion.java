package com.example.gestoralmacenes.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.Nullable;
import com.example.gestoralmacenes.models.almacen.BloqueEstanteria;
import com.example.gestoralmacenes.models.almacen.Contenedor;
import com.example.gestoralmacenes.models.almacen.Estanteria;
import com.example.gestoralmacenes.models.almacen.Producto;
import com.example.gestoralmacenes.models.documentos.Documento;
import com.example.gestoralmacenes.models.documentos.RegistrosContables;
import com.example.gestoralmacenes.models.documentos.Tarifario;
import com.example.gestoralmacenes.models.transaccion.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DaoTransaccion{
    private SQLiteDatabase db;
    private DaoSQLite connector;
    public DaoTransaccion(@Nullable Context context) {
        connector=new DaoSQLite(context);
    }
    public List<Transaccion> getTransacciones()
    {
        List<TransaccionExterna> transaccionExternas=getTransaccionesExternas();
        List<TransaccionInterna> transaccionInternas=getTransaccionInterna();
        List<Transaccion> transacciones=new ArrayList<>();
        for(TransaccionExterna transaccionExterna:transaccionExternas)
        {
            transacciones.add(transaccionExterna);
        }
        for(TransaccionInterna transaccionInterna:transaccionInternas)
        {
            transacciones.add(transaccionInterna);
        }
        Collections.sort(transacciones);
        return transacciones;
    }
    public List<TransaccionExternaUnitaria> getTransaccionExternaUnitariaByTransaccionExternaId(Long Id)
    {
        Cursor cursor=db.rawQuery("select * from TransaccionExternaUnitaria where TransaccionExternaId="+Id,null);
        if(cursor.moveToFirst())
        {
            List<TransaccionExternaUnitaria> transaccionExternaUnitarias=new ArrayList<>();
            do {
                TransaccionExternaUnitaria transaccionExternaUnitaria = new TransaccionExternaUnitaria(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3),
                        cursor.getFloat(4),
                        cursor.getFloat(5),
                        cursor.getFloat(6),
                        getContenedorById(cursor.getLong(7))
                );
                transaccionExternaUnitarias.add(transaccionExternaUnitaria);
            }while (cursor.moveToNext());
            return  transaccionExternaUnitarias;
        }
        else {
            return null;
        }
    }
    public List<TransaccionExterna> getTransaccionesExternas()
    {
        Cursor cursor=db.rawQuery("select * from TransaccionExterna",null);
        if(cursor.moveToFirst())
        {
            List<TransaccionExterna> transaccionExternas=new ArrayList<>();
            do {
                TransaccionExterna transaccionExterna=new TransaccionExterna(
                  cursor.getLong(0),
                  LocalDate.parse(cursor.getString(1)),
                  LocalDate.parse(cursor.getString(2)),
                  getTransaccionExternaUnitariaByTransaccionExternaId(cursor.getLong(3)),
                        getDocumentosByTransaccionExternaId(cursor.getLong(4)),
                        getRegistrosContablesByDocumentoId(cursor.getLong(5))
                );
                transaccionExternas.add(transaccionExterna);
            }while(cursor.moveToNext());
            return transaccionExternas;
        }
        else
        {
            return null;
        }
    }

    private Documento getDocumentosByTransaccionExternaId(Long Id) {
        Cursor cursor=db.rawQuery("select * from Documento where IdTransaccionEXT.CAB="+Id,null);
        if(cursor.moveToFirst())
        {
            Documento documento=new Documento(
                    cursor.getLong(0),
                    cursor.getString(1),
                    LocalDate.parse(cursor.getString(2)),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5)
            );
            return documento;
        }
        else
        {
            return null;
        }
    }

    private RegistrosContables getRegistrosContablesByDocumentoId(Long Id) {
        Cursor cursor=db.rawQuery("select F.Fecha from RegistroContable.CAB R, RegistroContable.DET RD, FechaContable F" +
                " where R.Id="+Id+" and R.Id=RD.IdRegistroContable.CAB and RD.Id=F.IdRegistroContable.DET;",null);
        List<LocalDate> fechasContables=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do {
                fechasContables.add(LocalDate.parse(cursor.getString(0)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        cursor=db.rawQuery("select D.Valor from RegistroContable.CAB R, RegistroContable.DET RD, Descripcion D" +
                " where R.Id="+Id+" and R.Id=RD.IdRegistroContable.CAB and RD.Id=D.IdRegistroContable.DET;",null);
        List<String> descripciones=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do {
                descripciones.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        cursor=db.rawQuery("select C.Valor,C.TipoCuenta from RegistroContable.CAB R, RegistroContable.DET RD, Cuenta C" +
                " where R.Id="+Id+" and R.Id=RD.IdRegistroContable.CAB and RD.Id=C.IdRegistroContable.DET;",null);
        List<Float> importes=new ArrayList<>();
        List<String> tipoCuentas=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do {
                importes.add(cursor.getFloat(0));
                tipoCuentas.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        cursor=db.rawQuery("select R.Id,R.NroOrden,R.Año from RegistroContable.CAB R where R.Id="+Id+";",null);
        if(cursor.moveToFirst())
        {
            RegistrosContables registrosContables=new RegistrosContables(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getLong(2),
                    fechasContables,
                    descripciones,
                    importes,
                    tipoCuentas
            );
            return registrosContables;
        }
        else
        {
            return null;
        }
    }

    public List<TransaccionInterna> getTransaccionInterna()
    {
        Cursor cursor=db.rawQuery("select * from TransaccionInterna",null);
        if(cursor.moveToFirst())
        {
            List<TransaccionInterna> transaccionInternas=new ArrayList<>();
            do {
                TransaccionInterna transaccionInterna=new TransaccionInterna(
                        cursor.getLong(0),
                        LocalDate.parse(cursor.getString(1)),
                        LocalDate.parse(cursor.getString(2)),
                        getTransaccionInternaUnitariaByTransaccionInternaId(cursor.getLong(3))
                );
                transaccionInternas.add(transaccionInterna);
            }while(cursor.moveToNext());
            return transaccionInternas;
        }
        else{
            return null;
        }
    }
    public List<TransaccionInternaUnitaria> getTransaccionInternaUnitariaByTransaccionInternaId(Long Id)
    {
        Cursor cursor=db.rawQuery("select * from TransaccionInternaUnitaria where IdTransaccionINT.CAB="+Id,null);
        if(cursor.moveToFirst())
        {
            List<TransaccionInternaUnitaria> transaccionesInternaUnitarias=new ArrayList<>();
            do {
                TransaccionInternaUnitaria transaccionInternaUnitaria=new TransaccionInternaUnitaria(
                        cursor.getLong(0),
                        getEstanteriaById(cursor.getLong(1)),
                        getEstanteriaById(cursor.getLong(2)),
                        getBloqueEstanteriaById(cursor.getLong(3)),
                        getBloqueEstanteriaById(cursor.getLong(4)),
                        getContenedorById(cursor.getLong(5))
                );
                transaccionesInternaUnitarias.add(transaccionInternaUnitaria);
            }while(cursor.moveToNext());
            return  transaccionesInternaUnitarias;
        }
        else
        {
            return null;
        }
    }
    public Estanteria getEstanteriaById(Long Id)
    {
        Cursor cursor=db.rawQuery("select * from Estanteria where Id="+Id,null);
        if (cursor.moveToFirst())
        {
            Estanteria estanteria=new Estanteria(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    getBloqueEstanteriaByEstanteriaId(cursor.getLong(3))
            );
            return estanteria;
        }
        else
        {
            return null;
        }
    }

    private List<BloqueEstanteria> getBloqueEstanteriaByEstanteriaId(Long Id) {
        Cursor cursor=db.rawQuery("select * from BloqueEstanteria where IdEstanteria="+Id,null);
        if (cursor.moveToFirst())
        {
            List<BloqueEstanteria> bloqueEstanterias=new ArrayList<>();
            do {
                BloqueEstanteria bloqueEstanteria=new BloqueEstanteria(
                        cursor.getLong(0),
                        cursor.getFloat(1),
                        cursor.getFloat(2),
                        cursor.getFloat(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getInt(8),
                        getContenedorByBloqueEstanteriaId(cursor.getLong(9))
                );
                bloqueEstanterias.add(bloqueEstanteria);
            }while (cursor.moveToNext());
            return bloqueEstanterias;
        }
        else
        {
            return null;
        }
    }

    public Contenedor getContenedorById(Long Id)
    {
        Cursor cursor=db.rawQuery("select * from Contenedor where Id="+Id,null);
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
                    getProductosByContenedorId(cursor.getLong(7)),
                    getBloqueEstanteriaByContenedorId(cursor.getLong(8))
            );
            return contenedor;
        }
        else
        {
            return null;
        }
    }
    public List<BloqueEstanteria> getBloquesEstanteria()
    {
        Cursor cursor=db.rawQuery("select * from BloqueEstanteria",null);
        if(cursor.moveToFirst())
        {
            List<BloqueEstanteria> bloqueEstanterias=new ArrayList<>();
            do {
                BloqueEstanteria bloqueEstanteria=new BloqueEstanteria(
                  cursor.getLong(0),
                        cursor.getFloat(1),
                        cursor.getFloat(2),
                        cursor.getFloat(3),
                        cursor.getInt(4),
                        cursor.getInt(5),
                        cursor.getInt(6),
                        cursor.getInt(7),
                        cursor.getInt(8),
                        getContenedorByBloqueEstanteriaId(cursor.getLong(9))
                );
                bloqueEstanterias.add(bloqueEstanteria);
            }while(cursor.moveToNext());
            return bloqueEstanterias;
        }
        else {
            return null;
        }
    }

    private List<Contenedor> getContenedorByBloqueEstanteriaId(Long Id) {
        Cursor cursor=db.rawQuery("select IdContenedor from BloqueEstanteria-Contenedor where IdBloqueEstanteria="+Id,null);
        if (cursor.moveToFirst())
        {
            List<Contenedor> contenedores=new ArrayList<>();
            do {
                contenedores.add(getContenedorById(cursor.getLong(0)));
            }while (cursor.moveToNext());
            return contenedores;
        }
        else
        {
            return null;
        }
    }
    public BloqueEstanteria getBloqueEstanteriaById(Long Id)
    {
        Cursor cursor=db.rawQuery("select * from BloqueEstanteria where Id="+Id,null);
        if(cursor.moveToFirst())
        {
            BloqueEstanteria bloqueEstanteria=new BloqueEstanteria(
                    cursor.getLong(0),
                    cursor.getFloat(1),
                    cursor.getFloat(2),
                    cursor.getFloat(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8),
                    getContenedorByBloqueEstanteriaId(cursor.getLong(9))
            );
            return bloqueEstanteria;
        }
        else
        {
            return null;
        }
    }
    public List<BloqueEstanteria> getBloqueEstanteriaByContenedorId(Long Id)
    {
        Cursor cursor=db.rawQuery("select * from BloqueEstanteria-Contenedor where IdContenedor="+Id,null);
        if (cursor.moveToFirst())
        {
            List<BloqueEstanteria> bloqueEstanterias=new ArrayList<>();
            do {
                BloqueEstanteria bloqueEstanteria=getBloqueEstanteriaById(cursor.getLong(1));
                bloqueEstanterias.add(bloqueEstanteria);
            }while (cursor.moveToNext());
            return bloqueEstanterias;
        }
        else
        {
            return null;
        }
    }
    public List<Producto> getProductosByContenedorId(Long Id)
    {
        Cursor cursor=db.rawQuery("select * from Producto where IdContenedor="+Id,null);
        if (cursor.moveToFirst())
        {
            List<Producto> productos=new ArrayList<>();
            do {
                Producto producto=new Producto(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        Boolean.parseBoolean(cursor.getString(5)),
                        getTarifariosByProductoId(cursor.getLong(6)),
                        getTarifarioActual(getTarifariosByProductoId(cursor.getLong(6)))
                );
                productos.add(producto);
            }while (cursor.moveToNext());
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
                        cursor.getFloat(1),
                        cursor.getInt(2),
                        cursor.getFloat(3),
                        cursor.getFloat(4),
                        LocalDate.parse(cursor.getString(5))
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
