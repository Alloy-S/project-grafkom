package Engine.NinjaMonkey;
import Engine.DartMonkey.*;
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

public class NinjaMonkey extends Object {
    float radiusX, radiusY, radiusZ, angleDegree;
    int sectorCount, stackCount, animTime;
    public float offsetX, offsetY, offsetZ;

    public NinjaMonkey(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radiusX = 0.4f;
        radiusY = 0.5f;
        radiusZ = 0.2f;
        sectorCount = 80;
        stackCount = 80;
        generate();
        setupVAOVBO();
        this.offsetX = -3f;
        this.offsetY = 0.0f;
        this.offsetZ = -3.0f;
        translateObject(offsetX, offsetY, offsetZ);
    }

    public int getAnimTime() {
        return animTime;
    }

    public void setAnimTime(int animTime) {
        this.animTime = animTime;
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
        getChildObject().add(new NinjaMonkeyBelly(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(245f,0.0f,0.0f,1.0f)
        ));
        getChildObject().add(new NinjaMonkeyHead(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(245f,0.0f,0.0f,1.0f)
        ));

        //Tangan
        getChildObject().add(new NinjaMonkeyArm1(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.710f, 0.0213f, 0.0213f,1.0f)
        ));
        getChildObject().add(new NinjaMonkeyArm2(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.710f, 0.0213f, 0.0213f,1.0f)
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
                new Vector4f(245f,0.0f,0.0f,1.0f),
                new Vector3f(0f,0f,0f),
                new Vector3f(0.2f, 0.2f, 0.2f),
                0.8f,
                0.1f,
                360f
        ));
        getChildObject().get(4).scaleObject(0.4f, 0.4f, 0.4f);
        getChildObject().get(4).rotateObject((float) Math.toRadians(90), 1f, 0f, 0f);
        getChildObject().get(4).translateObject(-0.18f, -0.6f, 0.f);

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
                new Vector4f(245f,0.0f,0.0f,1.0f),
                new Vector3f(0f,0f,0f),
                new Vector3f(0.2f, 0.2f, 0.2f),
                0.8f,
                0.1f,
                360f
        ));
        getChildObject().get(5).scaleObject(0.4f, 0.4f, 0.4f);
        getChildObject().get(5).rotateObject((float) Math.toRadians(90), 1f, 0f, 0f);
        getChildObject().get(5).translateObject(0.18f, -0.6f, 0f);

        getChildObject().get(4).getChildObject().add(new NinjaMonkeyFeet1(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.62f,0.42f,0.2f,1.0f)
        ));

        getChildObject().get(5).getChildObject().add(new NinjaMonkeyFeet2(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.62f,0.42f,0.2f,1.0f)
        ));

        getChildObject().add(new NinjaMonkeyTail(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(245f,0.0f,0.0f,1.0f)
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

    public void lookRightHead(){
        Object head = childObject.get(1);
        translateObject(-offsetX, -offsetY, -offsetZ);
        if (head.currAngleY <= 45f) {
            head.rotateObject((float) Math.toRadians(1f), 0.0f, 1.0f, 0.0f);
            head.currAngleY++;
        }
        translateObject(offsetX, offsetY, offsetZ);
    }

    public void lookLeftHead(){
        Object head = childObject.get(1);
        translateObject(-offsetX, -offsetY, -offsetZ);
        if (head.currAngleY >= -45f) {
            head.rotateObject((float) Math.toRadians(-1f), 0.0f, 1.0f, 0.0f);
            head.currAngleY--;
        }
        translateObject(offsetX, offsetY, offsetZ);

    }

    //f10
    public void lookLeftEye(){
        Object eye1 = childObject.get(1).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0);
        Object eye2 = childObject.get(1).getChildObject().get(0).getChildObject().get(1).getChildObject().get(0);
        eye1.translateObject(-offsetX, -offsetY, -offsetZ);
        eye2.translateObject(-offsetX, -offsetY, -offsetZ);
        if (eye2.offsetY <= 0.7 && eye1.offsetY <= 0.25f) {
            if (eye1.offsetY < 0.7) {
                eye1.translateObject(-0.01f, 0.0f, 0.0f);
            }
            if (eye2.offsetY <= 0.3) {
                eye2.translateObject(-0.01f, 0.0f, 0.0f);
            }
            eye1.offsetY += 0.05;
            eye2.offsetY += 0.05;
        }
        eye1.translateObject(offsetX, offsetY, offsetZ);
        eye2.translateObject(offsetX, offsetY, offsetZ);
    }
    //f9
    public void lookRightEye(){
        Object eye1 = childObject.get(1).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0);
        Object eye2 = childObject.get(1).getChildObject().get(0).getChildObject().get(1).getChildObject().get(0);
        eye1.translateObject(-offsetX, -offsetY, -offsetZ);
        eye2.translateObject(-offsetX, -offsetY, -offsetZ);
        if (eye1.offsetY >= -0.7 && eye2.offsetY >= -0.25f) {
            if (eye1.offsetY >= -0.7) {
                eye1.translateObject(0.01f, 0.0f, 0.0f);
            }
            if (eye2.offsetY >= -0.3f) {
                eye2.translateObject(0.01f, 0.0f, 0.0f);
            }

            eye1.offsetY -= 0.05;
            eye2.offsetY -= 0.05;
        }

        eye1.translateObject(offsetX, offsetY, offsetZ);
        eye2.translateObject(offsetX, offsetY, offsetZ);
    }

    public List<Object> getShurikenList() {
        return getChildObject().get(2).getChildObject().get(0).getChildObject();
    }

    public Object getShuriken() {
        return getChildObject().get(2).getChildObject().get(0);
    }

    public void throwShuriken(){
        List<Object> shuriken = getShurikenList();
        this.getChildObject().get(2).getChildObject().get(0).rotateShuriken();
        for (Object shur: shuriken) {
            shur.translateObject(0.0f, 0.0f, 0.1f);
        }
        shuriken.removeIf(shur -> shur.getCenterPoint().get(2) >= 2);
    }

    public void spawnShuriken() {
        Object shur = getShuriken();
        System.out.println(shur.getName());
        shur.generateShuriken();
    }

    public void handThrowAnim(){
        if (animTime == 0) return;
        NinjaMonkeyArm1 arm1 = (NinjaMonkeyArm1) childObject.get(2);
        if (animTime > 70) {
            arm1.rotateFromBody((float) Math.toRadians(3f), 0.0f, 1.0f, 0.0f, offsetX, offsetY, offsetZ);
        }
        else if (animTime >= 50) {}
        else if (animTime >= 40) {
            arm1.rotateFromBody((float) Math.toRadians(-3f), 0.0f, 1.0f, 0.0f, offsetX, offsetY, offsetZ);
            if (animTime == 45){
                spawnShuriken();
            }
        }

        animTime--;
        System.out.println(animTime);

    }
}
