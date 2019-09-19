package it.unical.project.core;

import java.awt.Color;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import it.unical.project.graphics.Animation;
//import it.unical.project.gamemanager.GameManager;
import it.unical.project.graphics.Assets;
import it.unical.project.handler.Handler;
//import it.unical.project.user.interfaces.Sesso;
import it.unical.project.tiles.Tile;
import it.unical.project.user.interfaces.Type;

public class Zombie extends AbstractDynamicObject 
{
	int direction;
	Random random;
	int speed;
	float speed_fast=2.5f;
	
	public Zombie(Handler handler,/*World world,*/ int x, int y/*, Direction direction, int speed, int life*/)
	{
		super(handler, x, y, AbstractDynamicObject.DEFAULT_CREATURE_WIDTH, AbstractDynamicObject.DEFAULT_CREATURE_HEIGHT,Type.ZOMBIE);
		speed=1;
		
		animDown = new Animation(500, Assets.zombie_down);
		animUp = new Animation(500, Assets.zombie_up);
		animLeft = new Animation(500, Assets.zombie_left);
		animRight = new Animation(500, Assets.zombie_right);
		
		random = new Random();
		direction=random.nextInt(3);
		target.x = 30;
		target.y = 0;
		target.height = 50;
		target.width = 25;
	}
	@Override
	public void tick() 
	{
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		
		if( (handler.getWorld().getObjectManager().getPlayer().getCollisionMB(0f, 0f).intersects(getCollisionMB(0f, 0f)) ) )
			insegui();
		else
		{
			setMovement();
		}
		move();
		
	}
	

	private void insegui() 
	{
		int PX=(int) (handler.getWorld().getObjectManager().getPlayer().getX()/Tile.TILEWIDTH) ;
		int PY=(int) (handler.getWorld().getObjectManager().getPlayer().getY()/Tile.TILEWIDTH) ;
	
		if(PX  == ((int) (this.getX()/Tile.TILEWIDTH)) && PY  < ((int) (this.getY()/Tile.TILEWIDTH)) ) //lo zombie sta sotto il player (deve andare su)
			yMove = -speed_fast;
		else if(PX == ((int) (this.getX()/Tile.TILEWIDTH))&& PY > ((int) (this.getY()/Tile.TILEWIDTH))) //zombie sta sopra il player
			yMove = speed_fast;
		else if(PX > ((int) (this.getX()/Tile.TILEWIDTH)) && PY == ((int) (this.getY()/Tile.TILEWIDTH))) // sta a sinistra
			xMove = speed_fast;
		else if(PX <((int) (this.getX()/Tile.TILEWIDTH)) && PY == ((int) (this.getY()/Tile.TILEWIDTH))) //sta a destra
			xMove = -speed_fast;
			
	}
	
	private void setMovement() 
	{
		xMove = 0;
		yMove = 0;
		
		//UP
		if(direction==0)
			yMove = -speed;
		//DOWN
		if(direction==1)
			yMove = speed;
		//LEFT
		if(direction==2)
			xMove = -speed;
		//RIGHT
		if(direction==3)
			xMove = speed;
		
	}


	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);

		/*g.setColor(Color.red);
		g.drawRect((int) (x + target.x),
				(int) (y + target.y),
				target.width, target.height);*/
	}
	
	public void move()
	{
		if(!checkObjectCollisions(xMove, 0f))
			moveX();
		else
			changeMovement();
		if(!checkObjectCollisions(0f, yMove))
			moveY();
		else
			changeMovement();
	}
	
	
	@Override
	public void changeMovement() 
	{
		Random rand;
		rand = new Random();
		direction=rand.nextInt(3);
	}
	
	
	@Override
	public void die()
	{
		handler.getWorld().setZombieNumber(1);
		if(handler.getWorld().getZombieNumber()==0)
			handler.getWorld().getObjectManager().addObject(new Key(handler, this.x, this.y));
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
	
}
