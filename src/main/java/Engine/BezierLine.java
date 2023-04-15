package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;

public class BezierLine extends Object{
    List<Vector3f> koordinat = new ArrayList<>();

    public BezierLine(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Vector3f> koordinat, Vector3f scale, Vector3f rotate, Vector3f translate) {
        super(shaderModuleDataList, vertices, color);
        this.koordinat = koordinat;
        getVertices().clear();
        generate(koordinat, getVertices());
        setupVAOVBO();
        scaleObject(scale.x, scale.y, scale.z);
        rotateObject((float) Math.toRadians(rotate.x), 1f, 0f, 0f);
        rotateObject((float) Math.toRadians(rotate.y), 0f, 1f, 0f);
        rotateObject((float) Math.toRadians(rotate.z), 0f, 0f, 1f);
        translateObject(translate.x, translate.y, translate.z);
    }

    public void generate(List<Vector3f> verticesSrc, List<Vector3f> verticesDest) {
        float x, y, z;
        int n = verticesSrc.size() - 1;
        for (float t = 0; t <= 1; t += 0.01) {
            x = 0;
            y = 0;
            z = 0;
            int count = verticesSrc.size() - 1;

            for (int i = 0; i < verticesSrc.size(); i++) {
                Vector3f pos = verticesSrc.get(i);
                float h = (float) (Math.pow((1 - t), count) * Math.pow(t, i)) * combi(n,i);
                x += (h * pos.x);
                y += (h * pos.y);
                z += (h * pos.z);
                count--;
            }
            verticesDest.add(new Vector3f(x, y, z));
        }
    }

    private int faktorial(int angka)
    {
        if (angka == 0 || angka == 1) {
            return 1;
        } else {
            return angka * faktorial(angka - 1);
        }
    }

    private int combi(int n, int r) {
        return faktorial(n) / (faktorial(n - r) * faktorial(r));
    }


    public void draw(Camera camera, Projection projection) {
        drawSetup(camera, projection);
        // draw the vertices
        glLineWidth(100);
        glPointSize(0);
        glDrawArrays(GL_POLYGON, 0, vertices.size());

    }
}
