/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
//
//  simpleCanvas.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

/**
 * This is a simple class to allow pixel level drawing in
 * java without using OpenGL
 *
 * techniques for pixel drawing taken from:
 *
 *   http://www.cap-lore.com/code/java/JavaPix.java
 *
 *  with my thanks.
 */
import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.util.*;

public class simpleCanvas extends Canvas {
	
	public BufferedImage I;
	private int	clearC;
	private int width;
	private int height;
	private Color curColor;
	
	private LinkedList<Polygon> poly_draw;
	private LinkedList<Color> poly_draw_color;

	simpleCanvas (int w, int h) 
	{
		width = w;
		height = h;
		I = new BufferedImage (width, height, BufferedImage.TYPE_INT_RGB);
		curColor = new Color (0.0f, 0.0f, 0.0f);
				
		poly_draw = new LinkedList<Polygon>();
		poly_draw_color = new LinkedList<Color>();
		
		setSize (w, h);

	}
	
	
	public void clear()
	{
		for (int i=0; i < width; i++)
			for (int j=0; j < height; j++)
				I.setRGB (i,j, curColor.getRGB());
        
        poly_draw.clear();
        poly_draw_color.clear();
	}
	
	public void setColor (float r, float g, float b)
	{
		curColor = new Color (r,g, b);
	}
	
    /**
     * ONLY USE THIS VERSION OF drawPoly() if you were not 
     * able to create a working drawPoly() Rasterizer routine of your
     * own.
     *
     * This method will only draw the outline of the polygon
     */
    public void drawPoly (int n, int x[], int y[] )
	{
		// Need to swap the y component
		Polygon P = new Polygon();
		for (int i=0; i < n; i++) P.addPoint (x[i],height - y[i]);
		poly_draw.add (P);
		poly_draw_color.add (curColor);
	}

    
	
	protected void setPixel (int x, int y)
	{
		I.setRGB (x, (height - y - 1), curColor.getRGB());
		
	}
	
	public void paint(Graphics g)
    {
		// draw pixels
        g.drawImage(I, 0, 0, Color.red, null);
        
		// draw polys
		ListIterator<Polygon> II = poly_draw.listIterator(0);
		ListIterator<Color> CC = poly_draw_color.listIterator(0);
		while (II.hasNext()) {
			Polygon P = II.next();
			Color C = CC.next();
			g.setColor (C);
			g.drawPolygon (P);
		}
		
    }	

}
