package it.unical.project.core;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//import javax.swing.SingleSelectionModel;

import it.unical.project.graphics.Animation;
import it.unical.project.handler.Handler;
//import it.unical.project.interfaces.BDObject;//import it.unical.project.interfaces.Direction;
//import it.unical.project.interfaces.Moovable;
//import it.unical.project.interfaces.Sesso;
import it.unical.project.tiles.Tile;
import it.unical.project.user.interfaces.Type;


public abstract class AbstractDynamicObject extends AbstractObject //implements Moovable 
{
	//private Direction direction;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64,DEFAULT_CREATURE_HEIGHT = 64;
	
	//ci serve per l'inseguimento
	protected Rectangle movingBorders;
	protected int mb=224;
	
	protected Animation animStop, animDown, animUp, animLeft, animRight;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove;

	public AbstractDynamicObject(Handler handler,float x, float y, int width, int height,Type type) 
	{
		//super(world, x, y);
		super(handler, x, y, width, height,type);
		speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
		
		borders.x = 20;
		borders.y = 44;
		borders.width = 30;
		borders.height = 19;
		
		movingBorders = new Rectangle(DEFAULT_CREATURE_WIDTH-150,DEFAULT_CREATURE_WIDTH-150, mb, mb);
	}
	
	public BufferedImage getCurrentAnimationFrame()
	{
		if(xMove < 0)
		{
			return animLeft.getCurrentFrame();
		}else if(xMove > 0)
		{
			return animRight.getCurrentFrame();
		}else if(yMove < 0)
		{
			return animUp.getCurrentFrame();
		}
		else
		{
			return animDown.getCurrentFrame();
		}
	}
	
	//COLLISION
	
	protected boolean collisionTile(int x, int y)
	{
		return handler.getWorld().getTile(x, y).isSolid();
	}
	
	
	public abstract void move();
	
	public void moveX()
	{
		int wx = (int) (x + xMove + DEFAULT_CREATURE_WIDTH) / Tile.TILEWIDTH; //collisioni con il bordo del mondo
		if(xMove > 0)//Moving right
		{
			int tx = (int) (x + xMove + borders.x + borders.width) / Tile.TILEWIDTH; // /tile perchè vogliamo la coordinata in tile 
			
			if(!collisionBorderWorldX(wx))
			{
				if(!collisionTile(tx, (int) (y + borders.y) / Tile.TILEHEIGHT) &&
					!collisionTile(tx, (int) (y + borders.y + borders.height) / Tile.TILEHEIGHT))
					x += xMove;
				else
				{
					x = tx * Tile.TILEWIDTH - borders.x - borders.width - 1; //SERVE AD ALLINEARE PER BENE L'IMMAGINE(ALTRIMENTI CI SAREBBE UNO SPAZIETTO)
					changeMovement();
				}
			}
			
			else
				changeMovement();
		}
			
			
		else if(xMove < 0)//Moving left
		{
			int tx = (int) (x + xMove + borders.x) / Tile.TILEWIDTH;
			
			if(!collisionBorderWorldX(wx))
			{
				if(!collisionTile(tx, (int) (y + borders.y) / Tile.TILEHEIGHT) && !collisionTile(tx, (int) (y + borders.y + borders.height) / Tile.TILEHEIGHT))
				{
					x += xMove;
				}
				else
				{
					x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - borders.x;
					changeMovement();
				}
			}
			else
				changeMovement();
		}
	}

	public abstract void changeMovement();

