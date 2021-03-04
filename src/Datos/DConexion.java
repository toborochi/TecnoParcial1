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

    //Declaramos las constantes primero 
    private final String base = "db_grupo18sc";
    private final String user = "grupo18sc";
    private final String password = "grup018grup018";
    private final String url = "jdbc:postgresql://tecnoweb.org.bo:5432/" + base;
    private final String driver = "org.postgresql.Driver";
    private Connection con = null;//Variable para obtener la conexion y guardarla 

    public DConexion() {

    }

    //Metodo getConexion donde se hará la conexion
    public Connection getConexion() {
        try {
            //Colocamos la ruta del Driver 
            //Controlador para realizar la conexion 
            Class.forName(this.driver);
            //La variable la igualamos a la conexion 
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
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, user, password);
            System.out.println("CONNECTED!");
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(DConexion.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("ERROR ON CONNECTING!");
        }
    }
    
    public void close() {

        try {
            con.close();
            System.out.println("CLOSING CONNECTION!");

        } catch (SQLException ex) {
            Logger.getLogger(DConexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR CLOSING CONNECTION!");
        }
    }
    
    public ResultSet query(String query){
        
        Statement Consulta;
        ResultSet resultado = null;
        
        connect();
        try{
            Consulta = (Statement) con.createStatement();
            resultado = Consulta.executeQuery(query);
            return resultado;
        }catch (SQLException e) {
            Logger.getLogger(DConexion.class.getName()).log(Level.SEVERE, null, e);
        }finally{
            close();
            return resultado;
        }
    }
}
