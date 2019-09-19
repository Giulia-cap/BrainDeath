package it.unical.project.states;

import java.awt.Color;
import java.awt.Graphics;

import it.unical.project.graphics.Assets;
//import it.unical.project.gamemanager.GameManager;
import it.unical.project.handler.Handler;
import it.unical.project.user.interfaces.ClickListener;
import it.unical.project.user.interfaces.UIImageButton;
import it.unical.project.user.interfaces.UIManager;

public class MenuState extends State 
{
	private UIManager uiManager;
	
	public MenuState(Handler handler)
	{
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);

		uiManager.addObject(new UIImageButton(200, 200, 128, 64, Assets.btn_start, new ClickListener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
	}

	@Override
	public void tick() 
	{
		uiManager.tick();
		
		
		//COMMENTARE PER ATTIVARE IL MENU
		handler.getMouseManager().setUIManager(null);
		State.setState(handler.getGame().gameState);
	}

	@Override
	public void render(Graphics g) 
	{
		g.setColor(Color.RED);
		g.fillRect(handler.getMouseManager().getMouseCordinateX(), handler.getMouseManager().getMouseCordinateY(), 8, 8);
		uiManager.render(g);
	}
	
}
