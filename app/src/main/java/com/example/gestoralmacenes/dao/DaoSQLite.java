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
            ,'Productos de panaderia y reposteria','Electrónicos','Electrodomésticos','Artículos de cocina','Accesorios para el hogar','Juguetes','Ropa y calzado','Herramientas','Muebles','Decoración del hogar')) NOT NULL
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

            for (String query : sql) {
                sqLiteDatabase.execSQL(query);
            }
            sqLiteDatabase.execSQL("insert into Usuario values(1,'admin','admin','Administrador','admin',date('now'),'Activo','127.0.0.1')");
            for (String insert : generarValores()) {
                Log.d("Insert",insert);
                sqLiteDatabase.execSQL(insert);
            }
    }
    private String[] generarValores()
    {
        return new String[]{"""
                -- Usuario
                INSERT INTO Usuario (Id, NombreUsuario, Contraseña, TipoUsuario, Nombre, FechaRegistro, TipoActividad, DireccionIp)
                VALUES (2, 'john_doe', 'SecurePass123', 'Empleado', 'John Doe', '2023-01-15', 'Activo', '192.168.0.1'),
                        (3, 'jane_smith', 'StrongPwd456', 'Empleado', 'Jane Smith', '2023-02-20', 'Activo', '192.168.0.2'),
                            (4, 'bob_jones', 'Secret123', 'Administrador', 'Bob Jones', '2023-03-10', 'Activo', '192.168.0.3'),
                            (5, 'alice_jackson', 'SecurePass789', 'Empleado', 'Alice Jackson', '2023-04-05', 'Activo', '192.168.0.4'),
                            (6, 'charlie_brown', 'CharliePass', 'Empleado', 'Charlie Brown', '2023-05-12', 'Activo', '192.168.0.5'),
                            (7, 'emily_davis', 'Password123', 'Administrador', 'Emily Davis', '2023-06-18', 'Activo', '192.168.0.6'),
                            (8, 'david_miller', 'DavidPass456', 'Empleado', 'David Miller', '2023-07-25', 'Activo', '192.168.0.7'),
                            (9, 'susan_white', 'Susan123', 'Empleado', 'Susan White', '2023-08-30', 'Activo', '192.168.0.8'),
                            (10, 'george_smith', 'Secret789', 'Administrador', 'George Smith', '2023-09-15', 'Activo', '192.168.0.9'),
                            (11, 'olivia_martin', 'OliviaPass', 'Empleado', 'Olivia Martin', '2023-10-22', 'Activo', '192.168.0.10'),
                            (12, 'james_jackson', 'James456', 'Empleado', 'James Jackson', '2023-11-01', 'Activo', '192.168.0.11'),
                            (13, 'linda_davis', 'LindaPass123', 'Administrador', 'Linda Davis', '2023-12-05', 'Activo', '192.168.0.12'),
                            (14, 'michael_smith', 'MichaelPass', 'Empleado', 'Michael Smith', '2024-01-10', 'Activo', '192.168.0.13'),
                            (15, 'sophia_white', 'Sophia789', 'Empleado', 'Sophia White', '2024-02-15', 'Activo', '192.168.0.14');
                    """, """                
                -- Accesos
                INSERT INTO Accesos (Id, IdUsuario, FechaIngreso, FechaSalida, NombreInput, ContraseñaInput, Estado)
                VALUES (1, 1, '2023-01-15 08:30:00', '2023-01-15 17:00:00', 'Login', 'P@ssw0rd', 'Nombre y contraseña incorrecta');
                 """, """                 
                -- LicenciaDeFuncionamiento
                INSERT INTO LicenciaDeFuncionamiento (NumeroLicencia, Municipalidad, FechaEmision)
                VALUES (1 ,'Municipalidad Central', '2023-01-10'),
                           (2, 'Municipalidad Norte', '2023-02-15'),
                           (3, 'Municipalidad Sur', '2023-03-20'),
                           (4, 'Municipalidad Este', '2023-04-25'),
                           (5, 'Municipalidad Oeste', '2023-05-30'),
                           (6, 'Municipalidad Centro', '2023-06-05'),
                           (7, 'Municipalidad Occidente', '2023-07-10'),
                           (8, 'Municipalidad Oriente', '2023-08-15'),
                           (9, 'Municipalidad Noroeste', '2023-09-20'),
                           (10, 'Municipalidad Suroeste', '2023-10-25');
                 """, """                 
                -- Almacen
                INSERT INTO Almacen (Id , NumeroEstanteria, Ubicacion, nroLicencia)
                VALUES (1, 101, 'Depósito A1', 1),
                    (2, 102, 'Depósito B1', 2),
                    (3, 103, 'Depósito C1', 3),
                    (4, 104, 'Depósito A2', 4),
                    (5, 105, 'Depósito B2', 5),
                    (6, 106, 'Depósito C2', 6),
                    (7, 107, 'Depósito A3', 7),
                    (8, 108, 'Depósito B3', 8),
                    (9, 109, 'Depósito C3', 9),
                    (10, 110, 'Depósito A4', 10);
                 """, """
                -- Estanteria
                INSERT INTO Estanteria (IdAlmacen, NroLote, Categoria)
                VALUES (1, 'Lote A1', 'Productos para el hogar'),
                    (1, 'Lote B1', 'Electrodomésticos'),
                    (1, 'Lote C1', 'Artículos de cocina'),
                    (2, 'Lote A2', 'Electrónicos'),
                    (2, 'Lote B2', 'Accesorios para el hogar'),
                    (3, 'Lote A3', 'Juguetes'),
                    (3, 'Lote B3', 'Ropa y calzado'),
                    (4, 'Lote A4', 'Herramientas'),
                    (4, 'Lote B4', 'Muebles'),
                    (5, 'Lote A5', 'Decoración del hogar');
                    """, """                 
                -- Fabricante
                INSERT INTO Fabricante (Id, Nombre, Ubicacion, Contacto)
                VALUES (1, 'TechGadget Inc.', 'Silicon Valley', 'Steve Jobs'),
                        (2, 'Gadget Supplier', 'Asia', 'John Supplier'),
                        (3, 'ElectroTech Ltd.', 'Europe', 'Emily Tech'),
                            (4, 'Gizmo Innovations', 'North America', 'George Gizmo'),
                            (5, 'Global Electronics Co.', 'South America', 'Grace Global'),
                            (6, 'TechMaster', 'Australia', 'Tom Tech'),
                            (7, 'Innovative Gadgets', 'Africa', 'Ivy Innovator'),
                            (8, 'Smart Devices Corp.', 'Antarctica', 'Sam Smart'),
                            (9, 'Future Tech Solutions', 'Middle East', 'Fiona Future'),
                            (10, 'EcoFriendly Electronics', 'Greenland', 'Ella Eco');
                """, """                  
                -- Contenedor
                INSERT INTO Contenedor (Id, CapacidadMaxima, CapacidadActual, Largo, Altura, Profundidad, Peso)
                VALUES (1, 500, 200, 3.5, 2.0, 2.0, 150),
                        (2, 100, 50, 2.0, 1.5, 1.5, 100),
                            (3, 300, 150, 2.5, 1.8, 1.8, 120),
                            (4, 200, 100, 2.0, 1.2, 1.2, 80),
                            (5, 400, 180, 3.0, 1.5, 1.5, 130),
                            (6, 150, 80, 1.5, 1.0, 1.0, 60),
                            (7, 250, 120, 2.2, 1.4, 1.4, 90),
                            (8, 350, 160, 2.8, 1.7, 1.7, 110),
                            (9, 450, 200, 3.2, 1.9, 1.9, 140),
                            (10, 120, 60, 1.0, 0.8, 0.8, 50);
                 """, """                
                -- Producto
                INSERT INTO Producto (Id, Nombre, Descripcion, Garantia, IdAlmacen, IdFabricante, IdContenedor, Unidad_Medida, Tipo)
                VALUES (1, 'Smartphone X', 'Potente y elegante', 1, 1, 1, 1, 'Unidades', 'No Perecedero'),
                        (2, 'Pan Bembo' , 'Pan de molde', 1, 1, 2, 2, 'Unidades', 'Perecedero'),
                            (3, 'Laptop Pro', 'Potente para trabajo y entretenimiento', 2, 2, 3, 3, 'Unidades', 'No Perecedero'),
                            (4, 'Leche Fresca', 'Leche pasteurizada', 1, 2, 4, 4, 'Litros', 'Perecedero'),
                            (5, 'Cámara de Seguridad', 'Vigilancia de alta definición', 1, 3, 5, 5, 'Unidades', 'No Perecedero'),
                            (6, 'Manzanas', 'Manzanas frescas', 1, 3, 6, 6, 'Kilogramos', 'Perecedero'),
                            (7, 'Fitness Tracker', 'Monitor de actividad física', 2, 4, 7, 7, 'Unidades', 'No Perecedero'),
                            (8, 'Yogur Natural', 'Yogur sin azúcar', 1, 4, 8, 8, 'Unidades', 'Perecedero'),
                            (9, 'Drone Explorer', 'Drone con cámara HD', 2, 5, 9, 9, 'Unidades', 'No Perecedero'),
                            (10, 'Queso Cheddar', 'Queso cheddar en lonchas', 1, 5, 10, 10, 'Kilogramos', 'Perecedero');
                        
                   """, """                          
                -- BloqueEstanteria
                INSERT INTO BloqueEstanteria (Id, Largo, Altura, Profundidad, PesoMaximo, PesoActual, IdEstanteria, Fila, Columna, Cara)
                VALUES (1, 3.0, 1.8, 1.8, 80, 40, 1, 1, 1, 1),
                       (2, 3.0, 1.8, 1.8, 80, 40, 1, 1, 2, 1),
                           (3, 2.5, 1.5, 1.5, 60, 30, 2, 2, 1, 1),
                           (4, 2.5, 1.5, 1.5, 60, 30, 2, 2, 2, 1),
                           (5, 3.2, 1.9, 1.9, 90, 50, 3, 3, 1, 2),
                           (6, 3.2, 1.9, 1.9, 90, 50, 3, 3, 2, 2),
                           (7, 2.0, 1.2, 1.2, 40, 20, 4, 4, 1, 1),
                           (8, 2.0, 1.2, 1.2, 40, 20, 4, 4, 2, 1),
                           (9, 2.8, 1.7, 1.7, 70, 40, 5, 5, 1, 2),
                           (10, 2.8, 1.7, 1.7, 70, 40, 5, 5, 2, 2);;
                 """,
                """
               -- BloqueEstanteriaContenedor
                   INSERT INTO BloqueEstanteriaContenedor (IdBloqueEstanteria, IdContenedor)
                     VALUES (1, 1),
                              (1, 2),
                                  (2, 3),
                                  (2, 4),
                                  (3, 5),
                                  (3, 6),
                                  (4, 7),
                                  (4, 8),
                                  (5, 9),
                                  (5, 10);
                """,
                """
                --Rango('Jefe de almacen','Ayudante de jefe de almacen','Trabajador','Contador','Auditor','Consultor','Dueño')
                --NivelEstudio IN('Basico','Bachiller','Tecnico','Profesional','Doctorado')
                INSERT INTO Empleado (Nombre, Rango, DescripcionResponsabilidad, IdUsuario, IdAlmacen, DNI, NivelEstudio, Telefono, Correo)
                VALUES ('NombreEmpleado', 'Trabajador', 'Descripción de responsabilidad', 1, 2, '12345678', 'Bachiller', '123-456-7890', 'correo@ejemplo.com'),
                ('OtroEmpleado', 'Auditor', 'Otra descripción de responsabilidad', 2, 3, '87654321', 'Profesional', '987-654-3210', 'otro_correo@ejemplo.com'),
                    ('Empleado3', 'Jefe de almacen', 'Responsabilidades adicionales', 3, 4, '56781234', 'Doctorado', '567-123-8901', 'empleado3@ejemplo.com'),
                    ('NuevoEmpleado', 'Ayudante de jefe de almacen', 'Coordinación de actividades', 4, 5, '34567890', 'Tecnico', '678-901-2345', 'nuevo_empleado@ejemplo.com');
                 """, """                 
                -- Proveedor
                INSERT INTO Proveedor (Id, Nombre, Ubicacion, Contacto, TelefonoContacto, TelefonoProveedor, GiroProveedor, RUUC)
                VALUES (1, 'Gadget Supplier', 'Asia', 'John Supplier', '123-456-7890', '987-654-3210', 'Electrónicos', '123456789-3'),
                 (2, 'ElectroTech Ltd.', 'Europe', 'Emily Tech', '345-678-9012', '876-543-2109', 'Electrónicos', '987654321-1'),
                    (3, 'TechMaster', 'Australia', 'Tom Tech', '567-890-1234', '765-432-1098', 'Tecnología Avanzada', '876543210-9'),
                    (4, 'Innovative Gadgets', 'Africa', 'Ivy Innovator', '789-012-3456', '654-321-0987', 'Innovación Tecnológica', '765432109-8');
                 """, """                 
                -- Organizacion
                --TipoOrganizacion IN('SAC','SA','Sin fines de lucro','Publica')
                --Sector IN('Primario','Secundario','Terciario')
                INSERT INTO Organizacion (Id, Nombre, RUUC, TipoOrganizacion, NroEmpleados, Sector)
                VALUES (1, 'TechCorp', '123456789-4', 'SA', 5000, 'Secundario'),
                    (2, 'FoodCo', '987654321-2', 'SA', 2000, 'Primario'),
                    (3, 'GreenTech', '876543210-1', 'SAC', 3000, 'Secundario'),
                    (4, 'FashionHub', '765432109-9', 'SA', 1500, 'Terciario');
                  """, """                
                -- Cliente
                INSERT INTO Cliente (Id, Nombre, Apellido, DNI, Telefono, Direccion, Correo, IdOrganizacion)
                VALUES (1, 'Eva Rodriguez', 'Pérez', '87654321', '555-1234', 'Calle Principal 123', 'eva.rodriguez@example.com', 2),
                 (2, 'Carlos Gómez', 'Fernández', '76543210', '555-5678', 'Avenida Central 456', 'carlos.gomez@example.com', 3),
                    (3, 'Laura Pérez', 'García', '65432109', '555-9012', 'Calle Secundaria 789', 'laura.perez@example.com', 1),
                    (4, 'Alejandro López', 'Martínez', '54321098', '555-3456', 'Avenida Principal 012', 'alejandro.lopez@example.com', 4);
                """, """                  
                -- Emisor
                INSERT INTO Emisor (Id, IdCliente, idEmpleado, IdProveedor, TipoEmisor)
                VALUES (1,2, 1, 2, 'Cliente'),
                   (2, 3, 2, 1, 'Cliente'),
                    (3, 1, 3, 3, 'Proveedor'),
                    (4, 4, 4, 2, 'Trabajador');
                """, """                  
                -- Receptor
                INSERT INTO Receptor (Id ,IdCliente, IdEmpleado, IdProveedor, TipoReceptor)
                VALUES (1 ,2, 1, 2, 'Cliente'),
                    (2, 3, 2, 1, 'Cliente'),
                    (3, 1, 3, 3, 'Proveedor'),
                    (4, 4, 4, 2, 'Trabajador');;
                """, """                  
                -- Documento
                INSERT INTO Documento (NroOrden, FechaEmision, FechaVencimiento, IdEmisor, IdReceptor, Ubicacion, TotalPaquetes, TipoDocumento)
                VALUES (1001, '2023-01-20', '2023-01-25', 1, 1, 'Almacén TechCorp', 5, 'Pedido'),
                 (1002, '2023-02-15', '2023-02-20', 2, 2, 'Almacén FoodCo', 8, 'Pedido'),
                    (1003, '2023-03-10', '2023-03-15', 3, 3, 'Almacén GreenTech', 10, 'Pedido'),
                    (1004, '2023-04-05', '2023-04-10', 4, 4, 'Almacén FashionHub', 7, 'Pedido');;
                """, """  
                --TransactionEXTCAB
                INSERT INTO TransaccionEXTCAB (Id, IdEmisor, IdReceptor, FechaInicio, IdDocumento, TipoTransaccion, FechaPrevista, EsTransaccionFinanciera, IdRegistroContableCAB) 
                VALUES(1, 1, 1, '2023-01-20', 1001, 'Entrada', '2023-01-25', 1, 1),
                    (2, 2, 2, '2023-02-15', 1002, 'Entrada', '2023-02-20', 1, 2),
                    (3, 3, 3, '2023-03-10', 1003, 'Salida', '2023-03-15', 0, 3),
                    (4, 4, 4, '2023-04-05', 1004, 'Entrada', '2023-04-10', 1, 4);
                --TransaccionEXTDET
                """, """  
                INSERT INTO TransaccionEXTDET (Id ,Secuencia, IdTransaccionEXTCAB, Cantidad, Unidad_medida, Longitud, Profundidad, Altura, IdContenedor)
                VALUES(1 ,1, 1, 5, 'Unidades', 0, 0, 0, 1),
                    (2, 2, 2, 8, 'Unidades', 0, 0, 0, 2),
                    (3, 3, 3, 10, 'Unidades', 0, 0, 0, 3),
                    (4, 4, 4, 7, 'Unidades', 0, 0, 0, 4);
                """, """  
                --TransaccionINTCAB
                INSERT INTO TransaccionINTCAB (IdAlmacen, IdEncargado, FechaInicio, FechaFinalizado)
                VALUES(1, 1, '2023-01-20', '2023-01-25'),
                    (2, 2, '2023-02-15', '2023-02-20'),
                    (3, 3, '2023-03-10', '2023-03-15'),
                    (4, 4, '2023-04-05', '2023-04-10');
                """, """  
                --TransaccionINTDET
                INSERT INTO TransaccionINTDET (Id ,Secuencia, IdTransaccionINTCAB, IdEstanteriaOrigen, IdContenedor, IdBloqueEstanteriaOrigen, IdBloqueEstanteriaDestino, IdEstanteriaDestino)
                VALUES(1, 1, 1, 1, 1, 1, 1, 1),
                    (2, 2, 2, 2, 2, 2, 2, 2),
                    (3, 3, 3, 3, 3, 3, 3, 3),
                    (4, 4, 4, 4, 4, 4, 4, 4);
                """,
                """  
                INSERT INTO Tarifario (IdProducto, PrecioUnitario, Cantidad, TasaImpositiva, Descuento, FechaVencimiento)
                VALUES(1, 1000, 5, 0.18, 0, '2023-01-25'),
                       (2, 5, 1, 0.18, 0, '2023-01-25'),
                           (3, 800, 3, 0.15, 10, '2023-02-20'),
                           (4, 20, 2, 0.18, 5, '2023-03-15'),
                           (5, 1500, 1, 0.20, 0, '2023-04-10');
                """,
                """
                    INSERT INTO RegistroContableCAB (NroOrden, Año)
                    VALUES (1, '2023-11-19'),
                        (2, '2023-11-19'),
                        (3, '2023-11-19'),
                        (4, '2023-11-19');
                """, """
                INSERT INTO RegistroContableDET (IdRegistroContableCAB)
                VALUES (1),
                        (2),
                        (3),
                        (4);
                """,
                """
                INSERT INTO FechaContable (Fecha, IdRegistroContableDET)
                VALUES ('2023-11-19', 1),
                    ('2023-11-20', 2),
                    ('2023-11-21', 3),
                    ('2023-11-22', 4);
                -- Aquí asumo que estás relacionando con el registro que acabas de insertar en RegistroContableDET
                """,
                """
                INSERT INTO Descripcion (Valor, IdRegistroContableDET)
                VALUES ('Descripción de ejemplo', 1),
                    ('Otra descripción', 2),
                    ('Descripción adicional', 3),
                    ('Texto descriptivo', 4); -- Aquí asumo que estás relacionando con el registro que acabas de insertar en RegistroContableDET
                ""","""
                INSERT INTO Cuenta (TipoCuenta, Valor, IdRegistroContableDET)
                VALUES ('Debe', 100.50, 1),
                    ('Haber', 75.25, 2),
                    ('Debe', 150.75, 3),
                    ('Haber', 50.50, 4);; -- Aquí asumo que estás relacionando con el registro que acabas de insertar en RegistroContableDET 
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
