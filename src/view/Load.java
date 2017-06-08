package view;


import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.geom.Ellipse2D;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dao.DatabaseManager;
import view.events.JavaVersionMouseEvent;

public class Load extends View{
	private JDialog mainFrame;
	private double javaVersion;
	private boolean error = false;
	public JDialog getMain(){
		return mainFrame;
	}
	public Load(){
		javaVersion = new Double(System.getProperty("java.version").substring(0,3));
		if(!isMinimumJavaVersion(javaVersion)){
			showErrorPane("Java Version");
			System.out.println("error");
			
		}else{
			display();
		}
		try {
			new DatabaseManager().openConnection();
		} catch (Exception e) {
			error = true;
			
			
		}
	}
	public boolean isMinimumJavaVersion(double javaVersion){
		return (javaVersion>=1.8);
	}
	private void showErrorPane(String title){
		
		JPanel content = new JPanel(new FlowLayout(FlowLayout.LEFT));
		content.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.2),(int)(Values.SCREEN_HEIGHT*0.2)));
		JLabel javaLogo = new JLabel(getImage((int)(Values.SCREEN_WIDTH*0.025), (int)(Values.SCREEN_WIDTH*0.03),"img/java-logo.png"));
		
		javaLogo.setBorder(BorderFactory.createEmptyBorder(0, (int)(Values.SCREEN_WIDTH*0.01), (int)(Values.SCREEN_WIDTH*0.1), (int)(Values.SCREEN_WIDTH*0.01)));
		content.add(javaLogo);
		JLabel errorMessage = new JLabel();
		errorMessage.setText("<html>"
				+ "<h2>The Java version installed is out of date!</h2><br/>Minimum version required is 1.8.0.<br/>Please download it by clicking on the link below or download it manually."
				+"<br/><a href=https://java.com/en/download/>https://java.com/en/download/</a>"
				+ "</html>");
		errorMessage.setBorder(BorderFactory.createEmptyBorder(-(int)(Values.SCREEN_WIDTH*0.05), (int)(Values.SCREEN_WIDTH*0.01), 0, (int)(Values.SCREEN_WIDTH*0.01)));
		errorMessage.setMaximumSize(new Dimension((int)(Values.SCREEN_WIDTH*0.18),(int)(Values.SCREEN_HEIGHT*0.8)));
		errorMessage.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.18),(int)(Values.SCREEN_HEIGHT*0.3)));
		errorMessage.setCursor(new Cursor(Cursor.HAND_CURSOR));
		errorMessage.addMouseListener(new JavaVersionMouseEvent());
		content.add(errorMessage);
		
		
		JDialog error = new JDialog();
		error.setPreferredSize(new Dimension((int)(Values.SCREEN_WIDTH*0.25),(int)(Values.SCREEN_HEIGHT*0.25)));
		error.setBounds((int)(Values.SCREEN_WIDTH/2-Values.SCREEN_WIDTH*0.25/2),(int)(Values.SCREEN_HEIGHT/2-Values.SCREEN_HEIGHT*0.25/2),(int)(Values.SCREEN_WIDTH*0.2),(int)(Values.SCREEN_HEIGHT*0.2));
		error.setTitle(title);
		error.setVisible(true);
		error.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		error.getContentPane().add(content);
		error.pack();
		
		
	}
	public void init(){
		mainFrame = new JDialog();
		
		mainFrame.setBounds((int)Values.SCREEN_WIDTH/2-(int)(Values.SCREEN_HEIGHT*0.55)/2,(int)Values.SCREEN_HEIGHT/2-(int)(Values.SCREEN_HEIGHT*0.55)/2,(int)(Values.SCREEN_HEIGHT*0.55),(int)(Values.SCREEN_HEIGHT*0.55));
		
		/*
		 * Cria umJPanel para comportar o logo em uma JLabel
		 */
		JPanel logo = new JPanel();
		logo.setDoubleBuffered(true);
		ImageIcon i = getLogo((int)(Values.SCREEN_HEIGHT*0.47), (int)(Values.SCREEN_HEIGHT*0.47));
		
		JLabel image = new JLabel();
		image.setIcon(i);
		image.setDoubleBuffered(true);//faz com que a imagem seja reinderizada por dois processos simultaneos
		logo.add(image);
		mainFrame.add(logo);
		
		/*
		 * deixar o JDialog transparente
		 */
		mainFrame.getRootPane().setOpaque(false);
		/*
		 * o metodo setUndecorated e/´ utilizado para retirar a barra que possui o minimizar e o fechar da janela
		 */
		mainFrame.setUndecorated(true);
		//
		/*
		 * setShape é utilizado para deixar o JDialog redondo
		 */
		mainFrame.setShape(new Ellipse2D.Double(0,0,(int)(Values.SCREEN_HEIGHT*0.475),(int)(Values.SCREEN_HEIGHT*0.475)));
		//
		/*
		 * Uma Thread é utilizada para trabalhos assincronos i.e. para tarefas realizadas em "paralelo"
		 * É recomendável o uso de uma nova Thread para realizar animacoes pois caso algo de errado a Thread principal
		 * responsavel por rodar nosso programa estara intacta
		 * o metodo estatico invokeLater funciona com um objeto Runnable o que nada mais e que uma tarefa a ser executada
		 * assim como a classe Thread o metodo invokeLater e assincrono porem essa e uma Thread preparada para suportar 
		 * mudancas bruscas em componentes visuais e tem prioridade em relacao a Thread
		 */
		
		
		
		
		
		mainFrame.setVisible(true);
		mainFrame.pack();
		/*
		 * O metodo pack e responsavel por fazer com que as configuracoes de tamanho definidas pelo programador
		 * sejam cumpridas
		 */
		
		SwingUtilities.invokeLater(blink());
		
	}
	/*
	 * O metodo blink e utilizado para fazer a janela aumentar e diminuir a opacidade
	 * O tipo Runnable e so uma tarefa a ser executada normalmente atrelada a Threads
	 */
	private Runnable blink(){
		
		return(new Runnable() {
			boolean glow = true;
			@Override
			public void run() {
				int times = 3;
				int opacity = 5;
				float opaqueValue = opacity/10;
				while(times>0){
					if(glow){
						opaqueValue += 0.005;	
					}else{
						opaqueValue -= 0.005;
					}
					mainFrame.setOpacity(opaqueValue);
					if(opaqueValue >= 0.9f){
						glow = false;
						times--;
					}
					if(opaqueValue<= 0.3f){
						glow = true;
					}
					try {
						Thread.sleep(7);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						///e.printStackTrace();
					}
					mainFrame.repaint();
					mainFrame.revalidate();
				}
				mainFrame.setOpacity(1f);
				mainFrame.setVisible(false);
				/*
				 * o metodo dispose tornara a janela inativa para o usuario
				 */
				mainFrame.dispose();
				if(error){
					JOptionPane.showMessageDialog(null, "Database connection could not be stablished!","Database Connection",JOptionPane.WARNING_MESSAGE);
					
					System.out.println("Exception@"+getClass().getSimpleName()+" - Database connection could not be stablished!!");
					System.exit(0);
				}
				Run.mainPage = new MainPage();
				Run.mainPage.display();
				
				
			}
		});
		
		
	}
	
	
}
