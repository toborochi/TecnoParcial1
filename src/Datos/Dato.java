package Datos;

import java.util.regex.*;
import java.sql.ResultSet;

abstract class Dato {

    protected String TABLE;
    protected String[] COLUMNS;
    private final DConexion dbc;

    
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

    public ResultSet listar() {
        String sql = "SELECT * FROM " + TABLE;
        return (ResultSet) dbc.query(sql);
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
        for (int i = 0; i < COLUMNS.length - 1; i++) {
            if (!isNumber(args[i])) {
                args[i] = "'" + args[i] + "'";
            }
            VALUES += (i == COLUMNS.length - 2) ? "= %s, " : " = %s ";
        }

        String sql = String.format(
                "UPDATE " + TABLE + "SET " + VALUES + "WHERE id = %s",
                args
        );

        return (boolean) dbc.query(sql);
    }

    public boolean eliminar(String id) {
        String sql = String.format("DELETE FROM " + TABLE + " WHERE id = %s", id);
        return (boolean) dbc.query(sql);
    }
}
