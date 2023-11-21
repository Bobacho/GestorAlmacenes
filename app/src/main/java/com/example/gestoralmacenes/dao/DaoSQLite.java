package com.example.gestoralmacenes.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class DaoSQLite extends SQLiteOpenHelper {
    public DaoSQLite(@Nullable Context context)
    {
        super(context,"Usuario",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String []sql= {"""
            CREATE TABLE Usuario(
            Id INTEGER PRIMARY KEY AUTOINCREMENT,
            NombreUsuario TEXT NOT NULL,
            Contraseña TEXT NOT NULL,
            TipoUsuario TEXT CHECK(TipoUsuario IN ('Administrador','Empleado','SYSDBA','Consultor','Auditor')) NOT NULL,
            Nombre TEXT NOT NULL,
            FechaRegistro DATE NOT NULL,
            TipoActividad TEXT CHECK (TipoActividad IN('Activo','Inactivo','Bloqueado')) NOT NULL,
            DireccionIp TEXT NOT NULL
            );
        """, """
    CREATE TABLE Accesos(
            Id INTEGER PRIMARY KEY AUTOINCREMENT,
            IdUsuario INTEGER NOT NULL,
            FechaIngreso DATE NOT NULL,
            FechaSalida DATE NOT NULL,
            NombreInput TEXT NOT NULL,
            ContraseñaInput TEXT NOT NULL,
            Estado TEXT CHECK(Estado IN('Nombre Incorrecto','Contraseña Incorrecta','Nombre y contraseña incorrecta','Numero de intentos excedidos')) NOT NULL,
            Foreign Key(IdUsuario) REFERENCES Usuario(Id)
            );
    """,
                """
CREATE TABLE LicenciaDeFuncionamiento(
            NumeroLicencia INTEGER PRIMARY KEY AUTOINCREMENT,
            Municipalidad TEXT NOT NULL,
            FechaEmision DATE NOT NULL
            );
    """,
                """
Create TABLE Fabricante(
            Id INTEGER PRIMARY KEY AUTOINCREMENT,
            Nombre TEXT NOT NULL,
            Ubicacion TEXT NOT NULL,
            Contacto TEXT NOT NULL
            );

""",
                """
                    CREATE TABLE Contenedor(
            Id INTEGER PRIMARY KEY AUTOINCREMENT,
            CapacidadMaxima INTEGER NOT NULL,
            CapacidadActual INTEGER NOT NULL,
            Largo REAL NOT NULL,
            Altura REAL NOT NULL,
            Profundidad REAL NOT NULL,
            Peso REAL NOT NULL
            );
        """,
                """
            CREATE TABLE Almacen(
            Id INTEGER PRIMARY KEY AUTOINCREMENT,
            NumeroEstanteria INTEGER NOT NULL,
            Ubicacion TEXT NOT NULL,
            nroLicencia INTEGER NOT NULL,
            FOREIGN KEY (nroLicencia) REFERENCES LicenciaDeFuncionamiento(NumeroLicencia)
            );
""",
                """
                CREATE TABLE Producto(
            Id INTEGER PRIMARY KEY AUTOINCREMENT,
            Nombre TEXT NOT NULL,
            Descripcion TEXT NOT NULL,
            Garantia BOOLEAN NOT NULL,
            IdAlmacen INTEGER NOT NULL,
            IdFabricante INTEGER NOT NULL,
            IdContenedor INTEGER NOT NULL,
            Unidad_Medida TEXT CHECK(Unidad_Medida IN ('Kilogramos','Litros','Unidades','Cajas','Paquetes','Bolsas','Metros','Centimetros','Milimetros','Pulgadas','Galones','Libras','Onzas')) NOT NULL,
            Tipo TEXT CHECK(Tipo IN ('Perecedero','No Perecedero')) NOT NULL,
            FOREIGN KEY (IdAlmacen) REFERENCES Almacen(Id),
            FOREIGN KEY (IdFabricante) REFERENCES Fabricante(Id),
            FOREIGN KEY (IdContenedor) REFERENCES Contenedor(Id)
            );
            """,
                """
            CREATE TABLE Estanteria(
            Id INTEGER PRIMARY KEY AUTOINCREMENT,
            IdAlmacen INTEGER NOT NULL,
            NroLote TEXT NOT NULL,
            Categoria TEXT CHECK( Categoria IN ('Alimentos y bebidas','Productos de cuidado personal','Productos para el hogar','Productos de conveniencia','Productos de cuidado de mascotas','Productos congelados','Bebidas alcoholicas'
            ,'Productos de panaderia y reposteria','Electronicos'))
            );
            """,
            """
                CREATE TABLE BloqueEstanteriaContenedor(
            IdBloqueEstanteria INTEGER NOT NULL,
            IdContenedor INTEGER NOT NULL,
            FOREIGN KEY (IdBloqueEstanteria) REFERENCES BloqueEstanteria(Id),
            FOREIGN KEY (IdContenedor) REFERENCES Contenedor(Id)
            );
            """,
                """
            CREATE TABLE BloqueEstanteria(
            Id INTEGER PRIMARY KEY AUTOINCREMENT,
            Largo REAL NOT NULL,
            Altura REAL NOT NULL,
            Profundidad REAL NOT NULL,
            PesoMaximo REAL NOT NULL,
            PesoActual REAL NOT NULL,
            IdEstanteria INTEGER NOT NULL,
            Fila INTEGER NOT NULL,
            Columna INTEGER NOT NULL,
            Cara INTEGER NOT NULL,
            FOREIGN KEY (IdEstanteria) REFERENCES Estanteria(Id)
            );
            """,
                """
            CREATE TABLE Empleado(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            Nombre TEXT NOT NULL,
            Rango TEXT CHECK (Rango IN('Jefe de almacen','Ayudante de jefe de almacen','Trabajador','Contador','Auditor','Consultor','Dueño')) NOT NULL,
            DescripcionResponsabilidad TEXT NOT NULL,
            IdUsuario INTEGER NOT NULL,
            IdAlmacen INTEGER NOT NULL,
            DNI TEXT NOT NULL,
            NivelEstudio TEXT CHECK(NivelEstudio IN('Basico','Bachiller','Tecnico','Profesional','Doctorado')),
            Telefono TEXT NOT NULL,
            Correo TEXT NOT NULL,
            FOREIGN KEY (IdUsuario) REFERENCES Usuario(Id),
            FOREIGN KEY (IdAlmacen) REFERENCES Almacen(Id)
            );
            
            """,
                """
            CREATE TABLE Proveedor(
            Id Integer Primary Key AUTOINCREMENT,
            Nombre TEXT NOT NULL,
            Ubicacion TEXT NOT NULL,
            Contacto TEXT NOT NULL,
            TelefonoContacto TEXT NOT NULL,
            TelefonoProveedor TEXT NOT NULL,
            GiroProveedor TEXT NOT NULL,
            RUUC TEXT NOT NULL
            );""",
                """
            CREATE TABLE Organizacion(
            Id Integer Primary Key AUTOINCREMENT,
            Nombre TEXT NOT NULL,
            RUUC TEXT NOT NULL,
            TipoOrganizacion TEXT CHECK(TipoOrganizacion IN('SAC','SA','Sin fines de lucro','Publica')) NOT NULL,
            NroEmpleados Integer NOT NULL,
            Sector TEXT CHECK(Sector IN('Primario','Secundario','Terciario')) NOT NULL
            );
            """,
                """
            CREATE TABLE Cliente(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            Nombre TEXT NOT NULL,
            Apellido TEXT NOT NULL,
            DNI TEXT NOT NULL,
            Telefono TEXT NOT NULL,
            Direccion TEXT NOT NULL,
            Correo TEXT NOT NULL,
            IdOrganizacion INTEGER NOT NULL,
            FOREIGN KEY (IdOrganizacion) REFERENCES Organizacion(Id)
            );
            """,
                """
            CREATE TABLE Emisor(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            IdCliente INTEGER NOT NULL,
            idEmpleado INTEGER NOT NULL,
            IdProveedor INTEGER NOT NULL,
            TipoEmisor TEXT CHECK(TipoEmisor IN('Cliente','Proveedor','Trabajador')) NOT NULL,
            FOREIGN KEY (IdCliente) REFERENCES Cliente(Id),
            FOREIGN KEY (idEmpleado) REFERENCES Empleado(Id),
            FOREIGN KEY (IdProveedor) REFERENCES Proveedor(Id)
            );
            """,
                """
            CREATE TABLE Receptor(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            IdCliente INTEGER NOT NULL,
            IdEmpleado INTEGER NOT NULL,
            IdProveedor INTEGER NOT NULL,
            TipoReceptor TEXT CHECK(TipoReceptor IN('Cliente','Proveedor','Trabajador')) NOT NULL
            );""",
                """
            CREATE TABLE Documento(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            NroOrden Integer NOT NULL,
            FechaEmision DATE NOT NULL,
            FechaVencimiento DATE NOT NULL,
            IdEmisor INTEGER NOT NULL,
            IdReceptor INTEGER NOT NULL,
            Ubicacion TEXT NOT NULL,
            TotalPaquetes INTEGER NOT NULL,
            TipoDocumento TEXT CHECK(TipoDocumento IN('Pedido','Hoja de solitud de mercancia','Nota de entrega','Hoja de recepcion')) NOT NULL,
            FOREIGN KEY (IdEmisor) REFERENCES Emisor(Id),
            FOREIGN KEY (IdReceptor) REFERENCES Receptor(Id)
            );
            """,
                """
            CREATE TABLE RegistroContableCAB(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            NroOrden Integer NOT NULL,
            Año DATE NOT NULL
            );
            ""","""
            CREATE TABLE RegistroContableDET(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            IdRegistroContableCAB INTEGER NOT NULL,
            FOREIGN KEY (IdRegistroContableCAB) REFERENCES RegistroContableCAB(Id)
            );""","""
            CREATE TABLE FechaContable(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            Fecha DATE NOT NULL,
            IdRegistroContableDET INTEGER NOT NULL,
            FOREIGN KEY (IdRegistroContableDET) REFERENCES RegistroContableDET(Id)
            );""","""
            CREATE TABLE Descripcion(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            Valor TEXT NOT NULL,
            IdRegistroContableDET INTEGER NOT NULL,
            FOREIGN KEY (IdRegistroContableDET) REFERENCES RegistroContableDET(Id)
            );""","""
            CREATE TABLE Cuenta(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            TipoCuenta TEXT CHECK(TipoCuenta IN('Debe','Haber')) NOT NULL,
            Valor REAL NOT NULL,
            IdRegistroContableDET INTEGER NOT NULL,
            FOREIGN KEY (IdRegistroContableDET) REFERENCES RegistroContableDET(Id)
            );""",
            """
            CREATE TABLE TransaccionEXTCAB(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            IdEmisor INTEGER NOT NULL,
            IdReceptor INTEGER NOT NULL,
            FechaInicio DATE NOT NULL,
            IdDocumento INTEGER NOT NULL,
            TipoTransaccion TEXT CHECK(TipoTransaccion IN ('Entrada','Salida')) NOT NULL,
            FechaPrevista DATE NOT NULL,
            EsTransaccionFinanciera BOOLEAN NOT NULL,
            IdRegistroContableCAB INTEGER NOT NULL,
            FOREIGN KEY (IdEmisor) REFERENCES Emisor(Id),
            FOREIGN KEY (IdReceptor) REFERENCES Receptor(Id),
            FOREIGN KEY (IdDocumento) REFERENCES Documento(Id),
            FOREIGN KEY (IdRegistroContableCAB) REFERENCES RegistroContableCAB(Id)
            );""","""
            CREATE TABLE TransaccionEXTDET(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            Secuencia INTEGER NOT NULL,
            IdTransaccionEXTCAB INTEGER NOT NULL,
            Cantidad INTEGER NOT NULL,
            Unidad_medida TEXT CHECK(Unidad_medida IN('Kilogramos','Litros','Unidades','Cajas','Paquetes','Bolsas','Metros','Centimetros','Milimetros','Pulgadas','Galones','Libras','Onzas')) NOT NULL,
            Longitud REAL NOT NULL,
            Profundidad REAL NOT NULL,
            Altura REAL NOT NULL,
            IdContenedor INTEGER NOT NULL,
            FOREIGN KEY (IdTransaccionEXTCAB) REFERENCES TransaccionEXTCAB(Id),
            FOREIGN KEY (IdContenedor) REFERENCES Contenedor(Id)
            );""","""
            CREATE TABLE TransaccionINTCAB(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            IdAlmacen INTEGER NOT NULL,
            IdEncargado INTEGER NOT NULL,
            FechaInicio DATE NOT NULL,
            FechaFinalizado DATE NOT NULL,
            Foreign Key (IdAlmacen) REFERENCES Almacen(Id)
            );""","""
            CREATE TABLE TransaccionINTDET(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            Secuencia INTEGER NOT NULL,
            IdTransaccionINTCAB INTEGER NOT NULL,
            IdEstanteriaOrigen INTEGER NOT NULL,
            IdContenedor INTEGER NOT NULL,
            IdBloqueEstanteriaOrigen INTEGER NOT NULL,
            IdBloqueEstanteriaDestino INTEGER NOT NULL,
            IdEstanteriaDestino INTEGER NOT NULL,
            FOREIGN KEY (IdTransaccionINTCAB) REFERENCES TransaccionINTCAB(Id),
            FOREIGN KEY (IdEstanteriaOrigen) REFERENCES Estanteria(Id),
            FOREIGN KEY (IdContenedor) REFERENCES Contenedor(Id),
            FOREIGN KEY (IdBloqueEstanteriaOrigen) REFERENCES BloqueEstanteria(Id),
            FOREIGN KEY (IdBloqueEstanteriaDestino) REFERENCES BloqueEstanteria(Id),
            FOREIGN KEY (IdEstanteriaDestino) REFERENCES Estanteria(Id)
            );""","""
            CREATE TABLE Tarifario(
            Id Integer PRIMARY KEY AUTOINCREMENT,
            IdProducto INTEGER NOT NULL,
            PrecioUnitario REAL NOT NULL,
            Cantidad INTEGER NOT NULL,
            TasaImpositiva REAL NOT NULL,
            Descuento REAL NOT NULL,
            FechaVencimiento DATE NOT NULL,
            FOREIGN KEY (IdProducto) REFERENCES Producto(Id)
            );
            """
        };

        for(String query:sql)
        {
            sqLiteDatabase.execSQL(query);
        }
        sqLiteDatabase.execSQL("insert into Usuario values(1,'admin','admin','Administrador','admin',date('now'),'Activo','127.0.0.1')");
        for(String insert:generarValores())
        {
            sqLiteDatabase.execSQL(insert);
        }

    }
    private String[] generarValores()
    {
        return new String[]{"""
                -- Usuario
                INSERT INTO Usuario (Id, NombreUsuario, Contraseña, TipoUsuario, Nombre, FechaRegistro, TipoActividad, DireccionIp)
                VALUES (2, 'john_doe', 'SecurePass123', 'Empleado', 'John Doe', '2023-01-15', 'Activo', '192.168.0.1');
                """, """                
                -- Accesos
                INSERT INTO Accesos (Id, IdUsuario, FechaIngreso, FechaSalida, NombreInput, ContraseñaInput, Estado)
                VALUES (1, 1, '2023-01-15 08:30:00', '2023-01-15 17:00:00', 'Login', 'P@ssw0rd', 'Nombre y contraseña incorrecta');
                 """, """                 
                -- LicenciaDeFuncionamiento
                INSERT INTO LicenciaDeFuncionamiento (NumeroLicencia, Municipalidad, FechaEmision)
                VALUES (1 ,'Municipalidad Central', '2023-01-10');
                 """, """                 
                -- Almacen
                INSERT INTO Almacen (Id , NumeroEstanteria, Ubicacion, nroLicencia)
                VALUES (1, 101, 'Depósito A1', 1);
                 """, """
                -- Estanteria
                INSERT INTO Estanteria (IdAlmacen, NroLote, Categoria)
                VALUES (1, 'Lote A1', 'Productos para el hogar');
                    """, """                 
                -- Fabricante
                INSERT INTO Fabricante (Id, Nombre, Ubicacion, Contacto)
                VALUES (1, 'TechGadget Inc.', 'Silicon Valley', 'Steve Jobs');
                """, """                  
                -- Contenedor
                INSERT INTO Contenedor (Id, CapacidadMaxima, CapacidadActual, Largo, Altura, Profundidad, Peso)
                VALUES (1, 500, 200, 3.5, 2.0, 2.0, 150);
                 """, """                
                -- Producto
                INSERT INTO Producto (Id, Nombre, Descripcion, Garantia, IdAlmacen, IdFabricante, IdContenedor, Unidad_Medida, Tipo)
                VALUES (1, 'Smartphone X', 'Potente y elegante', 1, 1, 1, 1, 'Unidades', 'No Perecedero');
                   """, """                          
                -- BloqueEstanteria
                INSERT INTO BloqueEstanteria (Id, Largo, Altura, Profundidad, PesoMaximo, PesoActual, IdEstanteria, Fila, Columna, Cara)
                VALUES (1, 3.0, 1.8, 1.8, 80, 40, 1, 1, 1, 1);
                 """, """
                INSERT INTO Empleado (Nombre, Rango, DescripcionResponsabilidad, IdUsuario, IdAlmacen, DNI, NivelEstudio, Telefono, Correo)
                VALUES ('NombreEmpleado', 'Trabajador', 'Descripción de responsabilidad', 1, 2, '12345678', 'Bachiller', '123-456-7890', 'correo@ejemplo.com');
                 """, """                 
                -- Proveedor
                INSERT INTO Proveedor (Id, Nombre, Ubicacion, Contacto, TelefonoContacto, TelefonoProveedor, GiroProveedor, RUUC)
                VALUES (1, 'Gadget Supplier', 'Asia', 'John Supplier', '123-456-7890', '987-654-3210', 'Electrónicos', '123456789-3');
                 """, """                 
                -- Organizacion
                INSERT INTO Organizacion (Id, Nombre, RUUC, TipoOrganizacion, NroEmpleados, Sector)
                VALUES (1, 'TechCorp', '123456789-4', 'SA', 5000, 'Secundario');
                  """, """                
                -- Cliente
                INSERT INTO Cliente (Id, Nombre, Apellido, DNI, Telefono, Direccion, Correo, IdOrganizacion)
                VALUES (1, 'Eva Rodriguez', 'Pérez', '87654321', '555-1234', 'Calle Principal 123', 'eva.rodriguez@example.com', 2);
                """, """                  
                -- Emisor
                INSERT INTO Emisor (Id, IdCliente, idEmpleado, IdProveedor, TipoEmisor)
                VALUES (1,2, 1, 2, 'Cliente');
                """, """                  
                -- Receptor
                INSERT INTO Receptor (Id ,IdCliente, IdEmpleado, IdProveedor, TipoReceptor)
                VALUES (1 ,2, 1, 2, 'Cliente');
                """, """                  
                -- Documento
                INSERT INTO Documento (NroOrden, FechaEmision, FechaVencimiento, IdEmisor, IdReceptor, Ubicacion, TotalPaquetes, TipoDocumento)
                VALUES (1001, '2023-01-20', '2023-01-25', 1, 1, 'Almacén TechCorp', 5, 'Pedido');
                """, """  
                --TransactionEXTCAB
                INSERT INTO TransaccionEXTCAB (Id, IdEmisor, IdReceptor, FechaInicio, IdDocumento, TipoTransaccion, FechaPrevista, EsTransaccionFinanciera, IdRegistroContableCAB) 
                VALUES(1, 1, 1, '2023-01-20', 1001, 'Entrada', '2023-01-25', 1, 1);
                --TransaccionEXTDET
                """, """  
                INSERT INTO TransaccionEXTDET (Id ,Secuencia, IdTransaccionEXTCAB, Cantidad, Unidad_medida, Longitud, Profundidad, Altura, IdContenedor)
                VALUES(1 ,1, 1, 5, 'Unidades', 0, 0, 0, 1); 
                """, """  
                --TransaccionINTCAB
                INSERT INTO TransaccionINTCAB (IdAlmacen, IdEncargado, FechaInicio, FechaFinalizado)
                VALUES(1, 1, '2023-01-20', '2023-01-25');
                """, """  
                --TransaccionINTDET
                INSERT INTO TransaccionINTDET (Id ,Secuencia, IdTransaccionINTCAB, IdEstanteriaOrigen, IdContenedor, IdBloqueEstanteriaOrigen, IdBloqueEstanteriaDestino, IdEstanteriaDestino)
                VALUES(1, 1, 1, 1, 1, 1, 1, 1);
                """,
                """  
                INSERT INTO Tarifario (IdProducto, PrecioUnitario, Cantidad, TasaImpositiva, Descuento, FechaVencimiento)
                VALUES(1, 1000, 5, 0.18, 0, '2023-01-25');
                """,
                """
                    INSERT INTO RegistroContableCAB (NroOrden, Año)
                    VALUES (1, '2023-11-19');
                """, """
                INSERT INTO RegistroContableDET (IdRegistroContableCAB)
                VALUES (1);
                """,
                """
                INSERT INTO FechaContable (Fecha, IdRegistroContableDET)
                VALUES ('2023-11-19', 1); -- Aquí asumo que estás relacionando con el registro que acabas de insertar en RegistroContableDET
                """,
                """
                INSERT INTO Descripcion (Valor, IdRegistroContableDET)
                VALUES ('Descripción de ejemplo', 1); -- Aquí asumo que estás relacionando con el registro que acabas de insertar en RegistroContableDET
                ""","""
                INSERT INTO Cuenta (TipoCuenta, Valor, IdRegistroContableDET)
                VALUES ('Debe', 100.50, 1); -- Aquí asumo que estás relacionando con el registro que acabas de insertar en RegistroContableDET 
                """
        };

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String[] tablas=new String[]{"Usuario","Accesos","LicenciaDeFuncionamiento","Fabricante","Contenedor","Almacen","Producto","Estanteria","BloqueEstanteria","Empleado","Proveedor","Organizacion","Cliente","Emisor","Receptor","Documento","RegistroContableCAB","RegistroContableDET","FechaContable","Descripcion","Cuenta","TransaccionEXTCAB","TransaccionEXTDET","TransaccionINTCAB","TransaccionINTDET","Tarifario"};
        for(String tabla:tablas)
        {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+tabla+";");
        }
        onCreate(sqLiteDatabase);
    }
}
