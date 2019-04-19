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
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

public class WindowEditor extends JFrame {

	private JPanel contentPane;
	private JInternalFrame internalFrame_1;
	private NaryTreeNode tree;
	private JTextPane textArea2;
	 

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
		contentPane.setBackground(SystemColor.control);
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
				//textArea.setText(textArea.getText()+((editor) internalFrame).getTextArea().getText()+"\n");
							}
		});
		menuBar.add(btnNewButton_1);
		
		internalFrame_1 = new JInternalFrame();
		internalFrame_1.setEnabled(false);
		internalFrame_1.setBounds(709, 375, 651, 325);
		
		internalFrame_1.setBorder(new CompoundBorder());
		
		
		
		/*
		contentPane.add(internalFrame_1);
		internalFrame_1.setVisible(true);
		*/
		 this.textArea2 = new JTextPane();
		 
		 textArea2.setFont(new Font("Arial", Font.PLAIN, 18));textArea2.setFont(new Font("Arial", Font.PLAIN, 18));
		 
		 
	     this.textArea2.setBounds(709, 41, 651, 316);
	     contentPane.add(this.textArea2);
	     
	     this.textArea2.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(0, 0, 0, 0)));
	        
		
		
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
		
		
		Map<String,Object> table = new HashMap();
		table.put("ejemplo1", 3);
		
		this.tree = new NaryTreeNode("1", null, table);
		tree.addChild("2", this.tree, null);
		tree.addChild("3", this.tree, null);
		//tree.addChild("5", this.tree, null);
		this.tree.getChild(0).addChild("a", this.tree.getChild(0), null);
		this.tree.getChild(0).addChild("b", this.tree.getChild(0), null);
		
		this.tree.getChild(1).addChild("a", this.tree.getChild(1), null);
		this.tree.getChild(1).addChild("b", this.tree.getChild(1), null);
		
		this.tree.getChild(0).getChild(0).addChild("w", this.tree.getChild(0).getChild(0), null);
		this.tree.getChild(0).getChild(1).addChild("k", this.tree.getChild(0).getChild(1), null);
		this.tree.getChild(0).getChild(1).addChild("l", this.tree.getChild(0).getChild(1), null);
		
		repintarArbol();
	
	}
	
	public void repintarArbol() {
        //this.internalFrame_1.removeAll();
        Rectangle tamaño = this.internalFrame_1.getBounds();
        this.internalFrame_1 = null;
        this.internalFrame_1 = new JInternalFrame("Representación gráfica", true);
        this.contentPane.add(this.internalFrame_1, JLayeredPane.DEFAULT_LAYER);
        this.internalFrame_1.setVisible(true);
        this.internalFrame_1.setBounds(tamaño);
        this.internalFrame_1.setEnabled(false);
        this.internalFrame_1.setBorder(null);
        this.internalFrame_1.getContentPane().add(this.tree.getdibujo(), BorderLayout.CENTER);
        ((javax.swing.plaf.basic.BasicInternalFrameUI)internalFrame_1.getUI()).setNorthPane(null);
        this.internalFrame_1.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(0, 0, 0, 0)));
        
       
        
        internalFrame_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textArea2.setText(""); 
				//System.out.println("X: "+(arg0.getX()-338)+ " Y: "+(arg0.getY()-55));
				//System.out.println(tree.buscarPosicion(arg0.getX()-338, arg0.getY()-55));
				
				NaryTreeNode auxNode = tree.buscarPosicion(arg0.getX()-338, arg0.getY()-55);
				
				if(auxNode!=null){
					textArea2.setText("Ambiente recursivo del nodo: "+auxNode.getLabel());
					//appendToPane(textArea2, "Ambiente recursivo del nodo: "+auxNode.getLabel(), Color.RED);
					textArea2.setText(textArea2.getText()+"\n");
					
					textArea2.setText(textArea2.getText()+"\n Variables \n");
					
					
					textArea2.setText(textArea2.getText()+auxNode.getVariables());
				}
				else{
					textArea2.setText("Ambiente recursivo del nodo: None");
				}
			}
		});
    }
}
