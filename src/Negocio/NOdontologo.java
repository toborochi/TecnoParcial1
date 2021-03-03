package Negocio;

import Datos.DOdontologo;
import Datos.DUsuario;
import java.sql.Date;

/**
 *
 * @author Grupo18
 */
public class NOdontologo {
     public  final String NOMBRE_TABLA="odontologo";
    private DOdontologo dato;
    private DUsuario user;

    public NOdontologo() {

        dato = new DOdontologo();
        user = new DUsuario();        
    }

    private String validarDatos() {
        String noNull="no puede ser nulo";
        String res = "";
        res += (dato.getCi() < 1) ? "Ci de "+NOMBRE_TABLA+noNull :"";
        res += (dato.getNombre().length() < 1) ? "Nombre no "+noNull :"";
        res += (dato.getCelular().length() < 1) ? "Celular "+noNull :"";
        res += (dato.getGenero().length() < 1) ? "Genero "+noNull :"";

        res += (dato.getfNac().toString().length() < 10) ? "Fecha de nacimiento o Debe ser formato DD-MM-AAAA," :"";
        res += (dato.getGenero().length() < 1) ? "Genero "+noNull : "";

        res += (user.getCorreo().length() < 5) ? "Mail "+noNull :"";
        res += (user.getContraseña().length() < 8) ? "Longitud de contraseña incorrecta,":"";
        return res;
    }
    /**
     * Datos del Odontologo
     * @param ci 
     * @param nombre 
     * @param celular 
     * @param fNac
     * @param genero 
     * @param mail del usuario
     * @param contraseña del usuario
     * @return 
     */
    public String registrarOdontologo(int ci, String nombre,  String celular, String fNac, String genero,String mail, String contraseña) {
        dato.setCi(ci);
        dato.setNombre(nombre);
        dato.setCelular(celular);
        dato.setfNac(Date.valueOf(fNac));
        dato.setGenero(genero);      
        user.setCorreo(mail);
        user.setContraseña(contraseña);
        String res = validarDatos();
        if (res.length() == 0) {
//            user.setContraseña(HASH(user.getContraseña()));            
            if (user.registrar()){
                int id_usuario=user.ObtenerID();
                dato.setFk_odontologo_usuario(id_usuario);                
            }                                            
            return dato.Registrar()? dato.Listar("Se Registro Correctamente el "+NOMBRE_TABLA):"Fallo al Registrar el "+NOMBRE_TABLA;
        }
        return res;
    }
    /**
     * Modifica la tabla Odontologo, todos estos datos le pertenecen a esta
     * @param ci
     * @param nombre
     * @param celular
     * @param fNac
     * @param genero
     * @return 
     */
    public String modificarOdontologo(int odonto_ci, String odonto_nombre,  String odonto_celular, String odonto_fNac, String odonto_genero) {        
        dato.setCi(odonto_ci);
        dato.setNombre(odonto_nombre);
        dato.setCelular(odonto_celular);
        dato.setfNac(Date.valueOf(odonto_fNac));
        dato.setGenero(odonto_genero);
        String noNull="no puede ser nulo";
        String res = "";
        res += (dato.getCi() < 1) ? "Ci de "+NOMBRE_TABLA+noNull :"";
        res += (dato.getNombre().length() < 1) ? "Nombre no "+noNull :"";
        res += (dato.getCelular().length() < 1) ? "Celular "+noNull :"";
        res += (dato.getGenero().length() < 1) ? "Genero "+noNull :"";
        res += (dato.getfNac().toString().length() < 10) ? "Fecha de nacimiento o Debe ser formato DD-MM-AAAA," :"";

         int s = res.length();
        if (s == 0) {
            return dato.Existe(dato.getCi()) && dato.Modificar() ? dato.Listar("Odontologo Modificado con exito") : "no se pudo modificar al "+NOMBRE_TABLA;
        }
        return res;
    }

    public String eliminarOdontologo(int ci) {
        if (ci > 1) {
            dato.setCi(ci);                
            if (dato.Existe(ci)) {                
                user.setCorreo(dato.ObtenerMailbyCI());
                               
                return user.eliminar()? dato.Listar(NOMBRE_TABLA+" eliminado exitosamente"): "Fallo al Eliminar el "+NOMBRE_TABLA;
            } else {
                return "El "+NOMBRE_TABLA+" no existe";
            }

        }else{
            return "Ci no es una longitud valida";
        }

    }

    public String listarOdontologo() {
        return dato.Listar("");
    }

    public String modificarContraseñaOdontologo(String email, String anterior_contraseña, String nueva_contraseña) {
        DUsuario usuario = new DUsuario();
        usuario.setCorreo(email);
        //aplicar hash a la anterior contraseña
        usuario.setContraseña(anterior_contraseña);
        if (usuario.Existe()) {
            usuario.setContraseña(nueva_contraseña);
            usuario.modificarContraseña();
            return "Contraseña del usuario " + email + " modificada con exito";
        }
        return "fallo al modificar , usuario o contraseña incorrectas";
    }
    public String findOdontologo(int ci){
        if (ci >0) {
            dato.setCi(ci);
            return dato.find(ci);            
        }else{
            return "Formato del CI no valido";
        }
    }
}
