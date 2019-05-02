package com.myorg.debuglanguage.interpreter;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.myorg.debuglanguage.interpreter.ast.NaryTreeNode;
import com.myorg.debuglanguage.interpreter.ast.TypeValue;
import com.myorg.debuglanguage.interpreter.ast.While;
import com.google.inject.matcher.Matcher;
import com.myorg.debuglanguage.interpreter.ast.ASTNode;
import com.myorg.debuglanguage.interpreter.ast.For;
import com.myorg.debuglanguage.interpreter.ast.Lineable;
import com.myorg.debuglanguage.interpreter.ast.ListaEjecucion;


import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.List;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.JLabel;

public class WindowEditor extends JFrame {

	private JPanel contentPane;
	private JInternalFrame internalFrame_1;
	private NaryTreeNode tree;
	private JTextPane textArea2;
	private JInternalFrame internalFrame;
	private JTextPane textArea;
	private ListaEjecucion list;
	private int step;
	Map<String, Object> symbolTable;
	Map<String, Object> localSymbolTable;
	private Modal modal;
	private ModalPreguntar modalPreguntar;
	private int time;
	private Timer timer;
	private Graph grapher;
	private HashMap<String, Integer> ejecucion;
	private PlayMode play;
	private HashMap<Integer, Integer> times;
	private int score;
	JLabel lblScoreP;
	

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
		
		
		OutputStream out = new OutputStream() {
	        @Override
	        public void write(int b) throws IOException {
	        }
	    };
	    
	    
	    
	    
	    //Initialize global variable step in 0 for exectution
	    this.step = 0;
	    this.time = 0;
	    this.modal = new Modal();
	    modal.setVisible(false);
	    this.modalPreguntar =  new ModalPreguntar(this);
	    modalPreguntar.setVisible(false);
	    
	    //Initialize grapher
	    this.grapher = new Graph();
	    this.grapher.getJframe().setVisible(false);
	    
	    JTextFieldPrintStream print = new JTextFieldPrintStream(out);
	    System.setOut(print);
	    
	    this.score = 0;
	    this.play = new PlayMode();
	    this.play.setVisible(false);;
	    
