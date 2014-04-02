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
import java.io.*;
import javax.media.opengl.awt.GLCanvas;
import javax.media.opengl.fixedfunc.*;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureData;
import com.jogamp.opengl.util.texture.TextureIO;

public class MainFile implements GLEventListener {

    public final static int cube = 0;
    public float RotationValues[];
    public float trans_1[];
    public float trans_2[];
    public float trans_3[];
    public float transData3[];
    public float size[];
    private float eye[];
    private float lookat[];
    private float up[];
    private float Shininess;
    public int Theta;
    public int Scale;
    public int Translation;
    public int Eye;
    public int Look;
    public int UpVector;
    public int lightP;
    public int shine;
    public int ambient;
    public int diffuse;
    public int specular;
    float[] lightPos = {2, 1, 5, 1};
    float[] Ambient = {0.0f, 1.0f, 1.0f, 1.0f};
    float[] Diffuse = {0.0f, 1.0f, 1.0f, 0.7f};
    float[] Specular = {0.0f, 1.5f, 1.0f, 1.0f};
    public float fovData = 60.0f;
    public float aspectData = 1.0f;
    public float nearData = 1.0f;
    public float farData = 10.0f;
    private shaderProgram myShaders;
    private int shaderProgID = 0;
    private boolean updateNeeded = true;
    private int currentShape = MainFile.cube;
    static int count = 0;
    private boolean InitialValueOfBuffer = false;
    private int BufferOfVector;
    private int BufferOfEye;

    cg1Shape myShape;

    GLCanvas myCanvas;

    public MainFile(GLCanvas G) {
        
        trans_1 = new float[3];
        trans_1[0] = -1.0f;
        trans_1[1] = -1.0f;
        trans_1[2] = 0.0f;

        
        trans_2 = new float[3];
        trans_2[0] = 1.0f;
        trans_2[1] = 1.0f;
        trans_2[2] = 0.0f;
      
        trans_3 = new float[3];
        trans_3[0] = 0.0f;
        trans_3[1] = 0.0f;
        trans_3[2] = 0.0f;
       
        RotationValues = new float[3];
        RotationValues[0] = 0.0f;
        RotationValues[1] = 0.0f;
        RotationValues[2] = 45.0f;
        
        size = new float[3];
        size[0] = 1.0f;
        size[1] = 1.0f;
        size[2] = 1.0f;

        eye = new float[3];
        eye[0] = 0.0f;
        eye[1] = 0.0f;
        eye[2] = 4.0f;

        lookat = new float[3];
        lookat[0] = 0.0f;
        lookat[1] = 0.0f;
        lookat[2] = 0.0f;

        up = new float[3];
        up[0] = 0.0f;
        up[1] = 1.0f;
        up[2] = 0.0f;

        Shininess = 10.0f;

        myShaders = new shaderProgram();
        myShape = new cg1Shape();

        myCanvas = G;

        G.addGLEventListener(this);

        return;
    }

    private void errorCheck(GL2 gl2) {
        int code = gl2.glGetError();
        if (code == GL.GL_NO_ERROR) {
            System.err.println("All is well");
        } else {
            System.err.println("Problem - error code : " + code);
        }

    }

