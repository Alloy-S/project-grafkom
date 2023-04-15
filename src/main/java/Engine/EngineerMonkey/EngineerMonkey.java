package Engine.EngineerMonkey;

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

public class EngineerMonkey extends Object{
    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
//    public float offsetX, offsetY, offsetZ;
    public EngineerMonkey(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radiusX = 0.4f;
        radiusY = 0.5f;
        radiusZ = 0.23f;
        sectorCount = 80;
        stackCount = 80;
        generate();
        setupVAOVBO();
        this.offsetX = -0.8f;
        this.offsetY = 0.0f;
        this.offsetZ = -3.0f;
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
                vertices.add(temp_vector);
            }
        }

        // bikin anak di sini
//        perut
        getChildObject().add(new EngineerMonkeyBelly(
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

//        kepala
        getChildObject().add(new EngineerMonkeyHead(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.44f,0.24f,0.12f,1.0f)
        ));

//        kaki
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
                1f,
                0.1f,
                360f
        ));
        getChildObject().get(2).scaleObject(0.4f, 0.4f, 0.4f);
        getChildObject().get(2).rotateObject((float) Math.toRadians(90), 1f, 0f, 0f);
        getChildObject().get(2).translateObject(-0.2f, -0.5f, 0f);

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
                1f,
                0.1f,
                360f
        ));
        getChildObject().get(3).scaleObject(0.4f, 0.4f, 0.4f);
        getChildObject().get(3).rotateObject((float) Math.toRadians(90), 1f, 0f, 0f);
        getChildObject().get(3).translateObject(0.2f, -0.5f, 0f);

        getChildObject().get(2).getChildObject().add(new EngineerMonkeyFeet1(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.44f,0.24f,0.12f,1.0f)
        ));

        getChildObject().get(3).getChildObject().add(new EngineerMonkeyFeet2(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.44f,0.24f,0.12f,1.0f)
        ));

//tangan





        getChildObject().add(new EngineerMonkeyShoulder1(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.48f,0.27f,0.12f,1.0f)
        ));

        getChildObject().add(new EngineerMonkeyShoulder2(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.48f,0.27f,0.12f,1.0f)
        ));

    }

    public void rotateObject(Float degree, Float x,Float y,Float z){
        translateObject(-offsetX, -offsetY, -offsetZ);
        model = new Matrix4f().rotate(degree,x,y,z).mul(new Matrix4f(model));
        updateCenterPoint();
        translateObject(offsetX, offsetY, offsetZ);
        for(Object child:childObject){
            child.translateObject(-offsetX, -offsetY, -offsetZ);
            child.rotateObject(degree,x,y,z);
            child.translateObject(offsetX, offsetY, offsetZ);
        }

    }

    public void moveLeg() {
        Object leg1 = getChildObject().get(2);
        Object leg2 = getChildObject().get(3);
        for (Object child: childObject) {
            System.out.println(child.offsetX);
        }



        System.out.println("bisa");
    }
}
