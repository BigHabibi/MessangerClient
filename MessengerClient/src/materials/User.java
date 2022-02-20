package materials;
/*
 * This Class represents the 
 */
public class User
{
    private String _name;
    private String _ID;
 // TODO Kommentare hinzufügen
    
    public User(String name, String ID) 
    {
        assert isValidName(name);
        assert isValidID(ID);
        
        _name = name;
        _ID = ID;
    }
    public String getId()
    {
        return _ID;
    }
    public static boolean isValidID(String ID) 
    {
        return ID.matches("\\d{10}");
    }
    public static boolean isValidName(String name) 
    {
        return name.matches("[a-zA-Z0-9_ ]+");
    }
    public String getName()
    {
        
        return _name;
    }

}
