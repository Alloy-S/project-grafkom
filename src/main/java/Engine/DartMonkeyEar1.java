package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DartMonkeyEar1 extends Object{
    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
    public DartMonkeyEar1(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        generate();
        setupVAOVBO();
    }
    public void generate() {
        vertices.clear();

    }
}