package view.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import beans.Request;
import model.business.BusinessRulesManager;
import view.Profile;

public class DenySolicitationMouseEvent implements MouseListener{
	private Request from;
	public DenySolicitationMouseEvent(Request e) {
		from = e;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			new BusinessRulesManager().finishRequest(from.getRequestId());
			new Profile();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
