/*
 * 04/25/2007
 *
 * RTextAreaUI.java - UI used by instances of RTextArea.
 * 
 * This library is distributed under a modified BSD license.  See the included
 * RSyntaxTextArea.License.txt file for details.
 */
package com.myorg.debuglanguage.interpreter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTML.Tag;
import javax.swing.text.html.parser.TagElement;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Font;
import java.awt.List;
import java.awt.Point;

/**
 * A simple example showing how to use RSyntaxTextArea to add Java syntax
 * highlighting to a Swing application.<p>
 */
public class editor extends JInternalFrame {
	
	JTextArea textArea;
	Color breakPointColor;
	ImageIcon breakPointIcon;
	RSyntaxTextArea syntaxTextArea; 
	RTextScrollPane sp;
	Gutter gutter;
	IconRowHeader iconArea;
	HashMap<String, Object> breakLines;
	
	int actualLine;
	Object actualLineO;
	int beforeLine;
	Object beforeLineO;
	
	
   public editor() throws BadLocationException, PropertyVetoException {
   	setRootPaneCheckingEnabled(false);
   	setEnabled(false);
   	setMaximizable(true);
   	setBorder(null);
   	Border border = BorderFactory.createLineBorder(Color.BLACK);

	  breakPointColor = new Color(26, 188, 156, 100);
	  
	  breakPointIcon = new ImageIcon("C:/Users/Sebastian/git/Algorithm-Debug-Language-final2/interpreter/src/main/java/com/myorg/debuglanguage/interpreter/ast/icon.png");
	  
	  breakLines = new HashMap();
	  
      JPanel cp = new JPanel();
      
      cp.setBackground(Color.WHITE);
      
      
      syntaxTextArea = new RSyntaxTextArea(20, 60);
      syntaxTextArea.setFont(new Font("Consolas", Font.PLAIN, 17));
      syntaxTextArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
      // No other property of RSyntaxTextArea is allowed to use
      syntaxTextArea.setUseSelectedTextColor(isSelected);
      
      syntaxTextArea.setCodeFoldingEnabled(true);
      sp = new RTextScrollPane(syntaxTextArea);
      
      sp.getGutter().setLineNumberFont(new Font("Monospaced", Font.PLAIN, 16));
      sp.getGutter().setBackground(UIManager.getColor("Button.background"));
      sp.getGutter().setLineNumberColor(Color.BLACK);
      sp.getGutter().setFoldIndicatorForeground(Color.WHITE);
      sp.getGutter().setBorderColor(Color.WHITE);
     
      
      
    //Creates glutter for line codes
      gutter = sp.getGutter();
      sp.setIconRowHeaderEnabled(true);
      
     
      // You can now modify textArea object similar to any other JTextArea object to add other functionality
      textArea = syntaxTextArea;
      cp.setLayout(new BoxLayout(cp, BoxLayout.X_AXIS));
      //JScrollPane sp = new JScrollPane(textArea);
      cp.add(sp);

      setContentPane(cp);
      setTitle("Interprete Analísis");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      pack();
      //setLocation(null);
      
      
   }
   
   public void createBreakPoint() throws BadLocationException{
	  
	   int currentLine = syntaxTextArea.getCaretLineNumber();
	   
	   if(!breakLines.keySet().contains(currentLine+"g")){
		   GutterIconInfo tmp = gutter.addLineTrackingIcon(currentLine , breakPointIcon , "delete"); 
		   Object tmp2 = syntaxTextArea.addLineHighlight(currentLine, breakPointColor); 
		   breakLines.put(currentLine+"g", tmp);
		   breakLines.put(currentLine+"l", tmp2);
	   }
	   else{
		   gutter.removeTrackingIcon((GutterIconInfo) breakLines.get(currentLine+"g"));
		   syntaxTextArea.removeLineHighlight(breakLines.get(currentLine+"l"));
		   breakLines.remove(currentLine+"g");
		   breakLines.remove(currentLine+"l");
	   } 
   }
   
   public JTextArea getTextArea(){
	   return this.textArea;
   }
   
   public void draw(int line){
	   if(line == -1){
		   syntaxTextArea.removeLineHighlight(beforeLineO);
	   }
	   else{
		   syntaxTextArea.removeLineHighlight(beforeLineO);
		   try {
			beforeLineO = syntaxTextArea.addLineHighlight(line, breakPointColor);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	   }
	   
	   
	   
   }

   public static void main(String[] args) {
      // Start all Swing applications on the EDT.
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            try {
				new editor().setVisible(true);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
         }
      });
   }

}