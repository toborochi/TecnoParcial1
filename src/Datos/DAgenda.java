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
public class DAgenda extends Dato {
    public DAgenda() {
        super();
        this.TABLE = "agenda";
        this.COLUMNS = new String[] {
            "nombre",
            "odontologoid"
        };
        this.TYPES= new String[]{
            Dato.Datatypes.STRING,
            Dato.Datatypes.INTEGER
        };
    }
}
