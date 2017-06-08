package view.events;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import beans.Ad;
import model.business.BusinessRulesManager;
import view.MainPage;
import view.Template;
import view.Values;


public class CategoryItemMouseEvent implements MouseListener{
	private JPanel currentPanel;
	
	public CategoryItemMouseEvent(JPanel panel) {
		// TODO Auto-generated constructor stub
		currentPanel = panel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel currentLabel = null;	
		currentLabel = (JLabel) e.getSource();
		String oldText = currentLabel.getText().replaceAll("\\|", "").trim();
		try {
			MainPage.getContentPanel().removeAll();
			Template t = new Template();
			ArrayList<Ad> ads = null;
			try {
				 ads = new BusinessRulesManager().findByKeyWord(oldText, 0, 9);
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
			} catch (Exception e1) {
				
				
			}
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null,e1.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel currentLabel = null;	
		currentLabel = (JLabel) e.getSource();
		currentLabel.setText("|  "+currentLabel.getText()+"  |");

	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel currentLabel = null;	
		currentLabel = (JLabel) e.getSource();
		String oldText = currentLabel.getText().replaceAll("\\|", "").trim();
		currentLabel.setText(oldText);

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
