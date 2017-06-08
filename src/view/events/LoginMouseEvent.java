package view.events;

import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import beans.User;
import model.business.BusinessRulesManager;
import model.business.Persistency;
import view.Login;
import view.MainPage;
import view.Run;
import view.Values;


public class LoginMouseEvent implements MouseListener{
	private User user;
	private Login src;
	private BusinessRulesManager model = new BusinessRulesManager();
	public LoginMouseEvent(Login src){
		this.src = src;
	}
	
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		User s = null;
		try{
			if(src.getTxtName().getText().trim().equals("") || new String(src.getTxtPass().getPassword()).trim().equals("")){
				JOptionPane.showMessageDialog(null, "User and Passwords are required fields!","Invalid Fields",JOptionPane.WARNING_MESSAGE);
				Login.setUser(s);
				Login.isLogged = false;
				return;
			}
			
			s= model.loginUser(src.getTxtName().getText().trim(), new String(src.getTxtPass().getPassword()).trim());
			Persistency saveInstanceState = new Persistency();
			saveInstanceState.SaveBoxState(Login.saveState);
			if(Login.saveState){
				saveInstanceState.SetDocEmail(src.getTxtName().getText().trim());
				saveInstanceState.SetDocSenha(new String(src.getTxtPass().getPassword()).trim());
				
			}
			
			
		}catch(Exception ex){
			String title = "";
			switch(ex.getMessage()){
				case Values.DB_ERROR:
					title = "Database Error";
					break;
				case Values.INVALID_FIELD:
					title = "Invalid Fields";
					
			}
			Login.isLogged = false;
			JOptionPane.showMessageDialog(null, ex.getMessage(), title, JOptionPane.WARNING_MESSAGE);
			return;
		}
		Login.setUser(s);
		
		Login.isLogged = true;
		Run.mainPage.repaintTopPanel();
		MainPage.addContentPanel(MainPage.getContentPanel());
		
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
