package view;

import javax.swing.SwingUtilities;


public class Run {
	/*
	 * CLasse que o programa chamará para inicializar
	 */
	public static MainPage mainPage;
	public static void main(String[] args){
		
			
			
			SwingUtilities.invokeLater(new Runnable() {
				
				@Override
				public void run() {
					new Load();
					
				}
			});
		
				
				
				
		
	}
}
