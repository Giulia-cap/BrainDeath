package it.unical.project.gamemanager;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
//import java.util.*;
import it.unical.project.core.*;
//import it.unical.project.core.World.WorldMatrix;
import it.unical.project.graphics.Assets;
import it.unical.project.graphics.Display;
import it.unical.project.handler.Handler;
import it.unical.project.input.KeyManager;
import it.unical.project.input.MouseManager;
/*import it.unical.project.interfaces.BDObject;
import it.unical.project.interfaces.Direction;
import it.unical.project.interfaces.Sesso;*/
import it.unical.project.states.GameState;
import it.unical.project.states.MenuState;
import it.unical.project.states.QuizState;
import it.unical.project.states.State;

public class GameManager implements Runnable {

	
	private Thread thread;
	private boolean running = false;
	private Player player;
	private Display display;
	private BufferStrategy bs;
	private Graphics g;
	public int width, height;
	public String title;

	public State gameState;
	public State menuState;
	public State quizState;
	
	private boolean quiz=false;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	private Handler handler;
		
/*VECCHIE
private World world;
private boolean fine = false;
private ArrayList<Bullet> bullets;
private House house;
private ArrayList<Zombie> zombies;
private Solid[] solids;
private String line;*/
		
		
public GameManager(String title, int width, int height)
{
	this.width = width;
	this.height = height;
	this.title = title;
	keyManager = new KeyManager();
	mouseManager = new MouseManager();
}

	
	

///////////////////////////////////////////////////////////////////////////////////////
	//Graphic-part
	private void init()
	{
		display = new Display(title, width, height);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		handler = new Handler(this);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		quizState=new QuizState(handler);
		State.setState(menuState);
	}

	public synchronized void start()
	{
		if(running) //dobbiamo prima controllare che il gioco non sia già in esecuzione
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//Clear Screen
		g.clearRect(0, 0, 1366, 768);
		//Draw Here!
		if(State.getState() != null)
			State.getState().render(g);

		//End Drawing!
		bs.show();
		g.dispose();
	}


	//int x = 0;

	private void tick()
	{
		//x += 1;
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}

	@Override
	public void run() 
	{
		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1){
				tick();
				render();
				ticks++;
				delta--;
			}

			if(timer >= 1000000000){
				//System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();

		/*WorldMatrix<BDObject> emptyMatrix = World.getEmptyMatrix();
		zombies = new ArrayList<Zombie>();
		world = new World(emptyMatrix);
		player = new Player(null,world, 0, 9, Direction.STOP, 1, Sesso.MALE, 1);
		zombies.add(new Zombie(null,world, 4, 2, Direction.RIGHT, 1, Sesso.FEMALE, 1));
		zombies.add(new Zombie(null,world, 9, 1, Direction.RIGHT, 1, Sesso.FEMALE, 1));
		zombies.add(new Zombie(null,world, 0, 0, Direction.DOWN, 1, Sesso.FEMALE, 1));
		solids = new Solid[] { new Solid(world, 7, 3), new Solid(world, 4, 5), new Solid(world, 4, 5),
				new Solid(world, 4, 0), new Solid(world, 6, 1), new Solid(world, 4, 9), new Solid(world,1,6) };
		house = new House(world, 8, 9);
		world.updateWorld(player, zombies, solids, house, bullets);*/
	


	}
	
	
	public MouseManager getMouseManager(){
		return mouseManager;
}
		
	public KeyManager getKeyManager()
	{
		return keyManager;
	}

	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public boolean isQuiz() {
		return quiz;
	}

