package view;


import javax.swing.border.BevelBorder;

import beans.User;
import dao.DatabaseManagerFind;
import model.business.BusinessRulesManager;
import view.events.VisualizeMouseEvent;
import beans.Rental;
import beans.Ad;
import beans.Landlord;

import javax.swing.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.text.NumberFormat;

public class Template extends View{
	private JPanel mainPanel;
	private User user;
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();
	
	private String getRentalInfo(Rental r){
		String s = "<html><center>";
		if(r == null || r.getRenter() == null){
		s = s
				+ "R$ "+0.00+"<br/>"
				+ "Renter: No Renter Found" 
				+ "</center></html>";
		}else{
			s = s
					+ formatter.format(r.getPrice())+"<br/>"
					+ "Rate: "
					+ "</center></html>";
		}
		return s;
	}
	
	private String getAdInfo(Ad a){
		String s = "<html><center>";
		if(a.getLandlord() == null){
			s = s
					+ "Name: No Name Found" + "<br/>"
					+ "R$ "+0.00+"<br/>"
					+ "Rate: "
					+ "</center></html>" ;
		}else{
		s = s
				+ ""+a.getTitle() + "<br/>"
				+ formatter.format(a.getPrice())+"<br/>"
				+ "Rate: "
				+ "</center></html>" ;
		}
		return s;
		
	}
	
	public JPanel makeRentalPanel(Rental r){
		JPanel content = new JPanel();
		content.setBorder(BorderFactory.createLineBorder(MainPage.themeColorHovered));
		content.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.31),(int)(Values.SCREEN_HEIGHT*0.3)));
		
		JPanel row1 = new JPanel();
		row1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel image = new JLabel();
		Landlord l = (Landlord)r.getLandlord();
		if(l==null || l.getImage() == null||l.getImage().isEmpty()){
			image.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.06),(int)(Values.SCREEN_WIDTH*0.06), "img/noImageIconProduct.png"));
		}else{
			image.setIcon(getImageFrom((int)(Values.SCREEN_WIDTH*0.06),(int)(Values.SCREEN_WIDTH*0.06), l.getImage()));
		}
		row1.setBorder(BorderFactory.createEmptyBorder((int)(Values.SCREEN_WIDTH*0.01), 0,0, 0));
		row1.add(image);
		
		JPanel row2 = new JPanel();
		row2.setLayout(new BoxLayout(row2, BoxLayout.PAGE_AXIS));
		row2.setBorder(BorderFactory.createEmptyBorder((int)(Values.SCREEN_WIDTH*0.015), 0, 0, 0));
		JPanel infoPanel = new JPanel();
		JLabel info = new JLabel(getRentalInfo(r));
		info.setFont(new Font(info.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.018)));
		infoPanel.add(info);
		JPanel ratePanel = new JPanel();
		JLabel rateStar = new JLabel();
		
		if(r == null|| r.getRenter()==null||r.getRenter().getRateRenter()==null||(int)Math.round(r.getRenter().getRateRenter()) == 0){
			rateStar.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.095), (int)(Values.SCREEN_HEIGHT*0.03), "img/zeroStar.png"));
		}else{
			String path = getRateImagePath(r.getRenter().getRateRenter());
			rateStar.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.095), (int)(Values.SCREEN_HEIGHT*0.03), path));
		}
		ratePanel.add(rateStar);
		row2.add(infoPanel);
		row2.add(ratePanel);
		content.add(row1);
		content.add(row2);
		
		
		return content;
	}
	
	public JPanel makeAdPanel(Ad a){
		try {
			if(a.getAdId() == 0) return null;
			a=new BusinessRulesManager().getAdByIdAd(a.getAdId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JPanel content = new JPanel();
		content.setBorder(BorderFactory.createLineBorder(MainPage.themeColorHovered));
		content.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.31),(int)(Values.SCREEN_HEIGHT*0.3)));
		
		JPanel row1 = new JPanel();
		row1.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel image = new JLabel();
		
		
		if(a==null || a.getImage()== null|| a.getImage().isEmpty()){
		image.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.06),(int)(Values.SCREEN_WIDTH*0.06), "img/noImageIconProduct.png"));
		}else{
			image.setIcon(getImageFrom((int)(Values.SCREEN_WIDTH*0.06),(int)(Values.SCREEN_WIDTH*0.06), a.getImage()));
		}
		
		row1.add(image);
		
		JPanel row2 = new JPanel();
		row2.setLayout(new BoxLayout(row2, BoxLayout.PAGE_AXIS));
		row2.setBorder(BorderFactory.createEmptyBorder((int)(Values.SCREEN_WIDTH*0.015), 0, 0, 0));
		JPanel infoPanel = new JPanel();
		JLabel info = new JLabel(getAdInfo(a));
		info.setFont(new Font(info.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.018)));
		infoPanel.add(info);
		JPanel ratePanel = new JPanel();
		JLabel rateStar = new JLabel();
		if(a == null|| a.getRate()==null||(int)Math.round(a.getRate()) == 0){
			rateStar.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.095), (int)(Values.SCREEN_HEIGHT*0.03), "img/zeroStar.png"));
		}else{
			String path = getRateImagePath(a.getRate());
			rateStar.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.095), (int)(Values.SCREEN_HEIGHT*0.03), path));
		}
		ratePanel.add(rateStar);
		row2.add(infoPanel);
		row2.add(ratePanel);
		JPanel openPanel = new JPanel();
		JButton open = new JButton("Visualize");
		open.addMouseListener(new VisualizeMouseEvent(a));
		open.setFont(new Font(open.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.015)));
		openPanel.add(open);
		row2.add(openPanel);
		content.add(row1);
		content.add(row2);
		
		
		return content;
	}
	private String getRateImagePath(double rate){
		String path = "";
		switch((int)Math.round(rate)){
			case 1:
				path = "img/oneStar.png";
				break;
			case 2:
				path = "img/twoStar.png";
				break;
			case 3:
				path = "img/threeStar.png";
				break;
			case 4:
				path = "img/fourStar.png";
				break;
			case 5:
				path = "img/fiveStar.png";
				break;
		}
		return path;
	}
public ImageIcon getImageFrom(int width,int height,String imageUrl){
		
		ImageIcon imageIcon = new ImageIcon(imageUrl);
		Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
