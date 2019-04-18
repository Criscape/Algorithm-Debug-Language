package com.myorg.debuglanguage.interpreter;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.HashMap;
import java.awt.*;
import java.util.*;
import javax.swing.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sebastian
 */
public class DibujarArbol extends JPanel
{
    
    private Arbol arbol;
    private HashMap posicionNodos = null;
    private HashMap subArboles = null;
    private Dimension blanco = new Dimension(0,0);
    private boolean estado = true;
     private int parent2child = 20, child2child = 30;
     private FontMetrics fm=null;

    public DibujarArbol(Arbol arbol)
    {
        this.arbol = arbol;
        posicionNodos = new HashMap();
          subArboles = new HashMap();
          estado = true;
          setPreferredSize(new java.awt.Dimension(450, 600));
          this.setBackground(Color.BLUE);
          repaint();
          
            
    }
     @Override
     public void paint(Graphics g) 
   {
          super.paint(g);
         fm = g.getFontMetrics();

         
           calcularPosiciones();
          
         Graphics2D g2d = (Graphics2D) g;
         g2d.translate(400, parent2child);
         dibujarArbol(g2d, this.arbol.getRaiz(), Integer.MAX_VALUE, Integer.MAX_VALUE, 
                  fm.getLeading() + fm.getAscent());
         fm = null;
    
         
         repaint();
   }
    
     private void calcularPosiciones() 
    {
         posicionNodos.clear();
         subArboles.clear();
         Nodo root = this.arbol.getRaiz();
         if (root != null) 
         {
             //calcularTamañoSubarbol(root);
             calcularPosicion(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
         }
    }
    
  
     
     private void dibujarArbol(Graphics2D g, Nodo n, int puntox, int puntoy, int yoffs) 
    {
     if (n == null) 
         return;
     
     Rectangle r = (Rectangle) posicionNodos.get(n);
     g.draw(r);
     g.drawString(n.getDato()+"", r.x + 3, r.y + yoffs);
   
     if (puntox != Integer.MAX_VALUE)
       
     g.drawLine(puntox, puntoy, (int)(r.x + r.width/2), r.y);
     
     dibujarArbol(g, n.getLeft(), (int)(r.x + r.width/2), r.y + r.height, yoffs);
     dibujarArbol(g, n.getRight(), (int)(r.x + r.width/2), r.y + r.height, yoffs);
     
     
     repaint();
     
   }
     
     
     private void calcularPosicion(Nodo n, int left, int right, int top) 
    {
      if (n == null) 
          return;
      
      
      int center = 0;
      
      if (right != Integer.MAX_VALUE)
          center = right - child2child;
      else if (left != Integer.MAX_VALUE)
          center = left + child2child;
      int width = fm.stringWidth(n.getDato()+"");
 
      posicionNodos.put(n,new Rectangle(center - width/2 - 3, top, width + 6, fm.getHeight()));
      
      calcularPosicion(n.getLeft(), Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child);
      calcularPosicion(n.getRight(), center + child2child/2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child);
    }
     
   
      
     
    
    
    
}
