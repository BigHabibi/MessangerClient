package materials;

import java.util.List;

public class Chat
{
    User _chatPartner;
    User _mainUser;
    List<Message> _messages;
    
    public Chat(User _mainUser, User chatPartner, List<Message> messages) 
    {
        _chatPartner = chatPartner;
        _messages = messages;
    }

    public User getUser()
    {
        return _chatPartner;
    }
    public boolean isValidMessage(Message message) 
    {
        return _chatPartner.equals(message.getAuthor()) || _mainUser.equals(message.getAuthor());
    }
    
    
}
