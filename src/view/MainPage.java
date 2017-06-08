package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import beans.Ad;
import beans.Landlord;
import beans.User;
import model.business.BusinessRulesManager;
import view.events.CategoryItemMouseEvent;
import view.events.HomeMouseEvent;
import view.events.ProfileMouseEvent;
import view.events.SearchMouseEvent;
import view.events.SettingsMouseEvent;
import view.events.SignUpMouseEvent;
import view.events.SignInMouseEvent;
import view.events.SignOutMouseEvent;

public class MainPage extends View{
	public static Color themeColor = new Color(255,179,128);
	public static Color themeColorHovered = new Color(255,160,110);
	private int categoryWidth = (int)(Values.SCREEN_WIDTH*0.01);
	private JScrollPane categories ;
	private static JPanel contentStructurePanel;
	private static JPanel contentPanel;
	private JPanel mainPanel;
	private JPanel topPanel,bottomPanel,middlePanel;
	private static JScrollPane scrollContentPanel;
	private JTextField searchBar;

	
	JFrame mainPage = new JFrame();
	
	public MainPage(){}
	
	public void init(){
		mainPage.setVisible(true);
		mainPage.setTitle("RentS");
		mainPage.setBounds(0, 0,(int) Values.SCREEN_WIDTH, (int)(Values.SCREEN_HEIGHT*0.99));
		mainPage.setMinimumSize(new Dimension((int)(Values.SCREEN_WIDTH*0.98),(int) (Values.SCREEN_HEIGHT*0.9)));
		mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLookAndFeel();
		createContent();
		
	}

	public JTextField getSearchBar() {
		return searchBar;
	}

	public void setSearchBar(JTextField searchBar) {
		this.searchBar = searchBar;
	}

	public static JPanel getContentPanel(){
		return contentPanel;
	}
	
	public static void addContentPanel(JPanel content){
		if(scrollContentPanel != null){
			scrollContentPanel.setVisible(false);
			contentStructurePanel.repaint();
			/*
			 * Pede a atualização do componente visual, é necessário para aplicar as alterações 
			 * feitas nos objetos que estão no JPanel
			 */
		}
		
		Run.mainPage.updateContent(content);
		/*
		 * Forca a atualização requisitada por repaint()
		 */
		contentStructurePanel.revalidate();
		
		
	}
	
	/*
	 * Este metodo sera utilizado para trocar os botoes de signIn e signUp para para signOut 
	 * caso o usuario estaja ou nao logado
	 */
	private void showLoginButtons(boolean isLogged,JPanel panel){
		if(!isLogged){
			JButton btnSignIn = new JButton("Sign In");
			JButton btnSignUp = new JButton("Sign Up");
			//metodo set font utilizado para trocar a fonte do componente nesse caso 
			//foi usado somente para aumenta-la
			btnSignIn.setFont(new Font(btnSignIn.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.008)));
			btnSignUp.setFont(new Font(btnSignUp.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.008)));
			if((int)(Values.SCREEN_WIDTH*0.008)<Values.FONT_MIN_SIZE){
				btnSignIn.setFont(new Font(btnSignIn.getFont().getName(),Font.BOLD,(Values.FONT_MIN_SIZE)));
				btnSignUp.setFont(new Font(btnSignUp.getFont().getName(),Font.BOLD,(Values.FONT_MIN_SIZE)));
			}
			
