package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class DartMonkeyHair extends Object{

    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
    float offsetX, offsetY, offsetZ;
    public DartMonkeyHair(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();
        vertices.add(new Vector3f(0.0f,0.0f,0.0f));
        vertices.add(new Vector3f(0.0f,0.0f,0.0f));
        vertices.add(new Vector3f(0.0f,0.0f,0.0f));
        vertices.add(new Vector3f(0.0f,0.0f,0.0f));
        vertices.add(new Vector3f(0.0f,0.0f,0.0f));
        vertices.add(new Vector3f(0.0f,0.0f,0.0f));
        vertices.add(new Vector3f(0.0f,0.0f,0.0f));
        vertices.add(new Vector3f(0.0f,0.0f,0.0f));
        setupVAOVBO();
        offsetX = 0.0f;
        offsetY = 4.0f;
        offsetZ = 0.0f;
        translateObject(offsetX, offsetY, offsetZ);
    }
}
