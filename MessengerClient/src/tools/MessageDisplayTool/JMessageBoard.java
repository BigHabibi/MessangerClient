package tools.MessageDisplayTool;

import java.awt.Component;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class JMessageBoard extends JComponent
{
    List<JMessage> _messages;
    
    public JMessageBoard() 
    {
        GridLayout layout = new GridLayout(20,2);
        
    	setLayout(layout);

    }
    public void displayMessages(List<JMessage> messages)
    {
    	clearBoard();
    	_messages = messages;
    	System.out.println("start display Message");
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
    		System.out.println("Added");
    	}
    	System.out.println("end display Messages");
    	this.revalidate();
    }
    private void clearBoard() 
    {
    	System.out.println("clearing"+this.getComponents().length+"components");
    	for(Component c: this.getComponents())
    	{
    		remove(c);
    	}
    }
}
