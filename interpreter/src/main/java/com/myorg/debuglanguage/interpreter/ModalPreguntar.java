package com.myorg.debuglanguage.interpreter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ModalPreguntar extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private int valor;
	private JInternalFrame internalFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModalPreguntar frame = new ModalPreguntar();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModalPreguntar() {
		this.valor = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblcuntasVecesCree = new JLabel("¿Cuántas veces se ejecuta la linea?");
		lblcuntasVecesCree.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblcuntasVecesCree.setBounds(24, 24, 385, 72);
		contentPane.add(lblcuntasVecesCree);
		
		textField = new JTextField();
		textField.setForeground(Color.GRAY);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setColumns(19);
		textField.setBounds(130, 143, 157, 44);
		contentPane.add(textField);
		
		JLabel lblcuntoCreesApuesto = new JLabel("¿Cuántas veces crees? ");
		lblcuntoCreesApuesto.setForeground(new Color(102, 205, 170));
		lblcuntoCreesApuesto.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblcuntoCreesApuesto.setBounds(116, 68, 251, 72);
		contentPane.add(lblcuntoCreesApuesto);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				valor = 0;
				if(textField.getText() != ""){
					valor = Integer.parseInt(textField.getText());
				}
				
				setVisible(false);
				execBreakPoint();
			}
		});
		btnIngresar.setBounds(130, 209, 157, 30);
		contentPane.add(btnIngresar);
	}
	
	
	public int getValor(){
		return this.valor;
	}
	
	public void execBreakPoint(){
		try {
			((editor) internalFrame).createBreakPoint();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setInternalFrame(JInternalFrame internalFrame){
		this.internalFrame = internalFrame;
	}
}
