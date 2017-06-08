package view.events;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import view.Login;
import view.MainPage;
import view.RegisterAd;
import view.Template;
import view.Values;
import model.business.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import beans.Ad;
import beans.Landlord;
import dao.DatabaseManagerUser;
import dao.DatabaseManagerProduct;

public class ConfirmAdRegistrationEvent implements MouseListener {
	RegisterAd r;
	public ConfirmAdRegistrationEvent(RegisterAd r){
		this.r = r;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		BusinessRulesManager model = new BusinessRulesManager();
		Landlord landlord = null;
		String name = r.getNameText().getText().trim().toUpperCase();
		String title = r.getAdTitleText().getText().trim().toUpperCase();
		String description = r.getDescriptionText().getText().toUpperCase();
		String path = RegisterAd.getPath();
		String errorMessage = "";
		boolean complete = true;
		double price = 0;
		int availableAmount = 0;
		try{
			landlord = new DatabaseManagerUser().getLandlordByUserId(Login.getUser().getUserId());
			if (landlord == null) throw new Exception();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
		try{
			price = Double.parseDouble(r.getPriceText().getText());
			if(price < 0) throw new NumberFormatException();
		}
		catch(NumberFormatException ex){
			complete = false;
			errorMessage = "<html>Please select a valid value for the <b>Price</b> field.</html>";
		}
		
		try{
			availableAmount = Integer.parseInt(r.getAvailableText().getText());
			if(availableAmount <= 0) throw new NumberFormatException();
		}
		catch(NumberFormatException ex){
			complete = false;
			errorMessage = "<html>Please select a valid value for the <b>Available</b> field.</html>";
		}
		
		if(name.isEmpty() || title.isEmpty() || description.isEmpty()){
			complete = false;
			errorMessage = "Fields marked with an * cannot be left blank.";
		}
		
		
		if(!complete){
			JOptionPane.showMessageDialog(null, errorMessage,"Warning",JOptionPane.WARNING_MESSAGE);
		}
		else{
			try{
				model.insertProduct(name, path, title, price, description, landlord, availableAmount);
				JOptionPane.showMessageDialog(null, "Product registered successfully!","Registration",JOptionPane.INFORMATION_MESSAGE);
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
				RegisterAd.setPath(null);
				RegisterAd.setImageIcon(null);
			}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Something went wrong: "+ex.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
			}
		}
		
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
