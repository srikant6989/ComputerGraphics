/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
//
//  cg1Canvas.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//
/**
 * This is a simple canvas class for adding functionality for the 2D portion of
 * Computer Graphics I.
 *
 */
import Jama.*;
import java.util.*;

class PolygonDetail {

    float x[] = new float[20];
    float y[] = new float[20];
}

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

class BucketList implements Comparable<BucketList> {

    int ymax, x, dx, dy, sum;

    public BucketList(int y_max, int current_x, int dx, int dy, int numr_sum) {
        this.set_ymax(y_max);
        this.set_x(current_x);
        this.set_dx(dx);
        this.set_dy(dy);
        this.set_num(numr_sum);
    }

    public int get_ymax() {
        return ymax;
    }

    public void set_ymax(int y_max) {
        this.ymax = y_max;
    }

    public int get_x() {
        return x;
    }

    public void set_x(int current_x) {
        this.x = current_x;
    }

    public int get_dx() {
        return dx;
    }

    public void set_dx(int dx) {
        this.dx = dx;
    }

    public int get_dy() {
        return dy;
    }

    public void set_dy(int dy) {
        this.dy = dy;
    }

    public int get_sum() {
        return sum;
    }

    public void set_num(int numr_sum) {
        this.sum = numr_sum;
    }

    public int compareTo(BucketList b) {
        if (this.x < b.get_x()) {
            return -1;
        } else if (this.x == b.get_x()) {
            return 0;
        } else {
            return 1;
        }
    }
}

public class cg1Canvas extends simpleCanvas {

    float no_vertex[];
    float x_cood[], y_cood[];
    float x_clipped[], y_clipped[];
    public static int polygon_id;
    Matrix transform;
    Matrix coordinate;
    Matrix output;
    public static byte code;
    float bottom, right, left, top;
    float xmax, xmin, ymin, ymax;
    PolygonDetail poly[];

    /**
     * Constructor
     *
     * @param w width of canvas
     * @param h height of canvas
     */
    cg1Canvas(int w, int h) {
        super(w, h);
        transform = Matrix.identity(3, 3);
        no_vertex = new float[4];
        x_cood = new float[50];
        y_cood = new float[50];
        polygon_id = 0;
        coordinate = new Matrix(3, 1);
        x_clipped = new float[100];
        y_clipped = new float[100];
        output = new Matrix(3, 1);
        poly = new PolygonDetail[4];
    }

    /**
     *
     * addPoly - Adds and stores a polygon to the canvas. Note that this method
     * does not draw the polygon, but merely stores it for later draw. Drawing
     * is initiated by the draw() method.
     *
     * Returns a unique integer id for the polygon.
     *
     * @param x - Array of x coords of the vertices of the polygon to be added.
     * @param y - Array of y coords of the vertices of the polygin to be added.
     * @param n - Number of verticies in polygon
     *
     * @return a unique integer identifier for the polygon
     */
    public int addPoly(float x[], float y[], int n) {
        poly[polygon_id] = new PolygonDetail();

        System.arraycopy(x, 0, poly[polygon_id].x, 0, x.length);
        System.arraycopy(y, 0, poly[polygon_id].y, 0, y.length);
        no_vertex[polygon_id] = n;
        return polygon_id++;
    }

    /**
     *
     * clearTransform - sets the current transformation to be the identity
     *
     */
    public void clearTransform() {
        transform = Matrix.identity(3, 3);
    }

