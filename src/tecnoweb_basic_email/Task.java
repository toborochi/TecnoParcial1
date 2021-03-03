package tecnoweb_basic_email;

import java.util.logging.Level;
import java.util.logging.Logger;

class Task implements Runnable    
{ 

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("TASK ENDED");
        } catch (InterruptedException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
