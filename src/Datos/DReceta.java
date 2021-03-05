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
public class DReceta extends Dato {
    
    public DReceta() {
        super();
        this.TABLE = "receta";
        this.COLUMNS = new String[]{
            "titulo",
            "descripcion",
            "fecha",
            "consultaid"
        };
    }
}
