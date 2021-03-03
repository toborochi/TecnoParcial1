package Datos;

import java.sql.*;

/**
 *
 * @author Grupo18
 */
public class DOdontologo {

    public final String NOMBRE_TABLA = "odontologo";
    private int ci;
    private String nombre;
    private String celular;
    private String genero;
    private Date fNac;
    private int fk_odontologo_usuario;

    public int getFk_odontologo_usuario() {
        return fk_odontologo_usuario;
    }

    public void setFk_odontologo_usuario(int fk_odontologo_usuario) {
        this.fk_odontologo_usuario = fk_odontologo_usuario;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getfNac() {
        return fNac;
    }

    public void setfNac(Date fNac) {
        this.fNac = fNac;
    }

    private Connection abrirConexion() {
        DConexion con = new DConexion();
        Connection DBC = con.getConexion();
        return DBC;
    }

    public boolean Registrar() {
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "INSERT INTO " + NOMBRE_TABLA + " (ci,nombre,celular,genero,fNac,usuarioid) VALUES (?,?,?,?,?,?);";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, this.getCi());
            ps.setString(2, this.getNombre());
            ps.setString(3, this.getCelular());
            ps.setString(4, this.getGenero());
            ps.setDate(5, this.getfNac());
            ps.setInt(6, this.getFk_odontologo_usuario());
            ps.execute();
            return true;

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

    public boolean Modificar() {
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "UPDATE " + NOMBRE_TABLA + " SET nombre=?,celular=?,genero=?,fNac=? WHERE ci=? ";
        try {
            ps = con.prepareStatement(sql);

            ps.setInt(1, this.getCi());
            ps.setString(2, this.getNombre());
            ps.setString(3, this.getCelular());
            ps.setString(4, this.getGenero());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

    public boolean Eliminar() {
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "DELETE FROM " + NOMBRE_TABLA + " WHERE ci=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getCi());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public String Listar(String mensaje) {
        Statement Consulta;
        ResultSet resultado = null;
        String imprimir = "Content-Type: text/html; charset=\"UTF-8\"\n"
                + "\n"
                + "<h3>" + mensaje + "</h3>\n"
                + "\n"
                + "<h1>Listado de Odontologos</h1>"
                + "<table style=\"border-collapse: collapse; width: 100%; border: 2px solid black;\">\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">CI</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">NOMBRE</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">CELULAR </th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">GENERO</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">FECHA DE NACIMIENTO</th>\n"
                + "\n"
                + "  </tr>\n"
                + "\n";
        try {
            String query = "SELECT *"
                    + "FROM " + NOMBRE_TABLA;
            Connection con = abrirConexion();
            Consulta = (Statement) con.createStatement();
            resultado = Consulta.executeQuery(query);
            ResultSetMetaData rsMd = resultado.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (resultado.next()) {
                imprimir = imprimir
                        + "  <tr>\n"
                        + "\n";
                for (int i = 0; i < cantidadColumnas; i++) {
                    imprimir = imprimir
                            + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">" + resultado.getString(i + 1) + "</td>\n"
                            + "\n";

                }
                imprimir = imprimir
                        + "  </tr>\n"
                        + "\n";
            }
            imprimir = imprimir
                    + "\n"
                    + "</table>";
            Consulta.close();

            con.close();

        } catch (Exception e) {
            System.out.println("no se pudo listar los datos");
        }
        return imprimir;
    }

    public boolean Existe(int ci) {
        setCi(ci);
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        String sql = "SELECT * FROM " + NOMBRE_TABLA + " where ci = ?";
        ResultSet resultado = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getCi());
            resultado = ps.executeQuery();
            if (resultado.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public String find(int ci) {
        PreparedStatement ps = null;
        Connection con = abrirConexion();
        this.setCi(ci);
        String sql = "SELECT * FROM " + NOMBRE_TABLA + " WHERE abg_ci = ?";
        ResultSet resultado = null;
        String imprimir = "Content-Type: text/html; charset=\"UTF-8\"\n"
                + "\n"
                + "<h3></h3>\n"
                + "\n"
                + "<h1>Datos del Odontologo</h1>"
                + "<table style=\"border-collapse: collapse; width: 100%; border: 2px solid black;\">\n"
                + "\n"
                + "  <tr>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">CI</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">NOMBRE</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">CELULAR</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">GENERO</th>\n"
                + "\n"
                + "    <th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">FECHA DE NACIMIENTO</th>\n"
                + "\n"
                + "  </tr>\n"
                + "\n";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.getCi());
            resultado = ps.executeQuery();
            ResultSetMetaData rsMd = resultado.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (resultado.next()) {
                imprimir = imprimir
                        + "  <tr>\n"
                        + "\n";
                for (int i = 0; i < cantidadColumnas; i++) {
                    imprimir = imprimir
                            + "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">" + resultado.getString(i + 1) + "</td>\n"
                            + "\n";

                }
                imprimir = imprimir
                        + "  </tr>\n"
                        + "\n";
            }
            imprimir = imprimir
                    + "\n"
                    + "</table>";
            ps.close();

            con.close();
            return imprimir;

        } catch (Exception e) {
        }
        return "No se pudo encontrar el Odontologo";
    }

    public String ObtenerMailbyCI() {
        PreparedStatement ps = null;
        Connection con = abrirConexion();

        String sql = "SELECT usuario.email FROM usuario," + NOMBRE_TABLA + " WHERE " + NOMBRE_TABLA + ".usuario = usuario.id and " + NOMBRE_TABLA + ".ci = ?";
        ResultSet resultado = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, this.ci);
            resultado = ps.executeQuery();
            resultado.next();
            return resultado.getString("usuario.email");

        } catch (Exception e) {
        }
        return "-1";

    }
}
