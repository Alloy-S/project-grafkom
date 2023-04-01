package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object extends ShaderProgram {
    List<Vector3f> vertices;
    int vao;
    int vbo;
    int vboColor;
    Vector4f color;
    UniformsMap uniformsMap;
    List<Vector3f> verticesColor;
    public Matrix4f model;

    List<Object> childObject = new ArrayList<>();

    public Vector3f updateCenterPoint() {
        Vector3f centerTmp = new Vector3f();
        model.transformPosition(0f, 0f, 0f, centerTmp);
        return centerTmp;
    }

    public List<Object> getChildObject() {
        return childObject;
    }

    public void setChildObject(List<Object> childObject) {
        this.childObject = childObject;
    }

    public Object(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        setupVAOVBO();
        this.color = color;
        uniformsMap = new UniformsMap(getProgramId());
        uniformsMap.createUniform("uni_color");
        uniformsMap.createUniform("model");
        model = new Matrix4f();
    }


    public Object(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, List<Vector3f> verticesColor) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        this.verticesColor = verticesColor;
        setupVAOVBOWithVerticesColor();
    }


    public void setupVAOVBO() {
        this.vao = glGenVertexArrays();
        glBindVertexArray(vao);

        this.vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(vertices), GL_STATIC_DRAW);
    }

    public void setupVAOVBOWithVerticesColor() {
        this.vao = glGenVertexArrays();
        glBindVertexArray(vao);

        this.vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(vertices), GL_STATIC_DRAW);

        this.vboColor = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(verticesColor), GL_STATIC_DRAW);
    }

    public void drawSetup() {
        bind();
        //bind vbo
        uniformsMap.setUniform("uni_color", color);
        uniformsMap.setUniform("model", model);
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
    }

    public void drawSetupWithVerticesColor() {
        bind();
        //bind vbo
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        //vboColor
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, vboColor);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);
    }


//    public void draw() {
//        drawSetup();
//        // draw the vertices
//        glLineWidth(1);
//        glPointSize(0);
//        glDrawArrays(GL_TRIANGLES, 0, vertices.size());
//    }

    public void draw() {
        drawSetup();
        // draw the vertices
        glLineWidth(1);
        glPointSize(0);
        glDrawArrays(GL_TRIANGLES, 0, vertices.size());
        for(Object child:childObject) {
            child.draw();
        }
    }

    public void drawLine() {
        drawSetup();
        // draw the vertices
        glLineWidth(5);
        glPointSize(0);
        glDrawArrays(GL_LINE_STRIP, 0, vertices.size());
    }

    public void addVertices(Vector3f newVector) {
        vertices.add(newVector);
        setupVAOVBO();
    }

    public void update(int index, Vector3f pos) {
        vertices.set(index, pos);
        setupVAOVBO();
    }

    public int getVerticesSize() {
        return vertices.size();
    }

    public Vector3f getPos(int index) {
        return vertices.get(index);
    }

    public List<Vector3f> getVertices() {
        return vertices;
    }

    public void drawWithVerticesColor() {
        drawSetupWithVerticesColor();
        // draw the vertices
        glLineWidth(10);
        glPointSize(100);
        glDrawArrays(GL_TRIANGLES, 0, vertices.size());
    }

    public void translateObject(Float offSetX, Float offSetY, Float offSetZ) {
        model = new Matrix4f().translate(offSetX, offSetY, offSetZ).mul(new Matrix4f(model));

        for(Object child:childObject) {
            child.translateObject(offSetX, offSetY, offSetZ);
        }
//        if (offSet.x < 1) {
//            offSet.x += offSetX;
//        } else {
//            offSet.x =0;
//        }
    }

    public void rotateObject(Float degree, Float offSetX, Float offSetY, Float offSetZ) {
        model = new Matrix4f().rotate(degree, offSetX, offSetY, offSetZ).mul(new Matrix4f(model));
        for(Object child:childObject) {
            child.rotateObject(degree, offSetX, offSetY, offSetZ);
        }
    }

    public void scaleObject(Float x, Float y, Float z) {
        model = new Matrix4f().scale(x, y, z).mul(new Matrix4f(model));
        for(Object child:childObject) {
            child.scaleObject(x, y, z);
        }
    }


}
