package view.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import beans.Address;
import model.business.BusinessRulesManager;
import view.MainPage;
import view.SignUp;

public class SignUpMouseEvent implements MouseListener{
	
	private static JPanel currentPane ;
	 
	
	
	
	public static void setTaskPanel(JPanel p){
		currentPane = p;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
			new SignUp();
			MainPage.addContentPanel(currentPane);
	
	}
		
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
