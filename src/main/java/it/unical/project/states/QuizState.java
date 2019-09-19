package it.unical.project.states;

import java.awt.Graphics;

import it.unical.project.handler.Handler;
import it.unical.project.quizManager.QuizManager;

public class QuizState extends State
{
	//non so..
	private boolean active = false;
	private QuizManager qManager;
	
	public QuizState(Handler handler) {
		super(handler);
		qManager = new QuizManager(handler);
		
	}

	@Override
	public void tick() {
		qManager.tick();
		
	}

	@Override
	public void render(Graphics g) {
		qManager.render(g);
		
	}
	
}
