package Engine.NinjaMonkey;

import Engine.Object;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class NinjaMonkeyBeltEdge2 extends Object {
    float offsetX, offsetY, offsetZ;
    public NinjaMonkeyBeltEdge2(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();
        vertices.add(new Vector3f(-0.01f,0.0f,-0.1f));
        vertices.add(new Vector3f(-0.1f,0.0f,-0.1f));
        vertices.add(new Vector3f(-0.1f,0.0f,-0.1f));

        vertices.add(new Vector3f(-0.1f,0.0f,0.1f));
        vertices.add(new Vector3f(-0.1f,0.0f,0.1f));
        vertices.add(new Vector3f(-0.1f,0.0f,0.1f));

        vertices.add(new Vector3f(0.1f,0.0f,0.1f));
        vertices.add(new Vector3f(0.1f,0.0f,0.1f));
        vertices.add(new Vector3f(0.1f,0.0f,0.1f));

        vertices.add(new Vector3f(0.1f,0.0f,-0.1f));
        vertices.add(new Vector3f(0.1f,0.0f,-0.1f));
        vertices.add(new Vector3f(0.1f,0.0f,-0.1f));


        setupVAOVBO();
        scaleObject(0.8f,0.8f,0.8f);
        rotateObject(0.5f, 1.1f, 0.50f, 0.0f);
        offsetX = 0.13f;
        offsetY = -0.04f;
        offsetZ = 0.11f;
        translateObject(offsetX, offsetY, offsetZ);
    }
}
