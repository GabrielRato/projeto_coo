package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import beans.User;
import model.business.Persistency;
import view.events.HomeMouseEvent;
import view.events.LoginMouseEvent;
import view.events.SavedStateEvent;
import view.events.SignInMouseEvent;


public class Login extends View{
	
	public static boolean isLogged = false;
	private JPanel olderContent;
	private static User currentUser;
	private JTextField txtName;
	private JPasswordField txtPass;
	private JCheckBox checkSave;
	public static boolean saveState = false; 
	
	/*
	 * A variavel olderContent sera utilizada para quando cancelarmos podermos tornar o painel
	 * visivel novamente
	 */
	public Login(){
		
		
		
	}
	
	public void init(){
		MainPage.addContentPanel(createLogin());
	}
	
	
	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JPasswordField getTxtPass() {
		return txtPass;
	}

	public void setTxtPass(JPasswordField txtPass) {
		this.txtPass = txtPass;
	}
	public static void setUser(User s){
		currentUser = s;
	}
	
	public static User getUser(){
		return currentUser;
	}
	
	
	/*
	 	 * mainPanel é o painel principal o qual comportara todos os paineis de conteudo
		 * o layout foi configurado da esquerda para a direita (FlowLayout.LEFT)
		 * O painel de logo consiste em uma JLabel com o fundo de uma imagem escalonada para o tamanho 130x135
		 *  o painel source comporta tudo que possui informacoes e acoes a serem feitas como os campos de texto senha e botoes
		 *  e tem como layout vertical igualmente espaçados
		 *  
	 */
	private JPanel createLogin(){
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);

		JLabel title = new JLabel("Login ");
		title.setOpaque(true);
		title.setFont(new Font(title.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.02)));
		title.setBorder(BorderFactory.createEmptyBorder((int)(Values.SCREEN_HEIGHT*0.03), 0, (int)(Values.SCREEN_HEIGHT*0.07),0));
		title.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.07),(int)(Values.SCREEN_HEIGHT*0.15)));
		title.setHorizontalAlignment(JLabel.CENTER);
		JPanel titlePane = new JPanel();
		JPanel space = new JPanel();
		space.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.03), (int)(Values.SCREEN_HEIGHT*0.03)));
		titlePane.add(space);
		titlePane.add(title);
		
		
		
		/*
		JPanel spaceUp = new JPanel();
		//spaceUp.setBounds(0, 0, (int)(Values.SCREEN_WIDTH*0.3), (int)(Values.SCREEN_HEIGHT*0.05));
		spaceUp.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.3),(int)(Values.SCREEN_HEIGHT*0.03)));
		*/
		Color highlight = new Color(255,214,178);
		
		JPanel superRow = new JPanel();
		superRow.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		JPanel spaceLeft = new JPanel();
		spaceLeft.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.3),(int)(Values.SCREEN_HEIGHT*0.2)));
		
		JPanel loginContent = new JPanel();
		loginContent.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		loginContent.setLayout(new BoxLayout(loginContent,BoxLayout.PAGE_AXIS));
		loginContent.setBackground(highlight);
		//loginContent.setBounds((int)(Values.SCREEN_WIDTH*0.3), (int)(Values.SCREEN_HEIGHT*0.2),(int)(Values.SCREEN_WIDTH*0*4) , (int)(Values.SCREEN_HEIGHT*0.2) );
		//loginContent.setPreferredSize(new Dimension( (int)(Values.SCREEN_WIDTH*0*3) , (int)(Values.SCREEN_HEIGHT*0.05) ));
		//
		JPanel row1 = new JPanel();
		row1.setLayout(new FlowLayout (FlowLayout.CENTER));
		JLabel lblName = new JLabel("User ");
		lblName.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.07),(int)(Values.SCREEN_HEIGHT*0.04)));
		lblName.setFont(new Font(lblName.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		Persistency savedInstanceState = new Persistency();
		txtName = new JTextField(savedInstanceState.GetDocEmail());
		txtName.setToolTipText("Name");
		txtName.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		txtName.setFont(new Font(lblName.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		txtName.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		row1.add(lblName);
		row1.add(txtName);
		row1.setBackground(highlight);
		
		JPanel row2 = new JPanel();
		row2.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15),(int)(Values.SCREEN_HEIGHT*0.06)));
		row2.setLayout(new FlowLayout (FlowLayout.CENTER));
		JLabel lblPass = new JLabel("Password ");
		lblPass.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.07),(int)(Values.SCREEN_HEIGHT*0.04)));
		lblPass.setFont(new Font(lblPass.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.01)));
		txtPass = new JPasswordField(savedInstanceState.GetDocSenha());
		txtPass.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.15), (int)(Values.SCREEN_HEIGHT*0.02)));
		txtPass.setFont(new Font(lblPass.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.008)));
		txtPass.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		row2.add(lblPass);
		row2.add(txtPass);
		row2.setBackground(highlight);
		
		JPanel row = new JPanel();
		checkSave = new JCheckBox();
		checkSave.setText("Keep Logged");
		saveState = savedInstanceState.GetBoxState();
		checkSave.setSelected(savedInstanceState.GetBoxState());
		checkSave.addItemListener(new SavedStateEvent());
		
		
		row.add(checkSave);
		row.setBackground(highlight);
		
		JPanel row3 = new JPanel();
		row3.setLayout(new FlowLayout (FlowLayout.CENTER));
		JButton btnOk = new JButton("OK");
		JButton btnCancel = new JButton("Cancel");
		btnOk.addMouseListener(new LoginMouseEvent(this));
		btnCancel.addMouseListener(new HomeMouseEvent(MainPage.getContentPanel()));
		row3.add(btnOk);
		row3.add(btnCancel);
		row3.setBackground(highlight);
		
		JPanel row4 = new JPanel();
		row4.setLayout(new FlowLayout (FlowLayout.CENTER));
		//row4.setBackground(highlight);
		JLabel btnForgotPass = new JLabel("Forgot your password?"); //click listener
		//qbtnForgotPass.setBackground(highlight);
		btnForgotPass.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnForgotPass.setBorder(null);
		row4.add(btnForgotPass);
		
		JPanel spaceRight = new JPanel();
		spaceRight.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.3),(int)(Values.SCREEN_HEIGHT*0.2)));
		
		
		
		JPanel spaceDown = new JPanel();
		spaceDown.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.3),(int)(Values.SCREEN_HEIGHT*0.25)));
		
		/*
		 * * o mainPanel comporta 4 linhas de componentes delimitadas pelos paineis row
		 */
		//mainPanel.add(spaceUp);
		
		
		//superRow.add(spaceLeft);
		
		loginContent.add(row1);
		loginContent.add(row2);
		loginContent.add(row);
		loginContent.add(row3);
		loginContent.add(row4);
		superRow.add(loginContent);
		//superRow.add(spaceRight);
		
		mainPanel.add(titlePane);
		mainPanel.add(superRow);
		mainPanel.add(spaceDown);
		
		return mainPanel;
	}
}