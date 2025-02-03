package com.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class componentset {
	
	//file Menu items.
	public JMenuItem New,open,save,save_as,exit;
	
	//edit menu items.
	public JMenuItem changeStyle;
	
	String fontNameString[] = {
			"Agency FB","Algerian","Arial","Arial Black",
			"Arial Narrow","Arial Rounded MT Bold","Bahnschrift",
			"Baskerville Old Face","Bauhaus 93","Bell MT",
			"Berlin Sans FB","Berlin Sans FB Demi","Bernard MT Condensed",
			"Blackadder ITC","Bodoni MT","Bodoni MT Black",
			"Bodoni MT Condensed","Bodoni MT Poster Compressed",
			"Book Antiqua","Bookman Old Style","Bookshelf Symbol 7",
			"Bradley Hand ITC","Britannic Bold","Broadway",
			"Brush Script MT","Calibri","Calibri Light","Californian FB",
			"Calisto MT","Cambria","Cambria Math","Candara","Candara Light",
			"Cascadia Code","Cascadia Mono","Castellar","Centaur",
			"Century","Century Gothic","Century Schoolbook","Chiller",
			"Colonna MT","Comic Sans MS","Consolas","Constantia","Cooper Black",
			"Copperplate Gothic Bold","Copperplate Gothic Light","Corbel",
			"Corbel Light","Courier New","Curlz MT","Dialog","DialogInput",
			"Dubai","Dubai Light","Dubai Medium","Ebrima","Edwardian Script ITC",
			"Elephant","Engravers MT","Eras Bold ITC","Eras Demi ITC","Eras Light ITC",
			"Eras Medium ITC","Felix Titling","Footlight MT Light","Forte","Franklin Gothic Book",
			"Franklin Gothic Demi","Franklin Gothic Demi Cond","Franklin Gothic Heavy",
			"Franklin Gothic Medium","Franklin Gothic Medium Cond","Freestyle Script",
			"French Script MT","Gabriola","Gadugi","Garamond","Georgia","Gigi",
			"Gill Sans MT","Gill Sans MT Condensed","Gill Sans MT Ext Condensed Bold",
			"Gill Sans Ultra Bold","Gill Sans Ultra Bold Condensed",
			"Gloucester MT Extra Condensed","Goudy Old Style","Goudy Stout","Haettenschweiler",
			"Harlow Solid Italic","Harrington","High Tower Text","HoloLens MDL2 Assets",
			"HP Simplified","HP Simplified Hans","HP Simplified Hans Light","HP Simplified Jpan",
			"HP Simplified Jpan Light","HP Simplified Light","Impact","Imprint MT Shadow",
			"Informal Roman","Ink Free","Javanese Text","Jokerman","Juice ITC","Kristen ITC",
			"Kunstler Script","Leelawadee","Leelawadee UI","Leelawadee UI Semilight","Lucida Bright",
			"Lucida Calligraphy","Lucida Console","Lucida Fax","Lucida Handwriting","Lucida Sans",
			"Lucida Sans Typewriter","Lucida Sans Unicode","Magneto","Maiandra GD","Malgun Gothic",
			"Malgun Gothic Semilight","Marlett","Matura MT Script Capitals","Microsoft Himalaya",
			"Microsoft JhengHei","Microsoft JhengHei Light","Microsoft JhengHei UI",
			"Microsoft JhengHei UI Light","Microsoft New Tai Lue","Microsoft PhagsPa",
			"Microsoft Sans Serif","Microsoft Tai Le","Microsoft Uighur","Microsoft YaHei",
			"Microsoft YaHei Light","Microsoft YaHei UI","Microsoft YaHei UI Light","Microsoft Yi Baiti",
			"MingLiU-ExtB","MingLiU_HKSCS-ExtB","Mistral","Modern No. 20","Mongolian Baiti",
			"Monospaced","Monotype Corsiva","MS Gothic","MS Outlook","MS PGothic","MS Reference Sans Serif",
			"MS Reference Specialty","MS UI Gothic","MT Extra","MV Boli","Myanmar Text","Niagara Engraved",
			"Niagara Solid","Nirmala UI","Nirmala UI Semilight","NSimSun","OCR A Extended",
			"Old English Text MT","Onyx","Palace Script MT","Palatino Linotype","Papyrus","Parchment",
			"Perpetua","Perpetua Titling MT","Playbill","PMingLiU-ExtB","Poor Richard","Pristina",
			"Rage Italic","Ravie","Rockwell","Rockwell Condensed","Rockwell Extra Bold",
			"Sans Serif Collection","SansSerif","Script MT Bold","Segoe Fluent Icons","Segoe MDL2 Assets",
			"Segoe Print","Segoe Script","Segoe UI","Segoe UI Black","Segoe UI Emoji","Segoe UI Historic",
			"Segoe UI Light","Segoe UI Semibold","Segoe UI Semilight","Segoe UI Symbol","Segoe UI Variable",
			"Serif","Showcard Gothic","SimSun","SimSun-ExtB","Sitka Text","Snap ITC","Stencil","Sylfaen",
			"Symbol","Tahoma","Tempus Sans ITC","Times New Roman","Trebuchet MS","Tw Cen MT",
			"Tw Cen MT Condensed","Tw Cen MT Condensed Extra Bold","Verdana","Viner Hand ITC","Vivaldi",
			"Vladimir Script","Webdings","Wide Latin","Wingdings","Wingdings 2","Wingdings 3",
			"Yu Gothic","Yu Gothic Light","Yu Gothic Medium","Yu Gothic UI","Yu Gothic UI Light",
			"Yu Gothic UI Semibold","Yu Gothic UI Semilight"	
	};
	Integer fontsizes[] = {8,9,10,11,12,14,16,18,20,22,24,26,28,30,32,34,36};
	public JComboBox<String> comboBox = new JComboBox<String>(fontNameString);
	public JComboBox<Integer> FontSizecombobox = new JComboBox<Integer>(fontsizes);
	
	public Menulickevents mce;
	public JFileChooser fileChooser = new JFileChooser();
	public JTabbedPane tp;
	private TabCloseClass tabCloseClassEvent;
	public List<Fileinfo> tab = new ArrayList<Fileinfo>();
	private window w;
	
	public componentset(window w) {
		comboBox.setSize(new Dimension(400, 50));

		this.w=w;
		tabCloseClassEvent = new TabCloseClass(this);

		mce = new Menulickevents(this,w);

		JMenuBar menuBar = new JMenuBar();
		tp= new JTabbedPane();
		// JPanel panelwithTA = new JPanel();


		JMenu File = new JMenu("File");
		JMenu Edit = new JMenu("Edit");
		
		
		New = new JMenuItem("New");
		open= new JMenuItem("Open");
		save = new JMenuItem("Save");
		save_as = new JMenuItem("Save as");
		exit = new JMenuItem("Exit");
		
		changeStyle = new JMenuItem("Change Font Style");

		New.addActionListener(mce);
		open.addActionListener(mce);
		save.addActionListener(mce);
		save_as.addActionListener(mce);
		exit.addActionListener(mce);
		
		changeStyle.addActionListener(mce);

		File.add(New);
		File.add(open);
		File.add(save);
		File.add(save_as);
		File.addSeparator();
		File.add(exit);

		Edit.add(changeStyle);
		
		menuBar.add(File);
		menuBar.add(Edit);

		// panelwithTA.add(textArea);

		addtab(null,null);

		w.setJMenuBar(menuBar);
		w.add(tp,BorderLayout.CENTER);
		w.add(new JPanel(),BorderLayout.SOUTH);
		w.add(new JPanel(),BorderLayout.NORTH);
		w.add(new JPanel(),BorderLayout.EAST);
		w.add(new JPanel(),BorderLayout.WEST);
		
	}

	public void addtab(String file,String dir) {
		file = file != null ? file : "Untitled.txt"; 
		dir = dir != null ? dir : "C:/Users/Amol/Desktop"; 
		int areaindex = tp.getTabCount();

		
		JTextArea  temp = new JTextArea();
		temp.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		temp.setLineWrap(true);
		temp.enableInputMethods(true);
		
		tp.addTab(file, temp);

		tab.add(new Fileinfo(dir+"/"+file,file,temp));
		tp.setSelectedIndex(tp.getTabCount()-1);
		
		tp.setTabComponentAt(getselectedindex(),getTabInfoPanel(tab.get(getselectedindex()).getLabelName(),getselectedindex())) ;
		
	}
	
	public JPanel getTabInfoPanel(JLabel label,int index) {
		
		JPanel panel= new JPanel();
		panel.setPreferredSize(new Dimension(100,25));
		
	    JButton b = new JButton("x");
	    b.addActionListener(tabCloseClassEvent);
	    b.setPreferredSize(new Dimension(30, 17));
	    b.setHorizontalAlignment(SwingConstants.RIGHT);
		b.setOpaque(false);
		b.setBorder(null);
		b.setContentAreaFilled(false);
		
		
		
		tabCloseClassEvent.addFkingButton(b);
		
		panel.setBackground(new Color(200, 221, 242));
		panel.setLayout(new FlowLayout());
		panel.setOpaque(false);
		
		panel.add(label);
		panel.add(b);
		
		return panel;
	}

	public Integer getselectedindex() {
		return (tp.getSelectedIndex());
	}
	
	public componentset returninstance(){
		return this;
	}
	
}