			btnSignUp.addMouseListener(new SignUpMouseEvent());
			btnSignIn.addMouseListener(new SignInMouseEvent());
			panel.add(btnSignIn);
			panel.add(btnSignUp);
		}else{
			
			JLabel image = new JLabel();
			image.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			image.setPreferredSize(new Dimension((int)(Values.SCREEN_HEIGHT*0.083), (int)(Values.SCREEN_HEIGHT*0.083)));
			if(Login.getUser() == null){
				image.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.08), (int)(Values.SCREEN_HEIGHT*0.08), "img/noImageIcon.png"));
			}else{
				Landlord l = null;
				try {
					l = new BusinessRulesManager().getLandlordByUserId(Login.getUser().getUserId());
					if(!l.getImage().equals("")){
						image.setIcon(getImageFrom((int)(Values.SCREEN_HEIGHT*0.08), (int)(Values.SCREEN_HEIGHT*0.08), l.getImage()));
					
					}else{
						image.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.08), (int)(Values.SCREEN_HEIGHT*0.08), "img/noImageIcon.png"));
					}
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error",JOptionPane.WARNING_MESSAGE);
				}
				
			}
			image.addMouseListener(new ProfileMouseEvent());
			JButton btnSignOut = new JButton("Sign Out");
			btnSignOut.setFont(new Font(btnSignOut.getFont().getName(),Font.BOLD,(int)(Values.SCREEN_WIDTH*0.008)));
			btnSignOut.addMouseListener(new SignOutMouseEvent());
			panel.add(image);
			panel.add(btnSignOut);
		}
	}
	

	/*
	 * Esse metodo sera responsavel por criar os conteudos do JFrame
	 */
	private void createContent(){
		mainPanel = new JPanel();
		/*
		 * O boxLayout coloca a orientacao em vertical ou em horizontal nesse caso PAGE_AXIS
		 * ou Y_AXIS configuram para vertical
		 * e o primeiro parametro representa o componente o qual sera aplicado o Layout
		 */
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		/*
		 * topPanel contem o logo, a barra de pesquisa e o acesso ao login
		 */

		
		topPanel = new JPanel();
		topPanel.setBackground(new Color(245,245,245));
		/*
		 * O flowlayout e/´ responsavel por colocar os componentes em linha, ao centro, a esquerda
		 * a direita etc
		 */
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		/*
		 * O metodo setPreferredSize e/´ responsavel por sugerir ao programa o tamanho otimo
		 * do componente mas e/´ legal ressaltar que alguns layouts simplesmente ignoram isso
		 */
		topPanel.setPreferredSize(new Dimension((int)Values.SCREEN_WIDTH,(int)(Values.SCREEN_HEIGHT*0.15)));
		
		/*
		 * o metodo getLogo esta na classe abstrata View e foi criado a fim de diminuir o 
		 * acoplamento, i.e. as dependencias de uma classe a mudanca de outra
		 */
		JLabel logo = new JLabel(getLogo((int)(Values.SCREEN_HEIGHT*0.15*0.8), (int)(Values.SCREEN_HEIGHT*0.15*0.8)));
		logo.setPreferredSize(new Dimension((int)(Values.SCREEN_HEIGHT*0.15*0.8)+100,(int)(Values.SCREEN_HEIGHT*0.15*0.8)));
		/*
		 * A propriedade borderfactory e/´ utilizada para criar um tipo border uma vez
		 * que ele nao pode ser instanciado no caso esta sendo utilizado bordas vazias a fim
		 * de corrigir os espacamentos
		 * os parametros dele se referem ao tamanoh em (top,right,bottom,left) do componente
		 */
		logo.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.001), 0, 0));
		
		searchBar = new JTextField(50);
		searchBar.setFont(new Font(searchBar.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.01)));
		searchBar.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.01), (int)(Values.SCREEN_HEIGHT*0.04)));
		searchBar.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.15), 0, 0));
		if(Values.SCREEN_WIDTH == 1024 ){
			searchBar.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.06), (int)(Values.SCREEN_HEIGHT*0.04)));
			searchBar.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.08), 0, 0));
		}else if(Values.SCREEN_WIDTH == 800){
			searchBar.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.04), (int)(Values.SCREEN_HEIGHT*0.04)));
			searchBar.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.01), 0, 0));
		}
		JLabel search = new JLabel();
		/*
		 * O metodo getImage é um metodo tbm da classe abstrata View e realiza o mesmo trabalho de getLogo a diferenca e/´ que
		 * esse metodo foi generalizado para qualquer imagem
		 */
		search.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.04), (int)(Values.SCREEN_HEIGHT*0.04), "img/lupe.png"));
		search.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.01), 0, (int)(Values.SCREEN_WIDTH*0.15)));
		if(Values.SCREEN_WIDTH == 800){
			search.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.01), 0, (int)(Values.SCREEN_WIDTH*0.05)));
		}else if(Values.SCREEN_WIDTH<1920 && Values.SCREEN_WIDTH>1360){
			search.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.01), 0, (int)(Values.SCREEN_WIDTH*0.07)));
		}if(Values.SCREEN_WIDTH<=1280){
			search.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.01), 0, (int)(Values.SCREEN_WIDTH*0.06)));
		}
		search.addMouseListener(new SearchMouseEvent());
		search.setToolTipText("Search for a Product");
		topPanel.add(logo);
		
		topPanel.add(searchBar);
		topPanel.add(search);
		topPanel.repaint();
		topPanel.revalidate();
		
		/*
		 * se estiver logado entao true se não false
		 */
		showLoginButtons(Login.isLogged, topPanel);
		
		
		
		
		
		/*
		 * Painel do meio ou seja p que possui o botao de configuracoes e a barra de categorias
		 */
		middlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		middlePanel.setPreferredSize(new Dimension((int)Values.SCREEN_WIDTH,(int)(Values.SCREEN_HEIGHT*0.09)));
		//pinta o jpanel com a cor do tema da aplicacao
		middlePanel.setBackground(themeColor);
		
		JLabel home = new JLabel(getImage((int)(Values.SCREEN_WIDTH*0.03), (int)(Values.SCREEN_HEIGHT*0.04), "img/homeIcon.png"));
		home.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.03),(int)(Values.SCREEN_HEIGHT*0.06)));
		home.setBorder(BorderFactory.createEmptyBorder((int)(Values.SCREEN_HEIGHT*0.02),(int)(Values.SCREEN_WIDTH*0.01),0,(int)(Values.SCREEN_WIDTH*0.01)));
		
		middlePanel.add(home);
		
		JLabel settings = new JLabel(getImage((int)(Values.SCREEN_WIDTH*0.03), (int)(Values.SCREEN_HEIGHT*0.04), "img/settings.png"));
		settings.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.03),(int)(Values.SCREEN_HEIGHT*0.06)));
		settings.setBorder(BorderFactory.createEmptyBorder((int)(Values.SCREEN_HEIGHT*0.02),(int)(Values.SCREEN_WIDTH*0.01),0,0));
		middlePanel.add(settings);
		
		
		
		
		
		
		//middlePanel.add(categories);
		
		contentStructurePanel = new JPanel();
		
		contentStructurePanel.setLayout(null);

		JPanel categoryContent = new JPanel();
		
		categoryContent.setBackground(themeColor);
		categoryContent.setLayout(new BoxLayout(categoryContent, BoxLayout.PAGE_AXIS));
		/*
		 * JScrollPane é um painel que contem a barra de scroll
		 */
		categories = new JScrollPane(categoryContent);
		categories.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		categories.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		settings.addMouseListener(new SettingsMouseEvent(categories));
		/*
		 * os metodos setVerticalScrollBarPolicy e o HorizontalScrollBarPolicy arrumam as regras para que um scroll apareca
		 * neste caso o scroll horizontal nunca aparecera e o vertical aparecera a medida que houver muitas categorias e não couber 
		 * mais na tela
		 */
		categories.setVisible(false);
		categories.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		categories.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		categories.getVerticalScrollBar().setUnitIncrement(20);

		categories.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.12), (int)(Values.SCREEN_HEIGHT*0.7)));
		categories.setBounds(0, 0, categories.getPreferredSize().width, categories.getPreferredSize().height);
		/*
		 * O metodo newCategory e/´ um metodo criado nessa classe mesmo com a finalidade de criar e adicionar categorias ao Painel
		 */
		addCategory("Automobile", categoryContent);
		addCategory("Clothes", categoryContent);
		addCategory("Tools", categoryContent);
		
		categories.setBorder(BorderFactory.createEmptyBorder());
		categories.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.12), (int)(Values.SCREEN_HEIGHT*0.7)));
		categories.setBounds(0, 0, categories.getPreferredSize().width, categories.getPreferredSize().height);
		
		
		contentStructurePanel.setPreferredSize(new Dimension((int)Values.SCREEN_WIDTH,(int)(Values.SCREEN_HEIGHT*0.7)));
		contentStructurePanel.add(categories);
		contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(3, 3));
		Template t = new Template();
		ArrayList<Ad> ads = null;
		try {
			 ads = new BusinessRulesManager().findByKeyWord("", 0, 9);
			 for(Ad ad:ads){
				 contentPanel.add(t.makeAdPanel(ad));
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		home.addMouseListener(new HomeMouseEvent(contentPanel));
		
		Run.mainPage.updateContent(contentPanel);
		
		
		bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension((int)Values.SCREEN_WIDTH,(int)(Values.SCREEN_HEIGHT*0.05)));
		bottomPanel.setBackground(themeColor);
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,0,-(int)(Values.SCREEN_HEIGHT*0.01),0));
		
		mainPanel.add(topPanel);
		mainPanel.add(middlePanel);
		mainPanel.add(contentStructurePanel);
		mainPanel.add(bottomPanel);
		mainPage.getContentPane().add(mainPanel);
		mainPage.setVisible(true);
		mainPage.pack();
	}
	private void updateContent(JPanel content){
		
		if(content == null){
		scrollContentPanel = new JScrollPane();
		}else{
		scrollContentPanel = new JScrollPane(content);
		
		}
		
		scrollContentPanel.setBorder(BorderFactory.createEmptyBorder());
		scrollContentPanel.setBounds(0,0,(int)(Values.SCREEN_WIDTH),(int)(Values.SCREEN_HEIGHT*0.64));
		scrollContentPanel.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH),(int)(Values.SCREEN_HEIGHT*0.7)));
		scrollContentPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollContentPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollContentPanel.getVerticalScrollBar().setUnitIncrement(20);
		scrollContentPanel.setVisible(true);
		
		contentStructurePanel.add(scrollContentPanel);


	}
	private void addCategory(String category,JPanel content){
		JLabel c = new JLabel(category);
		//ajusta o texto na label para o centro
		JPanel row = new JPanel();
		row.setLayout(new BoxLayout(row, BoxLayout.LINE_AXIS));
		
		c.setHorizontalAlignment(JLabel.CENTER);
		c.setVerticalAlignment(JLabel.CENTER);
		c.setBackground(themeColor);
		c.setOpaque(true);
		c.addMouseListener(new CategoryItemMouseEvent(row));
		c.setPreferredSize(new Dimension(categoryWidth,(int)(Values.SCREEN_HEIGHT*0.05)));
		c.setFont(new Font(c.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_HEIGHT*0.02)));
		if((int)(Values.SCREEN_WIDTH*0.01)<Values.FONT_MIN_SIZE){
			c.setFont(new Font(c.getFont().getName(),Font.PLAIN,(Values.FONT_MIN_SIZE)));
		}
		row.setBorder(BorderFactory.createEmptyBorder());
		row.setPreferredSize(new Dimension(categoryWidth, (int)(Values.SCREEN_HEIGHT*0.05)));
		row.add(c);
		
		content.add(row);
		//new ProductView().init();
	}
	public void repaintTopPanel(){
		mainPanel.removeAll();
		topPanel.removeAll();
		topPanel.setBackground(new Color(245,245,245));
		/*
		 * O flowlayout e/´ responsavel por colocar os componentes em linha, ao centro, a esquerda
		 * a direita etc
		 */
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		/*
		 * O metodo setPreferredSize e/´ responsavel por sugerir ao programa o tamanho otimo
		 * do componente mas e/´ legal ressaltar que alguns layouts simplesmente ignoram isso
		 */
		topPanel.setPreferredSize(new Dimension((int)Values.SCREEN_WIDTH,(int)(Values.SCREEN_HEIGHT*0.15)));
		
		/*
		 * o metodo getLogo esta na classe abstrata View e foi criado a fim de diminuir o 
		 * acoplamento, i.e. as dependencias de uma classe a mudanca de outra
		 */
		JLabel logo = new JLabel(getLogo((int)(Values.SCREEN_HEIGHT*0.15*0.8), (int)(Values.SCREEN_HEIGHT*0.15*0.8)));
		logo.setPreferredSize(new Dimension((int)(Values.SCREEN_HEIGHT*0.15*0.8)+100,(int)(Values.SCREEN_HEIGHT*0.15*0.8)));
		/*
		 * A propriedade borderfactory e/´ utilizada para criar um tipo border uma vez
		 * que ele nao pode ser instanciado no caso esta sendo utilizado bordas vazias a fim
		 * de corrigir os espacamentos
		 * os parametros dele se referem ao tamanoh em (top,right,bottom,left) do componente
		 */
		logo.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.001), 0, 0));
		
		searchBar = new JTextField(50);
		searchBar.setFont(new Font(searchBar.getFont().getName(),Font.PLAIN,(int)(Values.SCREEN_WIDTH*0.01)));
		searchBar.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.01), (int)(Values.SCREEN_HEIGHT*0.04)));
		searchBar.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.15), 0, 0));
		if(Values.SCREEN_WIDTH == 1024 ){
			searchBar.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.06), (int)(Values.SCREEN_HEIGHT*0.04)));
			searchBar.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.08), 0, 0));
		}else if(Values.SCREEN_WIDTH == 800){
			searchBar.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.04), (int)(Values.SCREEN_HEIGHT*0.04)));
			searchBar.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.01), 0, 0));
		}
		JLabel search = new JLabel();
		/*
		 * O metodo getImage é um metodo tbm da classe abstrata View e realiza o mesmo trabalho de getLogo a diferenca e/´ que
		 * esse metodo foi generalizado para qualquer imagem
		 */
		search.setIcon(getImage((int)(Values.SCREEN_HEIGHT*0.04), (int)(Values.SCREEN_HEIGHT*0.04), "img/lupe.png"));
		search.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.01), 0, (int)(Values.SCREEN_WIDTH*0.15)));
		if(Values.SCREEN_WIDTH == 800){
			search.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.01), 0, (int)(Values.SCREEN_WIDTH*0.05)));
		}else if(Values.SCREEN_WIDTH<1920 && Values.SCREEN_WIDTH>1360){
			search.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.01), 0, (int)(Values.SCREEN_WIDTH*0.07)));
		}if(Values.SCREEN_WIDTH<=1280){
			search.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.01), 0, (int)(Values.SCREEN_WIDTH*0.06)));
		}
		search.addMouseListener(new SearchMouseEvent());
		search.setToolTipText("Search for a Product");
		topPanel.add(logo);
		topPanel.add(searchBar);
		topPanel.add(search);
		topPanel.repaint();
		topPanel.revalidate();
		/*
		 * se estiver logado entao true se não false
		 */
		showLoginButtons(Login.isLogged, topPanel);
		mainPanel.add(topPanel);
		mainPanel.add(middlePanel);
		mainPanel.add(contentStructurePanel);
		mainPanel.add(bottomPanel);
		mainPanel.repaint();
		mainPanel.revalidate();
		mainPage.getContentPane().add(mainPanel);
		mainPage.setVisible(true);
		mainPage.pack();
	}
	
	public ImageIcon getImageFrom(int width,int height,String imageUrl){
		
		ImageIcon imageIcon = new ImageIcon(imageUrl);
		Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}

}




