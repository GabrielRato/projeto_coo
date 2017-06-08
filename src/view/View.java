package view;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.javasoft.plaf.synthetica.SyntheticaOrangeMetallicLookAndFeel;

public abstract class View {
	public abstract void init();
	private static JComponent[] components;
	private static JComponent current;
	public void display(){
		/*
		 * o metodo estatico invokeLater funciona com um objeto Runnable o que nada mais e que uma tarefa a ser executada
		 * assim como a classe Thread o metodo invokeLater e assincrono porem essa e uma Thread preparada para suportar 
		 * mudancas bruscas em componentes visuais e tem prioridade em relacao a Thread
		 */
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				init();
				
				//getJFrame().setIconImage( Toolkit.getDefaultToolkit().getImage((getClass().getClassLoader().getResource(Values.LOGO))));
			}
		});
	
}
	public ImageIcon getLogo(int width,int height){
		ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(Values.LOGO));
		Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	public ImageIcon getImage(int width,int height,String imageUrl){
		
		ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(imageUrl));
		Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	public static void setComponents(JComponent ...components){
		View.components = components;
	}
	public static void disableAllComponents(){
		
		for(JComponent c:View.components){
			c.setEnabled(false);
		}
	}
	public void hideComponent(JComponent c){
		current = c;
		c.setEnabled(false);
		c.setVisible(false);
	}
	public static void showComponent(){
		current.setEnabled(true);
		current.setVisible(true);
	}
	public void showComponent(JComponent c){
		c.setEnabled(true);
		c.setVisible(true);
	}
	public static void enableAllComponents(){
		for(JComponent c:View.components){
			c.setEnabled(true);
		}
	}
	public void setLookAndFeel(){
		/*
		 * configura os temas da aplicação
		 */
		try 
	    {
			
//	      UIManager.setLookAndFeel(new SyntheticaAluOxideLookAndFeel());
//	      Login l = new Login();
//	      UIManager.setLookAndFeel(new SyntheticaBlackEyeLookAndFeel());
//	      Login l = new Login();
//	      UIManager.setLookAndFeel(new SyntheticaBlackMoonLookAndFeel());
//	      Login l = new Login();
//	      UIManager.setLookAndFeel(new SyntheticaBlackStarLookAndFeel());
//	      Login l = new Login();
//	      UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
//	      Login l = new Login();
//	      UIManager.setLookAndFeel(new SyntheticaClassyLookAndFeel());
//	      Login l = new Login();
	      UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
//	      UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());
//	      Login l = new Login();
//	      
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    }
	}
}
