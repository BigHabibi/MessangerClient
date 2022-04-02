package tools;

import java.util.HashSet;
import java.util.Set;

public abstract class ObservabelSubtool
{
    Set<Observer> _observers;
    
    public ObservabelSubtool()
    {
        _observers = new HashSet<Observer>();
    }
    
    public void observ(Observer newObserver)
    {
        assert newObserver != null;
        
        _observers.add(newObserver);
    }
    
    public void removeObserver(Observer remObserver)
    {
        assert remObserver != null;
        
        _observers.remove(remObserver);
    }
    
    public void informAboutChange() 
    {
        for(Observer observer: _observers)
        {
            observer.reactToChange();
        }
    }
}
