package com.window;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class window extends JFrame{
	
	public componentset component;


	public window() {
		this.setTitle("C code editor!!");
		this.setSize(500,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		component=new componentset(this);

		this.setVisible(true);
	}
	public static void main(String args[]) {
		new window();
	}
	
}
