package view.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import view.RegisterAd;
import view.MainPage;
import javax.swing.*;


public class RegisterAdMouseEvent implements MouseListener{

	
	@Override
	public void mouseClicked(MouseEvent e) {
		new RegisterAd().init();
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
