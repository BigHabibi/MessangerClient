package persistenz;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
                
                String name = pName.getAttribute("value");
                String ID = pID.getAttribute("value");

                
                
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
    private Document getDomForFile(String filepath)
    {
        Document returnDom = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
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
    private Chat getChatForNode(Element c)
    {
        NodeList childs = c.getChildNodes();
        
        Element eName = (Element) childs.item(0);
        String name = eName.getFirstChild().getNodeValue();
        
        String id = c.getAttribute("id");

        Node eMassages =  childs.item(1);
        
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
    
    
}
