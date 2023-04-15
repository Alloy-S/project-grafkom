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

public class NinjaMonkeyShuriken1 extends Object {
    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;

    public NinjaMonkeyShuriken1(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
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
        this.offsetY = -0.2f;
        this.offsetZ = 0.02f;
        scaleObject(0.5f,0.5f,0.5f);
//        rotateObject((float) Math.toRadians(90f), 0.0f, 0.0f, 1.0f);
        translateObject(offsetX, offsetY, offsetZ);
    }

    public void generate() {
        getChildObject().add(new Pipe(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.400f, 0.356f, 0.356f,1.0f),
                new Vector3f(0f,0f,0f),
                new Vector3f(0.4f, 0.4f, 0.4f),
                0.03f,
                0.2f,
                360f
        ));
        getChildObject().get(0).rotateObject((float) Math.toRadians(90), 1f, 0f, 0f);

        //Lancip bawah atas
        getChildObject().add(new NinjaMonkeyShuriken2(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.400f, 0.356f, 0.356f,1.0f),
                new Vector3f(0f,0.38f,0.4f),
                new Vector3f(0.69f, 1.4f, 0.1f)
        ));
        //Lancip bawah
        getChildObject().add(new NinjaMonkeyShuriken3(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.400f, 0.356f, 0.356f,1.0f),
                new Vector3f(0f,1.38f,-0.4f),
                new Vector3f(0.69f, 1.4f, 0.1f)
        ));
        getChildObject().add(new NinjaMonkeyShuriken4(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.4f, 0.356f, 0.356f,1.0f),
                new Vector3f(0.48f,0.885f,0.4f),
                new Vector3f(0.69f, 1.4f, 0.1f)
        ));
        getChildObject().add(new NinjaMonkeyShuriken5(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.4f, 0.356f, 0.356f,1.0f),
                new Vector3f(0.48f,0.89f,-0.4f),
                new Vector3f(0.69f, 1.4f, 0.1f)
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