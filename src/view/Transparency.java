/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author ETESP
 */
public class Transparency{
public void setTranparent(JDialog f){
f.setUndecorated(true);
f.setBackground(new Color(0, 255, 0, 0));
f.setContentPane(new ContentPane());
f.getContentPane().setBackground(Color.BLACK);
}
public class ContentPane extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContentPane() {

        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        g2d.setColor(getBackground());
        g2d.fill(getBounds());
        g2d.dispose();

	    }


	}
}