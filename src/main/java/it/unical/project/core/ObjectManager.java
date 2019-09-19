package it.unical.project.core;

import java.awt.Graphics;
import java.util.ArrayList;
//import java.util.Comparator;
import java.util.Comparator;

import it.unical.project.handler.Handler;

public class ObjectManager
{
	private Handler handler;
	private Player player;
	private ArrayList<AbstractObject> Aobjects;
	
	private Comparator<AbstractObject> renderSorter = new Comparator<AbstractObject>()  //per capire chi deve essere disegnato sopra
	{
		@Override
		public int compare(AbstractObject a, AbstractObject b) {
			if(a.getY() + a.getHeight() < b.getY() + b.getHeight())
				return -1;
			return 1;
		}
	};
	
	
	public ObjectManager(Handler handler, Player player)
	{
		this.handler = handler;
		this.player = player;
		Aobjects = new ArrayList<AbstractObject>();
		addObject(player);
	}
	
	public void tick()
	{
		for(int i = 0;i < Aobjects.size();i++)
		{
			AbstractObject e = Aobjects.get(i);
			e.tick();
			if(!e.isActive())
			{
				Aobjects.remove(e);
			}
		}
		Aobjects.sort(renderSorter); //per capire chi deve essere disegnato sopra
	}
	
	public void render(Graphics g){
		for(AbstractObject e : Aobjects){
			e.render(g);
		}
	}
	
	public void addObject(AbstractObject e){
		Aobjects.add(e);
	}
	
	//GETTERS SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<AbstractObject> getObject() {
		return Aobjects;
	}

	public void setObject(ArrayList<AbstractObject> Aobjects ) {
		this.Aobjects = Aobjects;
	}
}
