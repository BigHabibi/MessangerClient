package tools.MessageDisplayTool;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import materials.Message;

public class JMessage extends JPanel
{
    Message _displayedMessage;
    
    public JMessage(Message message)
    {
        _displayedMessage = message;
        setLayout(new BorderLayout());
        add(new JLabel(_displayedMessage.getText()),BorderLayout.CENTER);
        add(new JLabel(getTimeString()),BorderLayout.SOUTH);
//        Dimension dim = new Dimension(200, 30);
//        setMaximumSize(dim);
//        setPreferredSize(dim);
        setSize(getPreferredSize());
        
    }
    
    @SuppressWarnings("deprecation")
    private String getTimeString()
    {
        Date today = new Date();
        
        Date messageDate = _displayedMessage.getCreationDate();
        
        if(today.getDay() != messageDate.getDay() || today.getMonth() != messageDate.getMonth() || today.getYear() != messageDate.getYear())
        {
            return messageDate.getDay()+"."+messageDate.getMonth()+"."+messageDate.getYear();
        }
        else
        {
            return messageDate.getHours()+":"+messageDate.getMinutes();
        }
        
    }
    public boolean wasSendByMainUser()
    {
        return _displayedMessage.wasSend();
    }
    public static List<JMessage> convertToJMessage(List<Message> messages)
    {
    	List<JMessage> returnList = new LinkedList<JMessage>();
    	for(Message message: messages)
    	{
    		returnList.add(new JMessage(message));
    	}
    	
    	return returnList;
    }
    
}
