package com.example.gestoralmacenes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoMySQL {
    Connection conexion;

    public void conectar() throws SQLException {
        String usuario="root";
        String password="";
        String bd="Almacen";
        String host="localhost";
        String puerto="";
        String conexionURL="jdbc:mysql://"+
                host+":"+puerto+"/"+bd+
                "?useSSL=false";
        conexion= DriverManager.getConnection(conexionURL,usuario,password);
    }
    public DaoMySQL() throws SQLException {
        conectar();
    } 
}
