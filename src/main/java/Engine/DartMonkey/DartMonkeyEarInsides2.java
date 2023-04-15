package Engine.DartMonkey;

import Engine.Camera;
import Engine.Object;
import Engine.Projection;
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

public class DartMonkeyEarInsides2 extends Object {
    float radius;
    float offsetX, offsetY, offsetZ;

    List<Vector3f> buildCurve1, buildCurve2;

    public DartMonkeyEarInsides2(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radius = 0.05f;
        generate();
        setupVAOVBO();
        this.offsetX = 0.1f;
        this.offsetY = 0.0f;
        this.offsetZ = 0.1f;
        scaleObject(0.5f,0.5f,1f);
        translateObject(offsetX, offsetY, offsetZ);
    }

    public void generate() {
        buildCurve1 = new ArrayList<>();
        buildCurve2 = new ArrayList<>();
        buildCurve1.add(new Vector3f(0.0f,2.0f,-0.5f));
        buildCurve1.add(new Vector3f(2.0f,2.0f,-0.5f));
        buildCurve1.add(new Vector3f(1.0f,-2.0f,-0.5f));
        buildCurve1.add(new Vector3f(0.0f,-2.0f,-0.5f));
        buildCurve2.add(new Vector3f(0.0f,2.0f,0.5f));
        buildCurve2.add(new Vector3f(2.0f,2.0f,0.5f));
        buildCurve2.add(new Vector3f(1.0f,-2.0f,0.5f));
        buildCurve2.add(new Vector3f(0.0f,-2.0f,0.5f));

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
        double interval = 0.05;

        vertices.clear();
        for (double i = 0; i <= 1; i += interval) {
            vertices.add(new Vector3f(calculateBezierPoint((float) i, buildCurve1)));
        }
        drawSegment(camera, projection);

        vertices.clear();
        for (double i = 0; i <= 0.95; i += interval) {
            vertices.add(new Vector3f(calculateBezierPoint((float) i, buildCurve1)));
            vertices.add(new Vector3f(calculateBezierPoint((float) i, buildCurve2)));
            vertices.add(new Vector3f(calculateBezierPoint((float) (i+interval), buildCurve2)));
            vertices.add(new Vector3f(calculateBezierPoint((float) (i+interval), buildCurve1)));
            vertices.add(new Vector3f(calculateBezierPoint((float) i, buildCurve1)));
        }
        drawSegment(camera, projection);

        vertices.clear();
        for (double i = 0; i <= 1; i += interval) {
            vertices.add(new Vector3f(calculateBezierPoint((float) i, buildCurve2)));
        }
        drawSegment(camera, projection);
    }
    public void drawSegment(Camera camera, Projection projection){
        setupVAOVBO();
        drawSetup(camera, projection);
        glLineWidth(10); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        glDrawArrays(GL_POLYGON, 0, vertices.size());
    }
}