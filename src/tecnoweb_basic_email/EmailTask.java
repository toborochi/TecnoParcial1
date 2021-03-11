package tecnoweb_basic_email;

import Negocio.NOdontologo;
import Negocio.NPaciente;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailTask implements Runnable {

    SMTP smtp = new SMTP();
    String to, subject;
    NOdontologo nOdontologo;
    NPaciente nPaciente;

    public EmailTask(String to, String subject) {
        this.to = to;
        this.subject = subject;
       this.nPaciente = new NPaciente();
    }

    @Override
    public void run() {
        try {
            String resultadoVerificacion=this.verificarComandos();

            System.out.println("Enviar a: " + this.to + ", Query: " + this.subject);
            smtp.connect();
            smtp.logIn();
            
            // Validar Subject
            // Switch
            smtp.sendMail(this.to, "Resultado", resultadoVerificacion);
            smtp.logOut();
            smtp.close();
        } catch (IOException ex) {
            Logger.getLogger(EmailTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String verificarComandos() {
        String encabezado = "";
        String datos[] = null;
        
        LinkedList<Object> datosParseados= parseComando(encabezado, datos);
        
        encabezado=(String)datosParseados.get(1);
        datos=(String[])datosParseados.get(0);
         String mensaje="";
        switch (encabezado) {

            // CU4: Gestionar Abogado
            case "reg_odontologo":
                this.registrarOdontologo(datos);
                break;
            case "crear_paciente":
                nPaciente.crear(datos);
                break;
            default:
                mensaje = "La petición '" + this.subject + "' es incorrecta.";

                break;
        }
        return mensaje;
    }

    /**
     * Valida y registra el odontologo
     *
     * @param datos del odontologo en un vector de strings
     */
    public void registrarOdontologo(String[] datos) {
        try {
            String respuesta = "";
            if (datos == null) {
                smtp.sendMail(this.to, "", "no se aceptan datos nulos");
                return;
            }
            if (datos.length < 7 || datos.length > 7) {
                smtp.sendMail(this.to, "", "Cantidad de parametros incorrecta:" + datos.length);
                return;
            }
            respuesta += !isInteger(datos[0]) ? "CI no valido \n " : "";
            respuesta += datos[1].length() <= 0 ? "El nombre no es valido \n" : "";
            respuesta += !isInteger(datos[2]) ? "El numero de celular no es valido \n" : "";
            respuesta += datos[3].length() < 10 ? "La fecha de nacimiento no es valido \n" : "";
            respuesta += datos[4].length() < 1 ? "El genero del abogado  no es valido \n" : "";
            respuesta += datos[5].length() <= 0 ? "Mail no valido \n" : "";
            respuesta += datos[6].length() < 8 ? "Contraseña de longitud no valida \n" : "";

            if (respuesta.length() == 0) {
                respuesta = this.nOdontologo.registrarOdontologo(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
            }
            smtp.sendMail(this.to, "", respuesta);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Funcion para verificar si una string es un entero
     *
     * @param s
     * @return
     */
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
    /**
     * separa el encabezado y el cuerpo de un comando enviado en una string
     * @param encabezado
     * @param cuerpo 
     */
    private LinkedList<Object> parseComando(String encabezado, String[] datos) {
        LinkedList<Object> parsedList= new LinkedList();
         String sub = this.subject.trim();
        String[] partesSubject = sub.split("\\[");
        encabezado= partesSubject[0];
         String cuerpo[] = partesSubject[1].split("\\]");
        
        if (cuerpo.length != 0) {
            datos = cuerpo[0].split("\\;;");
            for (int i = 0; i < datos.length; i++) {
                datos[i] = datos[i].trim();
            }
        }
        parsedList.push(encabezado);
        parsedList.push(datos);
       return parsedList;
    }
}
