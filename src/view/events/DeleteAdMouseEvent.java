package view.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.MainPage;
import view.ProductView;
import view.Template;
import view.Values;
import beans.Ad;
import dao.DatabaseManagerProduct;
import model.business.BusinessRulesManager;

public class DeleteAdMouseEvent implements MouseListener {
	ProductView p;
	public DeleteAdMouseEvent(ProductView src){
		this.p = src;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		int confirmDeletion = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this ad?", "Delete Ad", JOptionPane.YES_NO_OPTION);
		if (confirmDeletion == JOptionPane.YES_OPTION){
			try{
				new DatabaseManagerProduct().deleteAd(new ProductView().getAd().getAdId());
				JOptionPane.showMessageDialog(null, "Successfully deleted ad.","Delete Ad",JOptionPane.INFORMATION_MESSAGE);
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
				p.setAd(null);
			}
			catch(Exception e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	
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
