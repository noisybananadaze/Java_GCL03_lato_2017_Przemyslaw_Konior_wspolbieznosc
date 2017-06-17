package app.event;

import java.util.ArrayList;
import java.util.List;

public class CustomEvent
{
	private List<ICustomListener> listeners = new ArrayList<>();
	
	public void fire( Object source, Object info )
	{
		for( ICustomListener el : this.listeners )
			el.handle( source, info );
	}
	
	public void add( ICustomListener listener )
	{
		this.listeners.add( listener );
	}

	public void remove( ICustomListener listener )
	{
		this.listeners.remove( listener );
	}
}
