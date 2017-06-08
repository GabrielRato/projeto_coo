package view.events;


import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import beans.Ad;
import model.business.BusinessRulesManager;
import view.MainPage;
import view.Run;
import view.Template;
import view.Values;

public class SearchMouseEvent implements MouseListener {
	
	
	public ImageIcon getImage(int width,int height,String imageUrl){
		
		ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(imageUrl));
		Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		Template t = new Template();
		MainPage.getContentPanel().removeAll();
		ArrayList<Ad> ads = null;
		try {
			 ads = new BusinessRulesManager().findByKeyWord(Run.mainPage.getSearchBar().getText().trim(), 0, 9);
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		JLabel search = (JLabel)e.getSource();
		search.setIcon(getImage(search.getIcon().getIconWidth(), search.getIcon().getIconHeight(), "img/lupeHighlight.png"));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel search = (JLabel)e.getSource();
		search.setIcon(getImage(search.getIcon().getIconWidth(), search.getIcon().getIconHeight(), "img/lupe.png"));
		
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
