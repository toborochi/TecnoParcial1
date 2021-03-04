
package tecnoweb_basic_email;


import java.io.IOException;


public class Tecnoweb_basic_email {

    public static int MAX_T=20;
    
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
        
        
        
        int emails = MailSingleton.getInstance().getEmails(); 
        while(true){
            
            int emails_query =MailSingleton.getInstance().getEmails();
            
            if(emails_query>emails){
                MailSingleton.getInstance().sendMail(emails+1,emails_query);
                emails=emails_query;
            }
            
            System.out.println("Waiting to check new emails...");
            Thread.sleep(8000);
        }
        
    }
    
}

