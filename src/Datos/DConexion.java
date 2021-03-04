package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DConexion {

    private final String base = "db_grupo18sc";
    private final String user = "grupo18sc";
    private final String password = "grup018grup018";
    private final String url = "jdbc:postgresql://tecnoweb.org.bo:5432/" + base;
    private final String driver = "org.postgresql.Driver";
    private Connection con = null;

    public DConexion() {
    }

    public Connection getConexion() {
        try {
            Class.forName(this.driver);
            con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);

        } catch (SQLException e) {
            System.err.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void connect() {
        try {
            Class.forName(this.driver);
            con = DriverManager.getConnection(url, user, password);
            System.out.println("\033[32;1;2mDRIVER CONNECTED!\033[0m");
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(DConexion.class.getName()).log(Level.SEVERE, null, e);
            System.err.println("ERROR ON CONNECTING!");
        }
    }

    public void close() {
        try {
            con.close();
            System.out.println("CONNECTION CLOSE!");
            System.out.println("\033[32;1;2mCONNECTION CLOSED!!\033[0m");
        } catch (SQLException ex) {
            Logger.getLogger(DConexion.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("ERROR ON CLOSING!");
        }
    }

    public ResultSet query(String query) {
        Statement statement;
        ResultSet resultSet = null;
        connect();
        try {
            statement = (Statement) con.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            Logger.getLogger(DConexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            close();
        }

        return resultSet;
    }
}
