package Datos;

import java.util.regex.*;
import java.sql.ResultSet;

public class Dato {

    protected String TABLE;
    protected String[] COLUMNS;
    protected String[] TYPES;
    protected final DConexion dbc;

    public Dato() {
        this.dbc = new DConexion();
    }

    public Dato(String table, String[] Columns) {
        this.TABLE = table;
        this.COLUMNS = Columns;
        this.dbc = new DConexion();
    }

    private boolean isNumber(Object arg) {
        String regex = "^(\\d+(\\.\\d+)?)$";
        return Pattern.matches(regex, String.valueOf(arg));
    }

    public Tabla listar() {
        String sql = "SELECT * FROM " + TABLE;
        return new Tabla((ResultSet) dbc.query(sql));
    }
    public String[]  getTypesList(){
        return TYPES;
    }
    public String[]  getColums(){
        return COLUMNS;
    }
    public Tabla buscarPorID(String id) {
        String sql = "SELECT * FROM " + TABLE + "WHERE id = " + id;
        return new Tabla((ResultSet) dbc.query(sql));
    }
    public Tabla buscar(String[] columnas,Object[] parametros) {
       
        String VALUES = "";
         for (int i = 0; i < COLUMNS.length; i++) {
            if (!isNumber(parametros[i])) {
                parametros[i] = "'" + parametros[i] + "'";
            }

            if (i == COLUMNS.length - 1) {
                VALUES += columnas[i]+"=%s";
                
            } else {
                VALUES += columnas[i]+"=%s and ";
               
            }
        }
         
       String queryString= "SELECT * FROM " + TABLE + " WHERE "+VALUES;
         String sql = String.format(
               queryString,
                parametros
        );

       
        return new Tabla((ResultSet) dbc.query(sql));
    }
    public boolean crear(Object args[]) {
        String COLS = "";
        String VALUES = "";

        for (int i = 0; i < COLUMNS.length; i++) {
            if (!isNumber(args[i])) {
                args[i] = "'" + args[i] + "'";
            }

            if (i == COLUMNS.length - 1) {
                VALUES += "%s";
                COLS += COLUMNS[i];
            } else {
                VALUES += "%s,";
                COLS += COLUMNS[i] + ",";
            }
        }

        String sql = String.format(
                "INSERT INTO " + TABLE + "(" + COLS + ") VALUES (" + VALUES + ")",
                args
        );

        return (boolean) dbc.query(sql);
    }

    public boolean editar(Object args[]) {
        String VALUES = "";
        for (int i = 0; i < COLUMNS.length ; i++) {
            if (!isNumber(args[i])) {
                args[i] = "'" + args[i] + "'";
            }
         
            VALUES+=this.COLUMNS[i];
            VALUES += (i == COLUMNS.length - 1) ? "= %s " : " = %s, ";
        }

        String sql = String.format(
                "UPDATE " + TABLE + " SET " + VALUES + "WHERE id = %s",
                args
        );

        return (boolean) dbc.query(sql);
    }
        
    public boolean eliminar(String id) {
        String sql = String.format("DELETE FROM " + TABLE + " WHERE id = %s", id);
        return (boolean) dbc.query(sql);
    }
    public static class Datatypes {
        public static final String STRING   ="string";
        public static final String INTEGER   ="integer";
        public static final String DATE   ="date";
        public static final String FLOAT   ="float";
        public static final String TIME   ="time";
    }
}
