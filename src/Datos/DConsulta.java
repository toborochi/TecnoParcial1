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
public class DConsulta extends Dato {
    public DConsulta() {
        super();
        this.TABLE = "consulta";
        this.COLUMNS = new String[] {
            "fechaemision",
            "citaid"
        };
        this.TYPES= new String[]{
            Dato.Datatypes.DATE,
            Dato.Datatypes.INTEGER
        };
    }
}
