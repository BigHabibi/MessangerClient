package StartUp; 


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;




import materials.Chat;
import materials.Message;
import materials.User;
import persistenz.StorageManager;

public class StartUpMessanger
{

   

    public static void main(String[] args)
    {
        StorageManager strgmng = new StorageManager();
        
        List<Chat> chatList = strgmng.getAllChats();
        System.out.println(chatList.get(0).getUser().getName());   
           
    }

}
