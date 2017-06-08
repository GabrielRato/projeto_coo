package view.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.BevelBorder;

import view.Profile;
import view.MainPage;

public class ProfileMouseEvent implements MouseListener{
	private static JPanel currentPane;

	
	
	public static void setTaskPanel(JPanel p){
		currentPane = p;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		new Profile();
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel profile = (JLabel) e.getSource();
		profile.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		JLabel profile = (JLabel) e.getSource();
		profile.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
