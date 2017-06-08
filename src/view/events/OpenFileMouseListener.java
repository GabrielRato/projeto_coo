package view.events;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.MainPage;
import view.RegisterAd;
import view.SignUp;
import view.Values;

public class OpenFileMouseListener implements ActionListener{
	private JFileChooser chooser = new JFileChooser();
	private JPanel mainPanel = new JPanel();
	private static String filePath;
	private static JPanel currentPane ;
	private String src;
	
	public static String getFilePath(){
		return filePath;
	}

	
	public static void setTaskPanel(JPanel p){
		currentPane = p;
	}
	public OpenFileMouseListener(JPanel current,String source){
		FileFilter imageFilter = new FileNameExtensionFilter(
			    "Image files", ImageIO.getReaderFileSuffixes());
		chooser.setFileFilter(imageFilter);
		mainPanel = current;
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		src = source;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		chooser.setDialogTitle("Choose Image...");
		
		int action =chooser.showOpenDialog(mainPanel);  
		if(action == JFileChooser.APPROVE_OPTION){
			filePath = chooser.getSelectedFile().getPath();
			String type = filePath.substring(filePath.lastIndexOf(".")+1,filePath.length());
			if(type.equals("jpg")||type.equals("jpeg")||type.equals("png")||type.equals("bmp")||type.equals("gif")||type.equals("wbmp")){
				if(filePath != null){
					ImageIcon imageIcon = new ImageIcon(filePath);
					Image image = imageIcon.getImage().getScaledInstance((int)(Values.SCREEN_HEIGHT*0.08), (int)(Values.SCREEN_HEIGHT*0.08), Image.SCALE_SMOOTH);
					
					/*
					 * O bloco de baixo funciona como a ação do botao SignUp que cria uma nova tela por cima a fim de atualizar a imagem 
					 * escolhida pelo usuario
					 */
					if(src.equals("SIGN_UP")){
						SignUp.setPath(filePath);
						SignUp.setImageIcon( new ImageIcon(image));
						new SignUp();
					}else {
						RegisterAd.setPath(filePath);
						RegisterAd.setImageIcon( new ImageIcon(image));
						new RegisterAd().init();
					}
					MainPage.addContentPanel(currentPane);
				
				}else{
					SignUp.setPath(null);
					SignUp.setImageIcon(null);	
				}
			}else{
				System.out.println("error@OpenFileClickListener-actionPerformed: file type mismatch ");
			}
			
			
			
		}
	}
	
}
