package Datos;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Tabla {

    public String nombres[];
    public List<List<String> > data = new ArrayList<>();

    public Tabla(ResultSet result){
        try {
            ResultSetMetaData rsmd = result.getMetaData();
            int columnas = rsmd.getColumnCount();
            nombres=new String[columnas];
            
            for(int i=1;i<=columnas;++i){
                nombres[i-1]=rsmd.getColumnName(i);
            }
            
            while(result.next()){
                List a = new ArrayList<>();
                for (int i = 1; i <= columnas; i++) {
                    a.add(result.getString(i));
                }
                data.add(a);
            }} catch (SQLException ex) {
            Logger.getLogger(Tabla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int getColumna(){
        return data.get(0).size();
    }
    
    public int getFila(){
        return data.size();
    }
    
    public String getData(int i,int j){
        return data.get(i).get(j);
    }
}
