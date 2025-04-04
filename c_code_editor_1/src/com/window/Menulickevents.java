package com.window;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SecureCacheResponse;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

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
            savefile();
        }
        else if (m == components.save_as) {
            saveAs();
        }
        else if(m == components.runitem) {
        	RunCodeFile();
        }
        else if (m == components.changeStyle) {
			JDialog dialog = new JDialog(ww,"Font Style");
            dialog.setLayout(new FlowLayout());
			dialog.setSize(500,500);
            JButton applyButton = new JButton("Apply");
            applyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    components.tab.get(components.tp.getSelectedIndex()).area.setFont(new Font(components.comboBox.getSelectedItem().toString(), Font.PLAIN, (int)components.FontSizecombobox.getSelectedItem()));
                }
            });
            
            dialog.add(components.FontSizecombobox);
            dialog.add(components.comboBox);
			dialog.add(applyButton);
            dialog.setVisible(true);
		}
    }

    private void RunCodeFile() {
    	
    	int selectedIndex = components.tp.getSelectedIndex();
    	File currentFile = components.tab.get(selectedIndex);
    	System.out.println(currentFile.getName());// Assuming getFile() exists
    	 Thread thread = new Thread(new Runnable() {
    	        @Override
    	        public void run() {
    	            if (selectedIndex == -1) {
    	                JOptionPane.showMessageDialog(ww, "No file is open to run.");
    	                return;
    	            }


    	            if (currentFile == null) {
    	                JOptionPane.showMessageDialog(ww, "Please save the file before running.");
    	                return;
    	            }

    	            savefile(); // Save latest edits

    	            StringBuilder outputBuilder = new StringBuilder();
    	            String fileName = currentFile.getName();
    	            String dir = currentFile.getParent();

    	            try {
    	                if (fileName.endsWith(".java")) {
    	                    String className = fileName.substring(0, fileName.lastIndexOf("."));

    	                    // Compile Java
    	                    ProcessBuilder compilePB = new ProcessBuilder("javac", fileName);
    	                    compilePB.directory(new File(dir));
    	                    Process compileProcess = compilePB.start();

    	                    BufferedReader compileErr = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
    	                    String line;
    	                    boolean hasError = false;
    	                    while ((line = compileErr.readLine()) != null) {
    	                        outputBuilder.append("[Compilation (Java) Error] ").append(line).append("\n");
    	                        hasError = true;
    	                    }
    	                    compileProcess.waitFor();

    	                    if (hasError) {
    	                        showTerminalOutput(outputBuilder.toString());
    	                        return;
    	                    }

    	                    // Run Java
    	                    ProcessBuilder runPB = new ProcessBuilder("java", "-cp", dir, className);
    	                    runPB.directory(new File(dir));
    	                    Process runProcess = runPB.start();

    	                    BufferedReader runOut = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
    	                    BufferedReader runErr = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));

    	                    outputBuilder.append("--- Execution (Java) Output ---\n");
    	                    while ((line = runOut.readLine()) != null) {
    	                        outputBuilder.append(line).append("\n");
    	                    }
    	                    while ((line = runErr.readLine()) != null) {
    	                        outputBuilder.append("[Runtime (Java) Error] ").append(line).append("\n");
    	                    }

    	                    runProcess.waitFor();

    	                    showTerminalOutput(outputBuilder.toString());

    	                } else if (fileName.endsWith(".cpp")) {
    	                    String executableName = fileName.substring(0, fileName.lastIndexOf("."));
    	                    String executablePath = dir + File.separator + executableName;

    	                    // Compile C++ (assuming g++ is in PATH)
    	                    ProcessBuilder compilePB = new ProcessBuilder("g++", fileName, "-o", executablePath);
    	                    compilePB.directory(new File(dir));
    	                    Process compileProcess = compilePB.start();

    	                    BufferedReader compileErr = new BufferedReader(new InputStreamReader(compileProcess.getErrorStream()));
    	                    String line;
    	                    boolean hasError = false;
    	                    while ((line = compileErr.readLine()) != null) {
    	                        outputBuilder.append("[Compilation (C++) Error] ").append(line).append("\n");
    	                        hasError = true;
    	                    }
    	                    compileProcess.waitFor();

    	                    if (hasError) {
    	                        showTerminalOutput(outputBuilder.toString());
    	                        return;
    	                    }

    	                    // Run C++
    	                    ProcessBuilder runPB = new ProcessBuilder(executablePath);
    	                    runPB.directory(new File(dir));
    	                    Process runProcess = runPB.start();

    	                    BufferedReader runOut = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
    	                    BufferedReader runErr = new BufferedReader(new InputStreamReader(runProcess.getErrorStream()));

    	                    outputBuilder.append("--- Execution (C++) Output ---\n");
    	                    while ((line = runOut.readLine()) != null) {
    	                        outputBuilder.append(line).append("\n");
    	                    }
    	                    while ((line = runErr.readLine()) != null) {
    	                        outputBuilder.append("[Runtime (C++) Error] ").append(line).append("\n");
    	                    }

    	                    runProcess.waitFor();

    	                    showTerminalOutput(outputBuilder.toString());

    	                } else {
    	                    showTerminalOutput("Error: Unsupported file type. Only .java and .cpp files are supported.\n");
    	                }

    	            } catch (Exception e) {
    	                showTerminalOutput("Error: " + e.getMessage() + "\n");
    	            }
    	        }
    	    });
    	    thread.start();
    	
		
	}
    private void showTerminalOutput(String output) {
        JFrame terminalFrame = new JFrame("Terminal Output");
        terminalFrame.setSize(600, 400);
        terminalFrame.setLocationRelativeTo(ww);
    
        JTextArea terminalText = new JTextArea();
        terminalText.setEditable(false);
        terminalText.setBackground(Color.BLACK);
        terminalText.setForeground(Color.GREEN);
        terminalText.setFont(new Font("Consolas", Font.PLAIN, 14));
        terminalText.setText(output);
    
        JScrollPane scroll = new JScrollPane(terminalText);
        terminalFrame.add(scroll);
    
        terminalFrame.setVisible(true);
    }

	private void savefile() {
        
    	int selectedIndex = components.tp.getSelectedIndex();
    	
    	String selected_fname = components.tab.get(selectedIndex).fileName.getText();
    	
    	if(selected_fname.equals("Untitled.txt")) {
    		saveAs();
    	}
    	else {
    		
    		writecontenttofile((components.tab.get(components.getselectedindex())));
    		
    	}
    }
    private void writecontenttofile(File f) {
    	
    	String contentString = components.tab.get(components.tp.getSelectedIndex()).area.getText();
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
    		Fileinfo fileinfo = new Fileinfo(file.getPath(), file.getName(),components.creaTextPane());
            
    		components.tab.set(components.getselectedindex(), fileinfo) ;

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
        Fileinfo fileinfo = components.tab.get(components.tp.getSelectedIndex());
//        components.appendText(fileinfo.area,contentString);
        fileinfo.area.setText(contentString);
//        SwingUtilities.invokeLater(() -> componentset.highlight(fileinfo.area));
        
    }

}
