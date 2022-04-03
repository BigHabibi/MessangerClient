package tools.MessageDisplayTool;

import java.util.List;

import javax.swing.JPanel;

import materials.Message;
import tools.ObservabelSubtool;


public class MessageDisplayTool 
{
    private MessageDisplayToolUI _ui;
    private List<Message> _displayedMessages;
    
    public MessageDisplayTool() 
    {
        _ui = new MessageDisplayToolUI();
    }
    public JPanel getUIPanel()
    {
        // TODO Auto-generated method stub
        return _ui.getUIPanel();   
    }
    public void setMessages(List<Message> messages)
    {
    	_displayedMessages = messages;
    	JMessageBoard board =_ui.getMessageBoard();
        System.out.println("board geholt");
    	
    	board.displayMessages(JMessage.convertToJMessage( _displayedMessages));
    }
    

}
