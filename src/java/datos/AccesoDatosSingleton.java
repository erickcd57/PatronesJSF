/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Diaz
 */
public class AccesoDatosSingleton {
    private static AccesoDatosSingleton instance;
    private Connection _connection;

    private AccesoDatosSingleton() {
        try {
            DriverManager.registerDriver(new Driver());
            _connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/demopatronesjsf?useSSL=false", "root", "");
        } catch (SQLException e) {
            System.out.println("Error al registrar el controlador" + e.getMessage());
        }
    }

    public static AccesoDatosSingleton getInstance() {
        if (instance == null) {
            instance = new AccesoDatosSingleton();
        }
        return instance;
    }
    
    public Connection getConnection() {
        return _connection;
    }
}
