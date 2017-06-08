package view.events;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;


import javax.swing.JCheckBox;

import view.Login;

public class SavedStateEvent implements ItemListener{

	@Override
	public void itemStateChanged(ItemEvent e) {
		Login.saveState = ((JCheckBox)e.getSource()).isSelected();
		
	}

	
		
		
	
		

}
