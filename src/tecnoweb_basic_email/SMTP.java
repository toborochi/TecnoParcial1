package tecnoweb_basic_email;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SMTP {

    private static final String HOST = "mail.tecnoweb.org.bo";
    private static final int PORT = 25;
    private static final String FROM = "grupo18sc@tecnoweb.org.bo";
    private String COMAND = "";
    private static final String JMP = "\n";
    private Socket socket;
    private BufferedReader input;
    private DataOutputStream output;

    public SMTP() {
        this.socket = null;
        this.input = null;
        this.output = null;
    }

    public void connect() throws IOException {
        this.socket = new Socket(HOST, PORT);
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new DataOutputStream(socket.getOutputStream());
        System.out.println("S: " + this.input.readLine());
    }

    public void close() throws IOException {
        this.socket.close();
        this.input.close();
        this.output.close();
        System.out.println("Connection Closed!");
    }

    public void logIn() throws IOException {
        COMAND = "HELO " + HOST + JMP;
        this.output.writeBytes(COMAND);
        System.out.println("S:" + this.input.readLine());
    }
    
    public void reset() throws IOException {
        COMAND = "RSET " + JMP;
        this.output.writeBytes(COMAND);
        System.out.println("S:" + this.input.readLine());
    }

    public void logOut() throws IOException {
        COMAND = "QUIT" + JMP;
        this.output.writeBytes(COMAND);
        System.out.println("S:" + this.input.readLine());
    }
    
    
    public void sendMail(String RCPT, String SUBJECT, String DATA) throws IOException {
        COMAND = "MAIL FROM: " + FROM + JMP;
        this.output.writeBytes(COMAND);
        System.out.println("S:" + this.input.readLine());
        COMAND = "RCPT TO: " + RCPT + JMP;
        this.output.writeBytes(COMAND);
        System.out.println("S:" + this.input.readLine());
        COMAND = "DATA" + JMP;
        this.output.writeBytes(COMAND);
        System.out.println("S:" + this.input.readLine());
        COMAND = "SUBJECT: " + SUBJECT + JMP;
        this.output.writeBytes(COMAND);
        COMAND = DATA + JMP;
        this.output.writeBytes(COMAND);
        COMAND = "." + JMP;
        this.output.writeBytes(COMAND);
        System.out.println("S:" + input.readLine());
    }

}