    public void display(GLAutoDrawable drawable) {
       
        GL2 gl2 = (drawable.getGL()).getGL2();
        
        gl2.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        gl2.glCullFace(GL.GL_BACK);
        gl2.glUseProgram(shaderProgID);
        
        Texture texture_random;

        try {
            InputStream stream = getClass().getResourceAsStream("texture.jpg");

            TextureData data = TextureIO.newTextureData(gl2.getGLProfile(), stream, false, "jpg");
            texture_random = TextureIO.newTexture(data);
        } catch (IOException exc) {
            exc.printStackTrace();
            System.exit(1);
        }

        Buffer points = myShape.getVerticies();
        Buffer normal = myShape.getNormals();
        Buffer texture = myShape.getTextureFloat();
        Buffer elements = myShape.getElements();

        long vertBsize = myShape.getNVerts() * 4l * 4l;
        long normBsize = myShape.getNVerts() * 3l * 4l;
        long tBuffSize = myShape.getNVerts() * 2l * 4l;
        long eBuffSize = myShape.getNVerts() * 2l;

        int bf[] = new int[1];

        for (int i = 0; i < 3; i++) {

            if (InitialValueOfBuffer) {
                bf[0] = BufferOfVector;
                gl2.glDeleteBuffers(1, bf, 0);
            }
            gl2.glGenBuffers(1, bf, 0);
            BufferOfVector = bf[0];

            gl2.glBindBuffer(GL.GL_ARRAY_BUFFER, BufferOfVector);
            gl2.glBufferData(GL.GL_ARRAY_BUFFER, vertBsize + normBsize + tBuffSize, null, GL.GL_STATIC_DRAW);
            gl2.glBufferSubData(GL.GL_ARRAY_BUFFER, 0, vertBsize, points);
            gl2.glBufferSubData(GL.GL_ARRAY_BUFFER, vertBsize, normBsize, normal);
            gl2.glBufferSubData(GL.GL_ARRAY_BUFFER, vertBsize + normBsize, tBuffSize, texture);

            int vPosition = gl2.glGetAttribLocation(shaderProgID, "vPosition");
            gl2.glUseProgram(shaderProgID);
            gl2.glEnableVertexAttribArray(vPosition);


            gl2.glVertexAttribPointer(vPosition, 4, GL.GL_FLOAT, false,
                    0, 0l);

            InitialValueOfBuffer = true;

            int vNormal = gl2.glGetAttribLocation(shaderProgID, "vNormal");
            gl2.glEnableVertexAttribArray(vNormal);
            gl2.glVertexAttribPointer(vNormal, 3, GL.GL_FLOAT, false,
                    0, vertBsize);


            int vTexCoord = gl2.glGetAttribLocation(shaderProgID, "vTexCoord");
            gl2.glEnableVertexAttribArray(vTexCoord);

            gl2.glVertexAttribPointer(vTexCoord, 2, GL.GL_FLOAT, false,
                    0, normBsize);


            InitialValueOfBuffer = true;

            if (InitialValueOfBuffer) {
                bf[0] = BufferOfEye;
                gl2.glDeleteBuffers(1, bf, 0);
            }
            gl2.glGenBuffers(1, bf, 0);
            BufferOfEye = bf[0];

            gl2.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, BufferOfEye);
            gl2.glBufferData(GL.GL_ELEMENT_ARRAY_BUFFER, eBuffSize, elements, GL.GL_STATIC_DRAW);



            if (count == 0) {


                Theta = gl2.glGetUniformLocation(shaderProgID, "ValueOfTheta");
                gl2.glUniform3fv(Theta, 1, RotationValues, 0);

                Translation = gl2.glGetUniformLocation(shaderProgID, "ValueOfTransalation");
                gl2.glUniform3fv(Translation, 1, trans_1, 0);

                Scale = gl2.glGetUniformLocation(shaderProgID, "ValueOfScale");
                gl2.glUniform3fv(Scale, 1, size, 0);


            }

            if (count == 1) {

                Theta = gl2.glGetUniformLocation(shaderProgID, "ValueOfTheta");
                gl2.glUniform3fv(Theta, 1, RotationValues, 0);

                Translation = gl2.glGetUniformLocation(shaderProgID, "ValueOfTransalation");
                gl2.glUniform3fv(Translation, 1, trans_2, 0);

                Scale = gl2.glGetUniformLocation(shaderProgID, "ValueOfScale");
                gl2.glUniform3fv(Scale, 1, size, 0);

            }

            if (count == 2) {

                Theta = gl2.glGetUniformLocation(shaderProgID, "ValueOfTheta");
                gl2.glUniform3fv(Theta, 1, RotationValues, 0);

                Translation = gl2.glGetUniformLocation(shaderProgID, "ValueOfTransalation");
                gl2.glUniform3fv(Translation, 1, trans_3, 0);

                Scale = gl2.glGetUniformLocation(shaderProgID, "ValueOfScale");
                gl2.glUniform3fv(Scale, 1, size, 0);

            }

            count++;

            Eye = gl2.glGetUniformLocation(shaderProgID, "ValueOfEye");
            gl2.glUniform3fv(Eye, 1, eye, 0);

            Look = gl2.glGetUniformLocation(shaderProgID, "ValueOfLook");
            gl2.glUniform3fv(Look, 1, lookat, 0);

            UpVector = gl2.glGetUniformLocation(shaderProgID, "ValueOfUpVector");
            gl2.glUniform3fv(UpVector, 1, up, 0);

            lightP = gl2.glGetUniformLocation(shaderProgID, "lightPosition");
            gl2.glUniform4fv(lightP, 1, lightPos, 0);


            Shininess = gl2.glGetUniformLocation(shaderProgID, "shininess");
            gl2.glUniform1f(shine, Shininess);

            ambient = gl2.glGetUniformLocation(shaderProgID, "ambientProduct");
            gl2.glUniform4fv(ambient, 1, Ambient, 0);

            diffuse = gl2.glGetUniformLocation(shaderProgID, "diffuseProduct");
            gl2.glUniform4fv(diffuse, 1, Diffuse, 0);

            specular = gl2.glGetUniformLocation(shaderProgID, "specularProduct");
            gl2.glUniform4fv(specular, 1, Specular, 0);


            int nElems = myShape.getNVerts();
            gl2.glDrawElements(GL.GL_TRIANGLES, nElems, GL.GL_UNSIGNED_SHORT, 0l);
        }

        updateNeeded = false;
    }

    public void dispose(GLAutoDrawable drawable) {
    }

    public void init(GLAutoDrawable drawable) {
        
        GL2 gl2 = drawable.getGL().getGL2();
        
        shaderProgID = myShaders.readAndCompile(gl2, "C:\\Users\\Srikant\\Documents\\NetBeansProjects\\praveen\\src\\vshader.glsl", "C:\\Users\\Srikant\\Documents\\NetBeansProjects\\praveen\\src\\fshader.glsl");
        if (shaderProgID == 0) {
            System.err.println("Error setting ValueOfUpVector shaders");
            System.exit(1);
        }
        
        gl2.glEnable(GL.GL_DEPTH_TEST);
        gl2.glEnable(GL.GL_CULL_FACE);
        gl2.glFrontFace(GL.GL_CCW);
        gl2.glClearColor(0.9f, 0.9f, 0.9f, 1.0f);
        gl2.glDepthFunc(GL.GL_LEQUAL);
        gl2.glClearDepth(1.0f);
        
        createNewShape();

    }


    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
            int height) {
    }

    public void createNewShape() {
        
        myShape.clear();
        
        switch (currentShape) {
            case cube:
                myShape.makeCube(9);

                break;

        }

        updateNeeded = true;

    }

    public static void main(String[] args) {
        
        GLProfile glp = GLProfile.getDefault();
        GLCapabilities caps = new GLCapabilities(glp);
        GLCanvas canvas = new GLCanvas(caps);
        
        MainFile myMain = new MainFile(canvas);


        Frame frame = new Frame(" ");
        frame.setSize(512, 512);
        frame.add(canvas);
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
