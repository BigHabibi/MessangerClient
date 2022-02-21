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
    User _author;
    /*
     * The basic constructor for the Message Class. It requires the author and the message text
     * 
     * @require author != null
     * @require text != null
     */
    public Message(User author, String text)
    {
        assert author != null;
        assert text != null;
        
        
        _author = author;
        _messageText = text;
        _messageDate = new Date();
    }
    public Message(User author, String text, long epoch)
    {
        assert author != null;
        assert text != null;
        assert epoch >= 0 ;
        
        
        _author = author;
        _messageText = text;
        _messageDate = new Date(epoch);
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
    /*
     * Checks if a String is a formated String. 
     * 
     * @require text != null
     */
 
}