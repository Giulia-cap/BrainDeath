package it.unical.project.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import it.unical.project.user.interfaces.UIManager;

public class MouseManager implements MouseListener, MouseMotionListener
{

	private boolean leftKey, rightKey;
	private int mouseX, mouseY;
	private UIManager uiManager;

	public MouseManager()
	{
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		if(e.getButton() == MouseEvent.BUTTON1) //BUTTON1:TASTO SINISTRO
			leftKey = true;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightKey = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if(e.getButton() == MouseEvent.BUTTON1)
			leftKey = false;
		else if(e.getButton() == MouseEvent.BUTTON3)
			rightKey = false;
		if(uiManager != null)
			uiManager.onMouseRelease(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		mouseX = e.getX();
		mouseY = e.getY();
		if(uiManager != null)
			uiManager.onMouseMove(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public boolean isLeftKeyPressed(){
		return leftKey;
	}
	
	public boolean isRightKeyPressed(){
		return rightKey;
	}
	
	public int getMouseCordinateX(){
		return mouseX;
	}
	
	public int getMouseCordinateY(){
		return mouseY;
	}

	public UIManager getUIManager() {
		return uiManager;
	}

	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	

}
