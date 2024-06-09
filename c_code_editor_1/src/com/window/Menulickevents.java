package com.window;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

public class Menulickevents implements ActionListener {

    private componentset components;
    private window ww;
    public String filename, filedir, contentString;

    public Menulickevents(componentset cs, window w) {

        components = cs;
        ww = w;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object m = e.getSource();
        if (m == components.exit) {
            System.exit(0);
        } else if (m == components.New) {
            components.addtab(null,null);
        } else if (m == components.open) {
            System.out.println("in open");
            readfile();
        } else if (m == components.save) {
//        	System.out.println("save triggered");
            savefile();
        }
        else if (m == components.save_as) {
//        	System.out.println("save triggered");
            saveAs();
        }
        
        
        else if (m == components.changeStyle) {
			JDialog dialog = new JDialog(ww,"Font Style");
			dialog.setSize(500,500);
			
			
			
			dialog.setVisible(true);
		}
    }

    private void savefile() {
        
    	int selectedIndex = components.tp.getSelectedIndex();
    	
    	String selected_fname = components.tp.getTitleAt(selectedIndex);
    	
//    	System.out.println(selected_fname);
    	if(selected_fname.equals("Untitled.txt")) {
    		saveAs();
    	}
    	else {
    		
    		writecontenttofile((components.tab.get(components.getselectedindex())));
    		
    	}
    }
    private void writecontenttofile(File f) {
    	
    	String contentString = components.textArea[components.tp.getSelectedIndex()].getText();

		
		try {
			Files.writeString(Path.of(f.getPath()), contentString);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
    
    private void saveAs() {	
    	
    	components.fileChooser.setSelectedFile(components.tab.get(components.getselectedindex()));
    	int option = components.fileChooser.showSaveDialog(ww); 
    	
    	
    	
    	if(option == JFileChooser.APPROVE_OPTION) {
    		
    		File file = components.fileChooser.getSelectedFile();
    		
    		writecontenttofile(file);
    		
    		try {
				writeingcomtentfromfile(file.getParent()+"/"+file.getName());
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    	}
    	
    }

    private void readfile()  {

        int option = components.fileChooser.showOpenDialog(ww);
       
        if(option == JFileChooser.APPROVE_OPTION) {
        	
        	File file = components.fileChooser.getSelectedFile();
        	
        	filedir = file.getParent();
        	filename = file.getName();
        	
        	contentString = "";
        	
        	try {
        		components.addtab(filename, filedir);
        		writeingcomtentfromfile(filedir+"/"+filename);
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        }
        

    }

    public void writeingcomtentfromfile(String source) throws IOException {
          
        int i;
         InputStream is = new FileInputStream(source);
        System.out.println("available bytes " + is.available());

        while ((i = is.read()) > 0) {
            contentString = contentString + (char) i;
        }

        File f = new File(source);
        components.textArea[components.tp.getSelectedIndex()].append(contentString);
        
    }

}
