package materials;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class MessageTest
{
    User u = new User("Hans","5765765675");
    @Test
    public void testConstructor() 
    {
        Message m1 = new Message(u, "Moin!!!");
        
        Message m2 = new Message(m1.getFormatedString());
        
        assertEquals(m1, m2);
    }
    @Test
    public void testFormatedString() 
    {
        Message m1 = new Message(u, "Moin!!!");
        Message m2 = new Message(u, "HAOF!!!");
        Message m3 = new Message(u, "Maul!!!");
        
        String s1 = "1234567859#Hans\ufff8\ufff8 hefuheofoefo\ufff8\ufff81631822583182";//true
        String s2 = "123456789#Hans\ufff8\ufff8 hefuheofoefo\ufff8\ufff1631822583182";//false
        String s3 = "123456d789#__Hans\ufff8\ufff8 hefuheofoefo\ufff8\ufff1631822583182";//false
        
        assertTrue(Message.isFormatedMessageString(m1.getFormatedString()));
        assertTrue(Message.isFormatedMessageString(m2.getFormatedString()));
        assertTrue(Message.isFormatedMessageString(m3.getFormatedString()));
        
        assertTrue(Message.isFormatedMessageString(s1));
        assertFalse(Message.isFormatedMessageString(s2));
        assertFalse(Message.isFormatedMessageString(s3));
    }
    @Test 
    public void testValidMessageText() 
    {
        String s1 = "1heoregoijerogjeorjg2";//true
        String s2 = "123456789#Hans\ufff8\ufff8 hefu\ufff7heofoefo\ufff8\ufff1631822583182";//false
        
        assertTrue(Message.isValidText(s1));
        assertFalse(Message.isValidText(s2));
    }
}