    public void drawLine(int x0, int y0, int x1, int y1) {
        // YOUR IMPLEMENTATION GOES HERE

        int i, j;

        int dU, dL;
        int dx, dy;
        int delta = 0;

        int temp_x, temp_y;

        // Swap the coordinates if x cood of 2nd point is less than 1st one
        // so as to make sure line is drawn from left to right    

        if (x0 > x1) {

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

        if (dy <= dx && dy >= 0) {

            dU = 2 * (dy - dx);
            dL = 2 * dy;
            delta = dL - dx;

            for (i = x0, j = y0; i <= x1; i++) {

                setPixel(i, j);

                if (delta > 0) {

                    j++;
                    delta = delta + dU;
                } else {

                    delta = delta + dL;
                }
            }
        }

        // For lines with slope greater than 1   

        if (dy > dx && dy > 0) {

            dU = 2 * dx;
            dL = 2 * (dx - dy);
            delta = dU - dy;

            for (i = x0, j = y0; j <= y1; j++) {

                setPixel(i, j);

                if (delta > 0) {

                    i++;
                    delta = delta + dL;
                } else {

                    delta = delta + dU;
                }
            }
        }

        // For lines with slope between 0 and -1    

        if (-dy <= dx && dy < 0 && dx >= 0) {

            dU = 2 * (dy + dx);
            dL = 2 * dy;
            delta = dL + dx;

            for (i = x0, j = y0; i < x1; i++) {

                setPixel(i, j);

                if (delta > 0) {

                    delta = delta + dL;
                } else {

                    j--;
                    delta = delta + dU;
                }


            }
        }

        // For lines with slope less than -1    

        if (-dy > dx && dy < 0 && dx >= 0) {

            dU = 2 * dx;
            dL = 2 * (dx + dy);
            delta = dy + dU;

            for (i = x1, j = y1; j <= y0; j++) {

                setPixel(i, j);

                if (delta > 0) {

                    i--;
                    delta = delta + dL;
                } else {

                    delta = delta + dU;
                }
            }
        }

    }

    public void drawPolygon(int n, float x[], float y[]) {
        // YOUR IMPLEMENTATION GOES HERE
        int ymax, ymin;
        ymax = (int) y[0];
        ymin = (int) y[0];
        int flag[] = new int[10];

//        int xmax1[] = new int[6];
//        int xmin1[] = new int[6];
//        xmax1[0] = (int)x[0];
//        xmin1[0] = (int)x[0];
        for (int i = 1; i < n; i++) {
            if (y[i] > ymax) {
                ymax = (int) y[i];
            }
            if (y[i] < ymin) {
                ymin = (int) y[i];
            }
        }
//
//        for (int i = 1; i < n; i++) {
//            if (x[i] > xmax1[i - 1]) {
//                xmax1[i - 1] = (int)x[i];
//            }
//            if (x[i] < xmin1[i - 1]) {
//                xmin1[i] = (int)x[i];
//            }
//        }

        LinkedList<BucketList>[] globalEdgeTable = null;
        globalEdgeTable = (LinkedList<BucketList>[]) new LinkedList[ymax + 1];

        int ymax_new = 0, ymin_new = 0;
        int x_new = 0;
        int dx_new = 0, dy_new = 0;
        for (int i = 0; i < n - 1; i++) {

            if (y[i] > y[i + 1]) {
                ymax_new = (int) y[i];
                ymin_new = (int) y[i + 1];
                x_new = (int) x[i + 1];
                dx_new = (int) (x[i] - x[i + 1]);
                dy_new = (int) (y[i] - y[i + 1]);
            } else if (y[i] == y[i + 1]) {
                continue;
            } else {
                ymax_new = (int) y[i + 1];
                ymin_new = (int) y[i];
                x_new = (int) x[i];
                dx_new = (int) (x[i + 1] - x[i]);
                dy_new = (int) (y[i + 1] - y[i]);
            }
            if (globalEdgeTable[ymin_new] == null) {
                globalEdgeTable[ymin_new] = new LinkedList<BucketList>();
                globalEdgeTable[ymin_new].add(new BucketList(ymax_new, x_new, dx_new, dy_new, 0));
            } else {
                globalEdgeTable[ymin_new].add(new BucketList(ymax_new, x_new, dx_new, dy_new, 0));
            }
        }

        if (y[n - 1] > y[0]) {
            ymax_new = (int) y[n - 1];
            ymin_new = (int) y[0];
            x_new = (int) x[0];
            dx_new = (int) (x[n - 1] - x[0]);
            dy_new = (int) (y[n - 1] - y[0]);

            if (globalEdgeTable[ymin_new] == null) {
                globalEdgeTable[ymin_new] = new LinkedList<BucketList>();
                globalEdgeTable[ymin_new].add(new BucketList(ymax_new, x_new, dx_new, dy_new, 0));
            } else {
                globalEdgeTable[ymin_new].add(new BucketList(ymax_new, x_new, dx_new, dy_new, 0));
            }
        } else if (y[n - 1] < y[0]) {
            ymax_new = (int) y[0];
            ymin_new = (int) y[n - 1];
            x_new = (int) x[n - 1];
            dx_new = (int) (x[0] - x[n - 1]);
            dy_new = (int) (y[0] - y[n - 1]);

            if (globalEdgeTable[ymin_new] == null) {
                globalEdgeTable[ymin_new] = new LinkedList<BucketList>();
                globalEdgeTable[ymin_new].add(new BucketList(ymax_new, x_new, dx_new, dy_new, 0));
            } else {
                globalEdgeTable[ymin_new].add(new BucketList(ymax_new, x_new, dx_new, dy_new, 0));
            }
        }
        LinkedList<BucketList> AEL = new LinkedList<BucketList>();

        int scanLine = ymin;
        while (globalEdgeTable != null || AEL.size() != 0) {

            ListIterator<BucketList> iter = AEL.listIterator(0);
            while (iter.hasNext()) {
                if (scanLine == iter.next().get_ymax()) {
                    iter.remove();
                }
            }
            if (scanLine <= ymax) {
                if (globalEdgeTable[scanLine] != null) {
                    while (globalEdgeTable[scanLine].size() != 0) {
                        AEL.add(globalEdgeTable[scanLine].removeFirst());
                    }
                    globalEdgeTable[scanLine] = null;
                }
            } else {
                globalEdgeTable = null;
            }

            if (AEL != null) {

                Collections.sort(AEL);

                for (int j = 0; j < AEL.size(); j = j + 2) {
                    drawLine(AEL.get(j).get_x(), scanLine, AEL.get(j + 1).get_x(), scanLine);
                }
                scanLine++;

                iter = AEL.listIterator(0);
                while (iter.hasNext()) {
                    BucketList temp = iter.next();
                    int dx = temp.get_dx(), numr_sum = temp.get_sum(), current_x = temp.get_x();
                    int dy = temp.get_dy();

                    current_x += (dx + numr_sum) / dy;
                    numr_sum = (dx + numr_sum) % dy;
                    iter.previous().set_x(current_x);
                    iter.next().set_num(numr_sum);
                }
            }
        }
    }

    public int clipPolygon(float in, float inx[], float iny[], float outx[],
            float outy[], float x0, float y0, float x1, float y1) {

        int outputVerticesCount = 0;
        int in1 = (int) in;
        Coordinates lowerLeft = new Coordinates(x0, y0);
        Coordinates upperRight = new Coordinates(x1, y1);
        Coordinates s = null;
        Coordinates e = null;
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

            if (in1 > 1) {
                s = new Coordinates(inx1[in1 - 1], iny1[in1 - 1]);
            }
            outputVerticesCount = 0;

            for (int i = 0; i < in1; i++) {
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

            System.arraycopy(outx, 0, inx1, 0, outputVerticesCount);
            System.arraycopy(outy, 0, iny1, 0, outputVerticesCount);

            in1 = outputVerticesCount;
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

    public void acces_cood(float x, float y) {

        coordinate.set(0, 0, x);
        coordinate.set(1, 0, y);
        coordinate.set(2, 0, 1);
    }

    public void mutate_cood(Matrix output, int ab) {
        x_cood[ab] = (float) output.get(0, 0);
        y_cood[ab] = (float) output.get(1, 0);
    }

    /**
     *
     * draw - Draw the polygon with the given id. Draw should draw the polygon
     * after applying the current transformation on the vertices of the polygon.
     *
     * @param polyID - the ID of the polygin to be drawn.
     */
    public void draw(int polyID) {
        float vertex_count = no_vertex[polyID];

        System.arraycopy(poly[polyID].x, 0, x_cood, 0, poly[polyID].x.length);
        System.arraycopy(poly[polyID].y, 0, y_cood, 0, poly[polyID].y.length);


        for (int ab = 0; ab < vertex_count; ab++) {
            this.acces_cood(x_cood[ab], y_cood[ab]);
            output = transform.times(coordinate);
            this.mutate_cood(output, ab);

        }
        vertex_count = this.clipPolygon(no_vertex[polyID], x_cood, y_cood, x_clipped, y_clipped, left, bottom, right, top);
        Matrix transform_mat = new Matrix(3, 3);
        transform_mat = Matrix.identity(3, 3);
        float sx = (xmax - xmin) / (right - left);
        float sy = (ymax - ymin) / (top - bottom);
        float tx = ((right * xmin) - (left * xmax)) / (right - left);
        float ty = ((top * ymin) - (bottom * ymax)) / (top - bottom);

        transform_mat.set(0, 0, sx);
        transform_mat.set(0, 2, tx);
        transform_mat.set(1, 1, sy);
        transform_mat.set(1, 2, ty);


        for (int loop = 0; loop < vertex_count; loop++) {
            this.acces_cood(x_clipped[loop], y_clipped[loop]);
            output = transform_mat.times(coordinate);
            this.mutate_cood(output, loop);

        }
        drawPolygon((int) no_vertex[polyID], x_cood, y_cood);
    }

    /**
     *
     * rotate - Add a rotation to the current transformation by pre-multiplying
     * the appropriate rotation matrix to the current transformation matrix.
     *
     * @param degrees - Amount of rotation in degrees.
     *
     */
    public void rotate(float degrees) {
        Matrix init_mat = new Matrix(3, 3);
        init_mat = Matrix.identity(3, 3);

        double Rad = Math.toRadians(degrees);
        double sin = Math.sin(Rad);
        double cos = Math.cos(Rad);

        init_mat.set(0, 0, cos);
        init_mat.set(1, 1, cos);
        init_mat.set(1, 0, sin);
        init_mat.set(0, 1, -sin);

        transform = init_mat.times(transform);
    }

    /**
     *
     * scale - Add a scale to the current transformation by pre-multiplying the
     * appropriate scaling matrix to the current transformation matrix.
     *
     * @param x - Amount of scaling in x.
     * @param y - Amount of scaling in y.
     *
     */
    public void scale(float x, float y) {
        Matrix init_mat = new Matrix(3, 3);
        init_mat = Matrix.identity(3, 3);
        init_mat.set(0, 0, x);
        init_mat.set(1, 1, y);
        transform = init_mat.times(transform);
    }

    /**
     *
     * setClipWindow - defines the clip window
     *
     * @param bottom - y coord of bottom edge of clip window (in world coords)
     * @param top - y coord of top edge of clip window (in world coords)
     * @param left - x coord of left edge of clip window (in world coords)
     * @param right - x coord of right edge of clip window (in world coords)
     *
     */
    public void setClipWindow(float bottom, float top, float left, float right) {
        this.left = left;
        this.bottom = bottom;
        this.right = right;
        this.top = top;
    }

    /**
     *
     * setViewport - defines the viewport
     *
     * @param xmin - x coord of lower left of view window (in screen coords)
     * @param ymin - y coord of lower left of view window (in screen coords)
     * @param width - width of view window (in world coords)
     * @param height - width of view window (in world coords)
     *
     */
    public void setViewport(int x, int y, int width, int height) {
        xmin = x;
        xmax = x + width;
        ymax = y + height;
        ymin = y;
    }

    /**
     *
     * translate - Add a translation to the current transformation by
     * pre-multiplying the appropriate translation matrix to the current
     * transformation matrix.
     *
     * @param x - Amount of translation in x.
     * @param y - Amount of translation in y.
     *
     */
    public void translate(float x, float y) {
        Matrix init_mat = new Matrix(3, 3);
        init_mat = Matrix.identity(3, 3);
        init_mat.set(0, 2, x);
        init_mat.set(1, 2, y);
        transform = init_mat.times(transform);
    }
}
