package Engine.EngineerMonkey;

import Engine.Object;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class EngineerMonkeyHat extends Object {
    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
    float offsetX, offsetY, offsetZ;

    public EngineerMonkeyHat(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radiusX = 0.72f;
        radiusY = 0.67f;
        radiusZ = 0.67f;
        sectorCount = 110;
        stackCount = 180;
        generate();
        setupVAOVBO();
        this.offsetX = 0f;
        this.offsetY = 0.1f;
        this.offsetZ = 0.f;
        rotateObject((float) Math.toRadians(-110), 1f, 0f, 0f);
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
                System.out.println(y + " -- " + z);
                if (z > -0.1f ) {
                    vertices.add(temp_vector);
                }
            }
        }

    }


}
