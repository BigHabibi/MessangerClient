package tools.MessageDisplayTool;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import tools.ObservabelSubtool;

public class MessageDisplayToolUI extends ObservabelSubtool
{
    private JPanel _mainPanel;
    private JMessageBoard _messageBoard;
    
    public MessageDisplayToolUI()
    {
        _mainPanel = createMainPanel();
    }
    private JPanel createMainPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        _messageBoard = new JMessageBoard();
        panel.add(new JScrollPane(_messageBoard), BorderLayout.CENTER);
        
        
        return panel;
        
    }
    public JPanel getUIPanel()
    {
        return _mainPanel;
    }
    public JMessageBoard getMessageBoard() 
    {
        return _messageBoard;
    }

}
