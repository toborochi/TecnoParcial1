package tecnoweb_basic_email;

import Datos.DZTable;
import Datos.Tabla;
import Negocio.NPaciente;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import Negocio.*;

public class Tecnoweb_basic_email {

    public static int MAX_T = 20;

    public static void main(String[] args) throws InterruptedException, IOException {

        /*
        HtmlCanvas html = new HtmlCanvas();
        
        html.h2().content("HOLA");
        List<String> headers = new ArrayList<String>();
        String data[][] = {
            {"hola","nope"},
            {"holdda","xx"}
        };
        headers.add("Hola");
        headers.add("Como estas");
        
    html.table();

    html.tr();
    for (String header : headers) {
        html.th().content(header);
    }
    html._tr();
    for(int i=0;i<data.length;++i){
        String clazz = (i % 2 == 0) ? "even" : "odd";
        html.tr(class_(clazz));
        for(int j=0;j<data[i].length;++j){
            html.td().content(data[i][j]);
        }
        html._tr();
    }
        html._table();
        
        System.out.println(html.toHtml());
         */
        // DZTable dzt = new DZTable();
        NEspecialidad nes = new NEspecialidad();
        System.out.println(nes.crear(new Object[]{"nombre"}));

        int emails = MailSingleton.getInstance().getEmails();
        while (true) {

            int emails_query = MailSingleton.getInstance().getEmails();

            if (emails_query > emails) {
                MailSingleton.getInstance().sendMail(emails + 1, emails_query);
                emails = emails_query;
            }

            System.out.println("Waiting to check new emails...");
            Thread.sleep(8000);

            /*
            Tabla ta = new Tabla(dzt.listar());
            System.out.println(Arrays.toString(ta.nombres));
            for(int i=0;i<ta.getFila();++i){
                for(int j=0;j<ta.getColumna();++j){
                    System.out.print(ta.getData(i, j)+"\t");
                }
                System.out.println("");
            }*/
        }

    }

}
