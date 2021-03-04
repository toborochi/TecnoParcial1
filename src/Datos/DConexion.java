package Datos;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ParseHelper;

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

    public ResultSet query(String query) {

        Statement Consulta;
        ResultSet resultado = null;

        connect();
        try {
            Consulta = (Statement) con.createStatement();
            resultado = Consulta.executeQuery(query);
            return resultado;
        } catch (SQLException e) {
            Logger.getLogger(DConexion.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            close();
            return resultado;
        }
    }

    public ResultSet query(String query, String[] parametros) {

        PreparedStatement consulta;
        ResultSet resultado = null;

        connect();
        try {
            consulta = (PreparedStatement) con.prepareStatement(query);
            for (int i = 0; i < parametros.length; i++) {
                String parametro = parametros[i];
                Method metodo = getMethodToParse(parametro);
                Object parametroParseado=parseParam(parametro);
                metodo.invoke(consulta, parametroParseado);
               
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

    /**
     * Verifica el tipo de dato del parametro y retorna la funcion adecuada para
     * parsearla y ponerla al statement
     *
     * @param parametro
     * @return
     * @throws NoSuchMethodException
     */
    private Method getMethodToParse(String parametro) throws NoSuchMethodException {
        // preguntar que tipo es el parametro
        if (ParseHelper.tryParseDate(parametro)) {
            return PreparedStatement.class.getMethod("setDate", int.class, Date.class);
        } else if (ParseHelper.tryParseBoolean(parametro)) {
            return PreparedStatement.class.getMethod("setBoolean", int.class, boolean.class);
        } else if (ParseHelper.tryParseInt(parametro)) {
            return PreparedStatement.class.getMethod("setInt", int.class, int.class);
        }
        return PreparedStatement.class.getMethod("setString", int.class, String.class);

    }
}
