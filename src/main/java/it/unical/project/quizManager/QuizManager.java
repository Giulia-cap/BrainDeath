package it.unical.project.quizManager;

import java.awt.Graphics;
import java.util.ArrayList;

import it.unical.project.handler.Handler;
import it.unical.project.user.interfaces.UIObject;

public class QuizManager
{
	private Handler handler;
	private ArrayList<QuizObject> qObjects;
	
	public QuizManager(Handler handler)
	{
		this.handler = handler;
		qObjects = new ArrayList<QuizObject>();
	}
	
	public void tick(){
		for(QuizObject o : qObjects)
			o.tick();
	}
	
	public void render(Graphics g){
		for(QuizObject o : qObjects)
			o.render(g);
	}
	
	public void addObject(QuizObject o){
		qObjects.add(o);
	}
	
	public void removeObject(UIObject o){
		qObjects.remove(o);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<QuizObject> getObjects() {
		return qObjects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.qObjects = qObjects;
	}
}
