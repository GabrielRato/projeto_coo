package view;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;

import javax.swing.border.BevelBorder;

import view.events.AcceptSolicitationMouseEvent;
import view.events.ChangeRateMouseEvent;
import view.events.DenySolicitationMouseEvent;
import view.events.ProfileMouseEvent;
import view.events.RegisterAdMouseEvent;

import java.awt.Dimension;

import java.util.ArrayList;

import javax.swing.*;

import beans.Ad;
import beans.Address;
import beans.User;
import beans.Rental;
import beans.Request;
import beans.Landlord;
import dao.*;
import model.business.BusinessRulesManager;

public class Profile extends View{
	
	private JPanel mainPanel = new JPanel();	
	private JScrollPane infoContent;
	private User user;
	private JLabel rateStar1,rateStar2,rateStar3,rateStar4,rateStar5;
	private static boolean savedRate;
	

	public Profile(){
		display();
	}
	
	public void init() {
		//mainPanel.setBounds(0, 0,(int) Values.SCREEN_WIDTH, (int)Values.SCREEN_HEIGHT);
		user = Login.getUser();
		System.out.println(user.getName()+" / "+user.getBirthday()+ " / "+user.getAddress().getCep());
		MainPage.addContentPanel(createProfile());
		
	}
	public static boolean isSavedRate() {
		return savedRate;
	}

	public static void setSavedRate(boolean savedRate) {
		Profile.savedRate = savedRate;
	}
	public JLabel getRateStar1() {
		return rateStar1;
	}
	public void setRateStar1(JLabel rateStar1) {
		this.rateStar1 = rateStar1;
	}
	public JLabel getRateStar2() {
		return rateStar2;
	}
	public void setRateStar2(JLabel rateStar2) {
		this.rateStar2 = rateStar2;
	}
	public JLabel getRateStar3() {
		return rateStar3;
	}
	public void setRateStar3(JLabel rateStar3) {
		this.rateStar3 = rateStar3;
	}
	public JLabel getRateStar4() {
		return rateStar4;
	}
	public void setRateStar4(JLabel rateStar4) {
		this.rateStar4 = rateStar4;
	}
	public JLabel getRateStar5() {
		return rateStar5;
	}
	public void setRateStar5(JLabel rateStar5) {
		this.rateStar5 = rateStar5;
	}
	
