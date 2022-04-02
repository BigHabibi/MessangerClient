package materials;

import java.util.List;

public class Chat
{
    User _chatPartner;
    User _mainUser;
    List<Message> _messages;
    
    public Chat(User mainUser, User chatPartner, List<Message> messages) 
    {
        _mainUser = mainUser;
        _chatPartner = chatPartner;
        _messages = messages;
    }

    public User getChatpartner()
    {
        return _chatPartner;
    }
    public boolean isValidMessage(Message message) 
    {
        return _chatPartner.equals(message.getAuthor()) || _mainUser.equals(message.getAuthor());
    }
    public void printChat()
    {
        if(_mainUser != null) System.out.println("Chat zwischen"+_mainUser.getName()+" und "+_chatPartner.getName());
        else System.out.println("ERROR");  
        for(Message m: _messages) 
        {
            m.messagePrint();
        }
    }
    
}