	public void moveY()
	{
		int wy = (int) (y + yMove + DEFAULT_CREATURE_HEIGHT) / Tile.TILEWIDTH;
		if(yMove < 0)//Up
		{
			int ty = (int) (y + yMove + borders.y) / Tile.TILEHEIGHT;
			
			if(!collisionBorderWorldY(wy))
			{
				if(!collisionTile((int) (x + borders.x) / Tile.TILEWIDTH, ty) &&
					!collisionTile((int) (x + borders.x + borders.width) / Tile.TILEWIDTH, ty)){
					y += yMove;
				}
				else
				{
					y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - borders.y;
					changeMovement();
				}
			}
			else
				changeMovement();
		}
		else if(yMove > 0)//DOWN
		{
			int ty = (int) (y + yMove + borders.y + borders.height) / Tile.TILEHEIGHT;
			
			if(!collisionBorderWorldY(wy))
			{
				if(!collisionTile((int) (x + borders.x) / Tile.TILEWIDTH, ty) &&
					!collisionTile((int) (x + borders.x + borders.width) / Tile.TILEWIDTH, ty)){
					y += yMove;
				}
				else
				{
					y = ty * Tile.TILEHEIGHT - borders.y - borders.height - 1;
					changeMovement();
				}
			}
			else
				changeMovement();
		}
	}
	
	
	
	public Rectangle getCollisionMB(float xOffset, float yOffset){
		return new Rectangle((int) (x + movingBorders.x + xOffset), (int) (y + movingBorders.y + yOffset), movingBorders.width, movingBorders.height);
	}

	
	public boolean collisionBorderWorldY(int i)
	{
		if(i!=0 && i!=(handler.getWorld().getHeight()))
			return false;
		else 
			return true;
	}
	public boolean collisionBorderWorldX(int i)
	{
		if(i!=0 && i!=(handler.getWorld().getWidth()))
			return false;
		else 
			return true;
	}
	
