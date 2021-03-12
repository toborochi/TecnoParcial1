package Datos;

import java.sql.*;

/**
 *
 * @author Grupo18
 */
public class DOdontologo extends Dato {

     public DOdontologo() {
        super();
        this.TABLE = "odontologo";
        this.COLUMNS = new String[]{
            "ci",
            "nombre",
            "fnac",
            "celular",
            "genero",
            "usuarioid"
               
        };
        this.TYPES= new String[]{
            Dato.Datatypes.INTEGER,
            Dato.Datatypes.STRING,
            Dato.Datatypes.DATE,
            Dato.Datatypes.STRING,
            Dato.Datatypes.STRING,
            Dato.Datatypes.INTEGER
        };
       
    }  
}
