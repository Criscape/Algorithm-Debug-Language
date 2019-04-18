package com.myorg.debuglanguage.interpreter.ast;

import java.util.ArrayList;

import javax.swing.JPanel;
import com.myorg.debuglanguage.interpreter.*;

public class NaryTreeNode {
	
    private final String LABEL;
    private final ArrayList<NaryTreeNode> children;
    private NaryTreeNode father;
    private boolean appendable;

    public NaryTreeNode(String LABEL, NaryTreeNode father){
    	
        this.LABEL = LABEL;
        this.children = new ArrayList<>();
        this.father = father;
        this.appendable = true;
    }

    private boolean addChild(NaryTreeNode node){
    	
        return children.add(node);
    }

    public boolean addChild(String label, NaryTreeNode father){
    	
        return addChild(new NaryTreeNode(label, father));
    }

    public ArrayList<NaryTreeNode> getChildren(){
    	
        return new ArrayList<>(children);
    }
    
    public NaryTreeNode getFather(){
    	return this.father;
    }

    public NaryTreeNode getChild(int index){
    	
        if (index < children.size()) {
            return children.get(index);
        }

        return null;
    }
    
    public NaryTreeNode getLastChild(){
    	
    	return this.children.get(this.children.size()-1);
    }
    
    public boolean getAppendable(){
    	return this.appendable;
    }
    
    public void setAppendable(boolean appendable){
    	this.appendable = appendable;
    }
    
    public static void printLast(NaryTreeNode node){
    	
    	while(node.getFather()!=null){
    		
    		node = node.getFather();
    	}
    	
    	print(node);
    }

    public static void print(NaryTreeNode root){
    	
        printUtil(root, 0);
    }

    private static void printUtil(NaryTreeNode node, int depth) 
    {
        for (int i = 0; i < depth; ++i) {
            System.out.print("   ");
        }

        System.out.println(node.LABEL);

        for (NaryTreeNode child : node.getChildren()) {
            printUtil(child, depth + 1);
        }
    }
    
    public JPanel getdibujo() {
        return new ArbolExpresionGrafico(this);
    }
    
    public int getChildrenSize(){
    	return this.children.size();
    }
    
    public String getLabel(){
    	return this.LABEL;
    }
}

