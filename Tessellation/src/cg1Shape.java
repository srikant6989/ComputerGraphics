///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
///**
// *
// * @author Srikant
// */
///**
// * cg1Shape.java
// *
// * Class that includes routines for tessellating a number of basic shapes
// *
// * Students are to supply their implementations for the functions in this file
// * using the function "addTriangle()" to do the tessellation.
// *
// */
//import java.awt.*;
//import java.nio.*;
//import java.awt.event.*;
//import javax.media.opengl.*;
//import javax.media.opengl.awt.GLCanvas;
//import java.io.*;
//import java.util.Vector;
//
//class Coordinates {
//
//    float x;
//    float y;
//    float z;
//
//    public Coordinates(float x1, float y1, float z1) {
//        setx(x1);
//        sety(y1);
//        setz(z1);
//    }
//
//    public Coordinates() {
//    }
//
//    public void swap_value(Coordinates x1, Coordinates y1) {
//        x1.x = y1.x;
//        x1.y = y1.y;
//        x1.z = y1.z;
//    }
//
//    public void setx(float x1) {
//
//        x = x1;
//    }
//
//    public float getx() {
//
//        return x;
//    }
//
//    public void sety(float y1) {
//        y = y1;
//    }
//
//    public float gety() {
//        return y;
//    }
//
//    public void setz(float z1) {
//        z = z1;
//    }
//
//    public float getz() {
//        return z;
//    }
//}
//
//class Triangle {
//
//    Coordinates p1;
//    Coordinates p2;
//    Coordinates p3;
//
//    Triangle(Coordinates x1, Coordinates x2, Coordinates x3) {
//        p1 = x1;
//        p2 = x2;
//        p3 = x3;
//    }
//}
//
//public class cg1Shape extends simpleShape {
//
//    final float negative_constant = -0.5f, positive_constant = 0.5f;
//    final float zero = 0.0f;
//
//    /**
//     * constructor
//     */
//    public cg1Shape() {
//    }
//
//    void drawFace_front_back(Coordinates x1, Coordinates x2, Coordinates x3, Coordinates x4, int subdivisions) {
//        if (subdivisions == 1) {
//            addTriangle(x1.getx(), x1.gety(), x1.getz(), x2.getx(), x2.gety(), x2.getz(), x3.getx(), x3.gety(), x3.getz());
//            addTriangle(x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz(), x4.getx(), x4.gety(), x4.getz());
//            x1.setz(-x1.getz());
//            x2.setz(-x2.getz());
//            x3.setz(-x3.getz());
//            x4.setz(-x4.getz());
//
//            addTriangle(x1.getx(), x1.gety(), x1.getz(), x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz());
//            addTriangle(x3.getx(), x3.gety(), x3.getz(), x4.getx(), x4.gety(), x4.getz(), x2.getx(), x2.gety(), x2.getz());
//        } else {
//            for (int i = 1; i <= subdivisions; i++) {
//                float constant = 1.0f / subdivisions;
//                x2.sety((x1.gety()) - (constant));
//                for (int j = 1; j <= subdivisions; j++) {
//
//                    x3.setx(x1.getx() + (constant));
//                    addTriangle(x1.getx(), x1.gety(), x1.getz(), x2.getx(), x2.gety(), x2.getz(), x3.getx(), x3.gety(), x3.getz());
//
//                    x1.setz(-1 * x1.getz());
//                    x2.setz(-1 * x2.getz());
//                    x3.setz(-1 * x3.getz());
//                    x4.setz(-1 * x4.getz());
//                    addTriangle(x1.getx(), x1.gety(), x1.getz(), x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz());
//                    x1.setz(-1 * x1.getz());
//                    x2.setz(-1 * x2.getz());
//                    x3.setz(-1 * x3.getz());
//                    x4.setz(-1 * x4.getz());
//                    x4.setx(x3.getx());
//                    x4.sety(x2.gety());
//                    addTriangle(x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz(), x4.getx(), x4.gety(), x4.getz());
//                    x1.setz(-1 * x1.getz());
//                    x2.setz(-1 * x2.getz());
//                    x3.setz(-1 * x3.getz());
//                    x4.setz(-1 * x4.getz());
//                    addTriangle(x3.getx(), x3.gety(), x3.getz(), x4.getx(), x4.gety(), x4.getz(), x2.getx(), x2.gety(), x2.getz());
//                    x1.setz(-1 * x1.getz());
//                    x2.setz(-1 * x2.getz());
//                    x3.setz(-1 * x3.getz());
//                    x4.setz(-1 * x4.getz());
//
//                    x1.setx(x3.getx());
//                    x1.sety(x3.gety());
//                    x1.setz(x3.getz());
//                    x2.setx(x4.getx());
//                    x2.sety(x4.gety());
//                    x2.setz(x4.getz());
//                }
//
//                x1.setx(negative_constant);
//                x1.sety(positive_constant);
//                x1.setz(positive_constant);
//                x2.setx(negative_constant);
//                x2.sety(negative_constant);
//                x2.setz(positive_constant);
//                x3.setx(positive_constant);
//                x3.sety(positive_constant);
//                x3.setz(positive_constant);
//                x4.setx(positive_constant);
//                x4.sety(negative_constant);
//                x4.setz(positive_constant);
//                x1.sety((x1.gety() - (1.0f / subdivisions * i)));
//                x3.sety(x1.gety());
//
//            }
//        }
//    }
//
//    void drawFace_top_bottom(Coordinates x1, Coordinates x2, Coordinates x3, Coordinates x4, int subdivisions) {
//
//
//        if (subdivisions == 1) {
//            addTriangle(x1.getx(), x1.gety(), x1.getz(), x2.getx(), x2.gety(), x2.getz(), x3.getx(), x3.gety(), x3.getz());
//            addTriangle(x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz(), x4.getx(), x4.gety(), x4.getz());
//            x1.sety(-x1.gety());
//            x2.sety(-x2.gety());
//            x3.sety(-x3.gety());
//            x4.sety(-x4.gety());
//            addTriangle(x1.getx(), x1.gety(), x1.getz(), x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz());
//            addTriangle(x3.getx(), x3.gety(), x3.getz(), x4.getx(), x4.gety(), x4.getz(), x2.getx(), x2.gety(), x2.getz());
//        } else {
//            for (int i = 1; i <= subdivisions; i++) {
//                float smt = 1.0f / subdivisions;
//                x2.setz((x1.getz()) + smt);
//                for (int j = 1; j <= subdivisions; j++) {
//                    x3.setx((x1.getx()) + smt);
//                    addTriangle(x1.getx(), x1.gety(), x1.getz(), x2.getx(), x2.gety(), x2.getz(), x3.getx(), x3.gety(), x3.getz());
//                    x1.sety(-x1.gety());
//                    x2.sety(-x2.gety());
//                    x3.sety(-x3.gety());
//                    x4.sety(-x4.gety());
//
//                    addTriangle(x1.getx(), x1.gety(), x1.getz(), x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz());
//                    x1.sety(-x1.gety());
//                    x2.sety(-x2.gety());
//                    x3.sety(-x3.gety());
//                    x4.sety(-x4.gety());
//                    x4.setx(x3.getx());
//                    x4.setz(x2.getz());
//
//                    addTriangle(x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz(), x4.getx(), x4.gety(), x4.getz());
//                    x1.sety(-x1.gety());
//                    x2.sety(-x2.gety());
//                    x3.sety(-x3.gety());
//                    x4.sety(-x4.gety());
//
//                    addTriangle(x3.getx(), x3.gety(), x3.getz(), x4.getx(), x4.gety(), x4.getz(), x2.getx(), x2.gety(), x2.getz());
//                    x1.sety(-x1.gety());
//                    x2.sety(-x2.gety());
//                    x3.sety(-x3.gety());
//                    x4.sety(-x4.gety());
//                    x1.setx(x3.getx());
//                    x1.sety(x3.gety());
//                    x1.setz(x3.getz());
//                    x2.setx(x4.getx());
//                    x2.sety(x4.gety());
//                    x2.setz(x4.getz());
//
//                }
//
//                x1.setx(negative_constant);
//                x1.sety(positive_constant);
//                x1.setz(negative_constant);
//                x2.setx(negative_constant);
//                x2.sety(positive_constant);
//                x2.setz(positive_constant);
//                x3.setx(positive_constant);
//                x3.sety(positive_constant);
//                x3.setz(negative_constant);
//                x4.setx(positive_constant);
//                x4.sety(positive_constant);
//                x4.setz(positive_constant);
//                x1.setz((x1.getz() + (1.0f / subdivisions * i)));
//                x3.setz(x1.getz());
//            }
//        }
//    }
//
//    void drawleftrightfacesofcube(Coordinates x1, Coordinates x2, Coordinates x3, Coordinates x4, int subdivisions) {
//
//        if (subdivisions == 1) {
//            addTriangle(x1.getx(), x1.gety(), x1.getz(), x2.getx(), x2.gety(), x2.getz(), x3.getx(), x3.gety(), x3.getz());
//            addTriangle(x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz(), x4.getx(), x4.gety(), x4.getz());
//            x1.setx(-x1.getx());
//            x2.setx(-x2.getx());
//            x3.setx(-x3.getx());
//            x4.setx(-x4.getx());
//            addTriangle(x1.getx(), x1.gety(), x1.getz(), x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz());
//            addTriangle(x3.getx(), x3.gety(), x3.getz(), x4.getx(), x4.gety(), x4.getz(), x2.getx(), x2.gety(), x2.getz());
//        } else {
//            for (int i = 1; i <= subdivisions; i++) {
//                float smt = 1.0f / subdivisions;
//                x2.sety((x1.gety()) - smt);
//                for (int j = 1; j <= subdivisions; j++) {
//                    x3.setz((x1.getz()) + smt);
//                    addTriangle(x1.getx(), x1.gety(), x1.getz(), x2.getx(), x2.gety(), x2.getz(), x3.getx(), x3.gety(), x3.getz());
//                    x1.setx(-x1.getx());
//                    x2.setx(-x2.getx());
//                    x3.setx(-x3.getx());
//                    x4.setx(-x4.getx());
//
//                    addTriangle(x1.getx(), x1.gety(), x1.getz(), x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz());
//                    x1.setx(-x1.getx());
//                    x2.setx(-x2.getx());
//                    x3.setx(-x3.getx());
//                    x4.setx(-x4.getx());
//                    x4.setz(x3.getz());
//                    x4.sety(x2.gety());
//
//                    addTriangle(x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz(), x4.getx(), x4.gety(), x4.getz());
//                    x1.setx(-x1.getx());
//                    x2.setx(-x2.getx());
//                    x3.setx(-x3.getx());
//                    x4.setx(-x4.getx());
//
//                    addTriangle(x3.getx(), x3.gety(), x3.getz(), x4.getx(), x4.gety(), x4.getz(), x2.getx(), x2.gety(), x2.getz());
//                    x1.setx(-x1.getx());
//                    x2.setx(-x2.getx());
//                    x3.setx(-x3.getx());
//                    x4.setx(-x4.getx());
//                    x1.setx(x3.getx());
//                    x1.sety(x3.gety());
//                    x1.setz(x3.getz());
//                    x2.setx(x4.getx());
//                    x2.sety(x4.gety());
//                    x2.setz(x4.getz());
//                }
//                x1.setx(negative_constant);
//                x1.sety(positive_constant);
//                x1.setz(negative_constant);
//                x2.setx(negative_constant);
//                x2.sety(negative_constant);
//                x2.setz(negative_constant);
//                x3.setx(negative_constant);
//                x3.sety(positive_constant);
//                x3.setz(positive_constant);
//                x4.setx(negative_constant);
//                x4.sety(negative_constant);
//                x4.setz(positive_constant);
//
//                x1.sety((x1.gety() - (1.0f / subdivisions * i)));
//                x3.sety(x1.gety());
//            }
//        }
//    }
//
//    /**
//     * makeCube - Create a unit cube, centered at the origin, with a given
//     * number of subdivisions in each direction on each face.
//     *
//     * @param subdivision - number of equal subdivisons to be made in each
//     * direction along each face
//     *
//     * Can only use calls to addTriangle()
//     */
//    public void makeCube(int subdivisions) {
//        Coordinates x1 = new Coordinates(negative_constant, positive_constant, positive_constant);
//        Coordinates x2 = new Coordinates(negative_constant, negative_constant, positive_constant);
//        Coordinates x3 = new Coordinates(positive_constant, positive_constant, positive_constant);
//        Coordinates x4 = new Coordinates(positive_constant, negative_constant, positive_constant);
//        drawFace_front_back(x1, x2, x3, x4, subdivisions);
//
//        x1.setx(negative_constant);
//        x1.sety(positive_constant);
//        x1.setz(negative_constant);
//        x2.setx(negative_constant);
//        x2.sety(positive_constant);
//        x2.setz(positive_constant);
//        x3.setx(positive_constant);
//        x3.sety(positive_constant);
//        x3.setz(negative_constant);
//        x4.setx(positive_constant);
//        x4.sety(positive_constant);
//        x4.setz(positive_constant);
//        drawFace_top_bottom(x1, x2, x3, x4, subdivisions);
//        x1.setx(negative_constant);
//        x1.sety(positive_constant);
//        x1.setz(negative_constant);
//        x2.setx(negative_constant);
//        x2.sety(negative_constant);
//        x2.setz(negative_constant);
//        x3.setx(negative_constant);
//        x3.sety(positive_constant);
//        x3.setz(positive_constant);
//        x4.setx(negative_constant);
//        x4.sety(negative_constant);
//        x4.setz(positive_constant);
//        drawleftrightfacesofcube(x1, x2, x3, x4, subdivisions);
//    }
//
//    /**
//     * makeCylinder - Create polygons for a cylinder with unit height, centered
//     * at the origin, with separate number of radial subdivisions and height
//     * subdivisions.
//     *
//     * @param radius - Radius of the base of the cylinder
//     * @param radialDivision - number of subdivisions on the radial base
//     * @param heightDivisions - number of subdivisions along the height
//     *
//     * Can only use calls to addTriangle()
//     */
//    public void makeCylinder(float radius, int radialDivisions, int heightDivisions) {
//        Coordinates x1 = new Coordinates();
//        Coordinates x2 = new Coordinates();
//        Coordinates x3 = new Coordinates();
//        Coordinates x4 = new Coordinates();
//        Coordinates x5 = new Coordinates();
//        Coordinates x6 = new Coordinates();
//        Coordinates x7 = new Coordinates();
//        float angle = 360.0f / radialDivisions;
//
//
//        x1.setx(zero);
//        x1.sety(positive_constant);
//        x1.setz(zero);
//        x2.setx(positive_constant);
//        x2.sety(positive_constant);
//        x2.setz(zero);
//        if (radialDivisions > 2) {
//            for (int v = 1; v <= radialDivisions; v++) {
//
//                x3.setx((float) (positive_constant * Math.cos(v * angle * Math.PI / 180)));
//                x3.sety(positive_constant);
//                x3.setz((float) (positive_constant * Math.sin(v * angle * Math.PI / 180)));
//                addTriangle(x1.getx(), x1.gety(), x1.getz(), x3.getx(), x3.gety(), x3.getz(), x2.getx(), x2.gety(), x2.getz());
//                x1.swap_value(x4, x2);
//                x1.swap_value(x5, x3);
//                x1.sety(-x1.gety());
//                x2.sety(-x2.gety());
//                x3.sety(-x3.gety());
//
//                addTriangle(x1.getx(), x1.gety(), x1.getz(), x2.getx(), x2.gety(), x2.getz(), x3.getx(), x3.gety(), x3.getz());
//                x1.swap_value(x6, x3);
//                x1.swap_value(x7, x2);
//                x1.sety(-x1.gety());
//                x2.sety(-x2.gety());
//                x3.sety(-x3.gety());
//                if (heightDivisions == 1) {
//                    addTriangle(x5.getx(), x5.gety(), x5.getz(), x6.getx(), x6.gety(), x6.getz(), x7.getx(), x7.gety(), x7.getz());
//                    addTriangle(x5.getx(), x5.gety(), x5.getz(), x7.getx(), x7.gety(), x7.getz(), x4.getx(), x4.gety(), x4.getz());
//                } else {
//                    for (int s = 1; s <= heightDivisions; s++) {
//                        x6.sety(x5.gety() - (1.0f / heightDivisions));
//                        x7.sety(x4.gety() - (1.0f / heightDivisions));
//                        addTriangle(x5.getx(), x5.gety(), x5.getz(), x6.getx(), x6.gety(), x6.getz(), x7.getx(), x7.gety(), x7.getz());
//                        addTriangle(x5.getx(), x5.gety(), x5.getz(), x7.getx(), x7.gety(), x7.getz(), x4.getx(), x4.gety(), x4.getz());
//                        x1.swap_value(x5, x6);
//                        x1.swap_value(x4, x7);
//                    }
//                }
//                x1.swap_value(x2, x3);
//            }
//        }
//    }
//
//    /**
//     * makeCone - Create polygons for a cone with unit height, centered at the
//     * origin, with separate number of radial subdivisions and height
//     * subdivisions.
//     *
//     * @param radius - Radius of the base of the cone
//     * @param radialDivision - number of subdivisions on the radial base
//     * @param heightDivisions - number of subdivisions along the height
//     *
//     * Can only use calls to addTriangle()
//     */
//    public void makeCone(float radius, int radialDivisions, int heightDivisions) {
//        int radial_div = radialDivisions;
//        int height_div = heightDivisions;
//        float radius_1 = radius;
//        float x_1 = 0;
//        float z_1 = 0;
//        float angle = 360.0f / radial_div;
//        for (int i = 1; i <= radial_div; i++) {
//            float x = radius_1 * (float) (Math.cos((angle * (Math.PI / 180))));
//            float z = radius_1 * (float) (Math.sin((angle * (Math.PI / 180))));
//            z = -z;
//
//            if (i == 1) {
//                addTriangle(radius_1, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, x, -0.5f, z);
//                float x_prev = radius_1, y_prev = -0.5f, z_prev = 0.0f, x1_prev = 0.0f, y1_prev = 0.0f, z1_prev = 0.0f;
//                float x_next = x, y_next = -0.5f, z_next = z, x_new = 0.0f, y_new = 0.0f, z_new = 0.0f;
//                float t = 1 / (float) height_div;
//                float height = 1;
//                for (int j = 1; j <= height_div; j++) {
//                    addTriangle(x_next * height, y_next, z_next * height, x_prev * (height - t), y_prev + t, z_prev * (height - t), x_prev * height, y_prev, z_prev * height);
//                    addTriangle(x_prev * (height - t), y_prev + t, z_prev * (height - t), x_next * height, y_next, z_next * height, x_next * (height - t), y_next + t, z_next * (height - t));
//                    height -= t;
//                    y_next = y_next + t;
//                    y_prev = y_prev + t;
//                }
//            }
//
//            if (i != 1 && i < radial_div) {
//                addTriangle(x_1, -0.5f, z_1, 0.0f, -0.5f, 0.0f, x, -0.5f, z);
//                float x_prev = x, y_prev = -0.5f, z_prev = z, x1_prev = 0.0f, y1_prev = 0.0f, z1_prev = 0.0f;
//                float x_next = x_1, y_next = -0.5f, z_next = z_1, x_new = 0.0f, y_new = 0.0f, z_new = 0.0f;
//                float t = 1 / (float) height_div;
//                float height = 1;
//                for (int j = 1; j <= height_div; j++) {
//                    addTriangle(x_next * height, y_next, z_next * height, x_prev * height, y_prev, z_prev * height, x_prev * (height - t), y_prev + t, z_prev * (height - t));
//                    addTriangle(x_prev * (height - t), y_prev + t, z_prev * (height - t), x_next * (height - t), y_next + t, z_next * (height - t), x_next * height, y_next, z_next * height);
//                    height -= t;
//                    y_next = y_next + t;
//                    y_prev = y_prev + t;
//                }
//            }
//            if (i == radial_div) {
//                addTriangle(x_1, -0.5f, z_1, 0.0f, -0.5f, 0.0f, radius_1, -0.5f, 0.0f);
//                float x_prev = x, y_prev = -0.5f, z_prev = z, x1_prev = 0.0f, y1_prev = 0.0f, z1_prev = 0.0f;
//                float x_next = x_1, y_next = -0.5f, z_next = z_1, x_new = 0.0f, y_new = 0.0f, z_new = 0.0f;
//                float t = 1 / (float) height_div;
//                float height = 1;
//                for (int j = 1; j <= height_div; j++) {
//                    addTriangle(x_next * height, y_next, z_next * height, x_prev * height, y_prev, z_prev * height, x_prev * (height - t), y_prev + t, z_prev * (height - t));
//                    addTriangle(x_prev * (height - t), y_prev + t, z_prev * (height - t), x_next * (height - t), y_next + t, z_next * (height - t), x_next * height, y_next, z_next * height);
//                    height -= t;
//                    y_next = y_next + t;
//                    y_prev = y_prev + t;
//                }
//            }
//            x_1 = x;
//            z_1 = z;
//            angle = angle + (360.0f / radial_div);
//        }
//    }
//
//    /**
//     * makeSphere - Create sphere of a given radius, centered at the origin,
//     * using spherical coordinates with separate number of thetha and phi
//     * subdivisions.
//     *
//     * @param radius - Radius of the sphere
//     * @param slides - number of subdivisions in the theta direction
//     * @param stacks - Number of subdivisions in the phi direction.
//     *
//     * Can only use calls to addTriangle
//     */
//    public void makeSphere(float radius, int slices, int stacks) {
//        float a = (float) (2.0f / (1.0f + Math.sqrt(5.0f)));
//        Coordinates v0 = new Coordinates(0.0f, a, -1.0f);
//        Coordinates v1 = new Coordinates(-a, 1.0f, 0.0f);
//        Coordinates v2 = new Coordinates(a, 1.0f, 0.0f);
//        Coordinates v3 = new Coordinates(0.0f, a, 1.0f);
//        Coordinates v4 = new Coordinates(-1.0f, 0.0f, a);
//        Coordinates v5 = new Coordinates(0.0f, -a, 1.0f);
//        Coordinates v6 = new Coordinates(1.0f, 0.0f, a);
//        Coordinates v7 = new Coordinates(1.0f, 0.0f, -a);
//        Coordinates v8 = new Coordinates(0.0f, -a, -1.0f);
//        Coordinates v9 = new Coordinates(-1.0f, 0.0f, -a);
//        Coordinates v10 = new Coordinates(-a, -1.0f, 0.0f);
//        Coordinates v11 = new Coordinates(a, -1.0f, 0.0f);
//
//        Triangle t0 = new Triangle(v0, v1, v2);
//        Triangle t1 = new Triangle(v3, v2, v1);
//        Triangle t2 = new Triangle(v3, v4, v5);
//        Triangle t3 = new Triangle(v3, v5, v6);
//        Triangle t4 = new Triangle(v0, v7, v8);
//        Triangle t5 = new Triangle(v0, v8, v9);
//        Triangle t6 = new Triangle(v5, v10, v11);
//        Triangle t7 = new Triangle(v8, v11, v10);
//        Triangle t8 = new Triangle(v1, v9, v4);
//        Triangle t9 = new Triangle(v10, v4, v9);
//        Triangle t10 = new Triangle(v2, v6, v7);
//        Triangle t11 = new Triangle(v11, v7, v6);
//        Triangle t12 = new Triangle(v3, v1, v4);
//        Triangle t13 = new Triangle(v3, v6, v2);
//        Triangle t14 = new Triangle(v0, v9, v1);
//        Triangle t15 = new Triangle(v0, v2, v7);
//        Triangle t16 = new Triangle(v8, v10, v9);
//        Triangle t17 = new Triangle(v8, v7, v11);
//        Triangle t18 = new Triangle(v5, v4, v10);
//        Triangle t19 = new Triangle(v5, v11, v6);
//
//        Vector<Triangle> triangle1 = new Vector<Triangle>();
//        Vector<Triangle> triangle2 = new Vector<Triangle>();
//
//        triangle1.addElement(t0);
//        triangle1.addElement(t1);
//        triangle1.addElement(t2);
//        triangle1.addElement(t3);
//        triangle1.addElement(t4);
//        triangle1.addElement(t5);
//        triangle1.addElement(t6);
//        triangle1.addElement(t7);
//        triangle1.addElement(t8);
//        triangle1.addElement(t9);
//        triangle1.addElement(t10);
//        triangle1.addElement(t11);
//        triangle1.addElement(t12);
//        triangle1.addElement(t13);
//        triangle1.addElement(t14);
//        triangle1.addElement(t15);
//        triangle1.addElement(t16);
//        triangle1.addElement(t17);
//        triangle1.addElement(t18);
//        triangle1.addElement(t19);
//
//
//        for (int j = 1; j <= slices; j++) {
//            if (slices == 1) {
//                for (int i = 0; i < triangle1.size(); i++) {
//                    addTriangle(triangle1.elementAt(i).p1.x, triangle1.elementAt(i).p1.y, triangle1.elementAt(i).p1.z,
//                            triangle1.elementAt(i).p2.x, triangle1.elementAt(i).p2.y, triangle1.elementAt(i).p2.z, triangle1.elementAt(i).p3.x,
//                            triangle1.elementAt(i).p3.y, triangle1.elementAt(i).p3.z);
//                }
//            } else {
//                for (int i = 0; i < triangle1.size(); i++) {
//                    Coordinates p1p2 = cal_mid(triangle1.elementAt(i).p1, triangle1.elementAt(i).p2);
//                    Coordinates p1p3 = cal_mid(triangle1.elementAt(i).p1, triangle1.elementAt(i).p3);
//                    Coordinates p2p3 = cal_mid(triangle1.elementAt(i).p2, triangle1.elementAt(i).p3);
//
//                    Triangle T1 = new Triangle(triangle1.elementAt(i).p1, p1p2, p1p3);
//                    triangle2.addElement(T1);
//                    Triangle T2 = new Triangle(p1p2, triangle1.elementAt(i).p2, p2p3);
//                    triangle2.addElement(T2);
//                    Triangle T3 = new Triangle(p2p3, triangle1.elementAt(i).p3, p1p3);
//                    triangle2.addElement(T3);
//                    Triangle T4 = new Triangle(p1p2, p2p3, p1p3);
//                    triangle2.addElement(T4);
//                }
//
//
//            }
//
//            triangle1.clear();
//            for (int k = 0; k < triangle2.size(); k++) {
//                triangle1.addElement(triangle2.elementAt(k));
//            }
//
//            for (int l = 0; l < triangle2.size(); l++) {
//                triangle2.removeElementAt(l);
//            }
//
//        }
//        for (int i = 0; i < triangle1.size(); i++) {
//            normalize(triangle1.elementAt(i).p1);
//            normalize(triangle1.elementAt(i).p2);
//            normalize(triangle1.elementAt(i).p3);
//            addTriangle(triangle1.elementAt(i).p1.x, triangle1.elementAt(i).p1.y, triangle1.elementAt(i).p1.z, triangle1.elementAt(i).p2.x, triangle1.elementAt(i).p2.y, triangle1.elementAt(i).p2.z, triangle1.elementAt(i).p3.x,
//                    triangle1.elementAt(i).p3.y, triangle1.elementAt(i).p3.z);
//        }
//    }
//
//    Coordinates cal_mid(Coordinates cood1, Coordinates cood2) {
//        Coordinates cood = new Coordinates();
//        cood.setx((cood1.getx() + cood2.getx()) * 0.5f);
//        cood.sety((cood1.gety() + cood2.gety()) * 0.5f);
//        cood.setz((cood1.getz() + cood2.getz()) * 0.5f);
//        return cood;
//    }
//
//    void normalize(Coordinates cood) {
//        float a = (float) (1.0f / Math.sqrt(cood.getx() * cood.getx() + cood.gety() * cood.gety() + cood.getz() * cood.getz()));
//        cood.setx(cood.getx() * a);
//        cood.sety(cood.gety() * a);
//        cood.setz(cood.getz() * a);
//        cood.setx(cood.getx() * 0.5f);
//        cood.sety(cood.gety() * 0.5f);
//        cood.setz(cood.getz() * 0.5f);
//
//    }
//}


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

