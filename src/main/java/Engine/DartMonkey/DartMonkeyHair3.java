package Engine.DartMonkey;

import Engine.Object;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class DartMonkeyHair3 extends Object {
    float offsetX, offsetY, offsetZ;
    public DartMonkeyHair3(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
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
        scaleObject(0.15f,0.4f,0.2f);
        rotateObject(-0.7f, 1.0f, 0.0f, 0.0f);
        rotateObject(-0.3f, 0.0f, 1.0f, 0.0f);
        offsetX = 0.05f;
        offsetY = 0.6f;
        offsetZ = -0.23f;
        translateObject(offsetX, offsetY, offsetZ);
    }
}
