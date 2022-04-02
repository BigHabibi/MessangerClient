package tools.ChatSelectionTool;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import materials.Chat;
import tools.ObservabelSubtool;
/*
 * This tool allows the user to select a Chat
 * 
 * The Tool is a Subtool of MessengerTool
 */
public class ChatSelectionTool extends ObservabelSubtool
{
    ChatSelectionToolGUI _ui;
 
    private List<Chat> _chatList;
    /*
     * Creates a chat SelectionTool for a given ChatList
     */
    public ChatSelectionTool(List<Chat> chatList) 
    {
        _chatList = chatList;
        _ui = new ChatSelectionToolGUI(_chatList);
        
        registerUIAktions();
        
    }
    /*
     * This message is called wenn a chat was selected and currently just calls the informAbotChanges Method
     * to notify the observers about a change
     */
    private void chatWasSelected()
    {
        informAboutChange();
        System.out.println("Selected Chat!");
    }
    /*
     * Returns the Selected Chat. Returns null if no chat has been selected.
     */
    public Chat getSelectedChat()
    {
        Chat result = null;
        ChatFormatter adapter = _ui.getChatSelectionList().getSelectedValue();
        if(adapter != null)
        {
            result = adapter.getChat();
        }
        return result;
    }
    /*
     * Connects the functinal actions with the interactionelements of the UI 
     */
    private void registerUIAktions()
    {
        _ui.getChatSelectionList().addListSelectionListener(
                new ListSelectionListener()
                {
                    @Override
                    public void valueChanged(ListSelectionEvent event)
                    {
                        if(!event.getValueIsAdjusting())
                        {
                            chatWasSelected();
                        }
                    }
                });
        
    }
    /*
     * Returns the UIpanel of the tool
     */
    public JPanel getUIPanel()
    {
        return _ui.getUIPanel();
    }

}
