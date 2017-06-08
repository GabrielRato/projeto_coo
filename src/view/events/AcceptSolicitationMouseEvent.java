package view.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import beans.Rental;
import beans.Request;
import model.business.BusinessRulesManager;
import view.Profile;

public class AcceptSolicitationMouseEvent implements MouseListener{
	private Rental from;
	private Request f;
	public AcceptSolicitationMouseEvent(Rental e) {
		from = e;
	}
	public AcceptSolicitationMouseEvent(Request e) {
		f = e;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			if(f == null){
				
				new BusinessRulesManager().acceptRequest(from.getRentalId());
			}else{
				new BusinessRulesManager().acceptRequest(f.getRequestId());
			}
			
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
