
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


public class Tecnoweb_basic_email {

    public static int MAX_T=20;
    
    public static void main(String[] args) throws InterruptedException, IOException {

       
        
        int emails = MailSingleton.getInstance().getEmails(); 
        while(true){
            //  npac.validarDatos(new String[]{"123","Juan Perez","20290-12-01","75022"});
            int emails_query =MailSingleton.getInstance().getEmails();
            
            if(emails_query>emails){
                MailSingleton.getInstance().sendMail(emails+1,emails_query);
                emails=emails_query;
            }
            
            System.out.println("Waiting to check new emails...");
            Thread.sleep(10000);
            
           
        }
        
    }
    
}

