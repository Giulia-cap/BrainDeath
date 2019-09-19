package it.unical.project.core;

import java.awt.Color;
import java.awt.Graphics;

import it.unical.project.graphics.Assets;
import it.unical.project.handler.Handler;
import it.unical.project.user.interfaces.Type;

public class Key extends AbstractStaticObject 
{
	public Key(Handler handler, float x, float y) 
	{
		super(handler, x, y, 32, 32,Type.KEY);
		
		this.width=15;
		this.height=10;
		borders.x =50;
		borders.y = 60;
		borders.width = width*2;
		borders.height =height*2;
	}

//	@Override
//	public void update() {
//		throw new UnsupportedOperationException();
//		
//	}
	@Override
	public void tick() 
	{
		if(this.getCollisionBorders(x, y).intersects(handler.getWorld().getObjectManager().getPlayer().getCollisionBorders(handler.getWorld().getObjectManager().getPlayer().getX(), handler.getWorld().getObjectManager().getPlayer().getY()))) {
				this.die(); }
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Assets.key,(int) x, (int) y, 125, 125, null);
		/*g.setColor(Color.red);
		g.fillRect((int) (x + borders.x),
				(int) (y + borders.y),
				borders.width, borders.height);*/
	}
	
	@Override
	public void die()
	{
		active=false;
		handler.getWorld().getObjectManager().getPlayer().setKey(true);
	}
	
}