	public void setQuiz(boolean quiz) {
		this.quiz = quiz;
	}
	
	

	
	/*VECCHIE
	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}

	public ArrayList<Zombie> getZombies() {
		return zombies;
	}

	public void setZombies(ArrayList<Zombie> zombies) {
		this.zombies = zombies;
	}

	public Solid[] getSolids() {
		return solids;
	}

	public void setSolids(Solid[] solids) {
		this.solids = solids;
	}
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public House getHouse() {
		return house;
	}

	public void setHouse(House house) {
		this.house = house;
	}

	public boolean isFine() {
		return fine;
	}

	public void setFine(boolean fine) {
		this.fine = fine;
	}
	private void update() {
		if (checkS()) {
			System.out.println("Ostacolo,direzione non consentita!");
			ridaimossa();
		}
		player.update();
		checkZ();
		world.updateWorld(player, zombies, solids, house, bullets);
		printWorld();
	}
	private void ridaimossa() {
		System.out.println(this);
		System.out.println("press a to LEFT");
		System.out.println("press d to RIGHT");
		System.out.println("press w to UP");
		System.out.println("press s to DOWN");
		System.out.println("press o to SHOOT");
		try (Scanner scanner = new Scanner(System.in)) {
			line = scanner.nextLine();
			while (!line.equals("e") && (!this.isFine())) {
				switch (line) {
				case "a":
					this.getPlayer().setDirection(Direction.LEFT);
					break;
				case "d":
					this.getPlayer().setDirection(Direction.RIGHT);
					break;
				case "w":
					this.getPlayer().setDirection(Direction.UP);
					break;
				case "s":
					this.getPlayer().setDirection(Direction.DOWN);
					break;
				case "o":
					this.getPlayer().shoot();
					break;
				}
				this.update();
				line = scanner.nextLine();
			}
		}
		
		
		// è un metodo del player / zombies non deve stare qua
	private void checkLife(int i) {
		if (player.intersect(zombies.get(i))) {
			player.removeOneLife();
			System.out.print("Hai perso una vita!");
			if (player.getLife() == 0) {
				System.out.println("Game Over!");
				fine = true;
				return;
			}
		}

	}

	private boolean checkS() {
		for (int i = 0; i < solids.length; i++) {
			if (player.intersect(solids[i])) {
				return true;
			}
		}
		return false;
	}
	// è un metodo del player / zombies non deve stare qua
	private void checkZ() {
		for (int i = 0; i < zombies.size(); i++) {
			if (zombies.get(i) != null) {
				zombies.get(i).update();
			}
			changeD(i);
		}
	}
	// è un metodo del player / zombies non deve stare qua
	private void changeD(int i) {
		for (int j = 0; j < solids.length; j++) {
			if (zombies.get(i).intersect(solids[j])) {
				if ((zombies.get(i).getDirection()).equals(Direction.UP))
					zombies.get(i).setDirection(Direction.DOWN);
				else if ((zombies.get(i).getDirection()).equals(Direction.DOWN))
					zombies.get(i).setDirection(Direction.UP);
				else if ((zombies.get(i).getDirection()).equals(Direction.RIGHT))
					zombies.get(i).setDirection(Direction.LEFT);
				else if ((zombies.get(i).getDirection()).equals(Direction.LEFT))
					zombies.get(i).setDirection(Direction.RIGHT);
			}
			if (zombies.get(i).intersect(house)) {
				if ((zombies.get(i).getDirection()).equals(Direction.UP))
					zombies.get(i).setDirection(Direction.DOWN);
				else if ((zombies.get(i).getDirection()).equals(Direction.DOWN))
					zombies.get(i).setDirection(Direction.UP);
				else if ((zombies.get(i).getDirection()).equals(Direction.RIGHT))
					zombies.get(i).setDirection(Direction.LEFT);
				else if ((zombies.get(i).getDirection()).equals(Direction.LEFT))
					zombies.get(i).setDirection(Direction.RIGHT);
			}

			Random random = new Random();
			int k = random.nextInt(2);
			switch(zombies.get(i).checkWall())
			{
			case 'u':
				if ((zombies.get(i).getDirection()).equals(Direction.UP))
					zombies.get(i).setDirection(Direction.DOWN);
				break;
			case 'd':
				if ((zombies.get(i).getDirection()).equals(Direction.DOWN))
					zombies.get(i).setDirection(Direction.UP);
				break;
			case 'l':
				if ((zombies.get(i).getDirection()).equals(Direction.LEFT))
					zombies.get(i).setDirection(Direction.RIGHT);
				break;
			case 'r':
				if ((zombies.get(i).getDirection()).equals(Direction.RIGHT))
					zombies.get(i).setDirection(Direction.LEFT);
				break;
			case 'a':
				if ((zombies.get(i).getDirection()).equals(Direction.UP))
					zombies.get(i).setDirection(Direction.DOWN);
				else if ((zombies.get(i).getDirection()).equals(Direction.LEFT))
					zombies.get(i).setDirection(Direction.RIGHT);
				break;
			case 'b':
				if ((zombies.get(i).getDirection()).equals(Direction.UP))
					zombies.get(i).setDirection(Direction.DOWN);
				else if ((zombies.get(i).getDirection()).equals(Direction.RIGHT))
					zombies.get(i).setDirection(Direction.LEFT);
				break;
			case 'c':
				if ((zombies.get(i).getDirection()).equals(Direction.DOWN))
					zombies.get(i).setDirection(Direction.UP);
				else if ((zombies.get(i).getDirection()).equals(Direction.LEFT))
					zombies.get(i).setDirection(Direction.RIGHT);
				break;
			case 'f':
				if ((zombies.get(i).getDirection()).equals(Direction.DOWN))
					zombies.get(i).setDirection(Direction.UP);
				else if ((zombies.get(i).getDirection()).equals(Direction.RIGHT))
					zombies.get(i).setDirection(Direction.LEFT);
				break;

				if(k==0)             GENERARE RANDOM
						zombies.get(i).setDirection(Direction.UP);
					else if(k==1)
						zombies.get(i).setDirection(Direction.LEFT);
					else
						zombies.get(i).setDirection(Direction.RIGHT);

			}	

		}
		checkLife(i);
		checkB(i);
	}
	// è un metodo del player / zombies non deve stare qua
	private void checkB(int i) {
		if (player.getB() != null) {
			for (int j = 0; j < player.getB().size(); j++) {
				if (player.getB().get(j).intersect(zombies.get(i))) {
					zombies.remove(i);
					player.removeB(j);
				}
			}
		}
	}
	
	
	
	private void printWorld() {
		System.out.println(world.toString());

		if (checkS()) {
			System.out.println("Ostacolo,direzione non consentita!");
			ridaimossa();
		}
		player.update();
		checkZ();
		world.updateWorld(player, zombies, solids, house, bullets);


	}
	*/


	
}

