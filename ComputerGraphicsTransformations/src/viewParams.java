///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//
///**
// *
// * @author Srikant
// */
///**
// *
// * viewParams.java
// *
// * Simple class for setting up the viewing and projection transforms for the Transformation
// * Assignment.
// *
// * Students are to complete this class.
// *
// */
//
//
//import javax.media.opengl.*;
//import javax.media.opengl.fixedfunc.*; 

import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*; 

public class viewParams
{
    
	/**
	 * constructor
	 */
	public viewParams()
	{
        
	}
    
    
    /**
     * This functions sets up the view and projection parameter for a frustrum
     * projection of the scene. See the assignment description for the values
     * for the projection parameters.
     *
     * You will need to write this function, and maintain all of the values needed
     * to be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) program to which parameter values
     *    are to be sent
     *
     * @param gl2 - GL2 object on which all OpenGL calls are to be made
     *
     */
    public void setUpFrustrum (int program, GL2 gl2)
    {
    	  
       this.modelTrans(program, gl2);
       this.viewTrans(program, gl2);
    	int view_id=gl2.glGetUniformLocation(program,"view_id");
   	 gl2.glUniform1f(view_id, 1.0f);
    	
    }
    
    
    public void modelTrans (int program, GL2 gl2)
    {
    	float[] angles=new float[3];
        angles[0]=(float) 0.0;
        angles[1]=(float) 50.0;
        angles[2]=(float) 90.0;
        
        float[] translate=new float[3];
        translate[0]=(float)1.0;
        translate[1]=(float)0.0;
        translate[2]=(float)-1.0;
        
        float[] scales=new float[3];
        scales[0]=(float)1.0;
        scales[1]=(float)4.0;
        scales[2]=(float)1.0;
        int theta = gl2.glGetUniformLocation (program, "theta");
		gl2.glUniform3fv (theta, 1, angles, 0);
		int transform = gl2.glGetUniformLocation (program, "trans");
		gl2.glUniform3fv (transform, 1, translate, 0);
		int scale = gl2.glGetUniformLocation (program, "scale");
		gl2.glUniform3fv (scale, 1, scales, 0);
		
    }
    
  public void viewTrans(int program, GL2 gl2)
  {

    float[] camera_set=new float[3];
    camera_set[0]=(float)0.0;
    camera_set[1]=(float)3.0;
    camera_set[2]=(float)3.0;
    
    float[] look_at=new float[3];
    look_at[0]=(float)1.0;
    look_at[1]=(float)0.0;
    look_at[2]=(float)0.0;
    
    float[] up1=new float[3];
    up1[0]=(float)0.0;
    up1[1]=(float)1.0;
    up1[2]=(float)0.0;
   int eye = gl2.glGetUniformLocation (program, "eye");
	gl2.glUniform3fv (eye, 1, camera_set, 0);
	int lookat = gl2.glGetUniformLocation (program, "lookat");
	gl2.glUniform3fv (lookat, 1, look_at, 0);
	int up = gl2.glGetUniformLocation (program, "up");
	gl2.glUniform3fv(up, 1, up1, 0);

  } 
    
    /**
     * This functions sets up the view and projection parameter for an orthographic
     * projection of the scene. See the assignment description for the values
     * for the projection parameters.
     *
     * You will need to write this function, and maintain all of the values needed
     * to be sent to the vertex shader.
     *
     * @param program - The ID of an OpenGL (GLSL) program to which parameter values
     *    are to be sent
     *
     * @param gl2 - GL2 object on which all OpenGL calls are to be made
     *
     */
    public void setUpOrtho (int program, GL2 gl2)
    {

        this.modelTrans(program, gl2);
        this.viewTrans(program, gl2);
    	 
    	 int view_id=gl2.glGetUniformLocation(program,"view_id");
    	 gl2.glUniform1f(view_id, 2.0f);
    	 
    }

	
}
