package tecnoweb_basic_email;


public class MailSender {
    String to,subject,content;
    MailSender(String to,String subject,String content){
        this.to=to;
        this.subject=subject;
        this.content=content;
    }
}
