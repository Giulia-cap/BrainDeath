package it.unical.project.states;

import java.awt.Graphics;
import it.unical.project.core.World;
//import it.unical.project.gamemanager.GameManager;
/*import it.unical.project.interfaces.Direction;
import it.unical.project.interfaces.Sesso;*/
import it.unical.project.handler.Handler;


public class GameState extends State 
{
	private World world;
	private boolean q=false;
	
	public GameState(Handler handler)
	{
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		
	}
	
	@Override
	public void tick() {
		world.tick();
		if(handler.getGame().isQuiz())
			State.setState(handler.getGame().quizState);
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

	public boolean isQ() {
		return q;
	}

	public void setQ(boolean q) {
		this.q = q;
	}
}
