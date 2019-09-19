package it.unical.project.core;

import java.awt.Graphics;

import it.unical.project.graphics.Assets;
import it.unical.project.handler.Handler;
import it.unical.project.tiles.Tile;
import it.unical.project.user.interfaces.Type;

public class Rock extends AbstractStaticObject {

	public Rock(Handler handler, float x, float y) 
	{
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT,Type.ROCK);
		
		borders.x = 3;
		borders.y = (int) (height / 2f);
		borders.width = width - 6;
		borders.height = (int) (height - height / 2f);
		
		target.x = 3;
		target.y = (int) (height / 2f);
		target.width = width - 6;
		target.height = (int) (height - height / 2f);
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock,(int) x, (int) y, width, height, null);
	}
	
	/*@Override
	public void update() {
		// TODO Auto-generated method stub

	}*/

}
