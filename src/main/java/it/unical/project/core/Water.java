package it.unical.project.core;


import java.awt.Color;
import java.awt.Graphics;

import it.unical.project.graphics.Assets;
import it.unical.project.handler.Handler;
import it.unical.project.tiles.Tile;
import it.unical.project.user.interfaces.Type;

public class Water extends AbstractStaticObject {

	

	public Water(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT,Type.WATER);
		borders.x = 10;
		borders.y = 10;
		borders.width = width - 18;
		borders.height = (int) (height - 20);
		
		target.x = 10;
		target.y = 10;
		target.height = width - 18;
		target.width = (int) (height - 20);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.water, (int) x, (int) y, width, height, null);
		/*g.setColor(Color.red);
		g.drawRect((int) (x + target.x),
				(int) (y + target.y),
				target.width, target.height);*/

	}
	
	@Override
	public void die(){
		
	}

	/*@Override
	public void update() {
		// TODO Auto-generated method stub

	}*/

}
