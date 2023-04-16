package Engine.NinjaMonkey;

import Engine.Object;
import Engine.Pipe;
import Engine.ShaderProgram;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class NinjaMonkeyBelly extends Object {
    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
    float offsetX, offsetY, offsetZ;
    public NinjaMonkeyBelly(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radiusX = 0.3f;
        radiusY = 0.35f;
        radiusZ = 0.1f;
        sectorCount = 80;
        stackCount = 80;
        generate();
        setupVAOVBO();
        this.offsetX = 0.0f;
        this.offsetY = 0.0f;
        this.offsetZ = 0.12f;
        translateObject(offsetX, offsetY, offsetZ);
    }

    public void generate(){
        vertices.clear();
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i)
        {
            StackAngle = pi / 2 - i * stackStep;
            x = radiusX * (float)Math.cos(StackAngle);
            y = radiusY * (float)Math.cos(StackAngle);
            z = radiusZ * (float)Math.sin(StackAngle);

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float)Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;
                if(z>0.05f) vertices.add(temp_vector);
            }
        }

        // bikin anak di sini
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
                new Vector4f(0.910f, 0.697f, 0.564f,1.0f),
                new Vector3f(0f,0f,0f),
                new Vector3f(0.7f, 0.56f, 0.5f),
                0.14f,
                0.05f,
                360f
        ));
        getChildObject().get(0).scaleObject(0.6f, 0.4f, 0.4f);
        getChildObject().get(0).rotateObject((float) Math.toRadians(-90), 1f, 0f, 0f);
        getChildObject().get(0).translateObject(0.0f, 0.0f, -0.1f);

        getChildObject().add(new NinjaMonkeyBeltCurve1(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.910f, 0.697f, 0.564f,1.0f)
        ));
        getChildObject().add(new NinjaMonkeyBeltCurve2(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.910f, 0.697f, 0.564f,1.0f)
        ));
    }
    public void rotateObject(Float degree, Float x,Float y,Float z) {
        model = new Matrix4f().rotate(degree, x, y, z).mul(new Matrix4f(model));
        updateCenterPoint();
        for (Object child : childObject) {
            child.rotateObject(degree, x, y, z);
        }
    }
}
