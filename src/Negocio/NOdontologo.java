package Negocio;

import Datos.DOdontologo;
import Datos.DUsuario;
import java.sql.Date;

/**
 *
 * @author Grupo18
 */
public class NOdontologo extends Negocio{

    public final String NOMBRE_TABLA = "odontologo";
    private DOdontologo dato;
    private DUsuario user;

    public NOdontologo() {
        super(new DOdontologo());
    }

}