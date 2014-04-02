/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */

import java.awt.*;
import java.nio.*;
import java.util.Vector;
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.io.*;


public class cg1Shape extends simpleShape
{
	final float neg=  -0.5f;
    final float pos=  0.5f;
    final float nil =0.0f;
    
    boolean ok = false;
    /**
	 * constructor
	 */
	public cg1Shape()
	{
	}
    
    public class points{ 
        float x,y,z;
        
        public points(float x1, float y1,float z1){
            setx(x1);
            sety(y1);
            setz(z1);
            
        }
        public points() {
			
		}
		
        
        public void copy(points a, points b ) {
            a.x = b.x;
            a.y = b.y;
            a.z = b.z;
        }
        
        
        public void setx(float x1) {
            
            x=x1;
        }
        
        public float getx() {
            
            return x;
        }
        
        public void sety(float y1) {
            y=y1;
        }
        
        public float gety() {
            return y;
        }
        public void setz(float z1) {
            z=z1;
        }
        
        public float getz() {
            return z;
        }
        
    }
    
    
    void drawfrontbackface(points a,points b,points c,points d, int subdivisions){ 
        if( subdivisions==1){	
            addTriangle(a.getx(),a.gety(),a.getz(),b.getx(),b.gety(),b.getz(),c.getx(),c.gety(),c.getz() );	
            addTriangle(c.getx(),c.gety(),c.getz(),b.getx(),b.gety(),b.getz(),d.getx(),d.gety(),d.getz());
            a.setz(-a.getz());				
            b.setz(-b.getz());
            c.setz(-c.getz());
            d.setz(-d.getz());
            
            addTriangle(a.getx(),a.gety(),a.getz(),c.getx(),c.gety(),c.getz() ,b.getx(),b.gety(),b.getz());	
            addTriangle(c.getx(),c.gety(),c.getz(),d.getx(),d.gety(),d.getz(),b.getx(),b.gety(),b.getz());
        }
        else{
            for(int i = 1;i <= subdivisions; i++){
                float wow= 1.0f/subdivisions;
                b.sety( (a.gety()) - (wow));	
                for(int j = 1;j <=  subdivisions; j++){
                    
                    c.setx(a.getx() + (wow));
                    addTriangle(a.getx(),a.gety(),a.getz(),b.getx(),b.gety(),b.getz(),c.getx(),c.gety(),c.getz() );	
                    
                    a.setz(-1*a.getz()); b.setz(-1*b.getz());c.setz(-1*c.getz());d.setz(-1*d.getz());
                    addTriangle(a.getx(),a.gety(),a.getz(),c.getx(),c.gety(),c.getz() ,b.getx(),b.gety(),b.getz() );
                    a.setz(-1*a.getz()); b.setz(-1*b.getz());c.setz(-1*c.getz());d.setz(-1*d.getz());
                    d.setx( c.getx());
                    d.sety( b.gety());
                    addTriangle(c.getx(),c.gety(),c.getz(),b.getx(),b.gety(),b.getz(),d.getx(),d.gety(),d.getz());				//front face
                    a.setz(-1*a.getz()); b.setz(-1*b.getz());c.setz(-1*c.getz());d.setz(-1*d.getz());
                    addTriangle(c.getx(),c.gety(),c.getz(),d.getx(),d.gety(),d.getz(),b.getx(),b.gety(),b.getz());				//back face
                    a.setz(-1*a.getz()); b.setz(-1*b.getz());c.setz(-1*c.getz());d.setz(-1*d.getz());
                    
                    a.setx(c.getx()); a.sety(c.gety());a.setz(c.getz());								//change for next column
                    b.setx(d.getx());b.sety(d.gety());b.setz(d.getz());
                }
    			
                a.setx(neg); a.sety(pos);  a.setz(pos);		
                b.setx(neg); b.sety(neg);  b.setz(pos);	
                c.setx(pos); c.sety(pos);  c.setz(pos);	
                d.setx(pos); d.sety(neg);  d.setz(pos);	
                a.sety((a.gety() - (1.0f/subdivisions * i)));	
                c.sety(a.gety()); 
                
            }
        }
    }
    
    
    void drawtopbottomface(points a,points b,points c,points d, int  subdivisions){ 
        
        
        if( subdivisions==1){
            addTriangle(a.getx(),a.gety(),a.getz(),b.getx(),b.gety(),b.getz(),c.getx(),c.gety(),c.getz() );	
            addTriangle(c.getx(),c.gety(),c.getz(),b.getx(),b.gety(),b.getz(),d.getx(),d.gety(),d.getz());
            a.sety(-a.gety());				
            b.sety(-b.gety());
            c.sety(-c.gety());
            d.sety(-d.gety());
            addTriangle(a.getx(),a.gety(),a.getz(),c.getx(),c.gety(),c.getz() ,b.getx(),b.gety(),b.getz());	
            addTriangle(c.getx(),c.gety(),c.getz(),d.getx(),d.gety(),d.getz(),b.getx(),b.gety(),b.getz());
    	}
    	else{
    		for(int i = 1;i <=  subdivisions; i++){
                float wow= 1.0f/subdivisions;
    			b.setz((a.getz())+wow);						
    			for(int j = 1;j <=  subdivisions; j++){
    				c.setx((a.getx())+ wow);				
    				addTriangle(a.getx(),a.gety(),a.getz(),b.getx(),b.gety(),b.getz(),c.getx(),c.gety(),c.getz() ); 
    				a.sety(-a.gety());				
                    b.sety(-b.gety());
                    c.sety(-c.gety());
                    d.sety(-d.gety());
                    addTriangle(a.getx(),a.gety(),a.getz(),c.getx(),c.gety(),c.getz() ,b.getx(),b.gety(),b.getz());
                    a.sety(-a.gety());				
                    b.sety(-b.gety());
                    c.sety(-c.gety());
                    d.sety(-d.gety());
    				d.setx(c.getx());
    				d.setz(b.getz());
    				addTriangle(c.getx(),c.gety(),c.getz(),b.getx(),b.gety(),b.getz(),d.getx(),d.gety(),d.getz()); 
                    a.sety(-a.gety());				
                    b.sety(-b.gety());
                    c.sety(-c.gety());
                    d.sety(-d.gety());
                    addTriangle(c.getx(),c.gety(),c.getz(),d.getx(),d.gety(),d.getz(),b.getx(),b.gety(),b.getz());
                    a.sety(-a.gety());				
                    b.sety(-b.gety());
                    c.sety(-c.gety());
                    d.sety(-d.gety());
                    a.setx(c.getx()); a.sety(c.gety());a.setz(c.getz());								//change for next column
                    b.setx(d.getx());b.sety(d.gety());b.setz(d.getz());
    			}
    		
    			a.setx(neg);  a.sety(pos);  a.setz(neg);		
                
                b.setx(neg); b.sety(pos);  b.setz(pos);	   
                c.setx(pos); c.sety(pos);  c.setz(neg);	   
                d.setx(pos); d.sety(pos);  d.setz(pos);	   
    			a.setz((a.getz() + (1.0f/subdivisions * i)));			
    			c.setz(a.getz());
    		}
    	}
    }
    
