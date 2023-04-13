package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DartMonkey extends Object{
    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
    public DartMonkey(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        System.out.println(vertices);
        vertices.clear();
        vertices.add(new Vector3f(0.0f,0.5f,0.0f));
        vertices.add(new Vector3f(-0.5f,-0.5f,0.0f));
        vertices.add(new Vector3f(0.5f,-0.5f,0.0f));
        vertices.add(new Vector3f(1.0f,1.0f,1.0f));
        System.out.println(vertices);

        radiusX = 0.2f;
        radiusY = 0.4f;
        radiusZ = 0.2f;
        sectorCount = 20;
        stackCount = 20;
        setCenterPoint(Arrays.asList(0.0f, 0.0f, 0.0f));
        generate();
        setupVAOVBO();
    }



    public void generate(){
        vertices.clear();
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / 20;
        float stackStep = (float)Math.PI / 20;
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
    }

}
