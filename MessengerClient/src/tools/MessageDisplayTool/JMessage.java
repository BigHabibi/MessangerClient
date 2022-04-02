package tools.MessageDisplayTool;

import java.awt.BorderLayout;
import java.util.Date;

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
    
}
