package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import beans.Address;
import beans.Ad;
import beans.Landlord;
import beans.User;

import model.business.BusinessRulesManager;
import view.events.HomeMouseEvent;
import view.events.RentMouseEvent;
import view.events.DeleteAdMouseEvent;


public class ProductView extends View{
	private JPanel mainProductView = new JPanel();
	private static Ad ad;
	
	private NumberFormat formatter = NumberFormat.getCurrencyInstance();
	
	private JButton rent;
	private JComboBox<String> initDayCombo;
	private JComboBox<String> initMonthCombo;
	private JComboBox<String> finalDayCombo;
	private JComboBox<String> finalMonthCombo; 
	
	@Override
	public void init() {
		createProductView();
		
	}
	enum DATE{
		DAY,MONTH;
	}
	public JButton getRent() {
		return rent;
	}
	public void setRent(JButton rent) {
		this.rent = rent;
	}
	public JComboBox<String> getInitDayCombo() {
		return initDayCombo;
	}
	public void setInitDayCombo(JComboBox<String> initDayCombo) {
		this.initDayCombo = initDayCombo;
	}
	public JComboBox<String> getInitMonthCombo() {
		return initMonthCombo;
	}
	public void setInitMonthCombo(JComboBox<String> initMonthCombo) {
		this.initMonthCombo = initMonthCombo;
	}
	public JComboBox<String> getFinalDayCombo() {
		return finalDayCombo;
	}
	public void setFinalDayCombo(JComboBox<String> finalDayCombo) {
		this.finalDayCombo = finalDayCombo;
	}
	public JComboBox<String> getFinalMonthCombo() {
		return finalMonthCombo;
	}
	public void setFinalMonthCombo(JComboBox<String> finalMonthCombo) {
		this.finalMonthCombo = finalMonthCombo;
	}
	
	
	public void setAd(Ad ad){
		try{
			this.ad=new BusinessRulesManager().getAdByIdAd(ad.getAdId());
		}catch (Exception e) {
			this.ad = null;
		}
	}
	public Ad getAd(){
		return ad;
	}
	private void createProductView(){
		
		
		JPanel firstRow = new JPanel();
		firstRow.setLayout(new BoxLayout(firstRow,BoxLayout.LINE_AXIS));
		JLabel image = new JLabel();
		image.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.103),(int)(Values.SCREEN_WIDTH*0.103)));
		image.setBorder(BorderFactory.createEmptyBorder(0, 0, (int)(Values.SCREEN_HEIGHT*0.4), 0));
		ImageIcon icon = null;
		try{
			if(ad.getImage().isEmpty()){
				icon = getImage((int)(Values.SCREEN_WIDTH*0.1), (int)(Values.SCREEN_WIDTH*0.1),"img/noImageIconProduct.png");	
			}else{
				icon= getImageFrom((int)(Values.SCREEN_WIDTH*0.1), (int)(Values.SCREEN_WIDTH*0.1), ad.getImage());
			}
			
		}catch(Exception e){
			icon = getImage((int)(Values.SCREEN_WIDTH*0.1), (int)(Values.SCREEN_WIDTH*0.1),"img/noImageIconProduct.png");
		}
		image.setIcon(icon);
		firstRow.add(image);
		JPanel internalPanel = new JPanel(); 
		internalPanel.setLayout(new BoxLayout(internalPanel, BoxLayout.PAGE_AXIS));
		JPanel internalTitle = new JPanel();
		JLabel title = new JLabel();
		if(ad == null|| ad.getTitle() == null) title.setText("No Title Found");
		else title.setText("<html>"+ad.getTitle()+"</html>");
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		title.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15),(int)(Values.SCREEN_HEIGHT*0.08)));
		title.setFont(new Font(title.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.028)));
		
		JPanel ratingCountPane = new JPanel();
		JLabel ratingCount = new JLabel();
		ratingCount.setFont(new Font(ratingCount.getFont().getFontName(), Font.ITALIC, (int)(Values.SCREEN_HEIGHT*0.018)));
		
		if(ad == null) ratingCount.setText("Times rented: "+0);
		else ratingCount.setText("Times rented: "+ad.getOperations());
		ratingCountPane.add(ratingCount);
		
		JPanel rating = new JPanel();
		JLabel rateStar = new JLabel(); 
		
		

		JLabel rateLabel = new JLabel();
		rateLabel.setText("<html>Product<br/>Rating: </html>");
		rateLabel.setFont(new Font(rateLabel.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.015)));
