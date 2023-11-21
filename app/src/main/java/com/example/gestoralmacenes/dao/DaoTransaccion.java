package com.example.gestoralmacenes.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.annotation.Nullable;
import com.example.gestoralmacenes.models.almacen.*;
import com.example.gestoralmacenes.models.documentos.Documento;
import com.example.gestoralmacenes.models.documentos.LicenciaDeFuncionamiento;
import com.example.gestoralmacenes.models.documentos.RegistrosContables;
import com.example.gestoralmacenes.models.documentos.Tarifario;
import com.example.gestoralmacenes.models.personas.Empleado;
import com.example.gestoralmacenes.models.personas.Usuario;
import com.example.gestoralmacenes.models.transaccion.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DaoTransaccion{
    private SQLiteDatabase db;
    private DaoSQLite connector;
    public DaoTransaccion(@Nullable Context context) {
        connector=new DaoSQLite(context);
        db=connector.getReadableDatabase();
    }
    public List<Transaccion> getTransacciones()
    {
        List<TransaccionExterna> transaccionExternas=getTransaccionesExternas();
        Log.d("transaccion",(transaccionExternas==null)?"NULL":transaccionExternas.toString());
        List<TransaccionInterna> transaccionInternas=getTransaccionInterna();
        Log.d("transaccion",(transaccionInternas==null)?"NULL":transaccionInternas.toString());
        List<Transaccion> transacciones=new ArrayList<>();
        assert transaccionExternas != null;
        transacciones.addAll(transaccionExternas);
        assert transaccionInternas != null;
        transacciones.addAll(transaccionInternas);
        Collections.sort(transacciones);
        return transacciones;
    }
    public List<TransaccionExternaUnitaria> getTransaccionExternaUnitariaByTransaccionExternaId(Long Id)
    {
        Cursor cursor=db.rawQuery("select * from TransaccionEXTDET where Id="+Id,null);
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
    public String showTables()
    {
        String tables="";
        Cursor cursor=db.rawQuery("select name from sqlite_master where type='table'",null);
        if(cursor.moveToFirst())
        {
            do {
                tables+=cursor.getString(0)+"\n";
            }while (cursor.moveToNext());
        }
        return tables;
    }
    public List<TransaccionExterna> getTransaccionesExternas()
    {
        Cursor cursor=db.rawQuery("select * from TransaccionEXTCAB",null);
        if(cursor.moveToFirst())
        {
            List<TransaccionExterna> transaccionExternas=new ArrayList<>();
            do {
                TransaccionExterna transaccionExterna=new TransaccionExterna(
                  cursor.getLong(0),
                  LocalDate.parse(cursor.getString(3)),
                  LocalDate.parse(cursor.getString(6)),
                  getTransaccionExternaUnitariaByTransaccionExternaId(cursor.getLong(0)),
                        getDocumentosByTransaccionExternaId(cursor.getLong(4)),
                        getRegistrosContablesByDocumentoId(cursor.getLong(8))
                );
                transaccionExternas.add(transaccionExterna);
                Log.d("transaccion",transaccionExternas.toString());
            }while(cursor.moveToNext());
            return transaccionExternas;
        }
        else
        {
            Log.d("Cursor",cursor.toString());
            return null;
        }
    }

    private Documento getDocumentosByTransaccionExternaId(Long Id) {
        Cursor cursor=db.rawQuery("select * from Documento where Id="+Id,null);
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
        Cursor cursor=db.rawQuery("select F.Fecha from RegistroContableCAB R, RegistroContableDET RD, FechaContable F" +
                " where R.Id="+Id+" and R.Id=RD.IdRegistroContableCAB and RD.Id=F.IdRegistroContableDET;",null);
        List<LocalDate> fechasContables=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do {
                fechasContables.add(LocalDate.parse(cursor.getString(0)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        cursor=db.rawQuery("select D.Valor from RegistroContableCAB R, RegistroContableDET RD, Descripcion D" +
                " where R.Id="+Id+" and R.Id=RD.IdRegistroContableCAB and RD.Id=D.IdRegistroContableDET;",null);
        List<String> descripciones=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            do {
                descripciones.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        cursor=db.rawQuery("select C.Valor,C.TipoCuenta from RegistroContableCAB R, RegistroContableDET RD, Cuenta C" +
                " where R.Id="+Id+" and R.Id=RD.IdRegistroContableCAB and RD.Id=C.IdRegistroContableDET;",null);
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
        cursor=db.rawQuery("select R.Id,R.NroOrden,R.Año from RegistroContableCAB R where R.Id="+Id+";",null);
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
        Cursor cursor=db.rawQuery("select * from TransaccionINTCAB",null);
        if(cursor.moveToFirst())
        {
            List<TransaccionInterna> transaccionInternas=new ArrayList<>();
            do {
                TransaccionInterna transaccionInterna=new TransaccionInterna(
                        cursor.getLong(0),
                        LocalDate.parse(cursor.getString(3)),
                        LocalDate.parse(cursor.getString(4)),
                        getTransaccionInternaUnitariaByTransaccionInternaId(cursor.getLong(0)),
                        getAlmacenById(cursor.getLong(1)),
                        getEmpleadoById(cursor.getLong(2))
                );
                transaccionInternas.add(transaccionInterna);
            }while(cursor.moveToNext());
            return transaccionInternas;
        }
        else{
            return null;
        }
    }

    private Empleado getEmpleadoById(Long Id) {
        Cursor cursor=db.rawQuery("select * from Empleado where Id="+Id,null);
        if(cursor.moveToFirst())
        {
            Empleado empleado=new Empleado(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    getUsuarioById(cursor.getLong(4)),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    getTransaccionesByEmpleadoId(cursor.getLong(9))
            );
            return empleado;
        }
        else
        {
            return null;
        }
    }

    private List<Transaccion> getTransaccionesByEmpleadoId(Long Id){
        Cursor cursor=db.rawQuery("select * from TransaccionEXTCAB where IdEmisor="+Id+" or IdReceptor="+Id+";",null);
        List<Transaccion> retorno=new ArrayList<>();
        if(cursor.moveToFirst())
        {
            List<TransaccionExterna> transaccionExternas=new ArrayList<>();
            do {
                TransaccionExterna transaccionExterna=new TransaccionExterna(
                        cursor.getLong(0),
                        LocalDate.parse(cursor.getString(3)),
                        LocalDate.parse(cursor.getString(6)),
                        getTransaccionExternaUnitariaByTransaccionExternaId(cursor.getLong(0)),
                        getDocumentosByTransaccionExternaId(cursor.getLong(4)),
                        getRegistrosContablesByDocumentoId(cursor.getLong(4))
                );
            }while(cursor.moveToNext());
            retorno.addAll(transaccionExternas);
        }
        cursor.close();
        cursor=db.rawQuery("select * from TransaccionINTCAB where IdEncargado="+Id+";",null);
        if(cursor.moveToFirst())
        {
            List<TransaccionInterna> transaccionInternas=new ArrayList<>();
            do {
                TransaccionInterna transaccionInterna =new TransaccionInterna(
                        cursor.getLong(0),
                        LocalDate.parse(cursor.getString(3)),
                        LocalDate.parse(cursor.getString(4)),
                        getTransaccionInternaUnitariaByTransaccionInternaId(cursor.getLong(0)),
                        getAlmacenById(cursor.getLong(1)),
                        getEmpleadoById(cursor.getLong(2))
                );
            }while(cursor.moveToNext());
            retorno.addAll(transaccionInternas);
        }
        return retorno;
    }

    private Usuario getUsuarioById(long aLong) {
        Cursor cursor=db.rawQuery("select * from Usuario where Id="+aLong,null);
        if(cursor.moveToFirst())
        {
            Usuario usuario=new Usuario(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    LocalDate.parse(cursor.getString(5)),
                    cursor.getString(6),
                    cursor.getString(7)
            );
            return usuario;
        }
        else
        {
            return null;
        }
    }

    public Almacen getAlmacenById(Long Id)
    {
        Cursor cursor=db.rawQuery("select * from Almacen where Id="+Id,null);
        if(cursor.moveToFirst())
        {
            Almacen almacen=new Almacen(
                    cursor.getLong(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    getLicenciaDeFuncionamientoById(cursor.getLong(3)),
                    getEmpleadosByAlmacenId(cursor.getLong(0)),
                    getEstanteriasByAlmacenId(cursor.getLong(0))
            );
            return almacen;
        }
        else
        {
            return null;
        }
    }

    private List<Estanteria> getEstanteriasByAlmacenId(Long Id) {
        Cursor cursor=db.rawQuery("select * from Estanteria where IdAlmacen="+Id+";",null);
        if(cursor.moveToFirst())
        {
            List<Estanteria> estanterias=new ArrayList<>();
            do {
                Estanteria estanteria=new Estanteria(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        getBloqueEstanteriaByEstanteriaId(cursor.getLong(3))
                );
                estanterias.add(estanteria);
            }while(cursor.moveToNext());
            return estanterias;
        }
        else
        {
            return null;
        }
    }

    private List<Empleado> getEmpleadosByAlmacenId(Long Id) {
        Cursor cursor= db.rawQuery("select * from Empleado where IdAlmacen="+Id+";",null);
        if(cursor.moveToFirst())
        {
            List<Empleado> empleados=new ArrayList<>();
            do {
                Empleado empleado=new Empleado(
                        cursor.getLong(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        getUsuarioById(cursor.getLong(4)),
                        cursor.getString(6),
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(9),
                        getTransaccionesByEmpleadoId(cursor.getLong(9))
                );
                empleados.add(empleado);
            }while(cursor.moveToNext());
            return empleados;
        }
        else {
            return null;
        }
    }

    private LicenciaDeFuncionamiento getLicenciaDeFuncionamientoById(long aLong) {
        Cursor cursor= db.rawQuery("select * from LicenciaDeFuncionamiento where NumeroLicencia="+aLong+";",null);
        if(cursor.moveToFirst())
        {
            LicenciaDeFuncionamiento licenciaDeFuncionamiento=new LicenciaDeFuncionamiento(
                cursor.getLong(0),
                cursor.getString(1),
                LocalDate.parse(cursor.getString(2))
            );
            return licenciaDeFuncionamiento;
        }
        else
        {
            return null;
        }
    }


    public List<TransaccionInternaUnitaria> getTransaccionInternaUnitariaByTransaccionInternaId(Long Id)
    {
        Cursor cursor=db.rawQuery("select * from TransaccionINTDET where IdTransaccionINTCAB="+Id,null);
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
                    getProductosByContenedorId(cursor.getLong(0)),
                    getBloqueEstanteriaByContenedorId(cursor.getLong(0))
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
        Cursor cursor=db.rawQuery("select IdContenedor from BloqueEstanteriaContenedor where IdBloqueEstanteria="+Id,null);
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
        Cursor cursor=db.rawQuery("select * from BloqueEstanteriaContenedor where IdContenedor="+Id,null);
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
                        cursor.getString(7),
                        cursor.getString(8),
                        cursor.getString(1),
                        cursor.getString(2),
                        Boolean.parseBoolean(cursor.getString(3)),
                        getTarifariosByProductoId(cursor.getLong(0)),
                        getTarifarioActual(getTarifariosByProductoId(cursor.getLong(0))));
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
