package tecnoweb_basic_email;


import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MailSingleton {
    
    static final int MAX_T = 10;    
    ExecutorService pool = Executors.newFixedThreadPool(MAX_T);   
    POP3 pop3 = new POP3();
    SMTP smtp = new SMTP();
    
    
    private static MailSingleton single_instance = null; 
    
    // static method to create instance of Singleton class 
    public static MailSingleton getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new MailSingleton(); 
  
        return single_instance; 
    } 
    
    
    public int getEmails() throws IOException{
        
        int e = 0;
        
        pop3.connect();
        pop3.logIn();
        
        pop3.getListAmmount();
        e = pop3.emails;
        
        //String m = pop3.getMail(Integer.toString(e));
        //System.out.println(pop3.getFrom(m));
        
        pop3.logOut();
        pop3.close();
        
        return e;
    }
    
    public void sendMail(int from,int to) throws IOException{
        System.out.println("Mensajes por responder: "+(to-from+1));
        pop3.connect();
        pop3.logIn();
        for(int i=to;i<=from;++i){
            System.out.println("Respondiendo a "+i);
            
            String mensaje = pop3.getMail(Integer.toString(i));
            System.out.println("Mensaje"+mensaje);
            String emisor = pop3.getFrom(mensaje);
            String subject = pop3.getSubject(mensaje);
            
            Runnable task = new EmailTask(emisor, subject);
            pool.execute(task);
            
        }
        pop3.logOut();
        pop3.close();
    }
}
