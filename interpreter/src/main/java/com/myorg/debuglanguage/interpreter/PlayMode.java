package com.myorg.debuglanguage.interpreter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.myorg.debuglanguage.interpreter.ast.NaryTreeNode;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PlayMode extends JFrame {

	private JPanel contentPane;
	private JTextField i1;
	private JTextField i2;
	private JTextField i3;
	private NaryTreeNode arbol;
	private int complejidad;
	private JLabel Trivia;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlayMode frame = new PlayMode();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PlayMode() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 473, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAlturaDelrbol = new JLabel("Altura del árbol");
		lblAlturaDelrbol.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblAlturaDelrbol.setBounds(10, 37, 168, 72);
		contentPane.add(lblAlturaDelrbol);
		
		Trivia = new JLabel("Pequeño Quiz");
		Trivia.setForeground(new Color(102, 205, 170));
		Trivia.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		Trivia.setBounds(27, -13, 420, 72);
		contentPane.add(Trivia);
		
		i1 = new JTextField();
		i1.setForeground(Color.GRAY);
		i1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		i1.setColumns(19);
		i1.setBounds(205, 60, 157, 30);
		contentPane.add(i1);
		
		JButton btnVerficar = new JButton("Veríficar");
		btnVerficar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				verificar();
			}
		});
		btnVerficar.setBounds(152, 220, 157, 30);
		contentPane.add(btnVerficar);
		
		JLabel lblFRamificacin = new JLabel("F. ramificación");
		lblFRamificacin.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblFRamificacin.setBounds(10, 80, 185, 72);
		contentPane.add(lblFRamificacin);
		
		JLabel lblOrdenDeLos = new JLabel("Orden de los for");
		lblOrdenDeLos.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblOrdenDeLos.setBounds(10, 127, 190, 72);
		contentPane.add(lblOrdenDeLos);
		
		i2 = new JTextField();
		i2.setForeground(Color.GRAY);
		i2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		i2.setColumns(19);
		i2.setBounds(205, 101, 157, 30);
		contentPane.add(i2);
		
		i3 = new JTextField();
		i3.setForeground(Color.GRAY);
		i3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		i3.setColumns(19);
		i3.setBounds(205, 142, 157, 30);
		contentPane.add(i3);
	}
	
	boolean correcto1 = true, correcto2 = true, correcto3 = true;
	
	public void verificar(){
		int answer1 = Integer.parseInt(i1.getText());
		int a1 = this.arbol.getAltura(this.arbol);
		
		if(answer1 != a1){
			i1.setForeground(Color.red);
			correcto1 = false;
		}
		else{
			i1.setForeground(Color.black);
			correcto1 = true;
		}
		
		int answer2 = Integer.parseInt(i2.getText());
		int a2 = this.arbol.getAnchura(this.arbol);
		
		if(answer2 != a2){
			i2.setForeground(Color.red);
			correcto2 = false;
		}
		else{
			i2.setForeground(Color.black);
			correcto2 = true;
		}
		
		int answer3 = Integer.parseInt(i3.getText());
		int a3 = this.complejidad;
		
		if(answer3 != a3){
			i3.setForeground(Color.red);
			correcto3 = false;
		}
		else{
			i3.setForeground(Color.black);
			correcto3 = true;
		}
			
		if(correcto1 && correcto2 && correcto3){
			Trivia.setText("Felicidades, eres un genio");
			Trivia.setForeground(Color.cyan);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setVisible(false);
		}else{
			Trivia.setText("Revisa de nuevo");
			Trivia.setForeground(Color.red);
		}
		
	}
	
	
	public void setArbol(NaryTreeNode arbol, int complejidad){
		this.arbol = arbol;
		this.complejidad = complejidad;
	}
}
