/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import org.rendersnake.HtmlCanvas;
import static org.rendersnake.HtmlAttributesFactory.*;
import utils.ParseHelper;

/**
 *
 * @author cartory
 */
abstract class Negocio {

    private final String styles =
            "body {\n"
            + "  background-color: #f3f3f3;\n"
            + "  font-family: Futura, sans-serif;\n"
            + "}\n"
            + "\n"
            + ".wrapper {\n"
            + "  margin: 24px 180px;\n"
            + "}\n"
            + "\n"
            + "h1 {\n"
            + "  color: #716eb6;\n"
            + "  text-align: center;\n"
            + "}\n"
            + "\n"
            + "/* Table styles */\n"
            + "\n"
            + "table {\n"
            + "  border-collapse: collapse;\n"
            + "  border-spacing: 0;\n"
            + "}\n"
            + "\n"
            + "td,\n"
            + "th {\n"
            + "  padding: 0;\n"
            + "  text-align: left;\n"
            + "}\n"
            + "\n"
            + "td:first-of-type {\n"
            + "  padding-left: 36px;\n"
            + "  width: 66px;\n"
            + "}\n"
            + "\n"
            + ".c-table {\n"
            + "  -moz-border-radius: 4px;\n"
            + "  -webkit-border-radius: 4px;\n"
            + "  background-color: #fff;\n"
            + "  border-radius: 4px;\n"
            + "  font-size: 12px;\n"
            + "  line-height: 1.25;\n"
            + "  margin-bottom: 24px;\n"
            + "  width: 100%;\n"
            + "}\n"
            + "\n"
            + ".c-table__cell {\n"
            + "  padding: 12px 6px 12px 12px;\n"
            + "  word-wrap: break-word;\n"
            + "}\n"
            + "\n"
            + ".c-table__header tr {\n"
            + "  color: #fff;\n"
            + "}\n"
            + "\n"
            + ".c-table__header th {\n"
            + "  background-color: #716eb6;\n"
            + "  padding: 18px 6px 18px 12px;\n"
            + "}\n"
            + "\n"
            + ".c-table__header th:first-child {\n"
            + "  border-top-left-radius:  4px;\n"
            + "}\n"
            + "\n"
            + ".c-table__header th:last-child {\n"
            + "  border-top-right-radius: 4px;\n"
            + "}\n"
            + "\n"
            + ".c-table__body tr {\n"
            + "  border-bottom: 1px solid rgba(113, 110, 182, 0.15);\n"
            + "}\n"
            + "\n"
            + ".c-table__body tr:last-child {\n"
            + "  border-bottom: none;\n"
            + "}\n"
            + "\n"
            + ".c-table__body tr:hover {\n"
            + "  background-color: rgba(113, 110, 182, 0.15);\n"
            + "  color: #272b37;\n"
            + "}\n"
            + "\n"
            + ".c-table__label {\n"
            + "  display: none;\n"
            + "}\n"
            + "\n"
            + "/* Mobile table styles */\n"
            + "\n"
            + "@media only screen and (max-width: 767px) {\n"
            + "\n"
            + "	table, thead, tbody, th, td, tr { \n"
            + "		display: block; \n"
            + "  }\n"
            + "  \n"
            + "  td:first-child { \n"
            + "    padding-top: 24px;\n"
            + "  }\n"
            + "\n"
            + "  td:last-child { \n"
            + "    padding-bottom: 24px;\n"
            + "  }\n"
            + "  \n"
            + "  .c-table {\n"
            + "    border: 1px solid rgba(113, 110, 182, 0.15);\n"
            + "    font-size: 15px;\n"
            + "    line-break: 1.2;\n"
            + "  }\n"
            + "\n"
            + "  .c-table__header tr { \n"
            + "		position: absolute;\n"
            + "		top: -9999px;\n"
            + "		left: -9999px;\n"
            + "	}\n"
            + "  \n"
            + "  .c-table__cell { \n"
            + "    padding: 12px 24px;\n"
            + "		position: relative; \n"
            + "    width: 100%;\n"
            + "    word-wrap: break-word;\n"
            + "  }\n"
            + "\n"
            + "  .c-table__label {\n"
            + "    color: #272b37;\n"
            + "    display: block;\n"
            + "    font-size: 10px;\n"
            + "    font-weight: 700;\n"
            + "    line-height: 1.2;\n"
            + "    margin-bottom: 6px;\n"
            + "    text-transform: uppercase;\n"
            + "  }\n"
            + " \n"
            + "  .c-table__body tr:hover {\n"
            + "    background-color: transparent;\n"
            + "  }\n"
            + "\n"
            + "  .c-table__body tr:nth-child(odd) {\n"
            + "    background-color: rgba(113, 110, 182, 0.04); \n"
            + "  }\n"
            + "  \n"
            ;

    Dato dato;
    protected HtmlCanvas html;

    public Negocio(Dato dato) {
        this.dato = dato;
        this.html = new HtmlCanvas();
    }

    public String crear(String args[]) {
        try {
            
            this.validarDatos(args);
            Object[] datosParseados = this.parsearDatos(args);
            if (dato.crear(datosParseados)) {
                return TablaHTML("Registro Completado !");
            }
                    
        } catch (IOException e) {
            System.err.println(e);
        }
        return "<h1>Ups! Algo Pasó! :(</h1>";
    }

