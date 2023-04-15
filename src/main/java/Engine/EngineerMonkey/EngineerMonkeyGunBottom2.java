package Engine.EngineerMonkey;

import Engine.Object;
import Engine.Pipe;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class EngineerMonkeyGunBottom2 extends Object {
    float offsetX, offsetY, offsetZ;
    Vector3f center, radius;

    public EngineerMonkeyGunBottom2(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, Vector3f center, Vector3f radius) {
        super(shaderModuleDataList, vertices, color);
        this.center = center;
        this.radius = radius;
        vertices.clear();
        generate();
        setupVAOVBO();
        this.offsetX = 0f;
        this.offsetY = 0.195f;
        this.offsetZ = 0f;
//        scaleObject(0.6f, 0.6f, 0.6f);

//        rotateObject((float) Math.toRadians(90), 1f, 0f, 0f);
//        rotateObject((float) Math.toRadians(15), 0f, 1f, 0f);
        translateObject(offsetX, offsetY, offsetZ);
    }

    public void generate() {
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();

        float reduceSize = 0.1f;
        Vector3f reduceRadiusSize = new Vector3f(radius.x - reduceSize, radius.y - reduceSize, radius.z - reduceSize);


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
                new Vector3f(0.19f, 0.19f, 0.19f),
                0.37f,
                0.1f,
                360f
        ));

        getChildObject().get(0).scaleObject(0.8f, 0.8f, 0.8f);
        getChildObject().get(0).rotateObject((float) Math.toRadians(90), 1f, 0f, 0f);
//        getChildObject().get(0).rotateObject((float) Math.toRadians(90), 0f, 1f, 0f);
//        getChildObject().get(0).rotateObject((float) Math.toRadians(90), 0f, 0f, 1f);
        getChildObject().get(0).translateObject(0.f, 0.32f, -0.1f);

        getChildObject().get(0).getChildObject().add(new Pipe(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.920f, 0.644f, 0.0920f,1.0f),
                new Vector3f(0f,0f,0f),
                new Vector3f(0.3f, 0.3f, 0.3f),
                0.37f,
                0.3f,
                360f
        ));

        getChildObject().get(0).getChildObject().get(0).translateObject(0.f, 0.9f, 0.f);

        getChildObject().get(0).getChildObject().get(0).getChildObject().add(new Pipe(
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
                new Vector3f(0.23f, 0.23f, 0.23f),
                0.07f,
                0.15f,
                360f
        ));

        getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).translateObject(0.f, 0.9f, 0.45f);


    }
}
