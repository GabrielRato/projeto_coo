package view.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import org.omg.CORBA.Current;

import view.ProductView;
import view.Profile;
import view.Values;


public class ChangeRateMouseEvent implements MouseListener{
	private Profile current;

	public ChangeRateMouseEvent(Profile p) {
		current = p;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel rate = (JLabel)e.getSource();
		int id = rate.hashCode();
		int id1 = current.getRateStar1().hashCode();
		int id2 = current.getRateStar2().hashCode();
		int id3 = current.getRateStar3().hashCode();
		int id4 = current.getRateStar4().hashCode();
		int id5 = current.getRateStar5().hashCode();
			if(id == id5){
				current.getRateStar5().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
				current.getRateStar4().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
				current.getRateStar3().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
				current.getRateStar2().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
				current.getRateStar1().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
			}else if (id == id4){
				current.getRateStar5().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
				current.getRateStar4().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
				current.getRateStar3().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
				current.getRateStar2().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
				current.getRateStar1().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
			}else if(id == id3){
				current.getRateStar5().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
				current.getRateStar4().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
				current.getRateStar3().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
				current.getRateStar2().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
				current.getRateStar1().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
			}else if(id == id2){
				current.getRateStar5().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
				current.getRateStar4().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
				current.getRateStar3().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
				current.getRateStar2().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
				current.getRateStar1().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
			}else if(id == id1){
				current.getRateStar5().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
				current.getRateStar4().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
				current.getRateStar3().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
				current.getRateStar2().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
				current.getRateStar1().setIcon(current.getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/filledStar.png"));
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