    public String Editar(String args[]) {
        try {
            this.validarDatos(args);
            Object[] datosParseados = this.parsearDatos(args);
            if (dato.editar(datosParseados)) {
                return TablaHTML("Registro Completado !");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return "<h1>Ups! Algo Pasó! :(</h1>";
    }

    public String buscarPorID(String id) {
        Tabla data = this.dato.buscarPorID(id);
        try {
            if (data.getFila() != 0) {
                return TablaHTML("Consulta Exitósa");
            } else {
                return "<h1>Registro No encontrado</h1>";
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return "<h1>Ups! Algo Pasó! :(</h1>";
    }
    public String buscar(String columnas[],Object parametros[]) {
        
        Tabla data = this.dato.buscar(columnas,parametros);
        try {
            if (data.getFila() != 0) {
                return TablaHTML("Consulta Exitósa");
            } else {
                return "<h1>Registro No encontrado</h1>";
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return "<h1>Ups! Algo Pasó! :(</h1>";
    }
    public String getID(String columnas[],Object parametros[]) {
        
        Tabla data = this.dato.buscar(columnas,parametros);
        String id=data.getData(0, 0);
        return id;
    }

    public String Eliminar(String id) {
        try {
            if (this.dato.eliminar(id)) {
                return TablaHTML("Consulta Exitósa");
            }
        } catch (IOException e) {
            System.err.println(e);
        }
        return "<h1>Ups! Algo Pasó</h1>";
    }

    public String TablaHTML(String title) throws IOException {
        Tabla data = this.dato.listar();
       
        this.html.style().write(styles)._style();
        this.html.html().div(class_("wrapper"));
        this.html.table(class_(" c-table"));

        this.html.thead(class_("c-table__header")).tr();
        for (String nombre : data.nombres) {
            this.html.th(class_("c-table__col-label")).write(nombre)._th();
        }
        this.html._tr()._thead();
        this.html.tbody();
        for (int i = 0; i < data.getFila(); i++) {
            this.html.tr();
            for (int j = 0; j < data.getColumna(); j++) {
                this.html.td(class_("c-table__cell")).write(data.getData(i, j))._td();
            }
            this.html._tr();
        }

        this.html._tbody()._table()._div()._html();

        String innerHTML = this.html.toHtml();
        innerHTML= "Content-Type: text/html; charset=\"UTF-8\"\n" +innerHTML;
        this.html = new HtmlCanvas();
        return innerHTML;
    }

    public String validarDatos(String[] datos) {
        String mensajeValidacion = "";
        try {

            String[] columnas = dato.getColums();
            if (datos.length != columnas.length) {
                mensajeValidacion = "Cantidad de parametros incorrecta";
                throw new Exception(mensajeValidacion);
            }
            String[] tipos = dato.getTypesList();
            int i;
            for (i = 0; i < tipos.length; i++) {
                String tipo = tipos[i];
                switch (tipo) {
                    case Dato.Datatypes.DATE:
                        if (!validarFecha(datos[i])) {
                            mensajeValidacion = "Parametro incorrecto en posicion: " + i
                                    + " : " + columnas[i] + "Verifique que sea una fecha correcta";
                            throw new Exception(mensajeValidacion);
                        }
                        break;
                    case Dato.Datatypes.FLOAT:
                        if (!validarFloat(datos[i])) {
                            mensajeValidacion = "Parametro incorrecto en posicion: " + i
                                    + " : " + columnas[i] + "Verifique que sea un decimal correcto";
                            throw new Exception(mensajeValidacion);
                        }
                        break;
                    case Dato.Datatypes.INTEGER:
                        if (!validarInteger(datos[i])) {
                            mensajeValidacion = "Parametro incorrecto en posicion: " + i
                                    + " : " + columnas[i] + "Verifique que sea un numero correcto";
                            throw new Exception(mensajeValidacion);
                        }
                        break;
                    case Dato.Datatypes.STRING:
                        if (!validarString(datos[i])) {
                            mensajeValidacion = "Parametro incorrecto en posicion: " + i
                                    + " : " + columnas[i] + "Verifique que sea una Cadena correcta";
                            throw new Exception(mensajeValidacion);
                        }
                        break;
                    case Dato.Datatypes.TIME:
                        if (!validarFecha(datos[i])) {
                            mensajeValidacion = "Parametro incorrecto en posicion: " + i
                                    + " : " + columnas[i] + "Verifique que sea un Tiempo correcto";
                            throw new Exception(mensajeValidacion);
                        }
                        break;
                }

            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            return mensajeValidacion;
        }

    }

    private boolean validarFecha(String dato) {
        if (dato.length() < 10 || dato.length() > 10) {
            return false;
        }
        return true;
    }

    private boolean validarFloat(String dato) {
        return ParseHelper.tryParseFloat(dato);
    }

    private boolean validarInteger(String dato) {
        return ParseHelper.tryParseInt(dato);
    }

    private Object[] parsearDatos(String[] args) {

        String[] tipos = dato.getTypesList();
        Object[] datosParseados = new Object[tipos.length];

        for (int i = 0; i < tipos.length; i++) {
            String tipoActual = tipos[i];
            switch (tipoActual) {
                case Dato.Datatypes.INTEGER:
                    datosParseados[i] = Integer.parseInt(args[i]);
                    break;

                case Dato.Datatypes.FLOAT:
                    datosParseados[i] = Float.parseFloat(args[i]);
                    break;
                case Dato.Datatypes.DATE:
                    datosParseados[i] = Date.valueOf(args[i]);
                    break;
                case Dato.Datatypes.TIME:
                    datosParseados[i] = Time.valueOf(args[i]);
                    break;
                 case Dato.Datatypes.STRING:
                    datosParseados[i] =(args[i]);
                    break;
            }

        }
        return datosParseados;
    }

    boolean validarString(String dato) {
        if (dato.length() == 0) {
            return false;
        }
        return true;
    }
}
