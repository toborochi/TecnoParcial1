/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.DPaciente;
import Datos.Dato;
import tecnoweb_basic_email.EmailTask;

/**
 *
 * @author Pablo_Tardio
 */
public class NPaciente extends Negocio {

   

//    public String validarYCrear(String[] datos) {
//        try {
//            String respuesta = "";
//            if (datos == null) {
//                respuesta = "no se aceptan datos nulos";
//                return respuesta;
//            }
//            if (datos.length < 7 || datos.length > 7) {
//                respuesta = "Cantidad de parametros incorrecta:";
//                return respuesta;
//            }
//            respuesta += !EmailTask.isInteger(datos[0]) ? "CI no valido \n " : "";
//            respuesta += datos[1].length() <= 0 ? "El nombre no es valido \n" : "";
//            respuesta += datos[2].length() < 10 ? "La fecha de nacimiento no es valido \n" : "";
//            respuesta += !EmailTask.isInteger(datos[3]) ? "El numero de celular no es valido \n" : "";
//
//            if (respuesta.length() == 0) {
//                if(this.dPaciente.crear(new Object[]{Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]})){
//                    
//                }
//            }
//            return respuesta;
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return null;
//    }

    public NPaciente() {
        super(new DPaciente());
        
        
    }

}
