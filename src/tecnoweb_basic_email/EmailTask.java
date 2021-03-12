package tecnoweb_basic_email;

import Negocio.NAgenda;
import Negocio.NCita;
import Negocio.NConsulta;
import Negocio.NEspecialidad;
import Negocio.NOdontologo;
import Negocio.NPaciente;
import Negocio.NReceta;
import Negocio.NTratamiento;
import Negocio.NUsuario;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailTask implements Callable<MailSender> {

    String to, subject;

    NAgenda nAgenda;
    NCita nCita;
    NConsulta nConsulta;
    NEspecialidad nEspecialidad;
    NOdontologo nOdontologo;
    NPaciente nPaciente;
    NReceta nReceta;
    NTratamiento nTratamiento;
    NUsuario nUsuario;

    public EmailTask(String to, String subject) {
        this.to = to;
        this.subject = subject;
        this.nAgenda = new NAgenda();
        this.nCita = new NCita();
        this.nConsulta = new NConsulta();
        this.nEspecialidad = new NEspecialidad();
        this.nOdontologo = new NOdontologo();
        this.nPaciente = new NPaciente();
        this.nReceta = new NReceta();
        this.nTratamiento = new NTratamiento();
        this.nUsuario = new NUsuario();
    }

    public String verificarComandos() throws IOException {
        String encabezado = "";
        String datos[] = null;

        LinkedList<Object> datosParseados = parseComando(encabezado, datos);
        encabezado = (String) datosParseados.get(1);
        datos = (String[]) datosParseados.get(0);
        String mensaje = "";
        switch (encabezado) {

            // CU4: Gestionar Abogado
            case "crear_agenda":
                mensaje = nAgenda.crear(datos);
                break;
            case "listar_agendas":
                mensaje = nAgenda.TablaHTML("Lista");
                 break;
            case "mod_agenda":
                mensaje = nAgenda.Editar(datos);
                break;
            case "eliminar_agenda":
                mensaje = nAgenda.Eliminar(datos[0]);
                break;
            case "crear_cita":
                mensaje = nCita.crear(datos);
                break;
             case "listar_citas":
                mensaje = nCita.TablaHTML("Lista");
                 break;
            case "eliminar_cita":
                mensaje = nCita.Eliminar(datos[0]);
                break;
            case "crear_consulta":
                mensaje = nConsulta.crear(datos);
                break;
            case "eliminar_consulta":
                mensaje = nConsulta.Eliminar(datos[0]);
                break;
            case "listar_consultas":
                mensaje = nConsulta.TablaHTML("Lista");
                break;
            case "crear_especialidad":
                mensaje = nEspecialidad.crear(datos);
                break;
            case "eliminar_especialidad":
                mensaje = nEspecialidad.Eliminar(datos[0]);
                break;
            case "listar_especialidades":
                mensaje = nEspecialidad.TablaHTML("Lista");
                 break;

            case "reg_odontologo":
                mensaje += this.nUsuario.crear(Arrays.copyOfRange(datos, 5, 7));
                String[] odontologo = Arrays.copyOfRange(datos, 0, 6);
                odontologo[5] = this.nUsuario.getID(new String[]{"correo", "contraseña"}, new String[]{datos[5], datos[6]});
                mensaje = this.nOdontologo.crear(odontologo);
                break;
            case "eliminar_odontologo":
                mensaje = nOdontologo.Eliminar(datos[0]);
                break;
            case "listar_odontologos":
                mensaje = nOdontologo.TablaHTML("Lista");
                break;
            case "crear_paciente":
                mensaje = this.nPaciente.crear(datos);
                break;
            case "eliminar_paciente":
                mensaje = nPaciente.Eliminar(datos[0]);
                break;
            case "crear_receta":
                mensaje = nReceta.crear(datos);
                break;
            case "eliminar_receta":
                mensaje = nReceta.Eliminar(datos[0]);
                break;
            case "listar_recetas":
                mensaje = nReceta.TablaHTML("Lista");
                break;
            case "crear_tratamiento":
                mensaje = nTratamiento.crear(datos);
                break;
            case "eliminar_tratamiento":
                mensaje = nTratamiento.Eliminar(datos[0]);
                break;
            case "listar_tratamientos":
            mensaje = nReceta.TablaHTML("Lista");
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
    public String registrarOdontologo(String[] datos) {
        String respuesta = "";
        try {

            if (datos == null) {
                return "No se aceptan datos nulos";
            }
            if (datos.length < 7 || datos.length > 7) {
                return "Cantidad de parametros incorrecta:" + datos.length;
            }
            respuesta += !isInteger(datos[0]) ? "CI no valido \n " : "";
            respuesta += datos[1].length() <= 0 ? "El nombre no es valido \n" : "";
            respuesta += !isInteger(datos[2]) ? "El numero de celular no es valido \n" : "";
            respuesta += datos[3].length() < 10 ? "La fecha de nacimiento no es valido \n" : "";
            respuesta += datos[4].length() < 1 ? "El genero del abogado  no es valido \n" : "";
            respuesta += datos[5].length() <= 0 ? "Mail no valido \n" : "";
            respuesta += datos[6].length() < 8 ? "Contraseña de longitud no valida \n" : "";

            if (respuesta.length() == 0) {
                // respuesta = this.nOdontologo.registrarOdontologo(Integer.parseInt(datos[0]), datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
            }
            return respuesta;
        } catch (Exception e) {
            System.out.println(e);
        }
        return respuesta;
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
     *
     * @param encabezado
     * @param cuerpo
     */
    private LinkedList<Object> parseComando(String encabezado, String[] datos) {
        LinkedList<Object> parsedList = new LinkedList();
        String sub = this.subject.trim();
        String[] partesSubject = sub.split("\\[");
        encabezado = partesSubject[0];
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

    @Override
    public MailSender call() throws Exception {
        // TODO: Verificar las consultas, salta error cuando se
        // se llama a Negocio

        String resultadoVerificacion = this.verificarComandos();
        return new MailSender(this.to, "Resultado", resultadoVerificacion);
    }

    public String help() {
        //Arreglo que se usara para la tabla

        String[][] miTabla
                = {{"Caso de uso", "Método", "Comando"},
                {"CU1. Gestionar odontologo", "Registrar Odontologo", "reg_odontologo[int CI;; String nombre;;Date fNac[AAAA-MM-DD];;String celular ;;String Genero [M o F] ;; String Correo ;; String Contraseñ];"},
                {"CU1. Gestionar odontologo", "Modificar  Odontologo", "mod_odontologo[int ci ;; String nombre ;; int celular  ;; String fecha de nacimiento(AAAA-MM-DD)] ;; String genero;"},
                {"CU1. Gestionar odontologo", "Eliminar  Odontologo", "eliminar_odontologo[int id];"},
                {"CU1. Gestionar odontologo", "Listar  Odontologos", "list_odontologo[];"},
                {"CU1. Gestionar odontologo", "Encontrar  Odontologos", "find_odontologo[int ci];"}

                };

        String help = "Content-Type: text/html; charset=\"UTF-8\"\n"
                + "\n"
                + "<h1>Lista de Comandos  </h1>"
                // + "<h2>Por favor no utilizar tildes (´) o (ñ) en los datos de los comandos</h2>"
                // + "<h2>(Se acepta documentos tipo: .txt, .docx, .pdf, .jpg, .rar, .xmls) DEPENDIENTO DEL TAMAÑO DEL ARCHIVO EL TIEMPO DE EJECUCION DEL PROGRAMA SERÁ MAYOR</h2>"
                + "<table style=\"border-collapse: collapse; width: 100%; border: 2px solid black;\">\n"
                + "\n";
        //Ponemos el encabezado
        help += generarEncabezado(miTabla);
        help += generarCuerpoTabla(miTabla);
        //Ponemos el contenido del documento
        return help;
    }

    private String generarCuerpoTabla(String[][] miTabla) {
        String cuerpo = "";
        for (int i = 1; i < miTabla.length; i++) {
            cuerpo += "  <tr>\n"
                    + "\n";
            for (int j = 0; j < miTabla[i].length; j++) {
                cuerpo += "    <td style = \"text-align: left; padding: 8px; border: 2px solid black;\">"
                        + miTabla[i][j] + "</td>\n"
                        + "\n";
            }
            cuerpo += "  </tr>\n"
                    + "\n";
        }
        return cuerpo;
    }

    /**
     * Genera el encabezado a partir de la primera fila de la matriz de strings
     * que se le envie
     *
     * @param miTabla
     * @return
     */
    private String generarEncabezado(String[][] miTabla) {
        String encabezado = "<tr>\n"
                + "\n";
        for (int i = 0; i < miTabla[0].length; i++) {
            encabezado += "<th style = \"text-align: left; padding: 8px; background-color: #4CAF50; color: white; border: 2px solid black;\">"
                    + miTabla[0][i] + "</th>\n"
                    + "\n";
        }
        encabezado += "  </tr>\n"
                + "\n";
        return encabezado;
    }
}
