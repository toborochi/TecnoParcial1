package Datos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ParseHelper;

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
