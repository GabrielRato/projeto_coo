package view.events;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public 	class SettingsMouseEvent implements MouseListener{
	private JScrollPane categories;
	private static final int LEFT_CLICK =1;
	public SettingsMouseEvent(JScrollPane categories) {
		this.categories = categories;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getButton()==LEFT_CLICK){
			
			if(categories.isVisible()){
				
				hide().run();
			}else{
				show().run();
			}
			
			
		}
		
	}
	public ImageIcon getImage(int width,int height,String imageUrl){
		
		ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(imageUrl));
		Image image = imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(image);
	}
	public Runnable show(){
		
		return new Runnable(){
			@Override
			public void run(){
				
				float pos =-categories.getPreferredSize().width;
				while(-categories.getPreferredSize().width+pos<0){
						
					try {
						
						categories.repaint();
						categories.setBounds((int)(-categories.getPreferredSize().width+pos),categories.getY(),categories.getPreferredSize().width,categories.getPreferredSize().height);
						pos+=0.0025;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				categories.setBounds(0,categories.getY(),categories.getPreferredSize().width,categories.getPreferredSize().height);
				categories.setEnabled(!categories.isVisible());
				clickSettings(!categories.isVisible());
				
			}
		};
	}
	public Runnable hide(){
		return new Runnable(){
			@Override
			public void run(){
				
				float pos = 0;
				int size = categories.getPreferredSize().width;
				while(pos>-size){
					
					try {
						categories.setBounds((int)pos,categories.getY(),categories.getPreferredSize().width,categories.getPreferredSize().height);
						categories.repaint();
						
						pos-=0.0025;
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				categories.setBounds(0,categories.getY(),categories.getPreferredSize().width,categories.getPreferredSize().height);
				categories.setEnabled(!categories.isVisible());
				clickSettings(!categories.isVisible());
				
			}
		};
	}
	public void clickSettings(boolean show){
		categories.setVisible(show);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		JLabel set = ((JLabel)e.getSource());
		set.setIcon(getImage(set.getIcon().getIconWidth(), set.getIcon().getIconHeight(), "img/settingsHighlight.png"));
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JLabel set = ((JLabel)e.getSource());
		set.setIcon(getImage(set.getIcon().getIconWidth(), set.getIcon().getIconHeight(), "img/settings.png"));
		
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
