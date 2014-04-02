/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
//
//  Clipper.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//
/**
 * Object for performing clipping
 *
 */
class Coordinates {

    float x;
    float y;

    public Coordinates() {
        x = 0;
        y = 0;
    }

    public Coordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }

    void setX(float X) {
        x = X;
    }

    void setY(float Y) {
        y = Y;
    }
}

public class clipper {

    /**
     * clipPolygon
     *
     * Clip the polygon with vertex count in and vertices inx/iny against the
     * rectangular clipping region specified by lower-left corner (x0,y0) and
     * upper-right corner (x1,y1). The resulting vertices are placed in
     * outx/outy.
     *
     * The routine should return the with the vertex count of polygon resultinhg
     * from the clipping.
     *
     * @param in the number of vertices in the polygon to be clipped
     * @param inx - x coords of vertices of polygon to be clipped.
     * @param int - y coords of vertices of polygon to be clipped.
     * @param outx - x coords of vertices of polygon resulting after clipping.
     * @param outy - y coords of vertices of polygon resulting after clipping.
     * @param x0 - x coord of lower left of clipping rectangle.
     * @param y0 - y coord of lower left of clipping rectangle.
     * @param x1 - x coord of upper right of clipping rectangle.
     * @param y1 - y coord of upper right of clipping rectangle.
     *
     * @return number of vertices in the polygon resulting after clipping
     *
     */
//    public int intersectX;  
//    public int intersectY;  
//
//    public static final int top  = 0;  
//    public static final int left = 1;  
//    public static final int bottom  = 2;  
//    public static final int right  = 3;  
    
    public static byte code;
    public int clipPolygon(int in, float inx[], float iny[], float outx[],
            float outy[], float x0, float y0, float x1, float y1) {

        int outputVerticesCount = 0;
        Coordinates lowerLeft = new Coordinates(x0, y0); Coordinates upperRight = new Coordinates(x1, y1);     
        Coordinates s = null; Coordinates e = null;
        Coordinates temp_coordinates;

        int lengthX = outx.length;
        int lengthY = outx.length;
        int inLengthX = inx.length;
        int inLengthY = inx.length;

        float[] inx1 = new float[lengthX];
        float[] iny1 = new float[lengthY];

        System.arraycopy(inx, 0, inx1, 0, inLengthX);
        System.arraycopy(iny, 0, iny1, 0, inLengthY);

        byte scode, ecode;

        for (byte a = 8; a >= 1; a /= 2) {

            if (in > 1) {
                s = new Coordinates(inx1[in - 1], iny1[in - 1]);
            }
            outputVerticesCount = 0;

            for (int i = 0; i < in; i++) {
                e = new Coordinates(inx1[i], iny1[i]);
                scode = generateCode(s, lowerLeft, upperRight);
                ecode = generateCode(e, lowerLeft, upperRight);

                if ((ecode & a) != 0) {
                    if ((scode & a) == 0) {
                        temp_coordinates = intersect(s, e, lowerLeft, upperRight, ecode);
                        outx[outputVerticesCount] = temp_coordinates.x;
                        outy[outputVerticesCount++] = temp_coordinates.y;
                    }
                } else {
                    if ((scode & a) != 0) {
                        temp_coordinates = intersect(e, s, lowerLeft, upperRight, scode);
                        outx[outputVerticesCount] = temp_coordinates.x;
                        outy[outputVerticesCount++] = temp_coordinates.y;
                    }
                    outx[outputVerticesCount] = e.x;
                    outy[outputVerticesCount++] = e.y;

                }

                s = e;
            }

            System.arraycopy(outx, 0, inx1, 0, outputVerticesCount); System.arraycopy(outy, 0, iny1, 0, outputVerticesCount);
            
            in = outputVerticesCount;
        }
        return outputVerticesCount;     // should return number of verricies in clipped poly.
    }

    Coordinates intersect(Coordinates e1, Coordinates s1, Coordinates lowerleft, Coordinates upperRight, byte scode1) {
        Coordinates intersect_coordinate = new Coordinates(s1.x, s1.y);

        if ((scode1 & 8) != 0) {           // code for top edge = 8  
            if (s1.y - e1.y != 0) {
                intersect_coordinate.setX(e1.x + (s1.x - e1.x) * (upperRight.y - e1.y) / (s1.y - e1.y));
            } else {
                intersect_coordinate.setX(e1.x + (s1.x - e1.x) * (upperRight.y - e1.y) / 1000000);
            }
            intersect_coordinate.setY(upperRight.y);
        } else if ((scode1 & 4) != 0) {      // code for bottom edge = 4 
            if (s1.y - e1.y != 0) {
                intersect_coordinate.setX(e1.x + (s1.x - e1.x) * (lowerleft.y - e1.y) / (s1.y - e1.y));
            } else {
                intersect_coordinate.setX(e1.x + (s1.x - e1.x) * (lowerleft.y - e1.y) / 1000000);
            }
            intersect_coordinate.setY(lowerleft.y);
        } else if ((scode1 & 2) != 0) {  // code for right edge = 2
            if (s1.x - e1.x != 0) {
                intersect_coordinate.setY(e1.y + (s1.y - e1.y) * (upperRight.x - e1.x) / (s1.x - e1.x));
            } else {
                intersect_coordinate.setY(e1.y + (s1.y - e1.y) * (upperRight.x - e1.x) / 1000000);
            }
            intersect_coordinate.setX(upperRight.x);
        } else if ((scode1 & 1) != 0) {  // code for left edge = 1
            if (s1.x - e1.x != 0) {
                intersect_coordinate.setY(e1.y + (s1.y - e1.y) * (lowerleft.x - e1.x) / (s1.x - e1.x));
            } else {
                intersect_coordinate.setY(e1.y + (s1.y - e1.y) * (lowerleft.x - e1.x / 1000000));
            }
            intersect_coordinate.setX(lowerleft.x);
        }

        return intersect_coordinate;
    }

    byte generateCode(Coordinates temp_coordinate, Coordinates lowerLeft1, Coordinates upperRight1) {

        if (temp_coordinate.y > upperRight1.y) {
            code = 8;
            if (temp_coordinate.x > upperRight1.x) {
                code += 2;
            } else if (temp_coordinate.x < lowerLeft1.x) {
                code++;
            }
        } else if (temp_coordinate.y < lowerLeft1.y) {
            code = 4;
            if (temp_coordinate.x > upperRight1.x) {
                code += 2;
            } else if (temp_coordinate.x < lowerLeft1.x) {
                code++;
            }
        } else {
            code = 0;
            if (temp_coordinate.x > upperRight1.x) {
                code += 2;
            } else if (temp_coordinate.x < lowerLeft1.x) {
                code++;
            }
        }
        return code;
    }
}