	    ejecucion = new HashMap<String, Integer>();
	    times = new HashMap<Integer, Integer>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1412, 752);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
	    
		
		this.internalFrame = new editor();
		this.internalFrame.setBounds(10, 41, 687, 477);
		this.internalFrame.setBackground(Color.WHITE);
		this.internalFrame.setEnabled(true);
		((javax.swing.plaf.basic.BasicInternalFrameUI)internalFrame.getUI()).setNorthPane(null);
		contentPane.setLayout(null);
		contentPane.add(internalFrame);
		this.internalFrame.setBorder(new CompoundBorder());
	    
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1360, 43);
		menuBar.setBackground(SystemColor.control);
		menuBar.setBorderPainted(false);
		contentPane.add(menuBar);
		
		JLabel lblNewLabel = new JLabel("     ");
		menuBar.add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("Break Point     ");
		btnNewButton.setIcon(new ImageIcon(WindowEditor.class.getResource("/images/broken-link.png")));
		btnNewButton.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton.setContentAreaFilled(false);
		menuBar.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 529, 687, 173);
		contentPane.add(scrollPane);
		
		
		this.textArea = new JTextPane();
		textArea.setText("Consola >>");
		
		scrollPane.setViewportView(textArea);
		this.textArea.setForeground(new Color(0, 0, 0));
		this.textArea.setBackground(SystemColor.text);
		this.textArea.setFont(new Font("Calibri", Font.PLAIN, 18));
		this.textArea.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(20, 20, 20, 20)));
		
		UIManager.put("ScrollBar.thumb", new ColorUIResource(Color.black));
		
		JButton btnNewButton_1 = new JButton("Ejecutar     ");
		btnNewButton_1.setIcon(new ImageIcon(WindowEditor.class.getResource("/images/file (1).png")));
		btnNewButton_1.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
				long start = System.currentTimeMillis();
				
				textArea.setText("Console:>> \n");
				String code = ((editor) internalFrame).getTextArea().getText();
				
				saveFile(code);
				
				String program = "test/test.adl";

				
				
				debugGrammarLexer lexer = null;
				try {
					lexer = new debugGrammarLexer(new ANTLRFileStream(program));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				debugGrammarParser parser = new debugGrammarParser(tokens);

				debugGrammarParser.ProgramContext tree2 = parser.program();
				
				debugGrammarCustomVisitor visitor = new debugGrammarCustomVisitor();
				visitor.visit(tree2);
				
				/*System.out.println("");
				System.out.println(tree2.getChild(1).getChild(0).getText());
				System.out.println(tree2.getChild(1).getChild(1).getText());
				System.out.println(tree2.getChild(1).getChild(2).getText());
				System.out.println(tree2.getChild(1).getChild(3).getText());
				*/
				long end = System.currentTimeMillis();
				float sec = (end - start) / 1000F;
				System.out.println("Tiempo total: "+sec+" segundos");
				//System.out.println((char)27 + "[33mYELLOW");
				
				
				loadTree();
				//NaryTreeNode.print(tree);
				//System.out.println(tree.getChildrenSize());
				//System.out.println(this.tree.getLabel());
				
				repintarArbol();
				
				
				
				loadList();
				
				step = 0;
				
				
				symbolTable = new HashMap<>();
				ejecucion = new HashMap<>();
				times = new HashMap<>();
				localSymbolTable = new HashMap<>();
				
				symbolTable.put("guardo",true);
				symbolTable.put("ejecuto",true);
					
					
					timer = new Timer(modal.getTime()*1000, new ActionListener(){
						
						@Override
						public void actionPerformed(ActionEvent e){
							
							if(step<list.getOrden().size()){
								list.getOrden().get(step).execute(symbolTable, localSymbolTable);
								//System.out.println(list.getOrden().size());
								
								int tmpValue = 0;
								
								if(list.getOrden().get(step) instanceof Lineable){

									String tmpKey = ((Lineable) list.getOrden().get(step) ).getLine();
									
									if( (tmpValue = getLinesPerLines(tmpKey)) != 0 ){
										draw(tmpValue-1);
									}
									
								
									if(times.containsKey(tmpValue)){
										times.put(tmpValue, times.get(tmpValue)+1);
									}
									else{
										times.put(tmpValue, 1);
									}
									
									
								}
								
								
								if(list.getOrden().get(step) instanceof For){
									
									
									if(ejecucion.containsKey("for")){
										
										ejecucion.put("for",
												ejecucion.get("for")+1);
										
									}
									else{
										
										int fors = buscaFor();
										ejecucion.put("for",fors);
									}
								
								}
								
								if(list.getOrden().get(step) instanceof While){
									
									
									if(ejecucion.containsKey("while")){
										
										ejecucion.put("while",
												ejecucion.get("while")+1);
										
									}
									else{
										
										ejecucion.put("while",1);
									}
								
								}
								
								
								//System.out.println("OJOOO");
								//System.out.println(ejecucion);
								
								//((ListaEjecucion) symbolTable.get("lista_exec")).getExecuted().set(step, true);
								//System.out.println(localSymbolTable.keySet());
								writeInConsole();
								step = step + 1;
							}
							else{
								timer.stop();
								grapher.graph(ejecucion);
								draw(-1);
							}
							
						}
					});
					timer.start();
				
		
			}
		
				
		});
		menuBar.add(btnNewButton_1);
		
		
		
		//Botón Sigueinte
		JButton btnSiguiente = new JButton("Siguiente      ");
		btnSiguiente.setIcon(new ImageIcon(WindowEditor.class.getResource("/images/redo (2).png")));
		btnSiguiente.setBorder(BorderFactory.createEmptyBorder());
		btnSiguiente.setContentAreaFilled(false);
		btnSiguiente.setVisible(false);
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!modalPreguntar.getVisibility()){
					System.out.println("Estás en el paso "+step);
					if(step < list.getOrden().size()){
						moveForward();
						writeInConsole();
						step = step + 1;
						modalPreguntar.setVisibility(true);
					}
				}
				
			}
		});
		menuBar.add(btnSiguiente);
		
		//Botón atrás
		JButton btnAtras = new JButton("Anterior     ");
		btnAtras.setIcon(new ImageIcon(WindowEditor.class.getResource("/images/undo.png")));
		btnAtras.setBorder(BorderFactory.createEmptyBorder());
		btnAtras.setContentAreaFilled(false);
		btnAtras.setVisible(false);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Estás en el paso "+step);
				
				if(!modalPreguntar.getVisibility()){
					if(step > 0){
						localSymbolTable = new HashMap<String, Object>();
						symbolTable = new HashMap<String, Object>();
						symbolTable.put("ejecuto",true);

						step = step - 1;
						moveBackwards();
						wrtieInConsoleAll();
						modalPreguntar.setVisibility(true);
						
					}
				}
				
			}
		});
		menuBar.add(btnAtras);
		
		
		
		
		JButton btnNewButton_2 = new JButton("Ejecutar paso a paso     ");
		btnNewButton_2.setIcon(new ImageIcon(WindowEditor.class.getResource("/images/preview.png")));
		btnNewButton_2.setBorder(BorderFactory.createEmptyBorder());
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				long start = System.currentTimeMillis();
				
				textArea.setText("Console:>> \n");
				String code = ((editor) internalFrame).getTextArea().getText();
				
				saveFile(code);
				
				String program = "test/test.adl";

				
				
				debugGrammarLexer lexer = null;
				try {
					lexer = new debugGrammarLexer(new ANTLRFileStream(program));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				debugGrammarParser parser = new debugGrammarParser(tokens);

				debugGrammarParser.ProgramContext tree2 = parser.program();
				
				debugGrammarCustomVisitor visitor = new debugGrammarCustomVisitor();
				visitor.visit(tree2);
				
				long end = System.currentTimeMillis();
				float sec = (end - start) / 1000F;
				System.out.println("Tiempo total: "+sec+" segundos");
				//System.out.println((char)27 + "[33mYELLOW");
				
				
				loadTree();
				//NaryTreeNode.print(tree);
				//System.out.println(tree.getChildrenSize());
				//System.out.println(this.tree.getLabel());
				repintarArbol();
				loadList();

				
				
				step = 0;
				symbolTable = new HashMap<>();
				localSymbolTable = new HashMap<>();
				symbolTable.put("ejecuto",true);

				//System.out.println(list.getOrden().size());
				//list.getOrden().get(0).execute(symbolTable, localSymbolTable);
				//list.getOrden().get(3).execute(symbolTable, localSymbolTable);
				
				/*
				for(ASTNode node : list.getOrden()){
					node.execute(symbolTable, localSymbolTable);
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				*/
				btnSiguiente.setVisible(true);
				btnAtras.setVisible(true);
			}
		});
		menuBar.add(btnNewButton_2);
		
		JButton btnTiempoEjecucin = new JButton("Tiempo de ejecución     ");
		btnTiempoEjecucin.setIcon(new ImageIcon(WindowEditor.class.getResource("/images/text-width.png")));
		btnTiempoEjecucin.setBorder(BorderFactory.createEmptyBorder());
		btnTiempoEjecucin.setContentAreaFilled(false);
		btnTiempoEjecucin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modal.setVisible(true);
				modal.setVisible(true);
				time = modal.getTime();
			}
		});
		menuBar.add(btnTiempoEjecucin);
		
		JButton btnGraficar = new JButton("Graficar     ");
		btnGraficar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grapher.getJframe().setVisible(true);
				
			}
		});
		btnGraficar.setIcon(new ImageIcon(WindowEditor.class.getResource("/images/table.png")));
		btnGraficar.setBorder(BorderFactory.createEmptyBorder());
		btnGraficar.setContentAreaFilled(false);
		menuBar.add(btnGraficar);
		
		JButton btnMiniQuiz = new JButton("Mini Quiz          ");
		btnMiniQuiz.setBorder(BorderFactory.createEmptyBorder());
		btnMiniQuiz.setContentAreaFilled(false);
		btnMiniQuiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				play.setArbol(tree, complejidad());
				play.setVisible(true);
				
			}
		});
		btnMiniQuiz.setIcon(new ImageIcon(WindowEditor.class.getResource("/images/paste.png")));
		menuBar.add(btnMiniQuiz);
		
		JLabel lblScore = new JLabel("SCORE: ");
		lblScore.setForeground(new Color(0, 102, 153));
		lblScore.setFont(new Font("Tahoma", Font.BOLD, 17));
		menuBar.add(lblScore);
		
		lblScoreP = new JLabel("0");
		lblScoreP.setFont(new Font("Tahoma", Font.PLAIN, 19));
		menuBar.add(lblScoreP);
		
		
		
		
		
		
	
		
		internalFrame_1 = new JInternalFrame();
		internalFrame_1.setEnabled(false);
		internalFrame_1.setBounds(709, 375, 651, 325);
		
		internalFrame_1.setBorder(new CompoundBorder());
		
		
		
		/*
		contentPane.add(internalFrame_1);
		internalFrame_1.setVisible(true);
		*/
		 
		 JScrollPane scrollPane_1 = new JScrollPane();
		 scrollPane_1.setBounds(709, 43, 651, 326); 
		 contentPane.add(scrollPane_1);
		 this.textArea2 = new JTextPane();
		 scrollPane_1.setViewportView(textArea2);
		 
		 textArea2.setFont(new Font("Arial", Font.PLAIN, 18));textArea2.setFont(new Font("Arial", Font.PLAIN, 18));
	     
	     this.textArea2.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 2), new EmptyBorder(0, 0, 0, 0)));
	        
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//((editor) internalFrame).createBreakPoint();
				modalPreguntar.setInternalFrame(internalFrame);
				modalPreguntar.setLines(getTextL());
				modalPreguntar.setTimes(times);
				modalPreguntar.setVisible(true);
			}
		});
		internalFrame.setVisible(true);
		
		/*
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
		/*
		this.tree.getChild(0).getChild(0).addChild("w", this.tree.getChild(0).getChild(0), null);
		this.tree.getChild(0).getChild(1).addChild("k", this.tree.getChild(0).getChild(1), null);
		this.tree.getChild(0).getChild(1).addChild("l", this.tree.getChild(0).getChild(1), null);
		*/
		
		
		
	
	}
	
	
	public void saveFile(String code){
	
		File f;
		f = new File("test/test.adl");
		FileWriter w = null;
		try {
			w = new FileWriter(f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedWriter bw = new BufferedWriter(w);
		PrintWriter wr = new PrintWriter(bw);  
		wr.write(code);
		
		wr.close();
		try {
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
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
	
	public void loadList(){
		FileInputStream file = null;
		try {
			file = new FileInputStream("test/lista_ejecucion.ntn");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			this.list = (ListaEjecucion)in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadTree(){
		
		FileInputStream file = null;
		try {
			file = new FileInputStream("test/arbol-No1.ntn");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		try {
			this.tree = (NaryTreeNode)in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void moveForward(){
		
		list.getOrden().get(step).execute(symbolTable, localSymbolTable);
		
		if(list.getOrden().get(step) instanceof Lineable){
			int line;
			draw(line = getLinesPerLines(  ((Lineable)list.getOrden().get(step)).getLine())-1);
			this.modalPreguntar.setCurrentLine(line);
		}
	}
	
	public void moveBackwards(){
		
		
		for(int i=0;i<step;i++){
			list.getOrden().get(i).execute(symbolTable, localSymbolTable);
		}
		
		if(list.getOrden().get(step-1) instanceof Lineable){

			draw(getLinesPerLines(((Lineable)list.getOrden().get(step-1)).getLine())-1);
		}
	}
	
	public String getLocalTableData(int step){
		String retur = "";
    	
		
    	if(this.localSymbolTable != null){
    		retur +="----------------------------------------------"+"\n";
			retur +="PASO: "+step+"\n";
    		for(String variable : localSymbolTable.keySet()){
    			
        		retur +="nombre: "+variable+" , valor: "+((TypeValue) localSymbolTable.get(variable)).getValue()+" , tipo: "+
        				((TypeValue) localSymbolTable.get(variable)).getDataType()+"\n";
        	}
    		retur += "----------------------------------------------";
    		retur += "\n";
    	}
    	
    	return retur;
	}
	
	public void writeInConsole(){
		if(step==0){
			this.textArea2.setText("");
			this.textArea2.setText("Estado de variables paso a paso");
		}
		this.textArea2.setText(this.textArea2.getText()+"\n"+getLocalTableData(step));
	}
	
	public void wrtieInConsoleAll(){
		this.textArea2.setText("");
		this.textArea2.setText(this.textArea2.getText()+"Estado de variables paso a paso");
		for(int i=0;i<step;i++){
			this.textArea2.setText(this.textArea2.getText()+"\n"+getLocalTableData(i));
		}
	}
	
	public String getTextLines(){
		String code = ((editor) internalFrame).getTextArea().getText();
		//String[] data = code.split("\n");
		
		
		//for(int i=0;i<data.length;i++){
		//	System.out.println(data[i]);
		//}
		
		return code;
	}
	
	public String[] getTextL(){
		String code = ((editor) internalFrame).getTextArea().getText();
		String[] data = code.split("\n");
		
		
		for(int i=0;i<data.length;i++){
			data[i].replaceAll("\\s+","");
		}
		
		return data;
	}
	
	
	public int getLinesPerLines(String find){
		String code = ((editor) internalFrame).getTextArea().getText();
		String[] data = code.split("\n");
		String strTmp = "";
		
		for(int i=0;i<data.length;i++){
			strTmp = data[i];
			strTmp = strTmp.replaceAll("\\s+","");
			
			if(find.contains("(") || find.contains("")){
				find = find.replaceAll("\\(", "");
				find = find.replaceAll("\\)","");
				strTmp = strTmp.replaceAll("\\(", "");
				strTmp = strTmp.replaceAll("\\)", "");
				
			}
			
			if(strTmp.matches(".*"+find+".*")){
				return i+1;
			}
		}
		
		return 0;
	}
	

	
	public void draw(int line){
		((editor) internalFrame).draw(line);
	}
	
	public int buscaFor(){
		
		String texto = getTextLines();
		int numberWords = 0;
	    Pattern p = Pattern.compile("\\b+"+"for"+"\\b");
	    java.util.regex.Matcher  m = p.matcher(texto);
	       
	    
	    while(m.find()){
	           numberWords += 1;
	       }
	    
		return numberWords;
	}
	
	public int altura(){
		return tree.getAltura(tree);
	}
	
	public int anchura(){
		
		return tree.getAnchura(tree);
	}
	
	public int complejidad(){
		return buscaFor();
	}
	
	public void setScore(int score){
		if(this.score + score>0){
			this.score = this.score + score;
		}
		else{
			this.score = 0;
		}
		
		
		lblScoreP.setText(Integer.toString(this.score));
	}
	
	public int getStep(){
		return this.step;
	}
	
	
	class JTextFieldPrintStream extends PrintStream {
        public JTextFieldPrintStream(OutputStream out) {
            super(out);
        }
        @Override
        public void println(String x) {
        	textArea.setText(textArea.getText()+"\n"+x);
        }
    }
}
