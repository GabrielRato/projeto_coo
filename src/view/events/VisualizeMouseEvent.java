package view.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import beans.Ad;
import view.ProductView;

public class VisualizeMouseEvent implements MouseListener{
	private Ad ad;
	public VisualizeMouseEvent(Ad ad) {
		this.ad = ad;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		ProductView v = new ProductView();
		v.setAd(ad);
		v.init();
		
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
