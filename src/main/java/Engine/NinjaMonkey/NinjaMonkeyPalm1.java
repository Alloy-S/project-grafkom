
package Engine.NinjaMonkey;
import Engine.Object;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
public class NinjaMonkeyPalm1 extends Object {
    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
//    float offsetX, offsetY, offsetZ;
    public NinjaMonkeyPalm1(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radiusX = 0.3f;
        radiusY = 0.2f;
        radiusZ = 0.2f;
        sectorCount = 80;
        stackCount = 80;
        generate();
        setupVAOVBO();
        this.offsetX = -0.2f;
        this.offsetY = 0.0f;
        this.offsetZ = 0.71f;
        rotateObject((float) Math.toRadians(-10), 0f,1f,0f);
        scaleObject(0.6f, 0.6f, 0.6f);
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
                vertices.add(temp_vector);
            }
        }
    }

    public void generateShuriken(){
        getChildObject().add(new NinjaMonkeyShuriken1(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.400f, 0.356f, 0.356f,1.0f)
        ));
        int shurikenCount = getChildObject().size();
        Object shuriken = getChildObject().get(shurikenCount- 1);

        shuriken.rotateShuriken();
        shuriken.scaleObject(0.5f,0.5f,-0.5f);
        shuriken.translateObject(-3.0f,0.3f,-2.f);


    }
    public void rotateShuriken() {
        for (Object shurikenList: getChildObject()) {
            Vector3f shuriken = new Vector3f(shurikenList.model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));
            shurikenList.translateObject(-shuriken.x, -shuriken.y, -shuriken.z);
            shurikenList.rotateObject((float) Math.toRadians(10f), 0.0f, 1.0f, 0.0f);
            shurikenList.translateObject(shuriken.x, shuriken.y, shuriken.z);
        }
    }

}
