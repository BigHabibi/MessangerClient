package materials;


import java.util.Date;
/*
 * The Message Class represents a message unit in the messanger. It contains author, Date of creation and the respective message text. 
 * It can also return a formated MessageString, which can be used to Store Messages in a textfile.
 */
public class Message
{
    String _messageText;
    Date _messageDate;
    boolean _send;
    User _author;
    /*
     * The basic constructor for the Message Class. It requires the author and the message text
     * 
     * @require author != null
     * @require text != null
     */
    public Message(User author, String text, boolean send)
    {
        assert author != null;
        assert text != null;
        
        
        _author = author;
        _messageText = text;
        _messageDate = new Date();
        _send = send;
    }
    public Message(User author, String text, long epoch, boolean send)
    {
        assert author != null;
        assert text != null;
        assert epoch >= 0 ;
        
        
        _author = author;
        _messageText = text;
        _messageDate = new Date(epoch);
        _send = send;
    } 
    
    /*
     * Getter for the message text.
     */
    public String getText()
    {
        return _messageText;
    }
    /*
     * Getter for the author.
     */
    public User getAuthor()
    {
        return _author;
    }
    /*
     * Getter for the creation date.
     */
    public Date getCreationDate()
    {
        return _messageDate;
    }
    public void messagePrint()
    {
        System.out.println(_author.getName()+" said: \""+_messageText+"\"");
    }
    public boolean wasSend()
    {
        return _send;
    }
}