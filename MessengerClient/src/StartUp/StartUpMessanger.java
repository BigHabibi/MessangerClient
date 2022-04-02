package StartUp; 


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.swing.SwingUtilities;


import materials.Chat;
import materials.Message;
import materials.User;
import persistenz.StorageManager;
import tools.MessengerTool.MessengerTool;

public class StartUpMessanger
{

   

    public static void main(String[] args)
    {
        StorageManager strgmng = new StorageManager();
        
        
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new MessengerTool(strgmng);
            }
        });
       
           
    }

}
