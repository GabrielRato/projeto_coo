package view.events;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import beans.Ad;
import model.business.BusinessRulesManager;
import view.MainPage;
import view.Template;
import view.Values;

public class HomeMouseEvent implements MouseListener{
	private JPanel goBackTo;
	
	public HomeMouseEvent(JPanel currentPane){
		this.goBackTo = currentPane;
	}
	public ImageIcon getImage(int width,int height,String imageUrl){
		
		ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(imageUrl));
		Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		MainPage.getContentPanel().removeAll();
		Template t = new Template();
		ArrayList<Ad> ads = null;
		try {
			 ads = new BusinessRulesManager().findByKeyWord("", 0, 9);
			 if(ads.isEmpty()){
				 JPanel p = new JPanel();
				 JLabel noContentFound = new JLabel("<html><i>We're sorry no content was found :(</i></html>");
				 noContentFound.setFont(new Font(noContentFound.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.02)));
				 p.add(noContentFound);
				 MainPage.getContentPanel().add(p);
			 }else{
				 for(Ad ad:ads){
					 MainPage.getContentPanel().add(t.makeAdPanel(ad));
				}
			 }
			 MainPage.addContentPanel(MainPage.getContentPanel());
		}catch(Exception e1){}
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel set = null;
		
				if(e.getSource() instanceof JLabel){
					set = ((JLabel)e.getSource());
					set.setIcon(getImage(set.getIcon().getIconWidth(), set.getIcon().getIconHeight(), "img/homeIconHighlight.png"));
				}
				
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel set = null;
		if(e.getSource() instanceof JLabel){
			set = ((JLabel)e.getSource());
			set.setIcon(getImage(set.getIcon().getIconWidth(), set.getIcon().getIconHeight(), "img/homeIcon.png"));
		}
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
