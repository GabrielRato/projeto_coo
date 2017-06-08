package view.events;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import beans.Address;
import beans.User;
import model.business.BusinessRulesManager;
import view.MainPage;
import view.SignUp;

public class ConfirmRegistrationEvent implements MouseListener{
	SignUp s;
	public ConfirmRegistrationEvent(SignUp s) {
		this.s = s;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		BusinessRulesManager model = new BusinessRulesManager();
		String name = s.getNameField().getText().trim().toUpperCase();
		String bday = (String)(s.getYear().getSelectedItem())+"-"+(String)(s.getMonth().getSelectedItem())+"-"+(String)(s.getDay().getSelectedItem());
		String email = s.getEmailField().getText().trim().toUpperCase();
		String street =s.getStreetField().getText().trim().toUpperCase();
		String number = s.getNumberField().getText().trim();
		String cep = s.getZipcodeField().getText().trim();
		String state = (String)s.getStateOption().getSelectedItem();
		String city = s.getCityField().getText().trim().toUpperCase();
		String hood = s.getHoodField().getText().trim();
		String phone = s.getPhoneField().getText().trim();
		String passwd = new String(s.getPasswdField().getPassword()).trim();
		String passwdConf = new String(s.getPasswdConfField().getPassword()).trim();
		String path = s.getImagePath();
		String company = s.getCompanyField().getText().trim().toUpperCase();
		String complem = s.getComplemField().getText().toUpperCase().trim();
		boolean complete = true;
		String errorMessage = "";
		if(name.isEmpty()||email.isEmpty()||street.isEmpty()||cep.equals("-")||city.isEmpty()||hood.isEmpty()
				||phone.equals("(  )    -")||passwd.isEmpty()||passwdConf.isEmpty()||number.isEmpty()){
			complete = false;
			errorMessage = "Fields marked with an * cannot be left blank.";
		}
		else if(name.length()>30){
			complete = false;
			errorMessage = "<html>Maximum length for the field <b>Name</b> is 30 characters.</html>";
		}
		else if(bday.contains("\\|")){
			complete = false;
			errorMessage = "<html>Please select a valid value for the <b>Birthdate</b> field.</html>";
		}else if(!passwd.equals(passwdConf)){
			complete = false;
			errorMessage = "<html>Passwords don't match!</html>";
			
		}else if(passwd.length()<8){
			complete = false;
			errorMessage = "<html>Password needs to have at least 8 characters!</html>";
			
		}else if(!email.contains("@")){
			complete = false;
			errorMessage = "<html>Please select a valid value for the <b>E-mail</b> field.</html>";
		}
		if(!complete){
			JOptionPane.showMessageDialog(null, errorMessage,"Warning",JOptionPane.WARNING_MESSAGE);
		}else{
			try{
				Address address = new Address(street, cep, new Integer(number), city, state, hood, complem);
				model.registerNewUser(name, bday, email, address, phone, company, passwd, path);
				JOptionPane.showMessageDialog(null, "User registered Successfully!","Registration",JOptionPane.INFORMATION_MESSAGE);
				MainPage.addContentPanel(MainPage.getContentPanel());
				SignUp.setPath(null);
				SignUp.setImageIcon(null);
			}catch(Exception ex){
				//ex.printStackTrace();
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
