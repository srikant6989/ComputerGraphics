/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Srikant
 */
//
//  Rasterizer.java
//  
//
//  Created by Joe Geigel on 1/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//
/**
 *
 * This is a class that performas rasterization algorithms
 *
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
    Rasterizer(int n) {
        n_scanlines = n;
    }

    /**
     * Draw a filled polygon in the simpleCanvas C.
     *
     * The polygon has n distinct vertices. The coordinates of the vertices
     * making up the polygon are stored in the x and y arrays. The ith vertex
     * will have coordinate (x[i], y[i])
     *
     * You are to add the implementation here using only calls to C.setPixel()
     */
    public void drawPolygon(int n, int x[], int y[], simpleCanvas C) {
        // YOUR IMPLEMENTATION GOES HERE
        int ymax, ymin;
        ymax = y[0];
        ymin = y[0];
        int flag[] = new int[10];

        int xmax1[] = new int[7];
        int xmin1[] = new int[7];
        xmax1[0] = x[0];
        xmin1[0] = x[0];
        for (int i = 1; i < n; i++) {
            if (y[i] > ymax) {
                ymax = y[i];
            }
            if (y[i] < ymin) {
                ymin = y[i];
            }
        }

        for (int i = 1; i < n; i++) {
            if (x[i] > xmax1[i - 1]) {
                xmax1[i - 1] = x[i];
            }
            if (x[i] < xmin1[i - 1]) {
                xmin1[i] = x[i];
            }
        }
        int globalEdgeTable[][] = new int[n][6];
        int AEL[][] = new int[n][6];
        int scanLine = ymin;
        int AELPosition = 0;
        int temp_array[][] = new int[1][1];

        for (int i = 0; i < n - 1; i++) {

            if (y[i] < y[i + 1]) {
                globalEdgeTable[i][0] = y[i];
                globalEdgeTable[i][1] = y[i + 1];
                globalEdgeTable[i][2] = x[i];
                globalEdgeTable[i][3] = x[i] - x[i + 1];
                globalEdgeTable[i][4] = y[i] - y[i + 1];
                globalEdgeTable[i][5] = 0;

            } else {
                globalEdgeTable[i][0] = y[i + 1];
                globalEdgeTable[i][1] = y[i];
                globalEdgeTable[i][2] = x[i + 1];
                globalEdgeTable[i][3] = x[i + 1] - x[i];
                globalEdgeTable[i][4] = y[i + 1] - y[i];
                globalEdgeTable[i][5] = 0;
            }
        }

        if (y[0] < y[n - 1]) {

            globalEdgeTable[n - 1][0] = y[0];
            globalEdgeTable[n - 1][1] = y[n - 1];
            globalEdgeTable[n - 1][2] = x[0];
            globalEdgeTable[n - 1][3] = x[0] - x[n - 1];
            globalEdgeTable[n - 1][4] = y[0] - y[n - 1];
            globalEdgeTable[n - 1][5] = 0;

        } else {

            globalEdgeTable[n - 1][0] = y[n - 1];
            globalEdgeTable[n - 1][1] = y[0];
            globalEdgeTable[n - 1][2] = x[n - 1];
            globalEdgeTable[n - 1][3] = x[n - 1] - x[0];
            globalEdgeTable[n - 1][4] = y[n - 1] - y[0];
            globalEdgeTable[n - 1][5] = 0;
        }

        while (scanLine < ymax) {
            for (int i = 0; i < n; i++) {
                if (globalEdgeTable[i][0] == scanLine) {
                    AEL[AELPosition] = globalEdgeTable[i];
                    flag[AELPosition] = 1;
                    AELPosition++;
                } else {
                    flag[AELPosition] = 2;
                }
            }
            for (int i = 0; i < AELPosition; i++) {
                for (int j = 1; j < AELPosition - i; j++) {
                    if ((AEL[j - 1][2] > AEL[j][2]) && (flag[j - 1] == 1)) {
                        temp_array[0] = AEL[j - 1];
                        AEL[j - 1] = AEL[j];
                        AEL[j] = temp_array[0];
                    }
                }
            }
            for (int i = 0; i < AELPosition; i++) {
                if ((scanLine == AEL[i][1]) && (flag[i] == 1)) {
                    if (flag[i + 1] == 1) {
                        for (int j = i; j < AELPosition - 1; j++) {
                            if ((flag[j + 1] == 1)) {
                                AEL[j] = AEL[j + 1];
                            }
                        }
                        AELPosition--;
                    } else {
                        AELPosition--;
                    }
                }
            }

            for (int j = 0; j < AELPosition - 1; j = j + 2) {
                for (int i = AEL[0][2]; i <= AEL[1][2]; i++) {
                    C.setPixel(i, scanLine);
                }
                for (int q = 0; q < 2; q++) {
                    int a = 0;
                    if (AEL[q][4] != 0) {
                        a = (AEL[q][3] + AEL[q][5]) / AEL[q][4];
                    }
                    int r = (AEL[q][3] + AEL[q][5]) - (a * AEL[q][4]);
                    AEL[q][2] = AEL[q][2] + a;
                    AEL[q][5] = r;
                }
            }
            scanLine++;
        }
    }
}
