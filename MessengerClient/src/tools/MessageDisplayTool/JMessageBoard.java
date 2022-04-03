package tools.MessageDisplayTool;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class JMessageBoard extends JComponent
{
    List<JMessage> _messages;
    
    public JMessageBoard() 
    {
        setLayout(new GridLayout(0,2));
    }
    public void displayMessages(List<JMessage> messages)
    {
    	clearBoard()
    	_messages = messages;
    	
    	for(JMessage m: _messages)
    	{
    		if(m.wasSendByMainUser())
    		{
    			add(new JLabel());
    			add(m);
    		}
    		else
    		{
    			add(m);
    			add(new JLabel());
    		}
    	}
    }
    private void clearBoard() 
    {
    	for(JComponent c: getComponents())
    	{
    		remove(c);
    	}
    }
}
