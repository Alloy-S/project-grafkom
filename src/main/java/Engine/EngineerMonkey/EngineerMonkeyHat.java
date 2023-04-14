package Engine.EngineerMonkey;

import Engine.Object;
import Engine.ShaderProgram;
import Engine.Pipe;
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
        this.offsetY = 0.05f;
        this.offsetZ = -0.3f;
        rotateObject((float) Math.toRadians(-180), 1f, 0f, 0f);
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
//                System.out.println(y + " -- " + z);
                if (z > -0.1f ) {
                    vertices.add(temp_vector);
                }
            }
        }

        getChildObject().add(new EngineerMonkeyHat2(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.930f, 0.797f, 0.0465f,1.0f)
//                0.930f, 0.797f, 0.0465f
//
        ));

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
                new Vector4f(0.400f, 0.398f, 0.392f,1.0f),
                new Vector3f(0f,0f,0f),
                new Vector3f(0.71f, 0.7f, 0.7f),
                0.05f,
                0.05f,
                180f
        ));
        getChildObject().get(1).rotateObject((float) Math.toRadians(-30), 1f, 0f, 0f);
        getChildObject().get(1).translateObject(0f, 0.15f, 0f);

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
                new Vector4f(0.860f, 0.610f, 0.0258f,1.0f),
                new Vector3f(0f,0f,0f),
                new Vector3f(0.7f, 0.7f, 0.7f),
                0.15f,
                0.02f,
                180f
        ));
        getChildObject().get(2).rotateObject((float) Math.toRadians(90), 1f, 0f, 0f);
        getChildObject().get(2).rotateObject((float) Math.toRadians(90), 0f, 0f, 1f);
        getChildObject().get(2).translateObject(0f, -0.001f, 0f);
    }


}
