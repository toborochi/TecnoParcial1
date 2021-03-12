/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author cartory
 */
public class DEspecialidad extends Dato {
    public DEspecialidad() {
        super();
        this.TABLE = "especialidad";
        this.COLUMNS = new String[] {
            "nombre"
        };
         this.TYPES= new String[]{
            Dato.Datatypes.STRING
        };
    }
    public class DOdontologoEspecialidad extends Dato{
        public DOdontologoEspecialidad(){
           super();
        this.TABLE = "odontologoespecialidad";
        this.COLUMNS = new String[] {
            "odontologoid","especialidadid"
        }; 
         this.TYPES= new String[]{
            Dato.Datatypes.INTEGER,
            Dato.Datatypes.INTEGER
        };
        }
    }
}

