package Engine.EngineerMonkey;

import Engine.Object;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class EngineerMonkeyEar2 extends Object {
    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
//    public float offsetX, offsetY, offsetZ;

    public EngineerMonkeyEar2(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radiusX = 0.2f;
        radiusY = 0.4f;
        radiusZ = 0.4f;
        sectorCount = 80;
        stackCount = 80;
        generate();
        setupVAOVBO();
//        this.offsetX = 0.62f;
        this.offsetX = -0.62f;
        this.offsetY = 0.f;
        this.offsetZ = 0.1f;
        scaleObject(0.37f, 0.37f, 0.37f);
        rotateObject((float) Math.toRadians(-90), 0f, 1f, 0f);
        rotateObject((float) Math.toRadians(100), 1f, 0f, 0f);
//        rotateObject((float) Math.toRadians(90), 0f, 0f, 1f);
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
                if (z > -0.15) {
                    vertices.add(temp_vector);
                }
            }
        }

//        children

        getChildObject().add(new EngineerMonkeyEarInside2(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.890f, 0.743f, 0.400f,1.0f)
        ));

    }


}
