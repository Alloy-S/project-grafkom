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

public class DartMonkeyEarInsides1 extends Object {
    float radius;
    float offsetX, offsetY, offsetZ;

    List<List<Vector3f>> totalVertices = new ArrayList<>();

    public DartMonkeyEarInsides1(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radius = 0.05f;
        generate();
        setupVAOVBO();
        System.out.println(vertices);
        this.offsetX = -0.4f;
        this.offsetY = 0.0f;
        this.offsetZ = 0.1f;
        scaleObject(0.5f,0.5f,1.0f);
        translateObject(offsetX, offsetY, offsetZ);
    }

    public void generate() {
        List<Vector3f> buildCurve1 = new ArrayList<>(), buildCurve2 = new ArrayList<>();
        buildCurve1.add(new Vector3f(0.0f,2.0f,-0.5f));
        buildCurve1.add(new Vector3f(-2.0f,2.0f,-0.5f));
        buildCurve1.add(new Vector3f(-1.0f,-2.0f,-0.5f));
        buildCurve1.add(new Vector3f(0.0f,-2.0f,-0.5f));
        buildCurve2.add(new Vector3f(0.0f,2.0f,0.5f));
        buildCurve2.add(new Vector3f(-2.0f,2.0f,0.5f));
        buildCurve2.add(new Vector3f(-1.0f,-2.0f,0.5f));
        buildCurve2.add(new Vector3f(0.0f,-2.0f,0.5f));

        vertices.clear();

        double interval = 0.05;
        for (double i = 0; i <= 1; i += interval) {
            vertices.add(new Vector3f(calculateBezierPoint((float) i, buildCurve1)));
        }
        vertices.add(new Vector3f(calculateBezierPoint((float) 0, buildCurve1)));
        for (double i = 0; i <= 1; i += interval) {
            vertices.add(new Vector3f(calculateBezierPoint((float) i, buildCurve2)));
            vertices.add(new Vector3f(calculateBezierPoint((float) (i+interval), buildCurve2)));
            vertices.add(new Vector3f(calculateBezierPoint((float) (i+interval), buildCurve1)));
            vertices.add(new Vector3f(calculateBezierPoint((float) i, buildCurve1)));

        }
        for (double i = 0; i <= 1; i += interval) {
            vertices.add(new Vector3f(calculateBezierPoint((float) i, buildCurve2)));
        }
        vertices.add(new Vector3f(calculateBezierPoint((float) 0, buildCurve1)));
        vertices.add(new Vector3f(calculateBezierPoint((float) 0, buildCurve2)));
        vertices.add(new Vector3f(calculateBezierPoint((float) 1, buildCurve2)));
        vertices.add(new Vector3f(calculateBezierPoint((float) 1, buildCurve1)));
        vertices.add(new Vector3f(calculateBezierPoint((float) 0, buildCurve1)));

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

}