    void drawleftrightfacesofcube(points a,points b,points c,points d, int subdivisions){	
        
        if(subdivisions==1){
            addTriangle(a.getx(),a.gety(),a.getz(),b.getx(),b.gety(),b.getz(),c.getx(),c.gety(),c.getz() );	
            addTriangle(c.getx(),c.gety(),c.getz(),b.getx(),b.gety(),b.getz(),d.getx(),d.gety(),d.getz());
            a.setx(-a.getx());				
            b.setx(-b.getx());
            c.setx(-c.getx());
            d.setx(-d.getx());
            addTriangle(a.getx(),a.gety(),a.getz(),c.getx(),c.gety(),c.getz() ,b.getx(),b.gety(),b.getz());	
            addTriangle(c.getx(),c.gety(),c.getz(),d.getx(),d.gety(),d.getz(),b.getx(),b.gety(),b.getz());
    	}
    	else{
    		for(int i = 1;i <= subdivisions; i++){
                float wow= 1.0f/subdivisions;
    			b.sety((a.gety())-wow);					
    			for(int j = 1;j <= subdivisions; j++){
    				c.setz((a.getz())+wow);				
    				addTriangle(a.getx(),a.gety(),a.getz(),b.getx(),b.gety(),b.getz(),c.getx(),c.gety(),c.getz() );
                    a.setx(-a.getx());				
                    b.setx(-b.getx());
                    c.setx(-c.getx());
                    d.setx(-d.getx());
                    addTriangle(a.getx(),a.gety(),a.getz(),c.getx(),c.gety(),c.getz() ,b.getx(),b.gety(),b.getz());
                    a.setx(-a.getx());				
                    b.setx(-b.getx());
                    c.setx(-c.getx());
                    d.setx(-d.getx());
    				d.setz(c.getz());
    				d.sety( b.gety());
    				addTriangle(c.getx(),c.gety(),c.getz(),b.getx(),b.gety(),b.getz(),d.getx(),d.gety(),d.getz());
    				a.setx(-a.getx());				
                    b.setx(-b.getx());
                    c.setx(-c.getx());
                    d.setx(-d.getx());
                    addTriangle(c.getx(),c.gety(),c.getz(),d.getx(),d.gety(),d.getz(),b.getx(),b.gety(),b.getz());
                    a.setx(-a.getx());				
                    b.setx(-b.getx());
                    c.setx(-c.getx());
                    d.setx(-d.getx());
                    a.setx(c.getx()); a.sety(c.gety());a.setz(c.getz());								//change for next column
                    b.setx(d.getx());b.sety(d.gety());b.setz(d.getz());
    			}
    			a.setx(neg);  a.sety(pos);  a.setz(neg);		
                b.setx(neg); b.sety(neg);  b.setz(neg);	   
                c.setx(neg); c.sety(pos);  c.setz(pos);	   
                d.setx(neg); d.sety(neg);  d.setz(pos);	   
                
    			a.sety((a.gety() - (1.0f/subdivisions * i)));	
    			c.sety(a.gety());
    		}
    	}
    }
    
