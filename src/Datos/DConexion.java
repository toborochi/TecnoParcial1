package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
}
