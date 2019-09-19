package it.unical.project.core;

import java.awt.Graphics;
//import java.util.*;
import java.util.Random;

import it.unical.project.handler.Handler;
//import it.unical.project.interfaces.BDObject;
import it.unical.project.tiles.Tile;
import it.unical.project.utils.Utils;

public class World 
{
	private ObjectManager objectManager;
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;
	Random random;
	private int ZombieNumber=1;


	public World(Handler handler, String path)
	{
		this.handler = handler;
		
		objectManager = new ObjectManager(handler, new Player(handler, 100, 100));
		objectManager.addObject(new Tree(handler, 100, 250));
		objectManager.addObject(new Rock(handler, 100, 450));
		objectManager.addObject(new Tree(handler, 700, 150));
		objectManager.addObject(new House(handler, 1090, 5));
		objectManager.addObject(new Water(handler, 505, 65));
		
		loadWorld(path);
		
		//CONTROLLARE CHE NON VENGA GENERATO IN POSTI DOVE NON DOVREBBE
		for(int i=0;i<ZombieNumber;)
			if(generateZombie())
				i++;
			
			//objectManager.addObject(new Zombie(handler,random.nextInt(1000+1), random.nextInt(650+1)));
		//loadWorld(path);
		
		objectManager.getPlayer().setX(spawnX);
		objectManager.getPlayer().setY(spawnY);
	}
	
	//CONTROLLARE CHE NON VENGA GENERATO IN POSTI DOVE NON DOVREBBE
	private boolean generateZombie() 
	{
		Random random = new Random();
		int ZX = random.nextInt((handler.getWidth())-Tile.TILEWIDTH);
		int ZY = random.nextInt((handler.getHeight())-Tile.TILEWIDTH);
		 
		int X=((ZX+ AbstractDynamicObject.DEFAULT_CREATURE_WIDTH)/Tile.TILEWIDTH)-1;
		int Y=((ZY+ AbstractDynamicObject.DEFAULT_CREATURE_HEIGHT)/Tile.TILEHEIGHT);
		
		//System.out.println(X);
		//System.out.println(Y);
		//System.out.println(this.getTile(X,Y).getId());
		
		if(!this.getTile(X, Y).isSolid()&&!this.getTile(X+1, Y).isSolid()&&!this.getTile(X, Y+1).isSolid()&&!this.getTile(X+1, Y+1).isSolid())
		{
			objectManager.addObject(new Zombie(handler,ZX , ZY));
			//System.out.println("Ciao");
			return true;
		}	
		//System.out.println("noooooooooo");
		return false;
		
	}

	public void tick()
	{
		objectManager.tick();
	}
	
	public void render(Graphics g)
	{
		for(int y = 0;y < height;y++)
		{
			for(int x = 0;x < width;x++)
			{
				getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
			}
		}
		
		objectManager.render(g);
	}
	
	public Tile getTile(int x, int y)
	{
		if(x < 0 || y < 0 || x >= width || y >= height) { System.out.println(x);System.out.println(y);
			return Tile.grassTile;}
		
		Tile t = Tile.tiles[tiles[x][y]];
		
		if(t == null)
			return Tile.dirtTile;
		
		return t;
	}
	
	private void loadWorld(String path)
	{
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		for(int y = 0;y < height;y++){
			for(int x = 0;x < width;x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]); //+4 perchè nel file txt del mondo i primi 4 numeri corrispondono 
																		   //alla dim del mondo e alle coordinate iniziali del player
			}
		}
	}

	public ObjectManager getObjectManager() {
		return objectManager;
	}

	public void setObjectManager(ObjectManager objectManager) {
		this.objectManager = objectManager;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getZombieNumber() {
		return ZombieNumber;
	}

	public void setZombieNumber(int zombieNumber) {
		ZombieNumber -= zombieNumber;
	}
	

	/*public static class WorldMatrix<T extends BDObject> {
		private Object[][] matrix;
		private int spawnX, spawnY;
		private int[][] tiles;
		private int height;

		private int width;

		private WorldMatrix(int h, int w) {
			this.height = h;
			this.width = w;
			this.matrix = new Object[h][w];
		}
		public void render(Graphics g){
			for(int y = 0;y < height;y++){
				for(int x = 0;x < width;x++){
					getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
				}
			}
		}
		public Tile getTile(int x, int y){
			if(x < 0 || y < 0 || x >= width || y >= height)
				return Tile.grassTile;
			
			Tile t = Tile.tiles[tiles[x][y]];
			if(t == null)
				return Tile.dirtTile;
			return t;
		}
		public void add(int i, int j, T value) {
			matrix[i][j] = value;
		}

		@SuppressWarnings("unchecked")
		public T get(int i, int j) {
			return (T) matrix[i][j];
		}

		public int getHeight() {
			return height;
		}

		@SuppressWarnings("unchecked")
		public T[][] getMatrix() {
			return (T[][]) matrix;
		}

		public int getWidth() {
			return width;
		}

		public void setMatrix(T[][] matrix) {
			this.matrix = matrix;
		}
	}

	private int width;

	private int height;

	private WorldMatrix<BDObject> matrix;

	public World(WorldMatrix<BDObject> matrix) {
		this.matrix = matrix;
		this.width = matrix.getWidth();
		this.height = matrix.getHeight();
	}

	public static WorldMatrix<BDObject> getEmptyMatrix() {
		int h = 10;
		int w = 10;
		WorldMatrix<BDObject> wm = new WorldMatrix<>(h, w);
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				wm.add(i, j, new Empty(i, j));
			}
		}
		return wm;
	}

	public int getHeight() {
		return matrix.getHeight();
	}

	public int getWidth() {
		return matrix.getWidth();
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (matrix.get(i, j) instanceof Empty) {
					s += "-";
				} else if (matrix.get(i, j) instanceof Player) {
					s += "P";
				} else if (matrix.get(i, j) instanceof Zombie) {
					s += "Z";
				} else if (matrix.get(i, j) instanceof House) {
					s += "H";
				} else if (matrix.get(i, j) instanceof Solid) {
					s += "X";
				} else if (matrix.get(i, j) instanceof Bullet) {
					s += "@";
				}
			}
			s += "\n";
		}
		return s;
	}

	public WorldMatrix<BDObject> getMatrix() {
		return matrix;
	}

	public void setMatrix(WorldMatrix<BDObject> matrix) {
		this.matrix = matrix;
	}

	/*public void updateWorld(Player player_1, ArrayList<Zombie> zombies, Solid[] solid, House house,
			ArrayList<Bullet> bullet) {
		WorldMatrix<BDObject> emptyMatrix = getEmptyMatrix();
		emptyMatrix.add(player_1.getX(), player_1.getY(), player_1);

		for (Solid fence1 : solid) {
			emptyMatrix.add(fence1.getX(), fence1.getY(), fence1);
		}

		for (Zombie zombies1 : zombies) {
			emptyMatrix.add(zombies1.getX(), zombies1.getY(), zombies1);
		}

		emptyMatrix.add(house.getX(), house.getY(), house);

		if (player_1.getB() != null) {
			for (Bullet x : player_1.getB()) {
				emptyMatrix.add(x.getX(), x.getY(), x);
			}
		}
		matrix = emptyMatrix;
	}*/

}
