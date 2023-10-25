package com.example.gestoralmacenes.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoUsuario extends DaoMySQL{
    public DaoUsuario() throws SQLException {
        conectar();
    }
    public void agregarUsuario(Long id,Long idEmpleado,String usuario,String password) throws SQLException {
        String query="call insertarUsuario(?,?,?,?)";
        PreparedStatement statement=conexion.prepareStatement(query);
        statement.setLong(1,id);
        statement.setLong(2,idEmpleado);
        statement.setString(3,usuario);
        statement.setString(4,password);
    }
}
