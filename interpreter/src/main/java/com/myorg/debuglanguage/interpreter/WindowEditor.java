package com.myorg.debuglanguage.interpreter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import com.myorg.debuglanguage.interpreter.ast.NaryTreeNode;

import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class WindowEditor extends JFrame {

	private JPanel contentPane;
	private JInternalFrame internalFrame_1;
	private NaryTreeNode tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowEditor frame = new WindowEditor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws BadLocationException 
	 * @throws PropertyVetoException 
	 */
	public WindowEditor() throws BadLocationException, PropertyVetoException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1412, 752);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
	    
		
		JInternalFrame internalFrame = new editor();
		internalFrame.setBounds(10, 41, 687, 477);
		internalFrame.setBackground(Color.WHITE);
		internalFrame.setEnabled(true);
		((javax.swing.plaf.basic.BasicInternalFrameUI)internalFrame.getUI()).setNorthPane(null);
		contentPane.setLayout(null);
		contentPane.add(internalFrame);
		internalFrame.setBorder(new CompoundBorder());
	    
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 819, 30);
		menuBar.setBackground(SystemColor.control);
		menuBar.setBorderPainted(false);
		contentPane.add(menuBar);
		
		
		JButton btnNewButton = new JButton("Break Point");
		menuBar.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(new CompoundBorder());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 529, 687, 173);
		contentPane.add(scrollPane);
		
		
		JTextArea textArea = new JTextArea("Console:>> \n");
		scrollPane.setViewportView(textArea);
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setBackground(SystemColor.text);
		textArea.setFont(new Font("Calibri", Font.PLAIN, 18));
		textArea.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(20, 20, 20, 20)));
		
		UIManager.put("ScrollBar.thumb", new ColorUIResource(Color.black));
		
		
		JButton btnNewButton_1 = new JButton("Ejecutar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println(((editor) internalFrame).getTextArea().getText());
				textArea.setText(textArea.getText()+((editor) internalFrame).getTextArea().getText());
			}
		});
		menuBar.add(btnNewButton_1);
		
		JInternalFrame internalFrame_1 = new JInternalFrame("New JInternalFrame");
		internalFrame_1.setEnabled(false);
		internalFrame_1.setBounds(709, 354, 651, 348);
		contentPane.add(internalFrame_1);
		internalFrame_1.setVisible(true);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					((editor) internalFrame).createBreakPoint();
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		internalFrame.setVisible(true);
		
		this.tree = new NaryTreeNode(5,null);
		
		
	
	}
	
	public void repintarArbol() {
        this.internalFrame_1.removeAll();
        Rectangle tamaño = this.internalFrame_1.getBounds();
        this.internalFrame_1 = null;
        this.internalFrame_1 = new JInternalFrame("Representación gráfica", true);
        this.internalFrame_1.add(this.internalFrame_1, JLayeredPane.DEFAULT_LAYER);
        this.internalFrame_1.setVisible(true);
        this.internalFrame_1.setBounds(tamaño);
        this.internalFrame_1.setEnabled(false);
        this.internalFrame_1.add(this.tree.getdibujo(), BorderLayout.CENTER);
    }
	

}
