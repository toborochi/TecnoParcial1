package tecnoweb_basic_email;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailTask implements Runnable    
{ 
    SMTP smtp = new SMTP();
    String to,subject;

    public EmailTask(String to,String subject){
        this.to=to;
        this.subject=subject;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("Enviar a: "+this.to+", Query: "+this.subject);
            smtp.connect();
            smtp.logIn();
            
            smtp.sendMail(this.to, this.subject,"Hola");
            
            smtp.logOut();
            smtp.close();
        } catch (IOException ex) {
            Logger.getLogger(EmailTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
