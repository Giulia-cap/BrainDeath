package it.unical.project.core;


import java.awt.Color;
import java.awt.Graphics;

import it.unical.project.graphics.Assets;
import it.unical.project.handler.Handler;
import it.unical.project.tiles.Tile;
import it.unical.project.user.interfaces.Type;

public class Tree extends AbstractStaticObject {

	

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2,Type.TREE);
		borders.x = 15;
		borders.y = (int) (height / 1.4f);
		borders.width = width - 30;
		borders.height = (int) (height - height / 1.4f);
		
		target.x = 10;
		target.y = (int) (height / 1.5f);
		target.width = width - 20;
		target.height = (int) (height - height / 1.5f);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) x, (int) y, width, height, null);
		/*g.setColor(Color.red);
		g.drawRect((int) (x + borders.x),
				(int) (y + borders.y),
				borders.width, borders.height);*/

	}
	
	@Override
	public void die(){
		
	}

	/*@Override
	public void update() {
		// TODO Auto-generated method stub

	}*/

}
