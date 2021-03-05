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
public class DCita extends Dato {
    public DCita() {
        super();
        this.TABLE = "cita";
        this.COLUMNS = new String[] {
            "horainicio",
            "horafin",
            "pacienteid",
            "agendaid"
        };
    }
}
