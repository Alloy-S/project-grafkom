package Engine.EngineerMonkey;

import Engine.Object;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class EngineerMonkeyPupil2 extends Object {

    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
//    float offsetX, offsetY, offsetZ;

    public EngineerMonkeyPupil2(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radiusX = 0.05f;
        radiusY = 0.09f;
        radiusZ = 0.02f;
        sectorCount = 80;
        stackCount = 80;
        generate();
        setupVAOVBO();
        offsetX = -0.015f;
        offsetY = -0.03f;
        offsetZ = 0.04f;
        translateObject(offsetX, offsetY, offsetZ);
    }

    public void generate() {
        vertices.clear();
        float pi = (float) Math.PI;

        float sectorStep = 2 * (float) Math.PI / sectorCount;
        float stackStep = (float) Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i) {
            StackAngle = pi / 2 - i * stackStep;
            x = radiusX * (float) Math.cos(StackAngle);
            y = radiusY * (float) Math.cos(StackAngle);
            z = radiusZ * (float) Math.sin(StackAngle);

            for (int j = 0; j <= sectorCount; ++j) {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x * (float) Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float) Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;
                if (z > 0.0f) vertices.add(temp_vector);
            }
        }
    }
}