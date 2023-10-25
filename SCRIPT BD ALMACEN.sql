create database Almacen;
use Almacen;

create table Organizacion(
	id int not null auto_increment primary key,
    nombre varchar(40),
    RUUC char(11),
    tipoOrganizacion Enum("Sin fines de lucro","Corporativas","SAC","SAA","Persona juridica"),
    tamaño int not null,
    sector Enum("Sector publico","Sector privado","Tercer Sector")
);


create table LicenciaDeFuncionamiento(
	numero int not null unique primary key,
	municipalidad varchar(30),
    fechaRealizada datetime
);

create table Almacen(
	id int not null unique primary key,
    numeroEstanteria int not null,
    ubicacion varchar(40),
    nroLicenciadeFuncionamiento int,
    foreign key(nroLicenciadeFuncionamiento) references LicenciaDeFuncionamiento(numero)
);

create table Estanteria(
	id int not null unique primary key,
    idAlmacen int not null,
    nroLote char(4),
    categoria Enum("Alimentos y bebidas","Productos de cuidado personal","Productos para el hogar","Productos de conveniencia","Productos de cuidado de mascotas","Productos congelados","Bebidas alcoholicas","Productos de panaderia y reposteria"),
	foreign key (idAlmacen) references Almacen(id)
);

create table BloqueEstanteria(
	id int not null unique primary key,
    area float unsigned not null,
    altura float unsigned not null,
    idEstanteria int not null,
    capacidad int unsigned not null,
    fila int unsigned not null,
    columna int unsigned not null,
    orientacion int unsigned not null,
    foreign key (idEstanteria) references Estanteria(id)
);

create table Fabricante(
	id int not null unique primary key,
    nombre varchar(40) not null,
    ubicacion varchar(40) not null,
    contacto varchar(15)
);

create table Empleado(
	id int not null unique primary key,
    nombre varchar(40) not null,
    rango Enum("Jefe de almacen","Ayudante de jefe de almacen","Dueño","Trabajador de limpieza","Trabajador de carga"),
    idAlmacen int not null,
    fechaIngreso datetime,
    horarioLaboral varchar(50)
);

create table Usuario(
	id int not null unique auto_increment primary key,
    nombreUsuario varchar(50),
	contraseña text,
    idEmpleado int not null unique,
    foreign key (idEmpleado) references Empleado(id)
);

DELIMITER //
create procedure insertarUsuario(idInput int,usuarioInput varchar(50), passwordInput varchar(50),idEmpleadoInput int)
BEGIN
	insert into Usuario(id,nombreUsuario,contraseña,idEmpleado) values(idInput,usuarioInput,sha(passwordInput),idEmpleadoInput); 
END
//

DELIMITER //
CREATE FUNCTION verificarUsuario(usuarioInput VARCHAR(50), passwordInput VARCHAR(50)) RETURNS BOOLEAN DETERMINISTIC
BEGIN
    DECLARE passwordToCompare TEXT;
    
    SELECT contraseña INTO passwordToCompare FROM Usuario WHERE nombreUsuario = usuarioInput;
    
    IF passwordToCompare IS NOT NULL THEN
        RETURN passwordToCompare = SHA1(passwordInput);
    ELSE
        RETURN FALSE;
    END IF;
END
//
DELIMITER ;

