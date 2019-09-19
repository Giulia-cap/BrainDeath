package it.unical.project.graphics;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage dirt, player_stop, grass, stone, tree, zombie,rock,house,bullet,bullet2,water,floor,waterSand, player_fireRight, player_fireLeft, key;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
	public static BufferedImage[] btn_start;
	
	public static void init()
	{
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		water = sheet.crop(width * 2, height, width, height);
		waterSand = sheet.crop(width, height, width, height);
		floor = sheet.crop(width * 3, height*2, width, height);
		stone = sheet.crop(width * 3, 0, width, height);
		tree = sheet.crop(0, 0, width, height * 2);
		rock = sheet.crop(0, height * 2, width, height);
		house = sheet.crop(0, width*6, width*2, height*2);
		bullet = sheet.crop(width * 2, height * 2, width, height);
		bullet2 = sheet.crop(width, height * 2, width, height);
		player_stop = sheet.crop(width * 2, height * 5, width, height);
		key = sheet.crop(width * 2, height * 6, width, height);
		player_fireRight = sheet.crop(width * 7, height * 7, width, height);
		player_fireLeft = sheet.crop(width * 6, height * 7, width, height);
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 4, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[4];
		player_right = new BufferedImage[4];
		
		zombie_down = new BufferedImage[2];
		zombie_up = new BufferedImage[2];
		zombie_left = new BufferedImage[2];
		zombie_right = new BufferedImage[2];
				
		player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);
		
		player_right[0] = sheet.crop(width * 4, height, width, height);
		player_right[1] = sheet.crop(width * 5, height, width, height);
		player_right[2] = sheet.crop(width * 6, height, width, height);
		player_right[3] = sheet.crop(width * 7, height, width, height);
		
		player_left[0] = sheet.crop(width * 4, height*2, width, height);
		player_left[1] = sheet.crop(width * 5, height*2, width, height);
		player_left[2] = sheet.crop(width * 6, height*2, width, height);
		player_left[3] = sheet.crop(width * 7, height*2, width, height);
		
		zombie_down[0] = sheet.crop(0, height * 3, width, height);
		zombie_down[1] = sheet.crop(width, height*3, width, height);
		zombie_up[0] = sheet.crop(width * 2, height * 3, width, height);
		zombie_up[1] = sheet.crop(width * 3, height * 3, width, height);
		zombie_right[0] = sheet.crop(0, height * 4, width, height);
		zombie_right[1] = sheet.crop(width, height * 4, width, height);
		zombie_left[0] = sheet.crop(width * 2, height * 4, width, height);
		zombie_left[1] = sheet.crop(width * 3, height * 4, width, height);
	}
	
}
