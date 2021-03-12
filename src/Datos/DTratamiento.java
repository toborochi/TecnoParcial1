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
public class DTratamiento extends Dato {
    public DTratamiento() {
        super();
        this.TABLE = "tratamiento";
        this.COLUMNS = new String[] {
            "nombre",
            "especialidadid"
        };
         this.TYPES= new String[]{
            Dato.Datatypes.STRING,
            Dato.Datatypes.INTEGER
        };
    }
}