import java.util.Vector;



public class cg1Shape extends simpleShape
{
	final float neg=  -0.5f;
    final float pos=  0.5f;
    final float nil =0.0f;
	static float Pi =3.14159265f;
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
                    addTriangle(a.getx(),a.gety(),a.getz(),c.getx(),c.gety(),c.getz() ,b.getx(),b.gety(),b.getz() );//back face
                    a.setz(-1*a.getz()); b.setz(-1*b.getz());c.setz(-1*c.getz());d.setz(-1*d.getz());
                    d.setx( c.getx());
                    d.sety( b.gety());
                    addTriangle(c.getx(),c.gety(),c.getz(),b.getx(),b.gety(),b.getz(),d.getx(),d.gety(),d.getz());				//front face
                    a.setz(-1*a.getz()); b.setz(-1*b.getz());c.setz(-1*c.getz());d.setz(-1*d.getz());
                    addTriangle(c.getx(),c.gety(),c.getz(),d.getx(),d.gety(),d.getz(),b.getx(),b.gety(),b.getz());				//back face
                    a.setz(-1*a.getz()); b.setz(-1*b.getz());c.setz(-1*c.getz());d.setz(-1*d.getz());
                    
                    a.setx(c.getx()); a.sety(c.gety());a.setz(c.getz());							
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
    				a.sety(-a.gety());b.sety(-b.gety()); c.sety(-c.gety());d.sety(-d.gety());
                   
