/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
/**
 * cg1Shape.java
 *
 * Class that includes routines for tessellating a number of basic shapes
 *
 * Students are to supply their implementations for the
 * functions in this file using the function "addTriangle()" to do the 
 * tessellation.
 *
 */

import java.awt.*;
import java.nio.*;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.io.*;
import java.util.Vector;


public class cg1Shape extends simpleShape
{
    private Vector<Float> normailize_variable;
    /**
	 * constructor
	 */
	public cg1Shape()
	{
            normailize_variable = new Vector<Float>();            
	}
        
       	public Buffer getNormalizeVariables() {
    	float n[] = new float[normailize_variable.size()];
        for (int i = 0; i < normailize_variable.size(); i++) {
            n[i] = (normailize_variable.elementAt(i)).floatValue();
        }
        
        return FloatBuffer.wrap(n);
    }
    
     
    /**
     * makeDefaultShape - creates a "unit" shape of your choice using your tesselation routines.
     * 
     *
     */
    public void makeDefaultShape ()
    {
        // tessellate your favorite unit shape here.
        makeCube(10);
    }
    
        public void makeCube (int subdivisions)
    {
    	if(subdivisions < 1)
        
        subdivisions = 1;
    	
    	float x, y, z; 
        float x1, x2 = 0;
        float y1, y2;
        float z1, z2;
    	float a = 1.0f / subdivisions;
    	float s1, t1, s2, t2;
    	    	
    	z = 0.5f;
    	y1 = 0.5f;
    	t1 = 1.0f;
        	
    	for(int i = 0;i < subdivisions;i++) {
    		y2 = y1 - a;
    		x1 =  -0.5f;
    		s1 = 0.0f;
    		t2 = t1 - a;
    		
    		for(int j = 0;j < subdivisions;j++) {
    			x2 = x1 + a;
    			s2 = s1 + a;
    			
    			addTriangle(x1, y2, z,s1,t2, x2, y1, z,s2,t1, x1, y1, z,s1,t1);
    			normailize_variable.add(0.0f); normailize_variable.add(0.0f); normailize_variable.add(z);
    			normailize_variable.add(0.0f); normailize_variable.add(0.0f); normailize_variable.add(z);
    			normailize_variable.add(0.0f); normailize_variable.add(0.0f); normailize_variable.add(z);
    		
    			
    			addTriangle(x1, y2, z,s1,t2 ,x2, y2, z,s2,t2, x2, y1, z,s2,t1);
    			normailize_variable.add(0.0f); normailize_variable.add(0.0f); normailize_variable.add(z);
    			normailize_variable.add(0.0f); normailize_variable.add(0.0f); normailize_variable.add(z);
    			normailize_variable.add(0.0f); normailize_variable.add(0.0f); normailize_variable.add(z);
    		
    			
    			addTriangle(x1, y2, -z,s1,t2, x1, y1, -z,s1,t1, x2, y1, -z,s2,t1);
    			normailize_variable.add(0.0f); normailize_variable.add(0.0f); normailize_variable.add(-z);
    			normailize_variable.add(0.0f); normailize_variable.add(0.0f); normailize_variable.add(-z);
    			normailize_variable.add(0.0f); normailize_variable.add(0.0f); normailize_variable.add(-z);
    			
    			addTriangle(x1, y2, -z,s1,t2, x2, y1, -z,s2,t1, x2, y2, -z,s2,t2);
    			normailize_variable.add(0.0f); normailize_variable.add(0.0f); normailize_variable.add(-z);
    			normailize_variable.add(0.0f); normailize_variable.add(0.0f); normailize_variable.add(-z);
    			normailize_variable.add(0.0f); normailize_variable.add(0.0f); normailize_variable.add(-z);
    			
    			x1 = x2;
    			s1 = s2;
    		}
    		y1 = y2;
    		t1 = t2;
    	}
        	
    	y = 0.5f;
        z1 = -0.5f;
        t1 = 0.0f;
        
    	for(int j = 0;j < subdivisions;j++) {
    		z2 = z1 + a;
    		x1 =  -0.5f;
    		t2 = t1 + a;
    		
    		s1 = 1.0f;
    		for(int k = 0;k < subdivisions;k++) {
    			x2 = x1 + a;
    			s2 = s1 - a;
    			
    			addTriangle(x1, y, z2,s1,t2, x2, y, z1,s2,t1, x1, y, z1,s1,t1);
    			normailize_variable.add(0.0f); normailize_variable.add(y); normailize_variable.add(0.0f);
    			normailize_variable.add(0.0f); normailize_variable.add(y); normailize_variable.add(0.0f);
    			normailize_variable.add(0.0f); normailize_variable.add(y); normailize_variable.add(0.0f);
    			
    			addTriangle(x1, y, z2,s1,t2, x2, y, z2,s2,t2, x2, y, z1,s2,t1);
    			normailize_variable.add(0.0f); normailize_variable.add(y); normailize_variable.add(0.0f);
    			normailize_variable.add(0.0f); normailize_variable.add(y); normailize_variable.add(0.0f);
    			normailize_variable.add(0.0f); normailize_variable.add(y); normailize_variable.add(0.0f);
    			
    			addTriangle(x1, -y, z2, s1,t2,x1, -y, z1,s1,t1, x2, -y, z1,s2,t1);
    			normailize_variable.add(0.0f); normailize_variable.add(-y); normailize_variable.add(0.0f);
    			normailize_variable.add(0.0f); normailize_variable.add(-y); normailize_variable.add(0.0f);
    			normailize_variable.add(0.0f); normailize_variable.add(-y); normailize_variable.add(0.0f);
    			
    			addTriangle(x1, -y, z2,s1,t2, x2, -y, z1,s2,t1, x2, -y, z2,s2,t2);
    			normailize_variable.add(0.0f); normailize_variable.add(-y); normailize_variable.add(0.0f);
    			normailize_variable.add(0.0f); normailize_variable.add(-y); normailize_variable.add(0.0f);
    			normailize_variable.add(0.0f); normailize_variable.add(-y); normailize_variable.add(0.0f);
    			x1 = x2;
    			s1 = s2;
    		}
    		z1 = z2;
    		t1 = t2;
    	}
    	    	
    	x = 0.5f;
    	y1 = 0.5f;	
    	t1 = 1.0f;
    	
    	for(int j = 0;j < subdivisions;j++) {
    		y2 = y1 - a;
    		z1 =  0.5f;
    		t2 = t1 - a;
    		
    		s1 = 0.0f;
    		for(int k = 0;k < subdivisions;k++) {
    			z2 = z1 - a;
    			s2 = s1 + a;
    			
    			addTriangle(x, y2, z1,s1,t2, x, y1, z2,s2,t1, x, y1, z1,s1,t1);
    			normailize_variable.add(x); normailize_variable.add(0.0f); normailize_variable.add(0.0f);
    			normailize_variable.add(x); normailize_variable.add(0.0f); normailize_variable.add(0.0f);
    			normailize_variable.add(x); normailize_variable.add(0.0f); normailize_variable.add(0.0f);
    			
    			addTriangle(x, y2, z1,s1,t2, x, y2, z2,s2,t2, x, y1, z2,s2,t1);
    			normailize_variable.add(x); normailize_variable.add(0.0f); normailize_variable.add(0.0f);
    			normailize_variable.add(x); normailize_variable.add(0.0f); normailize_variable.add(0.0f);
    			normailize_variable.add(x); normailize_variable.add(0.0f); normailize_variable.add(0.0f);
    			
    			addTriangle(-x, y2, z1,s1,t2, -x, y1, z1,s1,t1, -x, y1, z2,s2,t1);
    			normailize_variable.add(-x); normailize_variable.add(0.0f); normailize_variable.add(0.0f);
    			normailize_variable.add(-x); normailize_variable.add(0.0f); normailize_variable.add(0.0f);
    			normailize_variable.add(-x); normailize_variable.add(0.0f); normailize_variable.add(0.0f);
    			
    			addTriangle(-x, y2, z1,s1,t2, -x, y1, z2,s2,t1, -x, y2, z2,s2,t2);
    			normailize_variable.add(-x); normailize_variable.add(0.0f); normailize_variable.add(0.0f);
    			normailize_variable.add(-x); normailize_variable.add(0.0f); normailize_variable.add(0.0f);
    			normailize_variable.add(-x); normailize_variable.add(0.0f); normailize_variable.add(0.0f);
    			
    			z1 = z2;
    			s1 = s2;
    		}
    		y1 = y2;
    		t1 = t2;
    	}
    }
    

}

