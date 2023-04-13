package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

public class DartMonkey extends Object{

    public DartMonkey(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        generate();
    }

    public void generate(){
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / 20;
        float stackStep = (float)Math.PI / 20;
        float sectorAngle, StackAngle, x, y, z;

        for (int i = 0; i <= 20; ++i)
        {
            StackAngle = pi / 2 - i * stackStep;
            x = 0.2f * (float)Math.cos(StackAngle);
            y = 0.4f * (float)Math.cos(StackAngle);
            z = 0.2f * (float)Math.sin(StackAngle);

            for (int j = 0; j <= 20; ++j)
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