                    addTriangle(a.getx(),a.gety(),a.getz(),c.getx(),c.gety(),c.getz() ,b.getx(),b.gety(),b.getz());
                    a.sety(-a.gety()); b.sety(-b.gety());c.sety(-c.gety());d.sety(-d.gety());d.setx(c.getx());d.setz(b.getz());
				
                   	addTriangle(c.getx(),c.gety(),c.getz(),b.getx(),b.gety(),b.getz(),d.getx(),d.gety(),d.getz()); 
                    a.sety(-a.gety());b.sety(-b.gety());c.sety(-c.gety()); d.sety(-d.gety());
                    
                    addTriangle(c.getx(),c.gety(),c.getz(),d.getx(),d.gety(),d.getz(),b.getx(),b.gety(),b.getz());
                    a.sety(-a.gety());b.sety(-b.gety());c.sety(-c.gety());d.sety(-d.gety());a.setx(c.getx()); a.sety(c.gety());
                    a.setz(c.getz()); b.setx(d.getx());b.sety(d.gety());b.setz(d.getz());
    			
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
    
    void drawleftrightfacesofcube(points a,points b,points c,points d, int subdivisions){	//to draw left and right face
        
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
                    a.setx(-a.getx());b.setx(-b.getx());c.setx(-c.getx());d.setx(-d.getx());
                   
                    addTriangle(a.getx(),a.gety(),a.getz(),c.getx(),c.gety(),c.getz() ,b.getx(),b.gety(),b.getz());
                    a.setx(-a.getx());b.setx(-b.getx());c.setx(-c.getx());d.setx(-d.getx());d.setz(c.getz());d.sety( b.gety());
    				
                    addTriangle(c.getx(),c.gety(),c.getz(),b.getx(),b.gety(),b.getz(),d.getx(),d.gety(),d.getz());
    				a.setx(-a.getx());b.setx(-b.getx());c.setx(-c.getx());d.setx(-d.getx());
                    
    				addTriangle(c.getx(),c.gety(),c.getz(),d.getx(),d.gety(),d.getz(),b.getx(),b.gety(),b.getz());
                    a.setx(-a.getx());b.setx(-b.getx());c.setx(-c.getx());d.setx(-d.getx());a.setx(c.getx()); 
                    a.sety(c.gety());a.setz(c.getz());b.setx(d.getx());b.sety(d.gety());b.setz(d.getz());
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
    
    
    /**
     * makeCube - Create a unit cube, centered at the origin, with a given number
     * of subdivisions in each direction on each face.
     *
     * @param subdivision - number of equal subdivisons to be made in each 
     *        direction along each face
     *
     * Can only use calls to addTriangle()
     */
    public void makeCube (int subdivisions)
    {
        // Your Cube code goes here
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
    
    /**
     * makeCylinder - Create polygons for a cylinder with unit height, centered at
     * the origin, with separate number of radial subdivisions and height 
     * subdivisions.
     *
     * @param radius - Radius of the base of the cylinder
     * @param radialDivision - number of subdivisions on the radial base
     * @param heightDivisions - number of subdivisions along the height
     *
     * Can only use calls to addTriangle()
     */
    
    public void makeCylinder (float radius, int radialDivisions, int heightDivisions)
    {
        
    	points a = new points();
    	points b = new points();
    	points c = new points();
    	points e = new points();
    	points f = new points();
    	points g = new points();
    	points h = new points();
    	float angle = 360.0f/radialDivisions;
    	
    	
    	a.setx(nil); a.sety(pos);  a.setz(nil);		
        b.setx(pos); b.sety(pos);  b.setz(nil);	
    	if(radialDivisions>2){
    		for(int v=1;v<=radialDivisions;v++){			
    			
    			c.setx((float)(pos*Math.cos(v*angle*Pi/180)));c.sety(pos);c.setz( (float)(pos*Math.sin(v*angle*Pi/180))); 
    			addTriangle(a.getx(),a.gety(),a.getz(),c.getx(),c.gety(),c.getz() ,b.getx(),b.gety(),b.getz());  //top 
                a.copy(e,b);
                a.copy(f,c);
    			a.sety(-a.gety());				
                b.sety(-b.gety());
                c.sety(-c.gety());
                
                addTriangle(a.getx(),a.gety(),a.getz(),b.getx(),b.gety(),b.getz(),c.getx(),c.gety(),c.getz() );
                a.copy(g,c);
                a.copy(h,b);
    			a.sety(-a.gety());				
                b.sety(-b.gety());
                c.sety(-c.gety());
    			if(heightDivisions==1){
    				addTriangle(f.getx(),f.gety(),f.getz(), g.getx(),g.gety(),g.getz(), h.getx(),h.gety(),h.getz() );
    				addTriangle(f.getx(),f.gety(),f.getz(), h.getx(),h.gety(),h.getz(), e.getx(),e.gety(),e.getz() );
    			}
    			else{
    				for(int s=1;s<=heightDivisions;s++){	
    					g.sety(f.gety() - (1.0f/heightDivisions));
    					h.sety(e.gety() - (1.0f/heightDivisions));
    					addTriangle(f.getx(),f.gety(),f.getz(), g.getx(),g.gety(),g.getz(), h.getx(),h.gety(),h.getz() );
    					addTriangle(f.getx(),f.gety(),f.getz(), h.getx(),h.gety(),h.getz(), e.getx(),e.gety(),e.getz() );
                        a.copy(f,g);
                        a.copy(e,h);
    				}
    			}
                a.copy(b,c);
    		}
    	}
    	return;
    	
    	
    }
    
    /**
     * makeCone - Create polygons for a cone with unit height, centered at the
     * origin, with separate number of radial subdivisions and height 
     * subdivisions.
     *
     * @param radius - Radius of the base of the cone
     * @param radialDivision - number of subdivisions on the radial base
     * @param heightDivisions - number of subdivisions along the height
     *
     * Can only use calls to addTriangle()
     */
    public void makeCone (float radius, int radialDivisions, int heightDivisions)
    {
    	int rd=radialDivisions;
		int h=heightDivisions;
		float r = radius;
		float x1 = 0;
		float z1 = 0;
		float angle = 360.0f/ rd;
		for (int i = 1; i <= rd; i++) {
			float x = r * (float) (Math.cos((angle * (Math.PI / 180))));
			float z = r * (float) (Math.sin((angle * (Math.PI / 180))));
			z = -z;
			         
			if (i == 1){
				addTriangle(r, -0.5f, 0.0f, 0.0f, -0.5f, 0.0f, x, -0.5f, z);
				float xprev = r, yprev = -0.5f, zprev = 0.0f, prevx1 = 0.0f, prevy1 = 0.0f, prevz1 = 0.0f;
				float xnext = x, ynext = -0.5f, znext = z, newx1 = 0.0f, newy1 = 0.0f, newz1 = 0.0f;
				float t = 1 / (float) h;
				float hgt = 1;
				for (int j = 1; j <= h; j++) {
					addTriangle(xnext * hgt, ynext, znext * hgt, xprev * (hgt - t), yprev + t, zprev * (hgt - t),xprev * hgt, yprev, zprev * hgt);
					addTriangle(xprev * (hgt - t), yprev + t, zprev* (hgt - t), xnext * hgt, ynext, znext * hgt,xnext * (hgt - t), ynext + t, znext * (hgt - t));
					hgt-= t;
					ynext=ynext + t;
					yprev=yprev + t;
				}
			}

			if (i != 1 && i < rd) {
				addTriangle(x1, -0.5f, z1, 0.0f, -0.5f, 0.0f, x, -0.5f, z);
				float xprev = x, yprev = -0.5f, zprev = z, prevx1 = 0.0f, prevy1 = 0.0f, prevz1 = 0.0f;
				float xnext = x1, ynext = -0.5f, znext = z1, newx1 = 0.0f, newy1 = 0.0f, newz1 = 0.0f;
				float t = 1 / (float) h;
				float hgt = 1;
				for (int j = 1; j <= h; j++){
					addTriangle(xnext * hgt, ynext, znext * hgt, xprev * hgt, yprev, zprev * hgt, xprev* (hgt - t), yprev + t, zprev * (hgt - t));
					addTriangle(xprev * (hgt - t), yprev + t, zprev * (hgt - t), xnext * (hgt - t), ynext + t, znext* (hgt - t), xnext * hgt, ynext, znext * hgt);
					hgt-=t;
					ynext=ynext + t;
					yprev=yprev + t;
				}
			}
			if (i == rd){
				addTriangle(x1, -0.5f, z1, 0.0f, -0.5f, 0.0f, r, -0.5f, 0.0f);
				float xprev = x, yprev = -0.5f, zprev = z, prevx1 = 0.0f, prevy1 = 0.0f, prevz1 = 0.0f;
				float xnext = x1, ynext = -0.5f, znext = z1, newx1 = 0.0f, newy1 = 0.0f, newz1 = 0.0f;
				float t = 1 / (float) h;
				float hgt = 1;
				for (int j = 1; j <= h; j++) {
					addTriangle(xnext * hgt, ynext, znext * hgt, xprev * hgt, yprev, zprev * hgt, xprev* (hgt - t), yprev + t, zprev * (hgt - t));
					addTriangle(xprev * (hgt - t), yprev + t, zprev * (hgt - t), xnext * (hgt - t), ynext + t, znext* (hgt - t), xnext * hgt, ynext, znext * hgt);
					hgt -= t;
					ynext = ynext + t;
					yprev = yprev + t;
				}
			}
			x1 = x;
			z1 = z;
			angle = angle + (360.0f / rd);
		}
    }
    
    
    
    void norm(points a ) {
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
    
    points calculatemidpoint(points a,points b){
        points c = new points();
        c.setx((a.getx() + b.getx())*0.5f);
        c.sety((a.gety() + b.gety())*0.5f);
        c.setz((a.getz() + b.getz())*0.5f);
        
        return c;
    }
    
    
    /**
     * makeSphere - Create sphere of a given radius, centered at the origin, 
     * using spherical coordinates with separate number of thetha and 
     * phi subdivisions.
     *
     * @param radius - Radius of the sphere
     * @param slides - number of subdivisions in the theta direction
     * @param stacks - Number of subdivisions in the phi direction.
     *
     * Can only use calls to addTriangle
     */
    public void makeSphere(float radius, int slices, int stacks){
    	Vector<Triangle> tri = new Vector<Triangle>();
        Vector<Triangle> triangle_1 = new Vector<Triangle>();
        
        float a = (float) (2.0f / ( 1.0f + Math.sqrt(5.0f) ));
        points v0 = new points( 0.0f,  a, -1.0f);
       
        points v1= new points(-a,  1.0f,  0.0f);
        points v2= new points( a,  1.0f,  0.0f);
        points v3= new points( 0.0f,  a,  1.0f);
        points v4= new points(-1.0f,  0.0f,  a);
        points v5= new points( 0.0f, -a,  1.0f);
        points v6= new points( 1.0f,  0.0f,  a);
        points v7= new points( 1.0f,  0.0f, -a);
        points v8= new points( 0.0f, -a, -1.0f);
        points v9= new points(-1.0f,  0.0f, -a);
        points v10=new points(-a, -1.0f, 0.0f);
        points v11= new points( a, -1.0f, 0.0f);
        
        norm(v0);norm(v1);norm(v2);norm(v3);norm(v4);norm(v5);norm(v6);norm(v7);
        norm(v8);norm(v9);norm(v10);norm(v11);
       
        
        
        Triangle t0=new Triangle(v0, v1, v2 );			
    	Triangle t1=new Triangle(v3, v2, v1 );			
    	Triangle t2= new Triangle(v3, v4, v5 );			
    	Triangle t3=new Triangle(v3, v5, v6 );
    	Triangle t4=new Triangle(v0, v7, v8 );
    	Triangle t5=new Triangle(v0, v8, v9);				
    	Triangle t6=new Triangle(v5, v10, v11 );
    	Triangle t7=new Triangle(v8, v11, v10 );
    	Triangle t8=new Triangle(v1, v9, v4 );
    	Triangle t9=new Triangle(v10, v4, v9 );
    	Triangle t10=new Triangle(v2, v6, v7 );				
    	Triangle t11=new Triangle(v11, v7, v6 );
    	Triangle t12=new Triangle(v3, v1, v4 );
    	Triangle t13=new Triangle(v3, v6, v2 );
    	Triangle t14=new Triangle(v0, v9, v1 );
    	Triangle t15=new Triangle(v0, v2, v7 );				
    	Triangle t16=new Triangle(v8, v10, v9 );
    	Triangle t17=new Triangle(v8, v7, v11 );
    	Triangle t18=new Triangle(v5, v4, v10 );
    	Triangle t19=new Triangle(v5, v11, v6 );
    	
    	

    	tri.addElement(t0);tri.addElement(t1);tri.addElement(t2);tri.addElement(t3);tri.addElement(t4);tri.addElement(t5);tri.addElement(t6);
    	tri.addElement(t7);tri.addElement(t8);tri.addElement(t9);tri.addElement(t10);tri.addElement(t11);
    	tri.addElement(t12);tri.addElement(t13);tri.addElement(t14);tri.addElement(t15);tri.addElement(t16);
    	tri.addElement(t17);tri.addElement(t18);tri.addElement(t19);
    	
        
    	for(int j=1;j<=slices;j++){
    		if(slices==1){
    			for(int i=0;i<tri.size();i++) {
    				addTriangle(tri.elementAt(i).p1.x,tri.elementAt(i).p1.y,tri.elementAt(i).p1.z,
                                tri.elementAt(i).p2.x,tri.elementAt(i).p2.y,tri.elementAt(i).p2.z,tri.elementAt(i).p3.x,
                                tri.elementAt(i).p3.y,tri.elementAt(i).p3.z);
                }}
    		else{
    			for(int i=0;i<tri.size();i++){
    				points mp1p2 = calculatemidpoint(tri.elementAt(i).p1,tri.elementAt(i).p2);	//calculate midpoints
    				points mp1p3 = calculatemidpoint(tri.elementAt(i).p1,tri.elementAt(i).p3);	
    				points mp2p3 = calculatemidpoint(tri.elementAt(i).p2,tri.elementAt(i).p3);
                    
    				Triangle T1=new Triangle(tri.elementAt(i).p1, mp1p2, mp1p3 );
    				triangle_1.addElement(T1);
    				Triangle T2=new Triangle(mp1p2, tri.elementAt(i).p2, mp2p3 );
    				triangle_1.addElement(T2);
    				Triangle T3=new Triangle(mp2p3, tri.elementAt(i).p3, mp1p3 );
    				triangle_1.addElement(T3);
    				Triangle T4=new Triangle(mp1p2, mp2p3, mp1p3 );
    				triangle_1.addElement(T4);
    			}
                
    			
    		}
    		
    		tri.clear();
    		for(int k=0;k<triangle_1.size();k++)
    			tri.addElement(triangle_1.elementAt(k));
            
    		for(int l=0;l<triangle_1.size();l++)
    			triangle_1.removeElementAt(l);
            
        }
    	for(int i=0;i<tri.size();i++){		
            norm(tri.elementAt(i).p1);
            norm(tri.elementAt(i).p2);
            norm(tri.elementAt(i).p3);
            addTriangle(tri.elementAt(i).p1.x,tri.elementAt(i).p1.y,tri.elementAt(i).p1.z,tri.elementAt(i).p2.x,tri.elementAt(i).p2.y,tri.elementAt(i).p2.z,tri.elementAt(i).p3.x,
                        tri.elementAt(i).p3.y,tri.elementAt(i).p3.z);
        }
        
    }
       
}