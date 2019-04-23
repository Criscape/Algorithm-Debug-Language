package com.myorg.debuglanguage.interpreter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.HashMap;

import javax.swing.JFrame;
 
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
 
public class Graph
{
	
	private JFrame ventana;
	private XYSeriesCollection dataset;
 
        public Graph() {
 
                //Informacion
 
                XYSeries funcion1 = new XYSeries("Funcion1");
                XYSeries funcion2 = new XYSeries("Funcion2");
                XYSeries funcion3 = new XYSeries("Funcion3");
                
                for(int x=0;x<10;x++){
                	funcion1.add(x,Math.pow(x, 2));
                	funcion2.add(x,Math.pow(x, 3));
                	//funcion3.add(x,Math.log(x));
                }
                
 
                dataset = new XYSeriesCollection();
                dataset.addSeries(funcion1);
                dataset.addSeries(funcion2);
                dataset.addSeries(funcion3);
 
                JFreeChart xylineChart = ChartFactory.createXYLineChart(
                                "Tiempo real vs. tiempo teoríco",
                                "Tiempo real",
                                "Tiempo teoríco",
                                dataset,
                                PlotOrientation.VERTICAL, true, true, false);
 
               
                XYPlot plot = xylineChart.getXYPlot();
               
                XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
               
                renderer.setSeriesPaint(0, Color.RED);
                renderer.setSeriesPaint(1, Color.GREEN);
                renderer.setSeriesPaint(2, Color.YELLOW);
                renderer.setSeriesStroke(0, new BasicStroke(4.0f));
                renderer.setSeriesStroke(1, new BasicStroke(3.0f));
                renderer.setSeriesStroke(2, new BasicStroke(2.0f));
                plot.setRenderer(renderer);
               
                ChartPanel panel = new ChartPanel(xylineChart);
 
                // Creamos la ventana
                ventana = new JFrame("Grafica");
                ventana.setVisible(true);
                ventana.setSize(800, 600);
                ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                
                ventana.add(panel);
 
        }
        
        public void graph(HashMap<String, Integer> map){
        	
        	dataset.removeAllSeries();
        	
        	for(String key:map.keySet()){
        		XYSeries funcion = new XYSeries(key);
            	
        		
        		for(int x=0;x<10;x++){
        			if(key.equals("while")){
        				funcion.add(x,Math.log1p(x));
        			}
        			else{
        				funcion.add(x,Math.pow(x, map.get(key)));
        			}
            	}
            	dataset.addSeries(funcion);
            }
        	
        }
        
        public int[] getFunction(String function){
        	if(function == "n2"){
        		return getn2();
        	}
        	
        	return null;
        }
        
        public int[] getlog(){
        	int [] answer = new int[10];
        	for(int x=0;x<10;x++){
            	answer[x] = (int) Math.log(x);
            }
        	
        	return answer;
        }
        
        public int[] getn(){
        	int [] answer = new int[10];
        	for(int x=0;x<10;x++){
            	answer[x] = x;
            }
        	
        	return answer;
        }
        
        public int[] getn2(){
        	int [] answer = new int[10];
        	for(int x=0;x<10;x++){
            	answer[x] = (int) Math.pow(x, 2);
            }
        	
        	return answer;
        }
        
        public int[] getn3(){
        	int [] answer = new int[10];
        	for(int x=0;x<10;x++){
            	answer[x] = (int) Math.pow(x, 3);
            }
        	
        	return answer;
        }
        
        public int[] getn4(){
        	int [] answer = new int[10];
        	for(int x=0;x<10;x++){
            	answer[x] = (int) Math.pow(x, 3);
            }
        	
        	return answer;
        }
        
        public JFrame getJframe(){
        	return this.ventana;
        }
 
}