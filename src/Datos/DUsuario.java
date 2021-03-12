/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.sql.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Grupo18
 */
public class DUsuario extends Dato{

      public DUsuario() {
        super();
        this.TABLE = "usuario";
        this.COLUMNS = new String[] {
            "correo",
            "contraseña"
        };
         this.TYPES= new String[]{
            Dato.Datatypes.STRING,
            Dato.Datatypes.STRING
        };
    }

}
