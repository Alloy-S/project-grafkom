package Engine.NinjaMonkey;

import Engine.Object;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class NinjaMonkeyBalloon extends Object {
    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
//    float offsetX, offsetY, offsetZ;

    public NinjaMonkeyBalloon(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();
        generate();
        setupVAOVBO();

    }

    public void generate() {


    }

    @Override
    public void rotateObject(Float degree, Float x, Float y, Float z) {

    }

}