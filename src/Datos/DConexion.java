package Datos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DConexion {
    
    public static void main(String[] args) {
        System.out.println("HOLA MUNDO");
    }

    private final String base = "db_grupo18sc";
    private final String user = "grupo18sc";
    private final String password = "grup018grup018";
    private final String url = "jdbc:postgresql://tecnoweb.org.bo:5432/" + base;
    private final String driver = "org.postgresql.Driver";
    private Connection con = null;

    public DConexion() {
    }

    public Connection getConexion() throws SQLException, ClassNotFoundException {
        Class.forName(this.driver);
        con = (Connection) DriverManager.getConnection(this.url, this.user, this.password);
        return con;
    }

    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName(this.driver);
        con = DriverManager.getConnection(url, user, password);
        System.out.println("CONNECTION OPEN!");
    }

    public void close() throws SQLException {
        con.close();
        System.out.println("CONNECTION CLOSE!");
    }

    public ResultSet query(String query) throws ClassNotFoundException, SQLException {
        Statement statement;
        ResultSet resultSet = null;

        connect();
        statement = (Statement) con.createStatement();
        resultSet = statement.executeQuery(query);
        close();

        return resultSet;
    }

    public ResultSet query(String query, String[] parametros) {

        PreparedStatement consulta;
        ResultSet resultado = null;

        connect();
        try {
            consulta = (PreparedStatement) con.prepareStatement(query);
            for (int i = 0; i < parametros.length; i++) {
               
               
            }

            resultado = consulta.executeQuery();
            return resultado;
        } catch (SQLException e) {
            Logger.getLogger(DConexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            close();
            return resultado;
        }
    }

   
}
