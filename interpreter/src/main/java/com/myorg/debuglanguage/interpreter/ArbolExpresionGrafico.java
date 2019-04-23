
package com.myorg.debuglanguage.interpreter;


  /**
 *
 * @author SebastianDuque
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;
import com.myorg.debuglanguage.interpreter.ast.*;

public class ArbolExpresionGrafico extends JPanel 
{
    private NaryTreeNode miArbol;
    private HashMap posicionNodos = null;
    private HashMap subtreeSizes = null;
    private boolean dirty = true;
    private int parent2child = 30, child2child = 40;
    private Dimension empty = new Dimension(0,0);
    private FontMetrics fm = null;
    
    
    /**
     * Constructor de la clase ArbolExpresionGrafico.
     * El constructor permite inicializar los atributos de la clase ArbolExpresionGrafico
     * y llama al método repaint(), que es el encargado de pintar el Arbol.
     * @param miExpresion: dato de tipo ArbolExpresion que contiene el Arbol a
     * dibujar.
     */
    public ArbolExpresionGrafico(NaryTreeNode miArbol) 
    {
          this.miArbol = miArbol;
          this.setBackground(Color.WHITE);
          posicionNodos = new HashMap();
          subtreeSizes = new HashMap();
          dirty = true;
          repaint();      
    }
    
    public NaryTreeNode buscarPosicion(int x, int y){
    	Rectangle auxrec = new Rectangle(x, y, 20, 20);
    	//Point p = new Point(x,y);
    	
    	for(Object node : posicionNodos.keySet()){
    		
    	//	System.out.println(((NaryTreeNode) node).getLabel()+"----->"+" x: "+((Rectangle) posicionNodos.get((NaryTreeNode)node)).x+
    			//	"y: "+((Rectangle) posicionNodos.get((NaryTreeNode)node)).y);
    		if(((Rectangle) posicionNodos.get((NaryTreeNode)node)).intersects(auxrec) ){
    			return ((NaryTreeNode) node);
    		}
    	}
    	return null;
    }
    
    


    /**
     * Calcula las posiciones de los respectivos subárboles y de cada nodo que 
     * forma parte de ese subárbol, para conocer en que posición van a ir dibujados
     * los rectángulos representativos del árbol de la expresión.
     */
    private void calcularPosiciones() 
    {
         posicionNodos.clear();
         subtreeSizes.clear();
         NaryTreeNode root = this.miArbol;
         if (root != null) 
         {
             calcularTamañoSubarbol(root);
             calcularPosicion(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
         }
    }
    
    /**
     * Calcula el tamaño de cada subárbol y lo agrega al objeto subtreeSizes de la clase
     * de tipo HashMap que va a contener la coleccion de todos los 
     * subárboles que contiene un arbol.
     * @param n:Objeto de la clase NodoB <T> que se utiliza como
     * referencia calcular el tamaño de cada subárbol.
     * @return Dimension con el tamaño de cada subárbol.
     */
    private Dimension calcularTamañoSubarbol(NaryTreeNode n) 
    {
          if (n == null) 
              return new Dimension(0,0);
          
          Dimension ld,rd;
          Dimension m = new Dimension(0,0);
          
          if(n.getChildrenSize() == 3){
        	  ld = calcularTamañoSubarbol(n.getChild(0));
              m = calcularTamañoSubarbol(n.getChild(1));
              rd = calcularTamañoSubarbol(n.getChild(2));
          }
          else{
        	  ld = calcularTamañoSubarbol(n.getChild(0));
        	  rd = calcularTamañoSubarbol(n.getChild(1));
          }
         
          
          
          
          int w;
          int h;
          if(n.getChildrenSize()== 3){
        	   w = ld.width + child2child + rd.width + 40;
        	   h = fm.getHeight() + parent2child + Math.max(Math.max(ld.height, rd.height) , m.height);
          }
          else{
        	   w = ld.width + child2child + rd.width;
        	   h = fm.getHeight() + parent2child + Math.max(ld.height, rd.height);
          }
          
          
          Dimension d = new Dimension(w, h);
          subtreeSizes.put(n, d);
          
          return d;
    }
    
    
    /**
     * Calcula la ubicación de cada nodo de cada subárbol y agrega cada nodo con 
     * un objeto de tipo Rectangule que tiene la ubicación y la información específica de dónde 
     * va a ser dibujado.
     * @param n: Objeto de tipo NodoB <T> que se utiliza como
     * referencia para calcular la ubicación de cada nodo.
     * @param left: int con alineación y orientación a la izquierda.
     * @param right: int con alineación y orientación a la derecha.
     * @param top: int con el tope.
     */
    
    
    private void calcularPosicion(NaryTreeNode n, int left, int right, int top) 
    {
      if (n == null) 
          return;
      
      Dimension ld = (Dimension) subtreeSizes.get(n.getChild(0));
      if (ld == null) 
          ld = empty;
     
      
      Dimension rd = new Dimension();
      if(n.getChildrenSize()==3){
    	  rd = (Dimension) subtreeSizes.get(n.getChild(2));
          if (rd == null) 
              rd = empty;
      }
      else if(n.getChildrenSize()==2){
    	  rd = (Dimension) subtreeSizes.get(n.getChild(1));
          if (rd == null) 
              rd = empty;
      }
     
      

      int center = 0;      
     
     
      if (right != Integer.MAX_VALUE)
          center = right - rd.width - child2child/2;
      else if (left != Integer.MAX_VALUE)
          center = left + ld.width + child2child/2;
      int width = fm.stringWidth(n.getLabel()+"");
      
     if(left == -2){
    	 center =((int) ((Rectangle) posicionNodos.get(n.getFather())).getX())+width/2+3;
     }

 
      posicionNodos.put(n,new Rectangle(center - width/2 - 3, top, width + 25, fm.getHeight()+15));
      
      if(n.getChildrenSize() == 3){
    	   calcularPosicion(n.getChild(0), Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child);
    	   calcularPosicion(n.getChild(1), -2, center + child2child/2, top + fm.getHeight() + parent2child);
    	   calcularPosicion(n.getChild(2), center + child2child/2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child);
      }
      else if(n.getChildrenSize() == 2){
    	  calcularPosicion(n.getChild(0), Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child);
    	  calcularPosicion(n.getChild(1), center + child2child/2, Integer.MAX_VALUE, top + fm.getHeight() + parent2child);
      }
      else if(n.getChildrenSize() == 1){
    	  calcularPosicion(n.getChild(0), Integer.MAX_VALUE, center - child2child/2, top + fm.getHeight() + parent2child);
   	   
      }
    }
    
    /**
     * Dibuja el árbol teniendo en cuenta las ubicaciones de los nodos y los 
     * subárboles calculadas anteriormente.
     * @param g: Objeto de la clase Graphics2D que permite realizar el dibujo de las líneas, rectangulos y del String de la información que contiene el Nodo.
     * @param n: Objeto de la clase NodoB <T> que se utiliza como referencia para dibujar el árbol.
     * @param puntox: int con la posición en x desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param puntoy: int con la posición en y desde donde se va a dibujar la línea hasta el siguiente hijo.
     * @param yoffs: int con la altura del FontMetrics.
     */
    private void dibujarArbol(Graphics2D g, NaryTreeNode n, int puntox, int puntoy, int yoffs) 
    {
     if (n == null) 
         return;
     
     g.setColor(new Color(16,172,132));
     g.setStroke(new BasicStroke(2));
     Rectangle r = (Rectangle) posicionNodos.get(n);
     //g.draw(r);
     g.drawOval(r.x, r.y, r.width, r.height);
     g.setFont(new Font("Monospaced", Font.BOLD, 16));
     
     g.setColor(Color.BLACK);
     g.drawString(n.getLabel()+"", r.x + 12, r.y + yoffs + 7);
   
     if (puntox != Integer.MAX_VALUE)
     g.setColor(new Color(230,103,103));
     g.drawLine(puntox, puntoy, (int)(r.x + r.width/2), r.y);
     
     if(n.getChildrenSize() == 3){
    	 dibujarArbol(g, n.getChild(0), (int)(r.x + r.width/2), r.y + r.height, yoffs);
         dibujarArbol(g, n.getChild(1), (int)(r.x + r.width/2), r.y + r.height, yoffs);
         dibujarArbol(g, n.getChild(2), (int)(r.x + r.width/2), r.y + r.height, yoffs);
    }
     else if(n.getChildrenSize() == 2){
    	dibujarArbol(g, n.getChild(0), (int)(r.x + r.width/2), r.y + r.height, yoffs);
        dibujarArbol(g, n.getChild(1), (int)(r.x + r.width/2), r.y + r.height, yoffs);  
    }
     else if(n.getChildrenSize() == 1){
    	dibujarArbol(g, n.getChild(0), (int)(r.x + r.width/2), r.y + r.height, yoffs);
    }
     
     
   }
    

   /**
     * Sobreescribe el metodo paint y se encarga de pintar todo el árbol.
     * @param g: Objeto de la clase Graphics.
     */
    @Override
   public void paint(Graphics g) 
   {
         super.paint(g);
         
         fm = g.getFontMetrics();
        

         if (dirty) 
         {
           calcularPosiciones();
           dirty = false;
         }
         
         Graphics2D g2d = (Graphics2D) g;
         g2d.translate(getWidth() / 2, parent2child);
         dibujarArbol(g2d, this.miArbol, Integer.MAX_VALUE, Integer.MAX_VALUE, 
                  fm.getLeading() + fm.getAscent());
         fm = null;
   }
   
 }



