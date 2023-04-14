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

public class NinjaMonkeyEye1 extends Object {

    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
    float offsetX, offsetY, offsetZ;
    public NinjaMonkeyEye1(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radiusX = 0.2f;
        radiusY = 0.21f;
        radiusZ = 0.05f;
        sectorCount = 80;
        stackCount = 80;
        generate();
        setupVAOVBO();
        offsetX = -0.25f;
        offsetY = 0.0f;
        offsetZ = 0.155f;
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
                if(z>0.0f) vertices.add(temp_vector);
            }
        }

        // bikin anak di sini
        List<Object> children = new ArrayList<>();
        children.add(new NinjaMonkeyPupil1(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.27f,0.15f,0.07f,1.0f)
        ));


        setChildObject(children);
    }
    public void rotateObject(Float degree, Float x,Float y,Float z) {
        model = new Matrix4f().rotate(degree, x, y, z).mul(new Matrix4f(model));
        updateCenterPoint();
        for (Object child : childObject) {
            child.rotateObject(degree, x, y, z);
        }
    }
}

