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
    float legRotation = 0.3f;
    float armRotation = 0.8f;
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
                0.8f,
                0.1f,
                360f
        ));
        getChildObject().get(2).scaleObject(0.4f, 0.4f, 0.4f);
        getChildObject().get(2).rotateObject((float) Math.toRadians(90), 1f, 0f, 0f);
        getChildObject().get(2).translateObject(-0.18f, -0.6f, 0.f);

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
                0.8f,
                0.1f,
                360f
        ));
        getChildObject().get(3).scaleObject(0.4f, 0.4f, 0.4f);
        getChildObject().get(3).rotateObject((float) Math.toRadians(90), 1f, 0f, 0f);
        getChildObject().get(3).translateObject(0.18f, -0.6f, 0f);

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

//    public void rotateObject(Float degree, Float x,Float y,Float z){
//        Vector3f monkeyCenter = new Vector3f(model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));
//        translateObject(-monkeyCenter.x, -monkeyCenter.y, -monkeyCenter.z);
//        model = new Matrix4f().rotate(degree,x,y,z).mul(new Matrix4f(model));
//        updateCenterPoint();
//        translateObject(offsetX, offsetY, offsetZ);
//        for(Object child:childObject){
//            child.translateObject(-offsetX, -offsetY, -offsetZ);
//            child.rotateObject(degree,x,y,z);
//            child.translateObject(offsetX, offsetY, offsetZ);
//        }
////        System.out.println("rotate");
//    }

    public void plainRotateObject(Float degree, Float x,Float y,Float z){
        model = new Matrix4f().rotate(degree,x,y,z).mul(new Matrix4f(model));
        updateCenterPoint();
        for(Object child:childObject){
            child.rotateObject(degree,x,y,z);
        }
    }

    public void aiming(boolean reverse) {
        Object arm2 = getChildObject().get(5);
        Vector3f Arm2 = new Vector3f(arm2.model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));



        if (reverse) {
            armRotation = -0.8f;
        } else {
            armRotation = 0.8f;
        }

        if ((int)arm2.currAngle + armRotation <= -45 || (int)arm2.currAngle +armRotation >= 0 ) armRotation = 0f;

        System.out.println(armRotation);
//        if (arm2.currAngle <= 45 && arm2.currAngle >= 0 ) {
            arm2.translateObject(-Arm2.x, -Arm2.y, -Arm2.z);
            arm2.rotateObject((float) Math.toRadians(armRotation), 1f, 0f, 0f);
            arm2.translateObject(Arm2.x, Arm2.y, Arm2.z);
        arm2.currAngle += armRotation;
//        }



        System.out.println(arm2.currAngle);
    }



    public void walk() {
        Object leg1 = getChildObject().get(2);
        Object leg2 = getChildObject().get(3);
        Object arm1 = getChildObject().get(4);
        Object arm2 = getChildObject().get(5);

        Vector3f Leg1 = new Vector3f(leg1.model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));
        Vector3f Leg2= new Vector3f(leg2.model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));
        Vector3f Arm1 = new Vector3f(arm1.model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));
        Vector3f Arm2 = new Vector3f(arm2.model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));

        if (leg1.currAngle <= -8) {

            legRotation = 0.3f;
        } else if (leg1.currAngle >= 8){

            legRotation = -0.3f;
        }

        if (arm1.currAngle <= -45) {

            armRotation = 0.8f;
        } else if (arm1.currAngle >= 45){

            armRotation = -0.8f;
        }

        leg1.translateObject(-Leg1.x, -Leg1.y, -Leg1.z);
        leg1.rotateObject((float) Math.toRadians(legRotation), 1f, 0f, 0f);
        leg1.translateObject(Leg1.x, Leg1.y, Leg1.z);

        leg2.translateObject(-Leg2.x, -Leg2.y, -Leg2.z);
        leg2.rotateObject((float) Math.toRadians(-legRotation), 1f, 0f, 0f);
        leg2.translateObject(Leg2.x, Leg2.y, Leg2.z);
        leg1.currAngle += legRotation;
//        System.out.println(rotation + " -- " + leg1.currAngle);
        arm1.translateObject(-Arm1.x, -Arm1.y, -Arm1.z);
        arm1.rotateObject((float) Math.toRadians(armRotation), 1f, 0f, 0f);
        arm1.translateObject(Arm1.x, Arm1.y, Arm1.z);

        arm2.translateObject(-Arm2.x, -Arm2.y, -Arm2.z);
        arm2.rotateObject((float) Math.toRadians(-armRotation), 1f, 0f, 0f);
        arm2.translateObject(Arm2.x, Arm2.y, Arm2.z);
        arm1.currAngle += armRotation;

//        System.out.println("bisa");
    }
}
