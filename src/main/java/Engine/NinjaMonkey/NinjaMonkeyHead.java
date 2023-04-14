package Engine.NinjaMonkey;

import Engine.Object;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class NinjaMonkeyHead extends Object {
    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
    public float offsetX, offsetY, offsetZ;
    public NinjaMonkeyHead(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radiusX = 0.7f;
        radiusY = 0.6f;
        radiusZ = 0.6f;
        sectorCount = 80;
        stackCount = 80;
        generate();
        setupVAOVBO();
        offsetX = 0.0f;
        offsetY = 1.0f;
        offsetZ = 0.0f;
        scaleObject(0.9f,0.9f,0.9f);
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
//        vertices.clear();

        // bikin anak di sini
        List<Object> children = new ArrayList<>();
        children.add(new NinjaMonkeyFace(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.62f,0.42f,0.2f,1.0f)
        ));
//        children.add(new NinjaMonkeyFace2(
//                Arrays.asList(
//                        new ShaderModuleData
//                                ("resources/shaders/scene.vert"
//                                        , GL_VERTEX_SHADER),
//                        new ShaderModuleData
//                                ("resources/shaders/scene.frag"
//                                        , GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(245f,0.0f,0.0f,1.0f)
//        ));
        setChildObject(children);
    }

    public void rotateObject(Float degree, Float x,Float y,Float z) {
        model = new Matrix4f().rotate(degree, x, y, z).mul(new Matrix4f(model));
        updateCenterPoint();
        for (Object child : childObject) {
            child.translateObject(-offsetX, -offsetY, -offsetZ);
            child.rotateObject(degree, x, y, z);
            child.translateObject(offsetX, offsetY, offsetZ);
        }
    }
}
