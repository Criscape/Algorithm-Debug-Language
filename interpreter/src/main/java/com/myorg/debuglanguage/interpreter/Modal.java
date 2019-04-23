package com.myorg.debuglanguage.interpreter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Modal extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JButton btnAadir;
	private int time;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Modal frame = new Modal();
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
	public Modal() {
		this.time  = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        setUndecorated(true);

		
		JLabel lblaQuVelocidad = new JLabel("¿A qué velocidad desea ejecutar?");
		lblaQuVelocidad.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblaQuVelocidad.setBounds(21, 21, 385, 72);
		contentPane.add(lblaQuVelocidad);
		
		textField = new JTextField();
		textField.setForeground(new Color(128, 128, 128));
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(127, 140, 157, 44);
		contentPane.add(textField);
		textField.setColumns(19);
		
		JLabel lblIngreseElValor = new JLabel("Ingrese el valor en segundos");
		lblIngreseElValor.setForeground(new Color(102, 205, 170));
		lblIngreseElValor.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblIngreseElValor.setBounds(93, 79, 251, 72);
		contentPane.add(lblIngreseElValor);
		
		btnAadir = new JButton("Añadir");
		btnAadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				time = Integer.parseInt(textField.getText());
				setVisible(false);
				
			}
		});
		btnAadir.setBounds(127, 206, 157, 30);
		contentPane.add(btnAadir);
		//lblIngreseElValor.setBorder(null);
        
	}
	
	public int getTime(){
		return this.time;
	}
}
