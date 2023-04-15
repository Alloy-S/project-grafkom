package Engine.EngineerMonkey;

import Engine.BezierLine;
import Engine.Camera;
import Engine.Object;
import Engine.Projection;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_LINE_STRIP;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class EngineerMonkeyMouth extends Object {
//    float offsetX, offsetY, offsetZ;
    List<Vector3f> line1 = new ArrayList<>();
    List<Vector3f> line2 = new ArrayList<>();


    public EngineerMonkeyMouth(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        line1.add(new Vector3f(-0.5f, 0f, 1f));
        line1.add(new Vector3f(0f, -0.5f, 1.1f));
        line1.add(new Vector3f(0.5f, 0f, 1f));

//        line2.add(new Vector3f(0.5f, 0f, 1f));
//        line2.add(new Vector3f(0f, 0.005f, 1.1f));
//        line2.add(new Vector3f(-0.5f, 0f, 1f));


        getVertices().clear();
        generate(line1, getVertices());
//        generate(line2, getVertices());
        setupVAOVBO();
        this.offsetX = 0f;
        this.offsetY = -0.f;
        this.offsetZ = -0.2655f;
        scaleObject(0.5f, 0.5f, 0.5f);
        rotateObject((float) Math.toRadians(30), 1f, 0f, 0f);
//        rotateObject((float) Math.toRadians(5), 0f, 1f, 0f);
        translateObject(offsetX, offsetY, offsetZ);
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

        getChildObject().add(new BezierLine(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.950f, 0.0285f, 0.213f,1.0f),
                new ArrayList<>(
                        List.of(
                                new Vector3f(-0.3f, -0.33f, 1f),
                                new Vector3f(-0.2f, -0.65f, 1.25f),
                                new Vector3f(-0.05f, -0.3f, 1f)
                        )
                ),
                new Vector3f(1f, 1f, 1f),
                new Vector3f(0f, 0f, -32f),
                new Vector3f(0.1f, 0.05f, 0f)
        ));


//        getChildObject().get(0).translateObject( 0f, 0f, -0.1f);
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
        glLineWidth(7);
        glPointSize(0);
        glDrawArrays(GL_POLYGON, 0, vertices.size());

        for(Object child:childObject) {
            child.draw(camera, projection);
        }

    }
}
