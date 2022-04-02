package tools.ChatSelectionTool;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import materials.Chat;

public class ChatSelectionToolGUI
{
    private JPanel _mainPanel;
    private JList<ChatFormatter> _chatSelectionList;
    
    /*
     * Initializes the UI
     */
    public ChatSelectionToolGUI(List<Chat> chatList)
    {
        _chatSelectionList = new JList<ChatFormatter>();
        loadChatFormatter(chatList);
        _mainPanel = createMainPanel();
    }
    /*
     * Creates the Panel with the Widgets
     */
    private JPanel createMainPanel()
    {
        //Setting up the Panel with a Borderlayout
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0,5));
        panel.add(new JLabel("Chats"),BorderLayout.NORTH);//Heading
        
        _chatSelectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        panel.add(new JScrollPane(_chatSelectionList),BorderLayout.CENTER);
        
        return panel;
    }
    /*
     * Loads a given List of Chats 
     * @require chatList != null
     */
    private void loadChatFormatter(List<Chat> chatList)
    {
        assert chatList != null: "Assert Failed: chatList != null";
        
        ChatFormatter[] varray = new ChatFormatter[chatList.size()];
        
        for(int i = 0; i< chatList.size(); i++)
        {
            varray[i] = new ChatFormatter(chatList.get(i));
        }
        _chatSelectionList.setListData(varray);
        _chatSelectionList.setSelectedIndex(0);
        
    }
    /*
     * returns the JPanel that of the UI Component
     */
    public JPanel getUIPanel()
    {
        return _mainPanel;
    }
    /*
     * Returns the JList of Chatformatter
     */
    public JList<ChatFormatter> getChatSelectionList()
    {
        return _chatSelectionList;
    }

}
