package it.unical.project.handler;

import it.unical.project.core.World;
import it.unical.project.gamemanager.GameManager;
import it.unical.project.input.KeyManager;
import it.unical.project.input.MouseManager;
import it.unical.project.quizManager.QuizManager;

public class Handler 
{
	private GameManager game;
	private QuizManager quiz;
	private World world; 
	
	public QuizManager getQuiz() {
		return quiz;
	}

	public void setQuiz(QuizManager quiz) {
		this.quiz = quiz;
	}

	
	public Handler(GameManager game){
		this.game = game;
	}
	
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight(){
		return game.getHeight();
	}

	public GameManager getGame() {
		return game;
	}

	public void setGame(GameManager game) {
		this.game = game;
	}

	public World getWorld() {
		return world;
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}

}