	private String retrieveUserData(){ 
		String s = "<html>";
		Address address = user.getAddress();
		s = s
			+ "<b><h4>" + user.getEmail() + "</h4></b>"
			
			+ address.getStreetName() + ", " 
			+ address.getNumber();
		if(address.getComplement()!=null && !address.getComplement().equals("")) s = s + ", " + address.getComplement();			
		s = s
			+ "<br/>"
			+ "CEP: " + address.getCep() + "<br/>"
			+ address.getNeighborhood() + ", "
			+ address.getCity() + " - "
			+ address.getState() + "<br/><br>"
				
			+user.getPhone(); 

				
		//constroe e retorna uma string contendo as informacoes do usuario
		
		return s;
	}
	

	
	private JPanel getAdPanels(){/*
		JPanel content = new JPanel();
		content.setVisible(true);	
		content.setLayout(new FlowLayout(FlowLayout.CENTER));
		content.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		content.setOpaque(true);
		content.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.98),(int)(Values.SCREEN_HEIGHT*0.54)));
		
		ArrayList<Ad> array = new ArrayList<Ad>();
		Template t = new Template();
		for(int i = 0; i<array.size(); i++){
			//Rental nextAd = new Rental();
			JPanel novo =  (t.makeAdPanel(array.get(i)));
			novo.setBorder(BorderFactory.createEmptyBorder((int)(Values.SCREEN_WIDTH*0.012), (int)(Values.SCREEN_WIDTH*0.012), (int)(Values.SCREEN_WIDTH*0.012), (int)(Values.SCREEN_WIDTH*0.012)));
			novo.setBackground(new Color(5,50,3));
			novo.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.31),(int)(Values.SCREEN_HEIGHT*0.4)));
			novo.setVisible(true);
			content.add(novo);
		}
		//testPanel(content, "content");
		 
		 */
		Landlord l = null;
		JPanel content = new JPanel();
		ArrayList<Ad> ads = new ArrayList<Ad>();
		
		try{
			l = new BusinessRulesManager().getLandlordById(user.getUserId());
			if(l!= null){
				System.out.println(l.getLandlordId());
				ads = new BusinessRulesManager().getAdByLandlord(l.getLandlordId());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		content.setLayout(new GridLayout(1,3));
		for(Ad a:ads){
			
			JPanel current = new Template().makeAdPanel(a);
			if(current == null) continue;
			content.add(current);
		}
		
		return content;
	}
	
	private JPanel getRentPanels(){
		/*
		content.setVisible(true);	
		content.setLayout(new FlowLayout(FlowLayout.CENTER));
		content.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		content.setOpaque(true);
		content.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.98),(int)(Values.SCREEN_HEIGHT*0.54)));
		*/
		Landlord l = null;
		JPanel content = new JPanel();
		ArrayList<Rental> rentals = new ArrayList<Rental>();
		
		try{
			 l = new BusinessRulesManager().getLandlordById(user.getUserId());
			 if(l != null ){
				 rentals = new BusinessRulesManager().getRentalByLandlordId(l.getLandlordId());
			 } 
			
		}catch(Exception e){
			e.printStackTrace();
		}
		content.setLayout(new GridLayout(3, 3));
		for(Rental r:rentals){
			if(r == null || r.getRenter() == null) continue;
			JPanel current = new Template().makeRentalPanel(r);
			if(current == null) continue;
			content.add(current);
		}
		return content;		
	}
	
	private JPanel createProfile(){
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		
		/*
		 * o mainPanel para o perfil comporta o seguinte conteudo:
		 * -row1: foto de perfil a esquerda (profilePic), um subBloco a direita (subRow).
		 * 		o subRow contem o nome de usuario seguido de seu dados, exceto endereco
		 * -row2: endereco do usuario
		 * -row3: abas de items, emprestimos e notificacoes
		 * 
		*/
		
		JPanel row1 = new JPanel();
		row1.setLayout(new FlowLayout (FlowLayout.CENTER));
		
		
		JLabel profilePic = new JLabel();
		if(user != null){
			try{
				Landlord current = new BusinessRulesManager().getLandlordByUserId(user.getUserId());
				if(!current.getImage().isEmpty()){
					profilePic.setIcon(Run.mainPage.getImageFrom((int)(Values.SCREEN_WIDTH*0.08), (int)(Values.SCREEN_WIDTH*0.08), current.getImage()));
				}else{
					profilePic.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.08),(int)(Values.SCREEN_WIDTH*0.08), "img/noImageIcon.png"));
				}
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
			}
			
		}else{
			profilePic.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.08),(int)(Values.SCREEN_WIDTH*0.08), "img/noImageIcon.png"));
		}
		profilePic.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		profilePic.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.08),(int)(Values.SCREEN_WIDTH*0.08)));
		row1.add(profilePic);
		
		JPanel subRow = new JPanel();
		subRow.setBackground(new Color(255,214,178));
		subRow.setLayout(new BoxLayout(subRow,BoxLayout.PAGE_AXIS));
		//subRow.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.2), (int)(Values.SCREEN_HEIGHT*0.2)));
		JLabel lblName = new JLabel("<html>" + user.getName() + "</html>");
		//lblName.setBackground(new Color(255,214,178));
		lblName.setOpaque(false);
		lblName.setFont(new Font(lblName.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.02)));
		lblName.setLayout(new FlowLayout(FlowLayout.CENTER));
		//lblName.setBackground(new Color(200,221,242));
		//lblName.setSize(new Dimension((int)(lblName.getText().length()*Values.SCREEN_WIDTH*0.02), (int) (Values.SCREEN_WIDTH*0.021)));
		lblName.setBorder(BorderFactory.createEmptyBorder());

		JLabel lblInfo = new JLabel(retrieveUserData());
		lblInfo.setOpaque(false);
		lblInfo.setLayout(new FlowLayout(FlowLayout.CENTER));
		lblInfo.setBorder(BorderFactory.createEmptyBorder());
		lblInfo.setFont(new Font(lblInfo.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.01)));

		subRow.add(lblName);
		subRow.add(lblInfo);
		
		infoContent = new JScrollPane(subRow);
		infoContent.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		infoContent.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		infoContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		infoContent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		infoContent.getVerticalScrollBar().setUnitIncrement(5);
		infoContent.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.25), (int)(Values.SCREEN_HEIGHT*0.145)));
		infoContent.setBounds(subRow.getX(), subRow.getY(), infoContent.getPreferredSize().width, infoContent.getPreferredSize().height);
		row1.add(infoContent);
		JPanel addPanel = new JPanel();
		JButton addAd = new JButton("New Ad");
		addAd.addMouseListener(new RegisterAdMouseEvent());
		addAd.setFont(new Font(addAd.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.02)));
		addPanel.add(addAd);
		
		
		JPanel row2 = new JPanel();
		JTabbedPane tab = new JTabbedPane();
		tab.setFont(new Font(tab.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.01)));
		tab.setBorder(BorderFactory.createEmptyBorder());
		//tab.setBackground(new Color(255,179,128));
		tab.setTabPlacement(JTabbedPane.TOP);
		//tab.setPreferredSize(new Dimension((int) (Values.SCREEN_WIDTH*0.99) ,(int) (Values.SCREEN_HEIGHT*0.6) ));
		
		JPanel adsPanel = new JPanel();
		JPanel rentsPanel = new JPanel();
		JPanel notifPanel = new JPanel();
		JPanel ratingsPanel = new JPanel();
		JPanel lendingsPanel = new JPanel();
		//JPanel transactionPanel = new JPanel();
		adsPanel.add(getAdPanels());
		adsPanel.setVisible(true);
		
		//testPanel(adPanel, "adPanel");

		//rentsPanel.add(getRentPanels());
		
		adsPanel.repaint();
		rentsPanel.repaint();
		notifPanel.repaint();
		
		adsPanel.revalidate();
		rentsPanel.revalidate();
		notifPanel.revalidate();
		tab.setOpaque(false);
		
		tab.setPreferredSize(new Dimension((int)Values.SCREEN_WIDTH, (int)(Values.SCREEN_HEIGHT*0.6)));
		
		tab.addTab("<html><h4>Ads</h4></html>", adsPanel);
		tab.addTab("<html><h4>Rents</h4></html>", rentsPanel);
		tab.addTab("<html><h4>Notifications</h4></html>", notifPanel);
	
		JPanel content  = new JPanel();
		content.setLayout(new GridLayout(3, 3));
		ratingsPanel = new JPanel();
		ratingsPanel.setLayout(new GridLayout(3, 3));
		lendingsPanel = new JPanel();
		lendingsPanel.setLayout(new GridLayout(3, 3));
