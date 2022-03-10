package persistenz;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;


import java.io.File;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;



import materials.Chat;
import materials.Message;
import materials.User;
/*
 * The StorageManager Class is responsible for saving messages, contacts and the profile on the local device. 
 */
public class StorageManager
{
    
    Document _dom ; 
    User _mainUser;
    public StorageManager()
    {
        _mainUser = getMainuser();
        _dom = getDomForFile("data/Contacts.xml");
    }
    /*
     * getMainUser ist der getter des Nutzers. Ist dieser dem System noch nicht bekannt, wird er geladen
     */
    public User getMainuser()
    {
        if(_mainUser == null)
        {
            
            try 
            {
                Document _userDome = getDomForFile("data/profile.xml");
               
                NodeList profileName = _userDome.getElementsByTagName("name");
                NodeList profileId = _userDome.getElementsByTagName("ID");
                
                Element pName = (Element) profileName.item(0);
                Element pID = (Element) profileId.item(0);
                
                String name = pName.getFirstChild().getNodeValue();
                String ID   = pID.getFirstChild().getNodeValue();

                
                
                _mainUser = new User(name,ID);
            }
            catch (Exception e) 
            {
                e.printStackTrace();
                
                // TODO: profiel retrieval error
            }
            
        }
        return _mainUser;
        
    }
    /* Gibt das DOM einer XML-Datei wieder, dessen Pfad als String pbergeben wird
     * @params filpath ist der Pfad des entsprechenden XML Dokuments  
     */
    private Document getDomForFile(String filepath)
    {
        Document returnDom = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);
        //factory.setValidating(true);
        try
        {
            DocumentBuilder builder = factory.newDocumentBuilder();
            returnDom = builder.parse(filepath);
        }
        catch (ParserConfigurationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (SAXException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return returnDom;
    }
    /*
     * Gibt eine Liste aller Chats aus.
     */
    public List<Chat> getAllChats()
    {
        List<Chat> returnList = new LinkedList<Chat>();
        NodeList chatList = _dom.getElementsByTagName("chat");
        
        for(int i = 0; i < chatList.getLength(); i++)
        {
            Node c = chatList.item(i);
            returnList.add(getChatForNode( (Element) c));
        }
        return returnList;
    }
    /*
     * Die Methode getChatForNode gibt zu einer Chat Node im DOM ein entsprechendes Chat Objekt
     * 
     * @param c Das DOm Chat Element
     * 
     * @assert c.getNodeName() == "chat"
     * 
     * @return Chatobjekt
     */
    private Chat getChatForNode(Element c)
    {
        assert c.getNodeName() == "chat";
        
        NodeList childs = c.getChildNodes();
        
        Element eName = (Element) childs.item(0);
        Node eMassages =  childs.item(1);
        
        String name = eName.getFirstChild().getNodeValue();
        String id = c.getAttribute("id");

        User chatPartner = new User(name,id);
        
        return new Chat(_mainUser, chatPartner, getMessagesForChat(eMassages.getChildNodes(), chatPartner));
     }
    
    private List<Message> getMessagesForChat(NodeList n, User chatpartner)
    {
        
        List<Message> returnList = new LinkedList<Message>();
        for(int i = 0; i< n.getLength(); i++)
        {
            
            Element eMassage = (Element) n.item(i);
            
            NodeList childs = eMassage.getChildNodes();
            
            Element eText       = (Element) childs.item(0);
            Element eSendString = (Element) childs.item(1);
            Element edate       = (Element) childs.item(2);

            String text = eText.getTextContent(); 
            long epochenZeit = Long.parseLong(edate.getTextContent());
            
            if(eSendString.getTextContent().equals("send"))
            {
                returnList.add(new Message(_mainUser, text, epochenZeit));
            }
            else
            {
                returnList.add(new Message(chatpartner, text, epochenZeit));
            }
        }
        
        return returnList;
    }
    public void saveMessage(Message message, String chatId)
    {
        System.out.println("running");
        NodeList cList = _dom.getElementsByTagName("chat");
        Element messageElement = _dom.createElement("message");
        Element textElement = _dom.createElement("text");
        Element typeElement = _dom.createElement("type");
        Element dateElement = _dom.createElement("date");
        
        String type;
        if(message.getAuthor() == _mainUser) type = "send";
        else type = "recive";
        
        textElement.appendChild(_dom.createTextNode(message.getText()));
        typeElement.appendChild(_dom.createTextNode(type));
        dateElement.appendChild(_dom.createTextNode(""+message.getCreationDate().getTime()));
        
        messageElement.appendChild(textElement);
        messageElement.appendChild(typeElement);
        messageElement.appendChild(dateElement);

        
        for(int i = 0; i< cList.getLength();i++) 
        {
            //System.out.println(((Element) cList.item(i)).getAttribute("id"));
            if(((Element) cList.item(i)).getAttribute("id").equals(chatId))
            {
                cList.item(i).getLastChild().appendChild(messageElement);
                System.out.println("testtest");
            }
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        DOMSource source = new DOMSource(_dom);
        StreamResult result = new StreamResult(new File("data/Contacts.xml"));
        try
        {
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        
        }   
        catch (TransformerConfigurationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (TransformerException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
}
