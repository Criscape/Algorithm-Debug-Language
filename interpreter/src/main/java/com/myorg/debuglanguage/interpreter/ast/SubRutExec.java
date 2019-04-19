package com.myorg.debuglanguage.interpreter.ast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubRutExec implements ASTNode {

	private String name;
	private List<ASTNode> args;
	private SubrutineSave subrutine;
	
	public SubRutExec(String name, List<ASTNode> args) {
		super();
		this.name = name;
		this.args = args;
	}

	@Override
	public Object execute(Map<String, Object> symbolTable, Map<String, Object> localSymbolTable) {
		
		Object dato = null;
		
		if(symbolTable.containsKey(this.name)){
			
			this.subrutine = (SubrutineSave)symbolTable.get(this.name);
			
			Map<String,Object> newLocal = new HashMap<>();
			
			for (int i = 0; i < this.subrutine.getParameters().size(); i++){
				
				Parameter parameter = (Parameter)this.subrutine.getParameters().get(i);

				TypeValue x = new TypeValue(parameter.getDataType(),parameter.getIoType(),
						this.args.get(i).execute(symbolTable, localSymbolTable));
				
				if(parameter.getIoType().equals("in")){
					
					newLocal.put(parameter.getId(), x);
				}
			}
			
			NaryTreeNode auxTree = this.subrutine.getLast();

			if(auxTree == null){
				
				this.subrutine.setCounter(0);
				auxTree = new NaryTreeNode(Integer.toString(this.subrutine.getCounter()),null,newLocal);
				this.subrutine.setCounter(this.subrutine.getCounter()+1);
				this.subrutine.setLast(auxTree);
			}else{
				
				if(auxTree.getAppendable()){
					
					auxTree.addChild(Integer.toString(this.subrutine.getCounter()), auxTree, newLocal);
					this.subrutine.setCounter(this.subrutine.getCounter()+1);
					this.subrutine.setLast(auxTree.getLastChild());
					
				}else{
						
						/*while(auxTree.getFather() != null  && auxTree.getFather().getAppendable() == false){
							
							auxTree = auxTree.getFather();
						}*/
						auxTree = auxTree.getFather();
						auxTree.addChild(Integer.toString(this.subrutine.getCounter()), auxTree, newLocal);
						this.subrutine.setCounter(this.subrutine.getCounter()+1);
						this.subrutine.setLast(auxTree.getLastChild());
						
						
						/*if(auxTree.getFather() == null && auxTree.getAppendable() == true){
							
							auxTree.addChild(((TypeValue)newLocal.get("numero")).getValue().toString(), auxTree);
							System.out.println("-dsdsd");
							this.subrutine.setLast(auxTree.getLastChild());
							
						}else if(auxTree.getFather() == null && auxTree.getAppendable() == false){
							
							System.out.println("Aqui no entra");
							
						}else{
							
							auxTree = auxTree.getFather();
							auxTree.addChild(((TypeValue)newLocal.get("numero")).getValue().toString(), auxTree);
							//System.out.println("------------------------");
							//NaryTreeNode.printLast(auxTree);
							this.subrutine.setLast(auxTree.getLastChild());

						}*/
					
				}
				
			}
			
			for (int i = 0; i < this.subrutine.getDeclarations().size(); i++){
				
				Declaration declare = (Declaration)this.subrutine.getDeclarations().get(i);

				declare.execute(symbolTable, newLocal);
			}
			
			if(this.subrutine.getFuncOrProc().equals("function")){
				
				boolean ret = false;
				
				for (ASTNode x: this.subrutine.getBody()){
					
					if(x instanceof Retorno){
						
						dato = x.execute(symbolTable, newLocal);
						ret = true;
					}
					
					if(!ret){
						
						x.execute(symbolTable, newLocal);
					}
				}
			}else{
				
				for (ASTNode x: this.subrutine.getBody()){
					
					x.execute(symbolTable, newLocal);
				}
			}

			auxTree = this.subrutine.getLast();
			auxTree.setSymbolTable(newLocal);
			auxTree.setAppendable(false);
			
			this.subrutine.setLast(auxTree.getFather());
			
			if(auxTree.getFather() == null){
				
				//this.subrutine.getArboles().add(auxTree);
				
				String filename = "test/arbol-Noo"+NaryTreeNode.getSerialversionuid()+".ntn";
				NaryTreeNode.print(auxTree);
				
				File f = new File(filename);
				if(f.exists()){
					f.delete();
				}
				
				try{
					
					FileOutputStream file = new FileOutputStream(filename);
										
					ObjectOutputStream out = new ObjectOutputStream(file);
					out.writeObject(auxTree);
					
					out.close();
					file.close();
					
				}catch(IOException ex){
					
					System.out.println("Ruta invalida.");
					System.out.println(ex);
					
				}
				
				this.subrutine.setLast(null);
			}
			
		}else{
			
			switch (this.name){
			
				case "print":
					
					Object x = args.get(0).execute(symbolTable, localSymbolTable);
					
					if(x instanceof Integer){
						
						System.out.println(Integer.parseInt(x.toString()));

					}else if(x instanceof String || x instanceof Boolean){
						
						System.out.println(x.toString());
					}else{
						
						System.out.println(x);
					}
					break;
				
			}
			
			if (this.name.matches("\\w+\\."+"get")){

				int get_x = (Integer)args.get(0).execute(symbolTable, localSymbolTable);
				
				String nombre = this.name.substring(0,this.name.indexOf("."));
				ListSave list_generated = (ListSave)((TypeValue)localSymbolTable.get(nombre)).getValue();
				
				dato = list_generated.getList().get(get_x);
			}else if(this.name.matches("\\w+\\."+"size")){
				
				String nombre = this.name.substring(0,this.name.indexOf("."));
				ListSave list_generated = (ListSave)((TypeValue)localSymbolTable.get(nombre)).getValue();

				dato = list_generated.getList().size();
			}else if(this.name.matches("\\w+\\."+"add")){
				
				Object object_x = args.get(0).execute(symbolTable, localSymbolTable);
				
				String nombre = this.name.substring(0,this.name.indexOf("."));
				ListSave list_generated = (ListSave)((TypeValue)localSymbolTable.get(nombre)).getValue();
				
				list_generated.getList().add(object_x);
			}			
			
		}
		
		return dato;
	}
}
