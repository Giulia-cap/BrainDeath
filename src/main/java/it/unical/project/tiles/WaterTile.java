package it.unical.project.tiles;

import it.unical.project.graphics.Assets;

public class WaterTile extends Tile {

	public WaterTile(int id) {
		super(Assets.water, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}