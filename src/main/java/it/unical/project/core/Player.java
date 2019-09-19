package it.unical.project.core;

import java.awt.Color;
//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

//import java.util.ArrayList;
//
//import it.unical.project.gamemanager.GameManager;
import it.unical.project.graphics.Assets;
import it.unical.project.handler.Handler;
import it.unical.project.tiles.Tile;
//import it.unical.project.interfaces.Direction;
import it.unical.project.graphics.*;
import it.unical.project.user.interfaces.Direction;
import it.unical.project.user.interfaces.Type;

//import it.unical.project.interfaces.Sesso;

public class Player extends AbstractDynamicObject 
{
	private int bullets;
	private boolean key=false;
	//TIMER PER GESTIRE GLI ATTACCHI
	private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;
	
	public Player(Handler handler, int x, int y)
	{
		super(handler, x, y, AbstractDynamicObject.DEFAULT_CREATURE_WIDTH, AbstractDynamicObject.DEFAULT_CREATURE_HEIGHT,Type.PLAYER);
		
		this.bullets=100;
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animLeft = new Animation(500, Assets.player_left);
		animRight = new Animation(500, Assets.player_right);
	}
	
	@Override
	public void tick() 
	{
		//animStop.tick();
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		
	
		getInput();
		move();
		if(bullets>0)
			checkAttacks();
	}
	
	
	private void checkAttacks()
	{
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		
		if(handler.getKeyManager().fire)
		{
			bullets--;
			if(handler.getKeyManager().up )
				handler.getWorld().getObjectManager().addObject( (new Bullet(handler,(int) this.x+16, (int) this.y-16, Direction.UP) ) ); 
			else if(handler.getKeyManager().down)
				handler.getWorld().getObjectManager().addObject( (new Bullet(handler,(int) this.x+16, (int) this.y+32, Direction.DOWN) ) ); 
			else if(handler.getKeyManager().left )
				handler.getWorld().getObjectManager().addObject( (new Bullet(handler,(int) this.x-16, (int) this.y+16, Direction.LEFT) ) ); 
			else if(handler.getKeyManager().right)
				handler.getWorld().getObjectManager().addObject( (new Bullet(handler,(int) this.x+32, (int) this.y+16, Direction.RIGHT) ) ); 
			else 
				handler.getWorld().getObjectManager().addObject( (new Bullet(handler,(int) this.x+20, (int) this.y+32, Direction.DOWN) ) ); 
			attackTimer = 0;
		}
		else
			return;
		
		
	}
	
	@Override
	public void render(Graphics g) 
	{
		if(xMove == 0 && yMove ==0)
			g.drawImage(Assets.player_stop, (int) x, (int) y, width, height, null);
		
		else if(xMove > 0 && handler.getKeyManager().fire)
			g.drawImage(Assets.player_fireRight, (int) x, (int) y, width, height, null);
		
		else if(xMove < 0 && handler.getKeyManager().fire)
			g.drawImage(Assets.player_fireLeft, (int) x, (int) y, width, height, null);
		
		else
			g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
		/*g.setColor(Color.red);
		g.drawRect((int) (x + borders.x),
				(int) (y + borders.y),
				borders.width, borders.height);*/
	}

	
	@Override
	public void die(){
		System.out.println("You lose");
			
	}
	
	
	
	private void getInput(){
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}
	
	public void move()
	{
	
		if(!checkObjectCollisions(xMove, 0f))
			moveX();
		if(!checkObjectCollisions(0f, yMove))
			moveY();
		if(key==true)
		{
			if(this.house(xMove, 0f))
				handler.getGame().setQuiz(true);
			else if(this.house( 0f,yMove))
				handler.getGame().setQuiz(true);

		}
	}

	@Override
	public void changeMovement() {
		return;
		
	}
	
	public boolean isKey() {
		return key;
	}

	public void setKey(boolean key) {
		this.key = key;
	}
	
	
	
	
	
	
	/*public void setBullet(Bullet bullet, int i) {
		bullets.set(i, bullet);
	}

	public Bullet getBullet(int i) {
		return bullets.get(i);
	}
	
	public ArrayList<Bullet> getB(){
		return this.bullets;
	}*/

	/*@Override
	public Direction getDirection() {
		// TODO Auto-generated method stub
		return null;
	}*/

/*	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}*/
	
	/*

	@Override
	public void setDirection(Direction direction) {
		super.setDirection(direction);
	}

	public void shoot() {
		Bullet x = new Bullet(this.world, this.getX(), this.getY(), this.getDirection(), 1, Sesso.NONE, -1);
		if (x.getDirection().equals(Direction.STOP))
			x.setDirection(Direction.DOWN);
		switch (this.getDirection()) {
		case UP:
			x.setX(this.getX() - this.getSpeed());
			break;
		case DOWN:
			x.setX(this.getX() + this.getSpeed());
			break;
		case LEFT:
			x.setY(this.getY() - this.getSpeed());
			break;
		case RIGHT:
			x.setY(this.getY() + this.getSpeed());
			break;
		default:
			break;
		}
		bullets.add(x);
	}

	public void removeB(int i) {
		bullets.remove(i);
	}

	@Override
	public void update() {//
		super.update();
		if (bullets != null) {
			for (int i = 0; i < bullets.size(); i++) {
					bullets.get(i).update();
			}
			for(int j=0;j<bullets.size();j++) {
				if(!bullets.get(j).isVisible())
					bullets.remove(j);
			}
		}
	}*/
}
