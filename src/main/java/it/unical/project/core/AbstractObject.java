package it.unical.project.core;

import java.awt.Graphics;
import java.awt.Rectangle;

import it.unical.project.handler.Handler;
import it.unical.project.user.interfaces.Type;
//import it.unical.project.interfaces.BDObject;

public abstract class AbstractObject// implements BDObject 
{
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle borders;
	
	protected Type type;
	//ci serve per le collisioni con il colpo
	protected Rectangle target; 
	public static final int DEFAULT_HEALTH = 10;
	protected int health;
	//BOOLEANA CHE CONTROLLA SE L'OGGETTO E' ANCORA PRESENTE NEL GIOCO
	protected boolean active = true;

	//protected World world;

	public AbstractObject(Handler handler, float x, float y,int width, int height,Type type) 
	{
	//	this.world = world;
//		if (x < 0 || y < 0 || y >= world.getHeight() || x >= world.getWidth()) {
//			throw new IllegalArgumentException();//va gestito
//		}
		this.type=type;
		health = 1;
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		borders = new Rectangle(0, 0, width, height);
		target=new Rectangle(0,0, width, height);
	}
	
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void hurt(int v) //HURT=FERIRE
	{
		health -= v; //SOTTRAGGO UNA CERTA QUANTITà AL MIO STATO DI SALUTE
		if(health <= 0) //SE LA SALUTE E' A 0 L'OGGETTO DEVE SCOMPARIRE DAL GIOCO
		{	
			active = false;
			die();
		}
	}
	
	public abstract void die();

	
	public boolean checkObjectCollisions(float xOffset, float yOffset)
	{
		for(AbstractObject e : handler.getWorld().getObjectManager().getObject())
		{
			if(e.equals(this))
				continue;
			if(e.getType()!=Type.KEY)
				if(e.getCollisionBorders(0f, 0f).intersects(getCollisionBorders(xOffset, yOffset)))
					return true;
		}
		return false;
	}
	
	public boolean house(float xOffset, float yOffset)
	{
		for(AbstractObject e : handler.getWorld().getObjectManager().getObject())
		{
			if(e.getType()==Type.HOUSE)
				if(e.getCollisionBorders(0f, 0f).intersects(getCollisionBorders(xOffset, yOffset)))
					return true;
		}
		return false;
	}	
	
	
	public Rectangle getCollisionBorders(float xOffset, float yOffset){
		return new Rectangle((int) (x + borders.x + xOffset), (int) (y + borders.y + yOffset), borders.width, borders.height);
	}
	
	public Rectangle getTargetBorders(float xOffset, float yOffset){
		return new Rectangle((int) (x + target.x + xOffset), (int) (y + target.y + yOffset), target.width, target.height);
	}


	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);


	/*public World getWorld() {
		return world;
	}
	
	
	public void setWorld(World world) {
		this.world = world;
	}*/
	
	/*@Override
	public boolean intersect(BDObject other) {
		if (other.getX() == x + 1 && other.getY() == y) {
			return true;
		} else if (other.getX() == x && other.getY() == y + 1) {
			return true;
		} else if (other.getX() == x - 1 && other.getY() == y + 1) {
			return true;
		} else if (other.getX() == x && other.getY() == y - 1) {
			return true;
		}
		return false;

	}*/

//	public abstract void update();
}





/*public void setX(int x) {
	if (x < 0 || x >= world.getWidth())
		throw new IllegalArgumentException();// da gestire
	this.x = x;
}

public void setY(int y) {
	if (y < 0 || y >= world.getHeight())
		throw new IllegalArgumentException();
	this.y = y;

}*/
