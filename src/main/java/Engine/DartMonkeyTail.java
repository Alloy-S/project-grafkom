package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class DartMonkeyTail extends Object{
    float offsetX, offsetY, offsetZ;
    public DartMonkeyTail(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();
        vertices.add(new Vector3f(0.0f,0.0f,0.0f));
        vertices.add(new Vector3f(0.0f,-0.2f,-2.0f));
        vertices.add(new Vector3f(0.4f,-0.25f,-1.6f));
        generate();
        setupVAOVBO();
        this.offsetX = 1.5f;
        this.offsetY = 0.0f;
        this.offsetZ = -3.0f;
        translateObject(offsetX, offsetY, offsetZ);
    }

    public void generate(){
        List<Vector3f> tempVertices = new ArrayList<>();
        for (int i=0; i<vertices.size(); i++){
            tempVertices.add(vertices.get(i));
        }
        vertices.clear();
        vertices.add(tempVertices.get(0));
        int size = tempVertices.size();
        double interval = 0.05;
        for (double i=0; i<=1; i+=interval){
            vertices.add(new Vector3f(calculateBezierPoint((float) i, tempVertices)));
            vertices.add(new Vector3f(calculateBezierPoint((float) i, tempVertices)));
        }
        vertices.add(tempVertices.get(tempVertices.size()-1));
    }

    public static Vector3f calculateBezierPoint(float t, List<Vector3f> points) {
        int n = points.size() - 1;
        float x = 0, y = 0, z = 0;

        for (int i = 0; i <= n; i++) {
            double coefficient = calculateCoefficient(n, i, t);
            x += coefficient * points.get(i).x;
            y += coefficient * points.get(i).y;
            z += coefficient * points.get(i).z;
        }

        return new Vector3f(x, y, z);
    }

    private static double calculateCoefficient(int n, int i, double t) {
        return binomialCoefficient(n, i) * Math.pow(t, i) * Math.pow(1 - t, n - i);
    }

    private static int binomialCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        } else {
            return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
        }
    }


    public void draw(Camera camera, Projection projection){
        drawSetup(camera, projection);
        // Draw the vertices
        //optional
        glLineWidth(20); //ketebalan garis
        glPointSize(60); //besar kecil vertex
        glDrawArrays(GL_LINES,
                0,
                vertices.size());
        for(Object child:childObject){
            child.draw(camera,projection);
        }
    }
}