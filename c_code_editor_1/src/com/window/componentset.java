package com.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class componentset {
	public void appendText(JTextPane textPane, String text) {
	    StyledDocument doc = textPane.getStyledDocument();
	    try {
	        doc.insertString(doc.getLength(), text, null); // null = no style
	    } catch (BadLocationException e) {
	        e.printStackTrace();
	    }
	}

	
	//file Menu items.
	public JMenuItem New,open,save,save_as,exit;
	
	//edit menu items.
	public JMenuItem changeStyle,runitem;
	
	private static final String[] KEYWORDS = {
	        "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
	        "class", "const", "continue", "default", "do", "double", "else", "enum",
	        "extends", "final", "finally", "float", "for", "goto", "if", "implements",
	        "import", "instanceof", "int", "interface", "long", "native", "new",
	        "package", "private", "protected", "public", "return", "short", "static",
	        "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
	        "transient", "try", "void", "volatile", "while"
	    };
	
	private static final StyleContext STYLE_CONTEXT = StyleContext.getDefaultStyleContext();
    private static final AttributeSet NORMAL_STYLE = STYLE_CONTEXT.addAttribute(STYLE_CONTEXT.getEmptySet(), StyleConstants.Foreground, Color.WHITE);
    private static final AttributeSet KEYWORD_STYLE = STYLE_CONTEXT.addAttribute(STYLE_CONTEXT.getEmptySet(), StyleConstants.Foreground, Color.BLUE);
    private static final AttributeSet STRING_STYLE = STYLE_CONTEXT.addAttribute(STYLE_CONTEXT.getEmptySet(), StyleConstants.Foreground, new Color(165, 42, 42)); // Brown
    private static final AttributeSet DARK_TEXT_STYLE = STYLE_CONTEXT.addAttribute(STYLE_CONTEXT.getEmptySet(), StyleConstants.Foreground, Color.WHITE);

    
    
    private static final JPopupMenu suggestionPopup = new JPopupMenu();
    private static final DefaultListModel<String> suggestionModel = new DefaultListModel<>();
    private static final JList<String> suggestionList = new JList<>(suggestionModel);

	
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
			"Microsoft JhengHei UI Light","Microsoft New Tai  Lue","Microsoft PhagsPa",
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
		
		runitem = new JMenuItem("Run");

		New.addActionListener(mce);
		open.addActionListener(mce);
		save.addActionListener(mce);
		save_as.addActionListener(mce);
		exit.addActionListener(mce);
		
		changeStyle.addActionListener(mce);
		
		runitem.addActionListener(mce);
		

		File.add(New);
		File.add(open);
		File.add(save);
		File.add(save_as);
		File.addSeparator();
		File.add(exit);

		Edit.add(changeStyle);
		
		menuBar.add(File);
		menuBar.add(Edit);
		menuBar.add(runitem);
		// panelwithTA.add(textArea);

		addtab(null,null);
		
		suggestionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        suggestionPopup.setFocusable(false);
        suggestionPopup.add(new JScrollPane(suggestionList));

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

		JTextPane temp = creaTextPane();
		
		
		tp.addTab(file, temp);

		tab.add(new Fileinfo(dir+"/"+file,file,temp));
		tp.setSelectedIndex(tp.getTabCount()-1);
		
		tp.setTabComponentAt(getselectedindex(),getTabInfoPanel(tab.get(getselectedindex()).getLabelName(),getselectedindex())) ;
		
	}
	
	private static final AttributeSet FUNCTION_STYLE = STYLE_CONTEXT.addAttribute(STYLE_CONTEXT.getEmptySet(), StyleConstants.Foreground, new Color(0, 128, 0)); // Green
	private static final AttributeSet VARIABLE_STYLE = STYLE_CONTEXT.addAttribute(STYLE_CONTEXT.getEmptySet(), StyleConstants.Foreground, new Color(128, 0, 128)); // Purple

	public static void highlight(JTextPane textPane) {
		StyledDocument doc = textPane.getStyledDocument();
	    String text = textPane.getText();
	    text = text.replace("\n", "");
	    System.out.println("Highlighting. Text length: " + text.length() + ", Document length: " + doc.getLength());

	    // Clear all styles
	    doc.setCharacterAttributes(0, text.length(), NORMAL_STYLE, true);

	    // Highlight keywords
	    for (String keyword : KEYWORDS) {
	        Pattern pattern = Pattern.compile("\\b" + Pattern.quote(keyword) + "\\b");
	        Matcher matcher = pattern.matcher(text);
	        while (matcher.find()) {
	            System.out.println("Keyword '" + keyword + "' found at: " + matcher.start() + "-" + matcher.end());
	            doc.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), KEYWORD_STYLE, false);
	        }
	    }

	    Pattern stringPattern = Pattern.compile("\"(\\\\.|[^\"\\\\])*\"");
	    Matcher stringMatcher = stringPattern.matcher(text);
	    while (stringMatcher.find()) {
	        System.out.println("String found at: " + stringMatcher.start() + "-" + stringMatcher.end());
	        doc.setCharacterAttributes(stringMatcher.start(), stringMatcher.end() - stringMatcher.start(), STRING_STYLE, false);
	    }

	    // Highlight char literals (single quotes)
	    Pattern charPattern = Pattern.compile("'(\\\\.|[^'\\\\])'");
	    Matcher charMatcher = charPattern.matcher(text);
	    while (charMatcher.find()) {
	        doc.setCharacterAttributes(charMatcher.start(), charMatcher.end() - charMatcher.start(), STRING_STYLE, false);
	    }
	    
	    // Highlight function names: match like `void myFunction(` or `int myFunc(`
	    Pattern funcPattern = Pattern.compile("\\b[a-zA-Z_$][a-zA-Z\\d_$]*\\s+(\\w+)\\s*\\(");
	    Matcher funcMatcher = funcPattern.matcher(text);
	    while (funcMatcher.find()) {
	        String functionName = funcMatcher.group(1);
	        int start = funcMatcher.start(1);
	        int length = functionName.length();
	        doc.setCharacterAttributes(start, length, FUNCTION_STYLE, false);
	    }

	    // Highlight variable declarations like `int x =`, `String name =`
	    Pattern varPattern = Pattern.compile("\\b(?:int|double|float|String|char|boolean|long|short|byte)\\s+(\\w+)\\s*(=|;)");
	    Matcher varMatcher = varPattern.matcher(text);
	    while (varMatcher.find()) {
	        int start = varMatcher.start(1);
	        int length = varMatcher.group(1).length();
	        doc.setCharacterAttributes(start, length, VARIABLE_STYLE, false);
	    }
	}

	public JPanel getTabInfoPanel(JLabel label,int index) {
		

		
		
		JPanel panel= new JPanel();
		panel.setPreferredSize(new Dimension(100,25));
		
	    JButton b = new JButton("x");
	    b.addActionListener(tabCloseClassEvent);
	  
	    b.setPreferredSize(new Dimension(20, 17));
	    b.setHorizontalAlignment(SwingConstants.RIGHT);
		b.setOpaque(false);
		b.setBorder(null);
		b.setContentAreaFilled(false);
		
		
		
		tabCloseClassEvent.addFkingButton(b);
		
		panel.setBackground(new Color(200, 221, 242));
		panel.setLayout(new BorderLayout());
		panel.setOpaque(false);
		
		panel.add(label,BorderLayout.CENTER);
		panel.add(b,BorderLayout.EAST);
		
		return panel;
	}
	
	public JTextPane creaTextPane() {
		JTextPane temp = new JTextPane() {
		    @Override
		    public boolean getScrollableTracksViewportWidth() {
		        return getUI().getPreferredSize(this).width <= getParent().getSize().width;
		    }
		};
		
		temp.setBackground(new Color(30, 30, 30)); // Dark background
		temp.setForeground(Color.WHITE);
		temp.setCaretColor(Color.WHITE);

		temp.setFont(new Font("Arial", Font.PLAIN, 20));
		temp.enableInputMethods(true);
		
		 temp.addKeyListener(new KeyAdapter() {
	            public void keyPressed(KeyEvent e) {
	                if (suggestionPopup.isVisible()) {
	                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
	                        suggestionList.setSelectedIndex((suggestionList.getSelectedIndex() + 1) % suggestionModel.size());
	                        e.consume();
	                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
	                        int newIndex = suggestionList.getSelectedIndex() - 1;
	                        if (newIndex < 0) newIndex = suggestionModel.size() - 1;
	                        suggestionList.setSelectedIndex(newIndex);
	                        e.consume();
	                    } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                        e.consume();
	                        insertSuggestion(temp);
	                    } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
	                        suggestionPopup.setVisible(false);
	                    }
	                }
	            }
	        });
		
		temp.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    highlight(temp);
                    showSuggestions(temp);
                });
            }

            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    highlight(temp);
                    showSuggestions(temp);
                });
            }

            public void changedUpdate(javax.swing.event.DocumentEvent e) {}
        });
		
		
		temp.addKeyListener(new KeyAdapter() {
			
			public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                int pos = temp.getCaretPosition();
        
                // Auto-closing pairs
                Map<Character, Character> pairs = Map.of(
                    '(', ')',
                    '{', '}',
                    '[', ']',
                    '"', '"',
                    '\'', '\''
                );
        
                if (pairs.containsKey(ch)) {
                    char closing = pairs.get(ch);
                    e.consume(); // prevent default input
        
                    try {
                        // Insert both open and close characters
                        temp.getDocument().insertString(pos, "" + ch + closing, null);
                        // Move caret between them
                        temp.setCaretPosition(pos + 1);
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }
            }
			
		});
		return temp;
	}

	public Integer getselectedindex() {
		return (tp.getSelectedIndex());
	}
	
	public componentset returninstance(){
		return this;
	}
	
	private static void showSuggestions(JTextPane textPane) {
	    try {
	        int caretPos = textPane.getCaretPosition();
	        int start = caretPos - 1;
	        String text = textPane.getText(0, caretPos);

	        while (start >= 0 && Character.isJavaIdentifierPart(text.charAt(start))) {
	            start--;
	        }
	        start++;

	        String typed = text.substring(start).toLowerCase();

	        if (typed.isEmpty()) {
	            suggestionPopup.setVisible(false);
	            return;
	        }

	        suggestionModel.clear();
	        for (String keyword : KEYWORDS) {
	            if (keyword.startsWith(typed)) {
	                suggestionModel.addElement(keyword);
	            }
	        }

	        if (suggestionModel.isEmpty()) {
	            suggestionPopup.setVisible(false);
	            return;
	        }

	        suggestionList.setSelectedIndex(0);
	        java.awt.geom.Rectangle2D caretBounds2D = null;
	        try {
	            caretBounds2D = textPane.modelToView2D(caretPos);
	        } catch (BadLocationException ex) {
	            ex.printStackTrace();
	            return; // Handle the exception appropriately
	        }
	        Rectangle caretBounds = caretBounds2D.getBounds();
	        suggestionPopup.setPreferredSize(new Dimension(150, 100));
	        suggestionPopup.show(textPane, caretBounds.x, caretBounds.y + caretBounds.height);
	        textPane.requestFocusInWindow();

	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
	}

    private static void insertSuggestion(JTextPane textPane) {
        if (suggestionList.getSelectedValue() == null) return;

        String selected = suggestionList.getSelectedValue();
        int caretPos = textPane.getCaretPosition();
        String text = textPane.getText();

        int start = caretPos - 1;
        while (start >= 0 && Character.isJavaIdentifierPart(text.charAt(start))) {
            start--;
        }
        start++;

        try {
            textPane.getDocument().remove(start, caretPos - start);
            textPane.getDocument().insertString(start, selected, null);
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }

        suggestionPopup.setVisible(false);
    }
	
}
