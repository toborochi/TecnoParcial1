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
public class DPaciente extends Dato {
    public DPaciente() {
        super();
        this.TABLE = "paciente";
        this.COLUMNS = new String[]{
            "ci",
            "nombre",
            "fnac",
            "celular"
        };
    }
}
