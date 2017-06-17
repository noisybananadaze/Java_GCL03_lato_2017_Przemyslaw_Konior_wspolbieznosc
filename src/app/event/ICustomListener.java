package app.event;

import java.util.EventListener;

@FunctionalInterface
public interface ICustomListener
{
	void handle(Object source, Object info);
}