//		transactionPanel = new JPanel();
//		transactionPanel.setLayout(new GridLayout(3, 3));
		ArrayList<Request> requests = new ArrayList<>();
		try {
			requests = new BusinessRulesManager().getAllRequestsByLandlord(new BusinessRulesManager().getLandlordByUserId(user.getUserId()).getLandlordId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Request r: requests){
			if(r == null) continue;
				
			if(!r.getLandlord().getName().equals(Login.getUser().getName())){
				lendingsPanel.add(createLendings(r));
			}
			content.add(createNotification(r));	
		}
		
		
		

		
		tab.setComponentAt(1,getRentPanels());
		tab.setComponentAt(2, content);
		;
		for(int i = 0; i<tab.getTabCount(); i++) tab.getComponentAt(i).setBackground(new Color(200,221,242));
		/*
		infoContent = new JScrollPane(tab);
		infoContent.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		infoContent.setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
		infoContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		infoContent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		infoContent.getVerticalScrollBar().setUnitIncrement(5);
		infoContent.setBounds(tab.getX(), (tab.getY()+10), infoContent.getPreferredSize().width, infoContent.getPreferredSize().height);
		*/
		tab.revalidate();
		row2.add(tab);
		mainPanel.add(row1);
		mainPanel.add(addPanel);
		mainPanel.add(row2);
		//testPanel(mainPanel, "mainPanel");
		return mainPanel;
	}
	private JPanel createTransaction(Rental r){
		JPanel content = new JPanel();
		content.setBorder(BorderFactory.createLineBorder(MainPage.themeColorHovered));
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		JPanel namePanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel statusPanel = new JPanel();
		JPanel deliverPanel = new JPanel();
		JPanel rentedPanel = new JPanel();
		JLabel name = new JLabel();
		if(r.getRenter() == null){
			name.setText("Renter: Not found");
		}else{
			name.setText("Renter: "+r.getRenter().getName());
		}
		namePanel.add(name);
		JLabel rentedAt = new JLabel();
		JLabel deliver = new JLabel();
		rentedAt.setText("Rented: "+r.getDateStartTransaction());
		deliver.setText("Deliver: "+r.getDateFinishTransaction());
		rentedPanel.add(rentedAt);
		deliverPanel.add(deliver);
		JLabel status = new JLabel();
		statusPanel.add(status);
		status.setText(r.getStatus());
		JButton finish = new JButton("Finish transaction");
		name.setFont(new Font(name.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.02)));
		status.setFont(new Font(status.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.02)));
		deliver.setFont(new Font(deliver.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.02)));
		rentedAt.setFont(new Font(rentedAt.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.02)));
		finish.setFont(new Font(finish.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.018)));
		buttonPanel.add(finish);
		content.add(namePanel);
		content.add(rentedPanel);
		content.add(deliverPanel);
		content.add(statusPanel);
		content.add(buttonPanel);
		return content;
	}
	private JPanel createRatings(Rental from){
		JPanel content = new JPanel();
		content.setBorder(BorderFactory.createLineBorder(MainPage.themeColorHovered));
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		JLabel name = new JLabel();
		if(from == null||from.getRenter() == null){
			name.setText("No Renter Found");
		}else{
			name.setText(from.getRenter().getName());
		}
		
		JLabel product = new JLabel();
		if(from == null || from.getTitle() == null||from.getTitle().isEmpty()){
			product.setText("No Product Found");
		}else{
			product.setText(from.getTitle());
		}
		JLabel rate = new JLabel();
		
		JPanel namePanel = new JPanel();
		namePanel.add(name);
		JPanel adPanel = new JPanel();
		adPanel.add(product);
		JPanel warning = new JPanel();
		JLabel warningLabel = new JLabel("Click on the stars to select a rate");
		warning.add(warningLabel);
		JPanel rating = new JPanel();
		rateStar1 = new JLabel();
		rateStar2 = new JLabel();
		rateStar3 = new JLabel();
		rateStar4 = new JLabel();
		rateStar5 = new JLabel();
		rateStar1.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
		rateStar2.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
		rateStar3.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
		rateStar4.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
		rateStar5.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
		rateStar1.addMouseListener(new ChangeRateMouseEvent(this));
		rateStar2.addMouseListener(new ChangeRateMouseEvent(this));
		rateStar3.addMouseListener(new ChangeRateMouseEvent(this));
		rateStar4.addMouseListener(new ChangeRateMouseEvent(this));
		rateStar5.addMouseListener(new ChangeRateMouseEvent(this));
		rating.add(rate);
		rating.add(rateStar1);
		rating.add(rateStar2);
		rating.add(rateStar3);
		rating.add(rateStar4);
		rating.add(rateStar5);
		
		
		rate.setText("Rate: ");
		
		name.setFont(new Font(name.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.02)));
		product.setFont(new Font(product.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.02)));
		rate.setFont(new Font(rate.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.018)));
		JPanel buttons = new JPanel();
		JButton confirm = new JButton("Save Rate");
		confirm.addMouseListener(new AcceptSolicitationMouseEvent(from));
		
		confirm.setFont(new Font(confirm.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.018)));
		
		buttons.add(confirm);
		
		content.add(adPanel);
		content.add(namePanel);
		content.add(warning);
		content.add(rating);
		content.add(buttons);
		return content;

	}
	private JPanel createLendings(Request from){
		JPanel content = new JPanel();
		content.setBorder(BorderFactory.createLineBorder(MainPage.themeColorHovered));
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		JLabel name = new JLabel();
		if(from == null||from.getLandlord() == null){
			name.setText("No Renter Found");
		}else{
			name.setText(from.getLandlord().getName());
		}
		
		JLabel product = new JLabel();
		if(from == null || from.getTitle() == null||from.getTitle().isEmpty()){
			product.setText("No Product Found");
		}else{
			product.setText(from.getAd().getTitle());
		}
		JLabel rate = new JLabel();
		
		JPanel namePanel = new JPanel();
		namePanel.add(name);
		JPanel adPanel = new JPanel();
		adPanel.add(product);
		JPanel warning = new JPanel();
		JLabel warningLabel = new JLabel("Click on the stars to select a rate");
		warning.add(warningLabel);
		JPanel rating = new JPanel();
		rateStar1 = new JLabel();
		rateStar2 = new JLabel();
		rateStar3 = new JLabel();
		rateStar4 = new JLabel();
		rateStar5 = new JLabel();
		rateStar1.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
		rateStar2.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
		rateStar3.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
		rateStar4.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
		rateStar5.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.02), (int)(Values.SCREEN_HEIGHT*0.02), "img/emptyStar.png"));
		rateStar1.addMouseListener(new ChangeRateMouseEvent(this));
		rateStar2.addMouseListener(new ChangeRateMouseEvent(this));
		rateStar3.addMouseListener(new ChangeRateMouseEvent(this));
		rateStar4.addMouseListener(new ChangeRateMouseEvent(this));
		rateStar5.addMouseListener(new ChangeRateMouseEvent(this));
		rating.add(rate);
		rating.add(rateStar1);
		rating.add(rateStar2);
		rating.add(rateStar3);
		rating.add(rateStar4);
		rating.add(rateStar5);
		
		
		rate.setText("Rate: ");
		
		name.setFont(new Font(name.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.02)));
		product.setFont(new Font(product.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.02)));
		rate.setFont(new Font(rate.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.018)));
		JPanel buttons = new JPanel();
		JButton confirm = new JButton("Save Rate");
		confirm.addMouseListener(new AcceptSolicitationMouseEvent(from));
		
		confirm.setFont(new Font(confirm.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.018)));
		
		buttons.add(confirm);
		
		content.add(adPanel);
		content.add(namePanel);
		content.add(warning);
		content.add(rating);
		content.add(buttons);
		return content;

	}
	private JPanel createNotification(Request from){
		JPanel content = new JPanel();
		content.setBorder(BorderFactory.createLineBorder(MainPage.themeColorHovered));
		content.setLayout(new BoxLayout(content, BoxLayout.PAGE_AXIS));
		JLabel name = new JLabel();
		JLabel product = new JLabel();
		if(from == null || from.getTitle() == null||from.getTitle().isEmpty()){
			product.setText("No Product Found");
		}else{
			product.setText(from.getTitle());
		}
		
		
		JLabel rate = new JLabel();
		JLabel rateStar = new JLabel();
		String path = "";
		if(from == null || from.getRenter() == null|| from.getRenter().getRateRenter() == null || from.getRenter().getRateRenter() == 0){
			path = "img/zeroStar.png";
			
		}else{
			path = getRateImagePath( from.getRenter().getRateRenter());
		}
		JPanel namePanel = new JPanel();
		namePanel.add(name);
		JPanel adPanel = new JPanel();
		adPanel.add(product);
		JPanel ratePanel = new JPanel();
		ratePanel.add(rate);
		ratePanel.add(rateStar);
		rate.setText("Rate: ");
		rateStar.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.095), (int)(Values.SCREEN_HEIGHT*0.03), path));
		name.setFont(new Font(name.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.02)));
		product.setFont(new Font(product.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.02)));
		rate.setFont(new Font(rate.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.018)));
		JPanel buttons = new JPanel();
		JButton confirm = new JButton("Confirm");
		confirm.addMouseListener(new AcceptSolicitationMouseEvent(from));
		JButton deny = new JButton("Deny");
		deny.addMouseListener(new DenySolicitationMouseEvent(from));
		confirm.setFont(new Font(confirm.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.018)));
		deny.setFont(new Font(deny.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.018)));
		buttons.add(confirm);
		buttons.add(deny);
		content.add(adPanel);
		content.add(namePanel);
		content.add(ratePanel);
		content.add(buttons);
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
}
