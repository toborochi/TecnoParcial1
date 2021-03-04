package Datos;

import Datos.DConexion;
import java.sql.ResultSet;
import java.util.LinkedList;

public class Dato {
 
    DConexion dbc;
    String table_name;
    String[] columns;
    
    public Dato(String TableName, String[] Columns){
        this.columns=Columns;
        this.table_name=TableName;
        this.dbc = new DConexion();
    }
    
    public ResultSet listar(){
        String sql = String.format("SELECT * FROM %s ", table_name);
        
        return dbc.query(sql);
    }
    
    public ResultSet crear(){
        String sql = String.format("SELECT * FROM %s ", table_name);
        return dbc.query(sql);
    }
    
    public ResultSet editar(){
        String sql = String.format("SELECT * FROM %s ", table_name);
        return dbc.query(sql);
    }
    
    public ResultSet eliminar(String id){
        String sql = String.format("DELETE FROM %s WHERE %s = %s;", table_name,columns[0],id);
        return dbc.query(sql);
    }
}
