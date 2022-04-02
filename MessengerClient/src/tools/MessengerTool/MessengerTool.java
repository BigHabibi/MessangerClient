package tools.MessengerTool;

import materials.User;
import persistenz.StorageManager;
import tools.ChatSelectionTool.ChatSelectionTool;
import tools.MessageDisplayTool.MessageDisplayTool;
import tools.MessageEnterTool.MessageEnterTool;

public class MessengerTool
{
    private User _mainUser;
    private StorageManager _storageManager;
    private MessengerToolGUI _ui;
    
    private ChatSelectionTool _chatSelectionTool;
    private MessageDisplayTool _messageDisplayTool;
    private MessageEnterTool _messageEnterTool;
    
    public MessengerTool(StorageManager storageManager) 
    {
        assert storageManager != null ;
        
        _mainUser = storageManager.getMainuser();
        _storageManager = storageManager;
        
        _chatSelectionTool = new ChatSelectionTool(_storageManager.getAllChats());
        _messageDisplayTool = new MessageDisplayTool();
        _messageEnterTool = new MessageEnterTool();
        
        createListenerForSubtools();
        
        _ui = new MessengerToolGUI(_chatSelectionTool.getUIPanel(),
                _messageDisplayTool.getUIPanel(),
                _messageEnterTool.getUIPanel());
        
        _ui.showWindow();
        
        
    }

    private void createListenerForSubtools()
    {
        // TODO Listener Implementation
        
    }
}
