package it.unical.project.core;

import it.unical.project.handler.Handler;
import it.unical.project.user.interfaces.Type;

public abstract class AbstractStaticObject extends AbstractObject
{
	public AbstractStaticObject(Handler handler, float x, float y, int width, int height, Type type)
	{
		super(handler, x, y, width, height,type);
		this.health=1000;
	}
}
