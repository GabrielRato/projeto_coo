package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.sql.Date;
import java.text.ParseException;
import java.time.Year;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;

import com.mysql.fabric.xmlrpc.base.Array;

import view.events.ConfirmRegistrationEvent;
import view.events.HomeMouseEvent;
import view.events.JTextFieldMouseEvent;
import view.events.OpenFileMouseListener;
import view.events.SignUpMouseEvent;

public class SignUp extends View{
	private JPanel mainPanel;
	
	private static ImageIcon m;
	private static String path;
	private JTextField nameText,streetText,hoodText,cityText,emailText,companyText,complemText;
	private JFormattedTextField zipcodeText,phoneText,numberText;
	private JPasswordField passwdText,passwdConfText;
	private JComboBox<String> day,month,year,stateOption;
		public JComboBox<String> getStateOption() {
		return stateOption;
	}

	public void setStateOption(JComboBox<String> stateOption) {
		this.stateOption = stateOption;
	}
		enum DATE{
		DAY,MONTH,YEAR;
	}

	public SignUp() {
		
		init();
		mainPanel.repaint();
		mainPanel.revalidate();
	}
	
	public JComboBox<String> getDay() {
		return day;
	}



	public void setDay(JComboBox<String> day) {
		this.day = day;
	}



	public JComboBox<String> getMonth() {
		return month;
	}



	public void setMonth(JComboBox<String> month) {
		this.month = month;
	}



	public JComboBox<String> getYear() {
		return year;
	}



	public void setYear(JComboBox<String> year) {
		this.year = year;
	}


	public JTextField getComplemField() {
		return complemText;
	}
	public void setComplemField(JTextField complemText) {
		this.complemText = complemText;
	}

	public JTextField getCompanyField() {
		return companyText;
	}
	public void setCompanyField(JTextField companyText) {
		this.companyText = companyText;
	}
	
	public String getImagePath(){
		return path;
	}
	public JTextField getCityField() {
		return cityText;
	}

	public void setCityField(JTextField cityText) {
		this.cityText = cityText;
	}



	public JTextField getNameField() {
		return nameText;
	}


	public void setNameField(JTextField nameText) {
		this.nameText = nameText;
	}


	public JTextField getStreetField() {
		return streetText;
	}


	public void setStreetField(JTextField streetText) {
		this.streetText = streetText;
	}


	public JTextField getHoodField() {
		return hoodText;
	}


	public void setHoodField(JTextField hoodText) {
		this.hoodText = hoodText;
	}


	public JTextField getEmailField() {
		return emailText;
	}


	public void setEmailField(JTextField emailText) {
		this.emailText = emailText;
	}


	public JTextField getZipcodeField() {
		return zipcodeText;
	}


	public void setZipcodeField(JFormattedTextField zipcodeText) {
		this.zipcodeText = zipcodeText;
	}


	public JTextField getPhoneField() {
		return phoneText;
	}


	public void setPhoneField(JFormattedTextField phoneText) {
		this.phoneText = phoneText;
	}


	public JTextField getNumberField() {
		return numberText;
	}


	public void setNumberField(JFormattedTextField numberText) {
		this.numberText = numberText;
	}


	public JPasswordField getPasswdField() {
		return passwdText;
	}


	public void setPasswdField(JPasswordField passwdText) {
		this.passwdText = passwdText;
	}


	public JPasswordField getPasswdConfField() {
		return passwdConfText;
	}


	public void setPasswdConfField(JPasswordField passwdConfText) {
		this.passwdConfText = passwdConfText;
	}
	
	public static void setPath(String path){
		SignUp.path = path;
		path = OpenFileMouseListener.getFilePath();
	}
	public static void setImageIcon(ImageIcon m){
		SignUp.m = m;
	}
	private ImageIcon update(){
		if(m == null || path == null){
			return getImage((int)(Values.SCREEN_HEIGHT*0.08), (int)(Values.SCREEN_HEIGHT*0.08), "img/noImageIcon.png");
		}
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage().getScaledInstance((int)(Values.SCREEN_HEIGHT*0.08), (int)(Values.SCREEN_HEIGHT*0.08), Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	@Override
	public void init() {
		JPanel p = createContent();
		SignUpMouseEvent.setTaskPanel(p);
		OpenFileMouseListener.setTaskPanel(p);
	}
	private JPanel createContent(){
		
		JPanel mainContent = new JPanel();
		mainContent.setPreferredSize(new Dimension((int)Values.SCREEN_WIDTH, (int)(Values.SCREEN_HEIGHT*0.8)));
		//mainContent.setBorder(BorderFactory.createDashedBorder(, 5f, 2f, 3f, true));
		JScrollPane scrollPane = new JScrollPane(mainContent,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		mainContent.setLayout(new BoxLayout(mainContent,BoxLayout.PAGE_AXIS));
		JLabel title = new JLabel("Register ");
		title.setFont(new Font(title.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.02)));
		title.setBorder(BorderFactory.createEmptyBorder((int)(Values.SCREEN_HEIGHT*0.03), 0, (int)(Values.SCREEN_HEIGHT*0.07),0));
		title.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.15)));
		title.setHorizontalAlignment(JLabel.CENTER);
		
		
		mainContent.add(title);
		
		
		