    public void makeCube (int subdivisions)
    {
        
    	points a = new points(neg,pos,pos);
    	points b = new points(neg,neg,pos);
    	points c = new points(pos,pos,pos);
    	points d = new points(pos,neg,pos);
        
        drawfrontbackface(a,b,c,d,subdivisions);	
        
        a.setx(neg); a.sety(pos);  a.setz(neg);		
        b.setx(neg); b.sety(pos);  b.setz(pos);			
        c.setx(pos); c.sety(pos);  c.setz(neg);		
        d.setx(pos); d.sety(pos);  d.setz(pos);		
        drawtopbottomface(a,b,c,d,subdivisions);	
        
        a.setx(neg); a.sety(pos);  a.setz(neg);		
        b.setx(neg); b.sety(neg);  b.setz(neg);		
        c.setx(neg); c.sety(pos);  c.setz(pos);		
        d.setx(neg); d.sety(neg);  d.setz(pos);		
        drawleftrightfacesofcube(a,b,c,d,subdivisions);	

        return;        
    }
    
 
    
    void normalize(points a ) {
        float s = (float) (1.0f / Math.sqrt(a.getx() * a.getx() + a.gety() * a.gety() + a.getz() * a.getz()));
        a.setx( a.getx()*s); a.sety( a.gety()*s);a.setz( a.getz()*s);
        a.setx(a.getx()*0.5f); a.sety(a.gety()*0.5f); a.setz(a.getz()*0.5f);
        
    }
    
    class Triangle{
        points p1;
        points p2;
        points p3;
        
        Triangle(points x1, points x2, points x3) {
            p1 = x1;
            p2 = x2;
            p3 = x3;
        }
        
		public Triangle() {
			
		}
    };
    
}