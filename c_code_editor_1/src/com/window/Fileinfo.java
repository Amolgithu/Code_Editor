package com.window;

import java.awt.Font;
import java.io.File;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Fileinfo extends File{

	JLabel fileName = new JLabel();
	JTextArea area = new JTextArea();
	
	public Fileinfo(String uri,String name,JTextArea ta) {
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