//		
		// colocar uma avaliacao
		if(ad == null|| ad.getRate()==null ||(int)Math.round(ad.getRate()) == 0){
			rateStar.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.06), (int)(Values.SCREEN_HEIGHT*0.02), "img/zeroStar.png"));
		}else{
			String path = getRateImagePath(ad.getRate());
			rateStar.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.06), (int)(Values.SCREEN_HEIGHT*0.02), path));
		}
		JLabel price = new JLabel();
		price.setFont(new Font(price.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.015)));
		if(ad == null|| ad.getPrice() == null||ad.getPrice()==0){
			price.setText("Product Unavailable!");
			price.setForeground(Color.red);
		}else{
			price.setText("Price: "+formatter.format(ad.getPrice()));
		}
		
		JPanel pricePanel = new JPanel();
		pricePanel.setBorder(BorderFactory.createEmptyBorder((int)(Values.SCREEN_HEIGHT*0.015), 0, (int)(Values.SCREEN_HEIGHT*0.03), 0));
		pricePanel.add(price);
		Landlord landLord = (Landlord)ad.getLandlord();
		User u = null;
		User currentUser = Login.getUser();
		try{
			u = new BusinessRulesManager().getUserByLandlordId(landLord.getLandlordId());
		}catch(Exception e){
			e.printStackTrace();
		}
		JPanel initDatePanel = new JPanel();
		JPanel finalDatePanel = new JPanel();
		JLabel initDate = new JLabel();
		JLabel finalDate = new JLabel();
		JPanel space = new JPanel();
		JPanel rentPanel = new JPanel();
		if(currentUser != null && currentUser.getUserId() != u.getUserId()){
			rent = new JButton("Rent");
			rent.setFont(new Font(rent.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.02)));
			rent.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1), (int)(Values.SCREEN_HEIGHT*0.035)));
			rent.addMouseListener(new RentMouseEvent(this));
			rentPanel.add(rent);
			space.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.01), (int)(Values.SCREEN_HEIGHT*0.05)));
			initDayCombo = new JComboBox<String>();
			initMonthCombo = new JComboBox<String>();
			finalDayCombo = new JComboBox<String>();
			finalMonthCombo = new JComboBox<String>();
			fillJCombo(initDayCombo, DATE.DAY);
			fillJCombo(finalDayCombo, DATE.DAY);
			fillJCombo(initMonthCombo, DATE.MONTH);
			fillJCombo(finalMonthCombo, DATE.MONTH);
			initDate.setText("<html>Rent<br/>from: </html>");
			finalDate.setText("To :");
			initDate.setFont(new Font(initDate.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.015)));
			finalDate.setFont(new Font(finalDate.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.015)));
			finalDayCombo.setFont(new Font(finalDayCombo.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.015)));
			finalMonthCombo.setFont(new Font(finalMonthCombo.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.015)));
			initMonthCombo.setFont(new Font(initMonthCombo.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.015)));
			initDayCombo.setFont(new Font(initDayCombo.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.015)));
			initDatePanel.add(initDate);
			initDatePanel.add(initDayCombo);
			initDatePanel.add(initMonthCombo);
			finalDatePanel.add(finalDate);
			finalDatePanel.add(finalDayCombo);
			finalDatePanel.add(finalMonthCombo);
		}else{
			rentPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
			if(currentUser != null){
				JButton deleteAd = new JButton("Delete ad");
				deleteAd.setBackground(Color.RED);
				deleteAd.setForeground(Color.RED);
				deleteAd.setLayout(new FlowLayout(FlowLayout.CENTER));
				deleteAd.setFont(new Font(deleteAd.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.02)));
				deleteAd.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1), (int)(Values.SCREEN_HEIGHT*0.035)));
				deleteAd.addMouseListener(new DeleteAdMouseEvent(this));
				rentPanel.add(deleteAd);
			}
			else{
				JLabel loginReminder = new JLabel("<html>To send a rental request, please sign in.</t></center></html>");
				loginReminder.setForeground(Color.RED);
				loginReminder.setBorder(BorderFactory.createEmptyBorder((int)(Values.SCREEN_HEIGHT*0.02), 0, (int)(Values.SCREEN_HEIGHT*0.02), 0));
				loginReminder.setFont(new Font(initDate.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.014)));
				rentPanel.add(loginReminder);
			
			}
		}
		
		
		JPanel ownerInfoPanel = new JPanel();
		ownerInfoPanel.setLayout(new BoxLayout(ownerInfoPanel, BoxLayout.PAGE_AXIS));
		JLabel address = new JLabel();
		address.setFont(new Font(address.getFont().getFontName(), Font.ITALIC, (int)(Values.SCREEN_HEIGHT*0.02)));
		address.setBorder(BorderFactory.createEmptyBorder(0, 0, (int)(Values.SCREEN_HEIGHT*0.02), 0));
		JLabel name = new JLabel();
		name.setFont(new Font(name.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.018)));
		JLabel email = new JLabel();
		email.setFont(new Font(email.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.018)));
		JLabel ratingLandLord = new JLabel();
		ratingLandLord.setFont(new Font(ratingLandLord.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.018)));
		JLabel ratingLandLordStar  = new JLabel();
		JLabel company = new JLabel();
		company.setFont(new Font(company.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.018)));
		ratingLandLord.setText("<html>Landlord<br/>Rating:</html>");
	
			
				if(ad == null||ad.getLandlord() == null ){
				address.setText("<html><center>Location: Avenida Vila Ema,1559, Perto da vila prudente<br/>90623-720 California - LA</center></html>");
				name.setText("Name: No Landlord Found");
				email.setText("E-mail: example@email.com");
				ratingLandLordStar.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.06), (int)(Values.SCREEN_HEIGHT*0.02), "img/zeroStar.png"));
				company.setText("Company: No Company.");
				}else{
					
					try{
						Address addressLand = u.getAddress();
						if(addressLand.getComplement() == null) addressLand.setComplement("");
						address.setText("<html><center>Location: "+addressLand.getStreetName()+ ","+addressLand.getNumber()+" "+addressLand.getComplement()+"<br/>"+addressLand.getCep()+" "+addressLand.getCity()+" - "+addressLand.getState()+"</center></html>");
						
						name.setText("Name: "+landLord.getName());
						email.setText("E-mail: "+landLord.getEmail());
						ratingLandLordStar.setIcon(getImage((int)(Values.SCREEN_WIDTH*0.06), (int)(Values.SCREEN_HEIGHT*0.02), getRateImagePath(landLord.getRateLandlord())));
						if(landLord.getCompanyInformation().isEmpty()){
							company.setText("Company: No Company.");
						}else{
							company.setText("Company: "+landLord.getCompanyInformation());
						}
					}catch(Exception e){
						e.printStackTrace();
					}
				
				
			
			}
		
		JPanel addressPanel = new JPanel();
		addressPanel.add(address);
		ownerInfoPanel.add(addressPanel);
		JPanel nameInnerPanel = new JPanel();
		nameInnerPanel.add(name);
		
		
		JPanel companyInnerPanel = new JPanel();
		companyInnerPanel.add(company);
		
		JPanel emailInnerPanel = new JPanel();
		emailInnerPanel.add(email);
	
		JPanel lower = new JPanel();
		lower.add(ratingLandLord);
		lower.add(ratingLandLordStar);
		ownerInfoPanel.add(nameInnerPanel);
		ownerInfoPanel.add(emailInnerPanel);
		ownerInfoPanel.add(companyInnerPanel);
		ownerInfoPanel.add(lower);
		
		JPanel descriptionPanel = new JPanel();
		JLabel desc = new JLabel();
		desc.setFont(new Font(desc.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.023)));
		desc.setBorder(BorderFactory.createEmptyBorder(0, 0, (int)(Values.SCREEN_HEIGHT*0.05), 0));
		descriptionPanel.setBorder(BorderFactory.createLineBorder(MainPage.themeColor,5,true));
		desc.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.25), (int)(Values.SCREEN_HEIGHT*0.25)));
		String content = "Description not available.";
		if(ad != null && ad.getDescription()!=null){
			content = ad.getDescription();
		}
		desc.setText("<html><i>Description:</i><br/><br/>"
				+content
				+ "</html>");
		descriptionPanel.add(desc);
		rating.add(rateLabel);
		rating.add(rateStar);
		
		JButton back = new JButton("Back");
		back.addMouseListener(new HomeMouseEvent(MainPage.getContentPanel()));
		back.setFont(new Font(back.getFont().getFontName(), Font.BOLD, (int)(Values.SCREEN_HEIGHT*0.016)));
		JPanel goBack = new JPanel();
		goBack.setBorder(BorderFactory.createEmptyBorder((int)(Values.SCREEN_HEIGHT*0.02), 0, 0, 0));
		goBack.add(back);
		
		internalTitle.add(title);
		internalPanel.add(internalTitle);
		internalPanel.add(ratingCountPane);
		internalPanel.add(rating);
		internalPanel.add(pricePanel);
		if(currentUser != null && currentUser.getUserId() != u.getUserId()){
			internalPanel.add(initDatePanel);
			internalPanel.add(finalDatePanel);
			internalPanel.add(space);
		}
		
		internalPanel.add(rentPanel);
		internalPanel.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.3),(int)(Values.SCREEN_HEIGHT*0.7)));
		space = new JPanel();
		space.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.01), (int)(Values.SCREEN_HEIGHT*0.05)));
		internalPanel.add(space);
		
		internalPanel.add(ownerInfoPanel);
		internalPanel.add(goBack);
		firstRow.add(image);
		firstRow.add(internalPanel);
		firstRow.add(descriptionPanel);
		
		
		mainProductView.add(firstRow);
		
		
		MainPage.addContentPanel(mainProductView);
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
	private void fillJCombo(JComboBox<String> combo,DATE d){
		ArrayList<String> info = new ArrayList<>(); 
		switch(d){
			case DAY:
				info.add("-- Day --");
				for(int i = 1;i<=31;i++){
					info.add(""+i);
				}
				break;
			case MONTH:
				info.add("-- Month --");
				for(int i = 1;i<=12;i++){
					info.add(""+i);
				}
				break;
			
		}
		for(String s: info){
			combo.addItem(s);
		}
	}
public ImageIcon getImageFrom(int width,int height,String imageUrl){
		
		ImageIcon imageIcon = new ImageIcon(imageUrl);
		Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}

}
