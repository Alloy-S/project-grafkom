package Engine.EngineerMonkey;

import Engine.Object;
import Engine.Pipe;
import Engine.ShaderProgram;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class EngineerMonkeyShoulder1 extends Object {
    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
//    float offsetX, offsetY, offsetZ;

    public EngineerMonkeyShoulder1(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radiusX = 0.2f;
        radiusY = 0.2f;
        radiusZ = 0.2f;
        sectorCount = 80;
        stackCount = 80;
        generate();
        setupVAOVBO();
        this.offsetX = 0.38f;
        this.offsetY = 0.22f;
        this.offsetZ = 0f;
        scaleObject(0.8f, 0.8f, 0.8f);
        rotateObject((float) Math.toRadians(-90), 1f, 0f, 0f);
        rotateObject((float) Math.toRadians(-15), 0f, 0f, 1f);
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
                if (z > 0.0f)vertices.add(temp_vector);
            }
        }

        getChildObject().add(new Pipe(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.44f,0.24f,0.12f,1.0f),
                new Vector3f(0f,0f,0f),
                new Vector3f(0.2f, 0.2f, 0.2f),
                0.65f,
                0.1f,
                360f
        ));

        getChildObject().get(0).scaleObject(0.45f, 0.45f, 0.45f);
//        getChildObject().get(0).rotateObject((float) Math.toRadians(0), 1f, 0f, 0f);
        getChildObject().get(0).rotateObject((float) Math.toRadians(-35), 0f, 1f, 0f);
//        getChildObject().get(0).rotateObject((float) Math.toRadians(15), 0f, 0f, 1f);
        getChildObject().get(0).translateObject(0.19f, 0f, -0.15f);

        getChildObject().get(0).getChildObject().add(new EngineerMonkeyArm1(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.890f, 0.743f, 0.400f,1.0f)
        ));

    }
}
