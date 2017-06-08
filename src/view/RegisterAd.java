package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.border.BevelBorder;

import beans.Landlord;
import view.events.ConfirmAdRegistrationEvent;
import view.events.ConfirmRegistrationEvent;
import view.events.HomeMouseEvent;
import view.events.OpenFileMouseListener;
import beans.Ad;

//landlord.getUserId
//endereco image

public class RegisterAd extends View{
	private JPanel mainPanel;
	private JTextField nameText, adTitleText, descriptionText, priceText, availableText;
	JDialog d = new JDialog();
	private static ImageIcon m;
	private static String path;
	
	public JTextField getNameText(){
		return this.nameText;
	}
	
	public JTextField getAdTitleText(){
		return this.adTitleText;
	}
	
	public JTextField getDescriptionText(){
		return this.descriptionText;
	}
	
	public JTextField getPriceText(){
		return this.priceText;
	}
	
	public JTextField getAvailableText(){
		return this.availableText;
	}
	

	public static void setPath(String path){
		RegisterAd.path = path;
		path = OpenFileMouseListener.getFilePath();
	}
	
	public static String getPath(){
		return path;
	}
	
	public static void setImageIcon(ImageIcon m){
		RegisterAd.m = m;
	}
	
	
	public RegisterAd(){
		
	}
	
	@Override
	public void init() {
		mainPanel = createContent();
		OpenFileMouseListener.setTaskPanel(mainPanel);
		path = OpenFileMouseListener.getFilePath();
		MainPage.addContentPanel(mainPanel);
		
		
	}
	private ImageIcon update(){
		if(m == null || path == null){
			return getImage((int)(Values.SCREEN_HEIGHT*0.08), (int)(Values.SCREEN_HEIGHT*0.08), "img/noImageIconProduct.png");
		}
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage().getScaledInstance((int)(Values.SCREEN_HEIGHT*0.08), (int)(Values.SCREEN_HEIGHT*0.08), Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	
	private JPanel createContent(){
		JPanel mainContent = new JPanel();
		mainContent.setLayout(new BoxLayout(mainContent,BoxLayout.PAGE_AXIS));
		
		JLabel title = new JLabel("Create Ad");
		title.setFont(new Font(title.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.02)));
		title.setBorder(BorderFactory.createEmptyBorder((int)(Values.SCREEN_HEIGHT*0.03), 0, (int)(Values.SCREEN_HEIGHT*0.07),(int)(Values.SCREEN_WIDTH*0.03)));
		title.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.08),(int)(Values.SCREEN_HEIGHT*0.15)));
		title.setHorizontalAlignment(JLabel.CENTER);
		
		mainContent.add(title);
		
		JLabel photo = new JLabel("Photo: ");
		photo.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		photo.setFont(new Font(title.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		
		
		JPanel row1 = new JPanel();
		JLabel image = new JLabel();
		image.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		image.setSize(new Dimension((int)(Values.SCREEN_WIDTH*0.015), (int)(Values.SCREEN_HEIGHT*0.04)));
		image.setIcon(update());
		JButton searchImage = new JButton("Open File...");
		
		searchImage.setFont(new Font(searchImage.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.008)));
		searchImage.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.07), (int)(Values.SCREEN_HEIGHT*0.03)));
		searchImage.addActionListener(new OpenFileMouseListener(mainPanel,"REGISTER"));
		
		row1.add(photo);
		row1.add(image);
		JPanel space = new JPanel();
		space.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.03), (int)(Values.SCREEN_HEIGHT*0.02)));
		row1.add(space);
		row1.add(searchImage);
		mainContent.add(row1);
		
		JLabel name = new JLabel("*Name:");
		name.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		name.setFont(new Font(name.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		nameText.setFont(new Font(name.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		nameText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		mainContent.add(createRow(name, nameText));
		
		JLabel adTitle = new JLabel("*Title:");
		adTitle.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		adTitle.setFont(new Font(adTitle.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		adTitleText = new JTextField();
		adTitleText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		adTitleText.setFont(new Font(adTitle.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		adTitleText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		mainContent.add(createRow(adTitle,adTitleText));
		
		JLabel description = new JLabel("*Description:");
		description.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		description.setFont(new Font(description.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		descriptionText = new JTextField();
		descriptionText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		descriptionText.setFont(new Font(description.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		descriptionText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		mainContent.add(createRow(description,descriptionText));
		
		JLabel price = new JLabel("*Price:");
		price.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		price.setFont(new Font(price.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		priceText = new JFormattedTextField();
		priceText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		priceText.setFont(new Font(price.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		priceText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		mainContent.add(createRow(price,priceText));
		
		JLabel available = new JLabel("*Number available:");
		available.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.02)));
		available.setFont(new Font(available.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		availableText = new JFormattedTextField();
		availableText.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		availableText.setFont(new Font(available.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		availableText.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		mainContent.add(createRow(available,availableText));
				
		JLabel message = new JLabel("* required fields");
		message.setFont(new Font(message.getFont().getFontName(), Font.BOLD,(int)(Values.SCREEN_HEIGHT*0.01) ));
		message.setForeground(Color.RED);
		mainContent.add(message);
		
		JPanel buttonRow = new JPanel();
		buttonRow.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.1),(int)(Values.SCREEN_HEIGHT*0.1)));
		buttonRow.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JButton confirmRegister = new JButton("Confirm");
		confirmRegister.setFont(new Font(confirmRegister.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.008)));
		confirmRegister.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.06), (int)(Values.SCREEN_HEIGHT*0.03)));
		confirmRegister.addMouseListener(new ConfirmAdRegistrationEvent(this));
		JPanel spaceButtons = new JPanel();
		spaceButtons.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.03), (int)(Values.SCREEN_HEIGHT*0.02)));
		
		JButton cancel = new JButton("Cancel");
		cancel.setFont(new Font(cancel.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.008)));
		cancel.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.06), (int)(Values.SCREEN_HEIGHT*0.03)));
		cancel.addMouseListener(new HomeMouseEvent(MainPage.getContentPanel()));

		
		buttonRow.add(confirmRegister);
		buttonRow.add(spaceButtons);
		buttonRow.add(cancel);
		mainContent.add(buttonRow);
		
		mainPanel = new JPanel();
		mainPanel.setPreferredSize(new Dimension((int)Values.SCREEN_WIDTH,(int)(Values.SCREEN_HEIGHT*0.7)));
		mainPanel.setVisible(true);
		mainPanel.add(mainContent);
		return mainPanel;
	}
	private JPanel createRow(JLabel label,JComponent component){
		JPanel row = new JPanel();
		row.add(label);
		JTextField textField = null;
		JFileChooser chooser = null;
		if(component instanceof JTextField){
			textField = (JTextField) component;
			row.add(textField);
		}else if(component instanceof JFileChooser){
			chooser = (JFileChooser) component;
			row.add(chooser);
		}
		return row;
	}

}
