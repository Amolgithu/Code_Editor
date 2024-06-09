package com.window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

public class TabCloseClass implements ActionListener{

	componentset components;
	List<JButton> buttons=new  ArrayList<JButton>();
	
	public TabCloseClass(componentset c) {
		components = c;
	}
	
	public void addFkingButton(JButton b) {
		buttons.add(b);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int index = buttons.indexOf(e.getSource());
		components.tp.removeTabAt(index);
		components.tab.remove(index);
		buttons.remove(e.getSource());
	}

}
