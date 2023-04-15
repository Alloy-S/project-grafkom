package Engine.NinjaMonkey;

import Engine.Object;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

public class NinjaMonkeyShuriken5 extends Object {
    //    float offsetX, offsetY, offsetZ;
    Vector3f center, radius;

    public NinjaMonkeyShuriken5(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, Vector3f center, Vector3f radius) {
        super(shaderModuleDataList, vertices, color);
        this.center = center;
        this.radius = radius;
        vertices.clear();
        generate();
        setupVAOVBO();
        this.offsetX = 0f;
        this.offsetY = 0.4f;
        this.offsetZ = 0.5f;
        rotateObject((float) Math.toRadians(270f), 1f,0f, 0f);
        rotateObject((float) Math.toRadians(90f), 0f,1f, 0f);
        translateObject(offsetX, offsetY, offsetZ);
    }

    public void generate() {
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();

        float reduceX = 0.69f;
        float reduceY = 1.4f;
        float reduceZ = 0.1f;
        Vector3f reduceRadiusSize = new Vector3f(radius.x - reduceX, radius.y - reduceY, radius.z - reduceZ);


        //        titik 1 kiri atas belakang
        temp.x = center.x - reduceRadiusSize.x / 2 ;
        temp.y = center.y +  reduceRadiusSize.y / 2 ;
        temp.z = center.z - reduceRadiusSize.z / 2 ;
        tempVertices.add(temp);
        temp = new Vector3f();

        //        titik 2 kiri bawah belakang
        temp.x = center.x - radius.x / 2 ;
        temp.y = center.y -  radius.y / 2 ;
        temp.z = center.z - radius.z / 2 ;
        tempVertices.add(temp);
        temp = new Vector3f();

        //        titik 3 kanan bawah belakang
        temp.x = center.x + radius.x / 2 ;
        temp.y = center.y -  radius.y / 2 ;
        temp.z = center.z - radius.z / 2 ;
        tempVertices.add(temp);
        temp = new Vector3f();

        //        titik 4 kanan atas belakang
        temp.x = center.x + reduceRadiusSize.x / 2 ;
        temp.y = center.y +  reduceRadiusSize.y / 2 ;
        temp.z = center.z - reduceRadiusSize.z / 2 ;
        tempVertices.add(temp);
        temp = new Vector3f();


        //        titik 5 kiri atas depan
        temp.x = center.x - reduceRadiusSize.x / 2 ;
        temp.y = center.y +  reduceRadiusSize.y / 2 ;
        temp.z = center.z + reduceRadiusSize.z / 2 ;
        tempVertices.add(temp);
        temp = new Vector3f();

        //        titik 6 kiri bawah depan
        temp.x = center.x - radius.x / 2 ;
        temp.y = center.y -  radius.y / 2 ;
        temp.z = center.z + radius.z / 2 ;
        tempVertices.add(temp);
        temp = new Vector3f();

        //        titik 7 kanan bawah depan
        temp.x = center.x + radius.x / 2 ;
        temp.y = center.y -  radius.y / 2 ;
        temp.z = center.z + radius.z / 2 ;
        tempVertices.add(temp);
        temp = new Vector3f();

        //        titik 8 kanan atas depan
        temp.x = center.x + reduceRadiusSize.x / 2 ;
        temp.y = center.y +  reduceRadiusSize.y / 2 ;
        temp.z = center.z + reduceRadiusSize.z / 2 ;
        tempVertices.add(temp);


//        sisi bawah
//        vertices.add(tempVertices.get(1));
//        vertices.add(tempVertices.get(5));
//        vertices.add(tempVertices.get(6));
//
//        vertices.add(tempVertices.get(5));
//        vertices.add(tempVertices.get(6));
//        vertices.add(tempVertices.get(2));

//        sisi belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));

        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(0));

//        sisi kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));

        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(0));

//        sisi kanan
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));

        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(7));

//        sisi depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));

        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(4));
    }
}
