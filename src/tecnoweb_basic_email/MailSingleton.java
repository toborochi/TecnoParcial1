package tecnoweb_basic_email;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailSingleton {

    POP3 pop3 = new POP3();
    SMTP smtp = new SMTP();

    private static MailSingleton single_instance = null;

    // static method to create instance of Singleton class 
    public static MailSingleton getInstance() {
        if (single_instance == null) {
            single_instance = new MailSingleton();
        }

        return single_instance;
    }

    public int getEmails() throws IOException {

        int e = 0;

        pop3.connect();
        pop3.logIn();

        pop3.getListAmmount();
        e = pop3.emails;

        pop3.logOut();
        pop3.close();

        return e;
    }

    public void sendMail(int from, int to) throws IOException, InterruptedException {
        System.out.println("Mensajes por responder: " + (to - from + 1));

        final int MAX_T = 10;
        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        List<EmailTask> emails = new ArrayList<>();

        pop3.connect();
        pop3.logIn();
        for (int i = to; i <= from; ++i) {

            String mensaje = pop3.getMail(Integer.toString(i));
            String emisor = pop3.getFrom(mensaje);
            String subject = pop3.getSubject(mensaje);

            System.out.println(emisor);
            System.out.println(subject);

            emails.add(new EmailTask(emisor, subject));
        }
        pop3.logOut();
        pop3.close();

        List<Future<MailSender>> pending = pool.invokeAll(emails);
        pool.shutdownNow();
        smtp.connect();
        smtp.logIn();
        for (int i = 0; i < pending.size(); ++i) {

            Future<MailSender> future = pending.get(i);
            if (future.isDone()) {
                try {
                    MailSender m = future.get();
                    smtp.sendMail(m.to, m.subject, m.content);
                    smtp.reset();
                } catch (ExecutionException ex) {
                    Logger.getLogger(MailSingleton.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        smtp.logOut();
        smtp.close();
    }
}
