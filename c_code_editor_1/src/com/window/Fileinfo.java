package com.window;

import java.awt.Font;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Fileinfo extends File{

	JLabel fileName = new JLabel();
	JTextPane area = new JTextPane();
	File f ;
	
	public Fileinfo(String uri,String name,JTextPane ta) {
		super(uri);
		area= ta;
		fileName.setText(name);
		fileName.setFont(new Font("Britannic Bold", Font.PLAIN, 12));
	}

	
	public JLabel getLabelName() {
		return fileName;
	}
	
	public void setName(String name) {
		
		fileName.setText(name);
		
	}
	
}