	//GETTERS SETTERS

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/*@Override
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;// controlli in seguito
	}

	@Override
	public String toString() {
		return "AbstractDynamicObject [direction=" + direction + ", speed=" + speed + ", toString()=" + super.toString()
				+ "]";
	}

	public Sesso getSesso() {
		return sesso;
	}

	public void setSesso(Sesso sesso) {
		this.sesso = sesso;
	}

	public int getLife() {
		return life;
	}

	public void removeOneLife() {
		if (life > 0)
			life--;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	@Override
	public void update() {/// fix speed mai magg uguale num
		switch (getDirection()) {
		case UP:
			if (getX() == 0) {
				if (this instanceof Bullet)
					setVisible(false);
				return;
			}
			setX(getX() - getSpeed());
			break;
		case DOWN:
			if (getX() == (world.getHeight() - 1)) {
				if (this instanceof Bullet)
					setVisible(false);
				return;
			}
			setX(getX() + getSpeed());
			break;
		case LEFT:
			if (getY() == 0) {
				if (this instanceof Bullet)
					setVisible(false);
				return;
			}
			setY(getY() - getSpeed());
			break;
		case RIGHT:
			if (getY() == (world.getWidth() - 1)) {
				if (this instanceof Bullet)
					setVisible(false);
				return;
			}
			setY(getY() + getSpeed());
			break;

		default:
			break;
		}

	}

	public boolean intersect(BDObject other) {// da controllare bordi tutti
		if (this.getX() == 0 && this.getY() == 0) {
			if (this.getDirection().equals(Direction.RIGHT) && other.getX() == this.getX()
					&& other.getY() == (this.getY() + 1))
				return true;
			if (this.getDirection().equals(Direction.DOWN) && other.getX() == (this.getX() + 1)
					&& other.getY() == this.getY())
				return true;
		}
		if (this.getX() == 0 && this.getY() == (world.getWidth() - 1)) {
			if (this.getDirection().equals(Direction.LEFT) && other.getX() == this.getX()
					&& other.getY() == (this.getY() - 1))
				return true;
			if (this.getDirection().equals(Direction.DOWN) && other.getX() == (this.getX() + 1)
					&& other.getY() == this.getY())
				return true;
		}
		if (this.getX() == (world.getHeight() - 1) && this.getY() == (world.getWidth() - 1)) {
			if (this.getDirection().equals(Direction.LEFT) && other.getX() == this.getX()
					&& other.getY() == (this.getX() - 1))
				return true;
			if (this.getDirection().equals(Direction.UP) && other.getX() == (this.getX() - 1)
					&& other.getY() == this.getY())
				return true;
		}
		if (this.getX() == (world.getWidth() - 1) && this.getY() == 0) {
			if (this.getDirection().equals(Direction.RIGHT) && other.getX() == this.getX()
					&& other.getY() == (this.getY() + 1))
				return true;
			if (this.getDirection().equals(Direction.UP) && other.getX() == (this.getX() - 1)
					&& other.getY() == this.getY())
				return true;
		}
		if (this.getX() == 0 && this.getY() != 0 && this.getY() != (world.getWidth() - 1)) {
			if (this.getDirection().equals(Direction.LEFT) && other.getX() == this.getX()
					&& other.getY() == (this.getY() - 1))
				return true;
			if (this.getDirection().equals(Direction.RIGHT) && other.getX() == this.getX()
					&& other.getY() == (this.getY() + 1))
				return true;
			if (this.getDirection().equals(Direction.DOWN) && other.getX() == (this.getX() + 1)
					&& other.getY() == this.getY())
				return true;
		}
		if (this.getY() == 0 && this.getX() != 0 && this.getX() != (world.getHeight() - 1)) {
			if (this.getDirection().equals(Direction.RIGHT) && other.getX() == this.getX()
					&& other.getY() == (this.getY() + 1))
				return true;
			if (this.getDirection().equals(Direction.UP) && other.getX() == (this.getX() - 1)
					&& other.getY() == this.getY())
				return true;
			if (this.getDirection().equals(Direction.DOWN) && other.getX() == (this.getX() + 1)
					&& other.getY() == this.getY())
				return true;
		}
		if (this.getX() == (world.getHeight() - 1) && this.getY() != 0 && this.getY() != world.getWidth()) {
			if (this.getDirection().equals(Direction.UP) && other.getX() == (this.getX() - 1)
					&& other.getY() == this.getY())
				return true;
			if (this.getDirection().equals(Direction.LEFT) && other.getX() == this.getX()
					&& other.getY() == (this.getY() - 1))
				return true;
			if (this.getDirection().equals(Direction.RIGHT) && other.getX() == this.getX()
					&& other.getY() == (this.getY() + 1))
				return true;
		}
		if (this.getY() == (world.getWidth() - 1) && this.getX() != 0 && this.getX() != (world.getHeight() - 1)) {
			if (this.getDirection().equals(Direction.LEFT) && other.getX() == this.getX()
					&& other.getY() == (this.getY() - 1))
				return true;
			if (this.getDirection().equals(Direction.DOWN) && other.getX() == (this.getX() + 1)
					&& other.getY() == this.getY())
				return true;
			if (this.getDirection().equals(Direction.UP) && other.getX() == (this.getX() - 1)
					&& other.getY() == this.getY())
				return true;
		}
		if (this.getX() != 0 && this.getY() != 0 && this.getX() != (world.getHeight() - 1)
				&& this.getY() != (world.getWidth() - 1)) {
			if (this.getDirection().equals(Direction.RIGHT) && other.getX() == this.getX()
					&& other.getY() == (this.getY() + 1))
				return true;
			if (this.getDirection().equals(Direction.LEFT) && other.getX() == this.getX()
					&& other.getY() == (this.getY() - 1))
				return true;
			if (this.getDirection().equals(Direction.DOWN) && other.getX() == (this.getX() + 1)
					&& other.getY() == this.getY())
				return true;
			if (this.getDirection().equals(Direction.UP) && other.getX() == (this.getX() - 1)
					&& other.getY() == this.getY())
				return true;
		}
		return false;

	}

	public char checkWall() { 
		char up = 'u', down = 'd', left = 'l', right = 'r',corner1='a',corner2='b',corner3='c',corner4='f';
		if(getX() == (world.getHeight() - 1) && getY() == (world.getWidth() - 1) )
			return corner4;
		if(getX() == 0 && getY() == (world.getWidth() - 1))
			return corner2;
		if(getX() == 0 && getY() == 0 )
			return corner1;
		if(getX() == (world.getHeight() - 1) && getY() == 0)
			return corner3;
		if (getX() == 0)
			return up;
		if(getY() == 0)
			return left;
		if(getX() == (world.getHeight() - 1))
			return down;
		if(getY() == (world.getWidth() - 1))
			return right;
		return 0;
	}*/

}
