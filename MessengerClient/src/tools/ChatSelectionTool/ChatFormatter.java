package tools.ChatSelectionTool;

import materials.Chat;
/*
 * Formatter for a {@link Chat}
 * 
 * @author Bernard Burdiek
 * @version 1.0
 */
public class ChatFormatter
{
    private Chat _chat;
    
    /*
     * Initializes a Formatter vor a given Chat
     * 
     * @param chat the Chat that is displayed by the Formatter
     */
    public ChatFormatter(Chat chat)
    {
        _chat = chat;
    }
    /*
     * returns the displayed chat
     */
    public Chat getChat()
    {
        return _chat;
    }
    @Override
    public String toString()
    {
        return  _chat.getChatpartner().getName();
    }
}
