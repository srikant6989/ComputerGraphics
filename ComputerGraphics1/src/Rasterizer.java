/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */

import java.util.*;

public class Rasterizer {
    
    /**
     * number of scanlines
     */
    int n_scanlines;
    
    /**
     * Constructor
     *
     * @param n - number of scanlines
     *
     */
   public Rasterizer (int n)
    {
        n_scanlines = n;
    }
    
    /**
     * Draw a line from (x0,y0) to (x1, y1) on the simpleCanvas C.
     *
     * Implementation should be using the Midpoint Method
     *
	 * You are to add the implementation here using only calls
	 * to C.setPixel()
     *
     * @param x0 - x coord of first endpoint
     * @param y0 - y coord of first endpoint
     * @param x1 - x coord of second endpoint
     * @param y1 - y coord of second endpoint
     * @param C - The canvas on which to apply the draw command.
	 */
	public void drawLine (int x0, int y0, int x1, int y1, simpleCanvas C)
	{
        // YOUR IMPLEMENTATION GOES HERE
            
            int i,j;
            
            int dU, dL;
            int dx, dy;
            int delta = 0;
            
            int temp_x, temp_y;
            
        // Swap the coordinates if x cood of 2nd point is less than 1st one
        // so as to make sure line is drawn from left to right    
            
            if(x0 > x1) {           
                
                temp_x = x0;
                x0 = x1;
                x1 = temp_x;
                
                temp_y = y0;
                y0 = y1;
                y1 = temp_y;
                
            }
            
            dx = x1 - x0;
            dy = y1 - y0;
            
           
         // For lines with slope between 0 and 1
            
            if(dy <= dx && dy >= 0) {
            
                dU = 2 * (dy - dx);
                dL = 2 * dy;
                delta = dL - dx;

                for(i=x0, j=y0; i<=x1; i++){

                    C.setPixel(i, j);

                    if(delta > 0) {

                        j++;
                        delta = delta + dU;
                    }

                    else {

                        delta = delta + dL;
                    }
                }
            }
         
         // For lines with slope greater than 1   
            
            if(dy > dx && dy > 0) {
                
                dU = 2 * dx;
                dL = 2 * (dx-dy);
                delta = dU - dy;

                for(i=x0, j=y0; j<=y1; j++){

                    C.setPixel(i, j);

                    if(delta > 0) {

                        i++;
                        delta = delta + dL;
                    }

                    else {

                        delta = delta + dU;
                    }
                }
            }
            
        // For lines with slope between 0 and -1    
            
            if(-dy <= dx && dy < 0 && dx >= 0) {
                
                dU = 2 * (dy + dx);
                dL = 2 * dy;
                delta = dL + dx;
                
                for(i = x0, j  = y0; i < x1; i++) {
                    
                    C.setPixel(i, j);
                    
                    if(delta > 0){
                                                
                        delta = delta + dL;
                    }
                    
                    else {
                        
                        j--;
                        delta = delta + dU;
                    }
                    
                    
                }
            }
            
        // For lines with slope less than -1    
            
            if(-dy > dx && dy < 0 && dx >= 0) {
                
                dU = 2 * dx ;
                dL = 2 * (dx + dy);
                delta = dy + dU;
                
                for(i = x1, j = y1; j <= y0; j++){
                    
                    C.setPixel(i, j);
                    
                    if(delta > 0) {
                                                
                        i--;
                        delta = delta + dL;
                    }
                    
                    else {
                                                            
                        delta = delta + dU;
                    }
                }
            }

    }    
      
}
