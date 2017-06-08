package view.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.Login;
import view.MainPage;
import view.Run;

public class SignOutMouseEvent implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		Login.isLogged = false;
		Login.setUser(null);
		Run.mainPage.repaintTopPanel();	
		MainPage.addContentPanel(MainPage.getContentPanel());
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
