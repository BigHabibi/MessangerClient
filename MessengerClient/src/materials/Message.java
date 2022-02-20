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
    /*
     * The constructor for the Message Class, that uses a formated message string. 
     * It is used to construct a message object from data in the Storage.
     * 
     * @require isFormatedMessageString(formatedString)
     */
    public Message(String formatedString ) 
    {
        assert isFormatedMessageString(formatedString);
        
        String[] stringParts = formatedString.split("\ufff8\ufff8");
        
        _author = new User(stringParts[0].substring(11), stringParts[0].substring(0,10));
        _messageText = stringParts[1].replace("\ufff7", "\n");
        
        _messageDate = new Date(Long.parseLong(stringParts[2]));
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
    public static boolean isFormatedMessageString(String text) 
    {
        assert text != null;
        
        String[] stringParts = text.split("\ufff8\ufff8");
        if(stringParts.length != 3) return false;
        
        boolean returnValue = stringParts[0].matches("[0-9]{10}#\\w+");
        
        returnValue = returnValue && !stringParts[1].contains("\n") && !stringParts[1].contains("\r");
        return returnValue && stringParts[2].matches("\\d+");
    }
    /*
     * The method checks, if a string is a valid message string. For this it can not contain the substrings
     * "\ufff8\ufff8" and "\ufff7", since they are needed for technical purpose 
     * 
     * @return 
     */
    public static boolean isValidText(String text) 
    {
        return !text.contains("\ufff8\ufff8") && !text.contains("\ufff7");
    }
    /*
     * This method returns a formated String. A formated String is a String representation of a message.
     * 
     * @ensure isFormatedMessageString(result) 
     */
    public String getFormatedString() 
    {
        return _author.getId() + "#" + _author.getName() +"\ufff8\ufff8"+ _messageText.replaceAll("\\R","\ufff7") +"\ufff8\ufff8"+ _messageDate.getTime();
    }
    
    @Override
    public boolean equals(Object o)
    {
       
        if( o instanceof Message) 
        {
            Message message = (Message) o;
            return message.getFormatedString().equals(getFormatedString());
            
        }
        else return false;
    }
    @Override 
    public int hashCode()
    {
        return getFormatedString().hashCode();
    }
}