		JLabel photo = new JLabel("Photo: ");
		photo.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		photo.setFont(new Font(title.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		
		
		JPanel firstRow = new JPanel();
		JLabel image = new JLabel();
		image.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		image.setSize(new Dimension((int)(Values.SCREEN_WIDTH*0.015), (int)(Values.SCREEN_HEIGHT*0.04)));
		image.setIcon(update());
		JButton searchImage = new JButton("Open File...");
		
		searchImage.setFont(new Font(searchImage.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.008)));
		searchImage.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.07), (int)(Values.SCREEN_HEIGHT*0.03)));
		searchImage.addActionListener(new OpenFileMouseListener(mainContent,"SIGN_UP"));
		
		firstRow.add(photo);
		firstRow.add(image);
		JPanel space = new JPanel();
		space.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.03), (int)(Values.SCREEN_HEIGHT*0.02)));
		firstRow.add(space);
		firstRow.add(searchImage);
		mainContent.add(firstRow);
		
		JLabel name = new JLabel("*Name:");
		name.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		name.setFont(new Font(name.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		nameText.setFont(new Font(name.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		nameText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		setNameField(nameText);
		mainContent.add(createRow(name, nameText));
		
		
		JLabel street = new JLabel("*Street:");
		street.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		street.setFont(new Font(street.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		streetText = new JTextField();
		streetText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		streetText.setFont(new Font(street.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		streetText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		setStreetField(streetText);
		mainContent.add(createRow(street,streetText));
		
		JLabel number = new JLabel("*Number: ");
		number.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		number.setFont(new Font(number.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		numberText = new JFormattedTextField();
		numberText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		numberText.setFont(new Font(number.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		MaskFormatter maskNumber = null;
		try {
			maskNumber = new MaskFormatter("#######");
		} catch (ParseException e) {
		}
		maskNumber.install(numberText);
		numberText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		setNumberField(numberText);
		numberText.addMouseListener(new JTextFieldMouseEvent());
		mainContent.add(createRow(number,numberText));
		
		JLabel complem= new JLabel("Complement:");
		complem.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		complem.setFont(new Font(complem.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		complemText = new JTextField();
		complemText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		complemText.setFont(new Font(complem.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		complemText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		setComplemField(complemText);
		mainContent.add(createRow(complem,complemText));
		
		JLabel hood = new JLabel("*Neighborhood:");
		hood.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		hood.setFont(new Font(hood.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		hoodText = new JTextField();
		hoodText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		hoodText.setFont(new Font(hood.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		hoodText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		setHoodField(hoodText);
		mainContent.add(createRow(hood, hoodText));
		
		JLabel city = new JLabel("*City:");
		city.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		city.setFont(new Font(city.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		cityText = new JTextField();
		cityText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		cityText.setFont(new Font(city.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		cityText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		setCityField(cityText);
		mainContent.add(createRow(city, cityText));
		
		JLabel state = new JLabel("*State:");
		state.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		state.setFont(new Font(state.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		stateOption = new JComboBox<String>();
		fillStateList(stateOption);
		stateOption.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		stateOption.setFont(new Font(state.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		
		
		mainContent.add(createRow(state, stateOption));
		
		JLabel zipcode = new JLabel("*CEP:");
		zipcode.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		zipcode.setFont(new Font(zipcode.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		zipcodeText = new JFormattedTextField();
		zipcodeText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		zipcodeText.setFont(new Font(zipcode.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		zipcodeText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		MaskFormatter maskData = null;
		try {
			maskData = new MaskFormatter("#####-###");
		} catch (ParseException e) {
		}
		
		maskData.install(zipcodeText);
		setZipcodeField(zipcodeText);
		zipcodeText.addMouseListener(new JTextFieldMouseEvent());
		mainContent.add(createRow(zipcode, zipcodeText));
		
		JLabel phone= new JLabel("*Phone:");
		phone.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		phone.setFont(new Font(zipcode.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		phoneText = new JFormattedTextField();
		phoneText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		phoneText.setFont(new Font(phone.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		phoneText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		MaskFormatter maskPhoneData = null;
		try {
			maskPhoneData = new MaskFormatter("(##)####-#####");
		} catch (ParseException e) {
		}
		maskPhoneData.install(phoneText);
		setPhoneField(phoneText);
		phoneText.addMouseListener(new JTextFieldMouseEvent());
		mainContent.add(createRow(phone, phoneText));
		
		JLabel company= new JLabel("Company:");
		company.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		company.setFont(new Font(company.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		companyText = new JTextField();
		companyText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		companyText.setFont(new Font(company.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		companyText.setCursor(new Cursor(Cursor.TEXT_CURSOR));		
		setCompanyField(companyText);
		mainContent.add(createRow(company, companyText));

		
		
		JLabel email= new JLabel("*E-mail:");
		email.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		email.setFont(new Font(email.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		emailText = new JTextField();
		emailText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		emailText.setFont(new Font(email.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		emailText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		setEmailField(emailText);
		
		
		mainContent.add(createRow(email, emailText));

		JPanel row = new JPanel();
		JLabel date= new JLabel("*BirthDate:");
		date.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		date.setFont(new Font(date.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		day = new JComboBox<>();
		day.setFont(new Font(date.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.008)));
		month =new JComboBox<>();
		month.setFont(new Font(date.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.008)));
		year = new JComboBox<>();
		year.setFont(new Font(date.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.008)));
		fillJCombo(day,DATE.DAY);
		fillJCombo(month,DATE.MONTH);
		fillJCombo(year,DATE.YEAR);
		row.add(date);
		row.add(day);
		row.add(month);
		row.add(year);
		mainContent.add(row);
		
		JLabel passwd= new JLabel("*Password:");
		passwd.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		passwd.setFont(new Font(passwd.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		passwdText = new JPasswordField();
		passwdText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		passwdText.setFont(new Font(passwd.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		passwdText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		mainContent.add(createRow(passwd, passwdText));
		
		
		JLabel passwdConf= new JLabel("<html>Password<br/>Confirmation:</html>");
		passwdConf.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.04)));
		passwdConf.setFont(new Font(passwdConf.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		passwdConfText = new JPasswordField();
		passwdConfText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		passwdConfText.setFont(new Font(passwdConf.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		passwdConfText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		
		mainContent.add(createRow(passwdConf, passwdConfText));
		
		JLabel message = new JLabel("* required fields");
		message.setFont(new Font(message.getFont().getFontName(), Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.01) ));
		message.setForeground(Color.RED);
		mainContent.add(message);
		
		JButton confirmRegister = new JButton("Confirm");
		confirmRegister.setFont(new Font(confirmRegister.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.008)));
		confirmRegister.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.06), (int)(Values.SCREEN_HEIGHT*0.03)));
		confirmRegister.addMouseListener(new ConfirmRegistrationEvent(this));
		JPanel spaceButtons = new JPanel();
		spaceButtons.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.03), (int)(Values.SCREEN_HEIGHT*0.02)));
		
		JButton cancel = new JButton("Cancel");
		cancel.setFont(new Font(cancel.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.008)));
		cancel.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.06), (int)(Values.SCREEN_HEIGHT*0.03)));
		cancel.addMouseListener(new HomeMouseEvent(MainPage.getContentPanel()));

		JPanel lastRow = new JPanel();
		lastRow.add(confirmRegister);
		lastRow.add(spaceButtons);
		lastRow.add(cancel);
		mainContent.add(lastRow);
		scrollPane.setPreferredSize(new Dimension((int)Values.SCREEN_WIDTH,(int)(Values.SCREEN_HEIGHT*0.63)));
		scrollPane.setVisible(true);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension((int)Values.SCREEN_WIDTH,(int)(Values.SCREEN_HEIGHT*0.5)));
		mainPanel.setVisible(true);
		mainPanel.add(scrollPane);
		
	
		return mainPanel;
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
			case YEAR:
				info.add("-- Year --");
				for(int i = Year.now().getValue();i>=(Year.now().getValue()-95);i--){
					info.add(""+i);
				}
				break;
		}
		for(String s: info){
			combo.addItem(s);
		}
	}
	private JPanel createRow(JLabel label,JComponent component){
		JPanel row = new JPanel();
		row.add(label);
		row.add(component);
		return row;
		
	}
	private void fillStateList(JComboBox<String> combo){
		combo.addItem("AC");	 
		combo.addItem("AL"); 
		combo.addItem("AP");	 
		combo.addItem("AM");	 
		combo.addItem("BA");	 
		combo.addItem("CE");	 
		combo.addItem("DF");	 
		combo.addItem("ES");	 
		combo.addItem("GO");	 
		combo.addItem("MA");	 
		combo.addItem("MT");	 
		combo.addItem("MS");	 
		combo.addItem("MG");	 
		combo.addItem("PA");	 
		combo.addItem("PB");	 
		combo.addItem("PR");	 
		combo.addItem("PE");	 
		combo.addItem("PI");	 
		combo.addItem("RJ");	 
		combo.addItem("RN");	 
		combo.addItem("RS");	 
		combo.addItem("RO");	 
		combo.addItem("RR");	 
		combo.addItem("SC");	 
		combo.addItem("SP");	 
		combo.addItem("SE");	 
		combo.addItem("TO");
	}
	
	

}



