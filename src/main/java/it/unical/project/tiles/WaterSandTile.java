package it.unical.project.tiles;

import it.unical.project.graphics.Assets;

public class WaterSandTile extends Tile {

	public WaterSandTile(int id) {
		super(Assets.waterSand, id);
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}