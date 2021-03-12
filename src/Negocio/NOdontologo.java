package Negocio;

import Datos.DOdontologo;
import Datos.DUsuario;
import Datos.Tabla;
import java.sql.Date;

/**
 *
 * @author Grupo18
 */
public class NOdontologo extends Negocio {

    public final String NOMBRE_TABLA = "odontologo";
    private DOdontologo dato;
    private DUsuario user;

    public NOdontologo() {
        super(new DOdontologo());
    }

    public String getReporteGenero() {
        Tabla miTable=dato.getReporteGenero();
        String m=miTable.getData(0, 1);
        String f=miTable.getData(1, 1);
        String reporteChart = "Content-Type: text/html; charset=\"UTF-8\"\n"
                        + "<img \n"
                        + "     style=\"-webkit-user-select: none; display: block; margin: auto; padding: env(safe-area-inset-top) env(safe-area-inset-right) env(safe-area-inset-bottom) env(safe-area-inset-left); cursor: zoom-in;\"\n"
                        + "     src=\"https://quickchart.io/chart?bkg=white&amp;c={\n"
                        + "          type:%27pie%27,\n"
                        + "          data:{\n"
                        + "          labels:['hombres', 'mujeres'],\n"
                        + "          datasets:[{\n"
                        + "            label:%27Odóntologos%27,data:["+m+","+f+"]\n"
                        + "              }]}\n"
                        + "          }\" \n"
                        + "      width=\"623\" \n"
                        + "     height=\"373\">";
        
        return reporteChart;
    }
}
