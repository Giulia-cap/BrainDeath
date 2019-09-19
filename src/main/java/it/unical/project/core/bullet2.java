package it.unical.project.core;

import java.awt.Color;
import java.awt.Graphics;

import it.unical.project.graphics.Assets;
import it.unical.project.handler.Handler;
import it.unical.project.tiles.Tile;
import it.unical.project.core.*;
import it.unical.project.user.interfaces.Direction;
import it.unical.project.user.interfaces.Type;


public class bullet2 extends AbstractDynamicObject
{
	private Direction direction;
	
	public bullet2(Handler handler, int x, int y,Direction direction) 
	{
		super(handler, x, y, AbstractDynamicObject.DEFAULT_CREATURE_WIDTH, AbstractDynamicObject.DEFAULT_CREATURE_HEIGHT, Type.BULLET);
		this.speed=4f;
		this.direction=direction;
		borders.x =0;
		borders.y = 0;
		borders.width = 22;
		borders.height = 22;
	}
	@Override
	public void tick() 
	{
		setMovement();
		move();
	}

	private void setMovement() 
	{
		xMove = 0;
		yMove = 0;
		
		//UP
		if(direction==Direction.UP)
			yMove = -speed-10;				//velocità -10
		//DOWN
		if(direction==Direction.DOWN)
			yMove = speed+10;				//velocità +10
		//LEFT
		if(direction==Direction.LEFT)
			xMove = -speed-10;				//velocità -10
		//RIGHT
		if(direction==Direction.RIGHT)
			xMove = speed+10;               //velocità proiettile +10
		
	}
	@Override
	public void render(Graphics g) 
	{
		if(direction==Direction.UP)
		g.drawImage(Assets.bullet2, (int) x, (int) y, null);
		else if(direction==Direction.DOWN)
			g.drawImage(Assets.bullet2, (int) x, (int) y, null);
		else
			g.drawImage(Assets.bullet, (int) x, (int) y, null);
		/*g.setColor(Color.red);
		g.drawRect((int) (x + borders.x),
				(int) (y + borders.y),
				borders.width, borders.height);*/
	}
	
	@Override
	public void die()
	{
		
	}
	
	@Override
	public void move() 
	{
		/*if(!checkObjectCollisions(xMove, 0f))*/
			moveX();
			for(AbstractObject e : handler.getWorld().getObjectManager().getObject())
			{
				if(e.equals(this))
					continue;
				else if(e.equals(handler.getWorld().getObjectManager().getPlayer()))
					continue;
				if(e.getTargetBorders(0f, 0f).intersects(this.getCollisionBorders(xMove, 0f)))
				{
					e.hurt(1);
					active = false;
					return;
				}
			}
		/*if(!checkObjectCollisions(0f, yMove))*/
			moveY();
		//else
			for(AbstractObject e : handler.getWorld().getObjectManager().getObject())
			{
				if(e.equals(this))
					continue;
				else if(e.equals(handler.getWorld().getObjectManager().getPlayer()))
					continue;
				if(e.getTargetBorders(0f, 0f).intersects(this.getCollisionBorders(yMove, 0f)))
				{
					e.hurt(1);
					active = false;
					return;
				}
			}
	}
	
	@Override
	public void changeMovement() {
		active=false;
		
	}
}
	
	
	
	/*@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return null;
	}*/
//	@Override
//	public void update() {
//		// TODO Auto-generated method stub
//		
//	}
