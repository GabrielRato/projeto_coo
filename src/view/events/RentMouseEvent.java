package view.events;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.Year;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import beans.Ad;
import beans.Renter;
import model.business.BusinessRulesManager;
import view.Login;
import view.MainPage;
import view.ProductView;
import view.Template;
import view.Values;

public class RentMouseEvent implements MouseListener{
	ProductView product;
	public RentMouseEvent(ProductView p) {
		product = p;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		String endDay =(String)product.getFinalDayCombo().getSelectedItem();
		String startDay = (String)product.getInitDayCombo().getSelectedItem();
		String startMonth = (String)product.getInitMonthCombo().getSelectedItem();
		String endMonth = (String)product.getFinalMonthCombo().getSelectedItem();
		Year year = Year.now();
		if(endDay.contains("-")||startDay.contains("-")||endMonth.contains("-")||startMonth.contains("-")){
			JOptionPane.showMessageDialog(null, "Please select a valid date!","Warning",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			String endDate = "";
			if(new Integer(startMonth)>new Integer(endMonth)){
			endDate= (year.getValue()+1)+"-"+endMonth+"-"+endDay;	
			}else if(new Integer(startMonth)==new Integer(endMonth) && new Integer(startDay)>=new Integer(endDay)){
				endDate= (year.getValue()+1)+"-"+endMonth+"-"+endDay;	
			}else{
				endDate= (year.getValue())+"-"+endMonth+"-"+endDay;
			}
			
			Renter r  = new BusinessRulesManager().getRenterByUserId(Login.getUser().getUserId());
			String initDate = year.getValue()+"-"+startMonth+"-"+startDay;
			 
			int idAd = product.getAd().getAdId();
			JOptionPane.showMessageDialog(null, "Rental request sent!","Confirm Rent",JOptionPane.INFORMATION_MESSAGE);
			new BusinessRulesManager().startRequest(idAd,r.getRenterId(), product.getAd().getLandlord().getLandlordId(),initDate,endDate);
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
			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			new Login().init();
			
			
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
