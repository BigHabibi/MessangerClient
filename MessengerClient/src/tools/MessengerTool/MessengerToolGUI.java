package tools.MessengerTool;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class MessengerToolGUI
{
    private JFrame _frame;
   
    
    public MessengerToolGUI(JPanel chatSelectionPanel, 
            JPanel messageDisplayPanel, JPanel messageEnterPanel) 
    {
        _frame = new JFrame("Messanger");
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.getContentPane().setLayout(new BorderLayout());
        
        JComponent leftPanel = chatSelectionPanel;
        JComponent rightPanel = createChatPanel(messageDisplayPanel, messageEnterPanel);
        JSplitPane splitter = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                leftPanel, rightPanel);
        _frame.getContentPane().add(splitter, BorderLayout.CENTER);
        
        
    }


    private JComponent createChatPanel(JPanel messageDisplayPanel,
            JPanel messageEnterPanel)
    {
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());
        
        rightPanel.add(messageDisplayPanel, BorderLayout.CENTER);
        rightPanel.add(messageEnterPanel, BorderLayout.SOUTH);
        
        return rightPanel;
    }
    
    public void showWindow()
    {
        _frame.setSize(1200, 900);
        _frame.setVisible(true);
    }
    
    public void closeWindow()
    {
        _frame.dispose();
    }


	public void refreshFrame() {
		_frame.repaint();
		
	}
}
