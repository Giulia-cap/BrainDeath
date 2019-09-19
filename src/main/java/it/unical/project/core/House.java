package it.unical.project.core;

import java.awt.Color;
import java.awt.Graphics;

import it.unical.project.graphics.Assets;
//import it.unical.project.graphics.Assets;
import it.unical.project.handler.Handler;
import it.unical.project.quizManager.QuizManager;
import it.unical.project.tiles.Tile;
import it.unical.project.user.interfaces.Type;

public class House extends AbstractStaticObject 
{

	public House(Handler handler, float x, float y) 
	{
		super(handler, x, y, 64, 64,Type.HOUSE);
		
		borders.x = 3;
		borders.y = (int) (height);
		borders.width = width + 50;
		borders.height = (int) (height - height / 7f);
	}

//	@Override
//	public void update() {
//		throw new UnsupportedOperationException();
//		
//	}
	@Override
	public void tick() 
	{
		
	}

	@Override
	public void render(Graphics g)
	{
		g.drawImage(Assets.house,(int) x, (int) y, 125, 125, null);
		/*g.setColor(Color.red);
		g.fillRect((int) (x + borders.x),
				(int) (y + borders.y),
				borders.width, borders.height);*/
	}
	
	@Override
	public void die(){
		
	}
	
	
}