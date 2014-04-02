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
import java.awt.event.*;
import javax.media.opengl.*;
import javax.media.opengl.awt.GLCanvas;
import java.util.*;


public class simpleShape
{
    /**
     * our vertex points
     */
    private Vector<Float> points;
    private float u[] = new float[3];
    private float v[] = new float[3];    
    private Vector<Float> normals;
    private Vector<Float> textures;
    double pi= Math.PI;
    
    /**
     * our array elements
     */
    private Vector<Short> elements;
    private short nVerts;
		private Vector<Float> uv;
	/**
	 * constructor
	 */
	public simpleShape()
	{
        points = new Vector<Float>();
        elements = new Vector<Short>();
        normals = new Vector<Float>();
        textures = new Vector<Float>();
		uv = new Vector<Float>();
        nVerts = 0;
	}
    
    /**
     * add a triangle to the shape
     */
    protected void addTriangle (float x0, float y0, float z0,
                                float x1, float y1, float z1,
                                float x2, float y2, float z2)
    {
        //calculating points
        u[0] = x1-x0;
        u[1] = y1-y0;
        u[2] = z1-z0;
        
        v[0] = x2-x0;
        v[1] = y2-y0;
        v[2] = z2-z0;
        
        double x = u[1]*v[2] - u[2]*v[1];
        double y = u[2]*v[0] - u[0]*v[2];
        double z = u[0]*v[1] - u[1]*v[0];
        
        double j= x*x + y*y + z*z;
        double k = Math.sqrt(j);
        
        
        x/=k;
        y/=k;
        z/=k;
        
                
        for(int i=0;i<3;i++) {
            normals.add((float)x);
            normals.add((float)y);
            normals.add((float)z);
        }

        // calculating texture coords
       
        float a = (float)(Math.asin(x0)/(2.0f * pi) + 0.5f);
        float b = (float)(Math.asin(y0)/pi + 0.5f);
        float c = (float)(Math.asin(x1)/(2.0f * pi) + 0.5f);
        float d = (float)(Math.asin(y1)/pi + 0.5f);
        float e = (float)(Math.asin(x2)/(2.0f * pi) + 0.5f);
        float f = (float)(Math.asin(y2)/Math.PI + 0.5f);  
       
            textures.add(a);
            textures.add(b);
            textures.add(c);
            textures.add(d);
            textures.add(e);
            textures.add(f);
            
        points.add (new Float(x0));
        points.add (new Float(y0));
        points.add (new Float(z0));
        points.add (new Float(1.0f));
        elements.add (new Short(nVerts));
        nVerts++;
        
        points.add (new Float(x1));
        points.add (new Float(y1));
        points.add (new Float(z1));
        points.add (new Float(1.0f));
        elements.add (new Short(nVerts));
        nVerts++;
        
        points.add (new Float(x2));
        points.add (new Float(y2));
        points.add (new Float(z2));
        points.add (new Float(1.0f));
        elements.add (new Short(nVerts));
        nVerts++;
    }
    
    /**
     * clear the shape
     */
    public void clear()
    {
        points= new Vector<Float>(); 
        elements = new Vector<Short>();
        nVerts = 0;
    }
    
    public Buffer getVerticies ()
    {
        float v[] = new float[points.size()];
        for (int i=0; i < points.size(); i++) {
            v[i] = (points.elementAt(i)).floatValue();
        }
        return FloatBuffer.wrap (v);
    }
    
    public Buffer getNormals()
    {
        float v[] = new float[normals.size()];
        for (int i=0; i < normals.size(); i++) {
            v[i] = (normals.elementAt(i)).floatValue();
        }
        return FloatBuffer.wrap (v);
    }

    public Buffer getTextureFloat() {
        float v[] = new float[textures .size()];
        for (int i=0; i < textures.size(); i++) {
            v[i] = (textures.elementAt(i)).floatValue();
        }
        return FloatBuffer.wrap (v);
    }
        
    
    public Buffer getElements ()
    {
        short e[] = new short[elements.size()];
        for (int i=0; i < elements.size(); i++) {
            e[i] = (elements.elementAt(i)).shortValue();
        }

        return ShortBuffer.wrap (e);
    }
    
    public short getNVerts()
    {
        return nVerts;
    }
	protected void addTriangle2 (float x0, float y0, float z0, float u0, float v0,
                                float x1, float y1, float z1, float u1, float v1,
                                float x2, float y2, float z2, float u2, float v2)
    {
        points.add (new Float(x0));
        points.add (new Float(y0));
        points.add (new Float(z0));
        points.add (new Float(1.0f));
        elements.add (new Short(nVerts));
        nVerts++;
        
        points.add (new Float(x1));
        points.add (new Float(y1));
        points.add (new Float(z1));
        points.add (new Float(1.0f));
        elements.add (new Short(nVerts));
        nVerts++;
        
        points.add (new Float(x2));
        points.add (new Float(y2));
        points.add (new Float(z2));
        points.add (new Float(1.0f));
        elements.add (new Short(nVerts));
        nVerts++;
        
        // calculate the normal
        float ux = x1 - x0;
        float uy = y1 - y0;
        float uz = z1 - z0;
        
        float vx = x2 - x0;
        float vy = y2 - y0;
        float vz = z2 - z0;
        
        float nx = (uy * vz) - (uz * vy);
        float ny = (uz * vx) - (ux * vz);
        float nz = (ux * vy) - (uy * vx);
        
        // Attach the normal to all 3 vertices
        for (int i=0; i < 3; i++) {
            normals.add (new Float (nx));
            normals.add (new Float (ny));
            normals.add (new Float (nz));
        }
        
        // Attach the texture coords
        uv.add (new Float (u0));
        uv.add (new Float (v0));
        uv.add (new Float (u1));
        uv.add (new Float (v1));
        uv.add (new Float (u2));
        uv.add (new Float (v2));
    }
	public Buffer getUV ()
    {
        float v[] = new float[uv.size()];
        for (int i=0; i < uv.size(); i++) {
            v[i] = (uv.elementAt(i)).floatValue();
        }
        return FloatBuffer.wrap (v);
    }
    
    
	
}
