package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class DartMonkeyHair1 extends Object{
    float offsetX, offsetY, offsetZ;
    public DartMonkeyHair1(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();
        vertices.add(new Vector3f(-0.1f,0.0f,-0.1f));
        vertices.add(new Vector3f(-0.25f,0.2f,-0.25f));
        vertices.add(new Vector3f(-0.1f,0.0f,-0.1f));

        vertices.add(new Vector3f(-0.1f,0.0f,0.1f));
        vertices.add(new Vector3f(-0.25f,0.2f,0.25f));
        vertices.add(new Vector3f(-0.1f,0.0f,0.1f));

        vertices.add(new Vector3f(0.1f,0.0f,0.1f));
        vertices.add(new Vector3f(0.25f,0.2f,0.25f));
        vertices.add(new Vector3f(0.1f,0.0f,0.1f));

        vertices.add(new Vector3f(0.1f,0.0f,-0.1f));
        vertices.add(new Vector3f(0.25f,0.2f,-0.25f));
        vertices.add(new Vector3f(0.1f,0.0f,-0.1f));

        vertices.add(new Vector3f(-0.1f,0.0f,-0.1f));

        vertices.add(new Vector3f(-0.25f,0.2f,-0.25f));
        vertices.add(new Vector3f(0.0f,0.5f,0.0f));
        vertices.add(new Vector3f(-0.25f,0.2f,-0.25f));

        vertices.add(new Vector3f(0.25f,0.2f,-0.25f));
        vertices.add(new Vector3f(0.0f,0.5f,0.0f));
        vertices.add(new Vector3f(0.25f,0.2f,-0.25f));

        vertices.add(new Vector3f(0.25f,0.2f,0.25f));
        vertices.add(new Vector3f(0.0f,0.5f,0.0f));
        vertices.add(new Vector3f(0.25f,0.2f,0.25f));

        vertices.add(new Vector3f(-0.25f,0.2f,0.25f));
        vertices.add(new Vector3f(0.0f,0.5f,0.0f));
        vertices.add(new Vector3f(-0.25f,0.2f,0.25f));

        vertices.add(new Vector3f(-0.25f,0.2f,-0.25f));


        setupVAOVBO();
        scaleObject(0.32f,1f,0.38f);
        rotateObject(-0.7f, 1.0f, 0.0f, 0.0f);
        offsetX = 0.0f;
        offsetY = 0.5f;
        offsetZ = -0.2f;
        translateObject(offsetX, offsetY, offsetZ);
    }
}
