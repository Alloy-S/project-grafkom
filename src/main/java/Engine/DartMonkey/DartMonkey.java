package Engine.DartMonkey;

import Engine.Object;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class DartMonkey extends Object {
    float radiusX, radiusY, radiusZ;
    int sectorCount, stackCount;
    float offsetX, offsetY, offsetZ;
    int lookTime, scratchTime, throwTime;
    float animDebug;

    public void setLookTime(int lookTime) {
        this.lookTime = lookTime;
    }

    public void setScratchTime(int scratchTime) {
        this.scratchTime = scratchTime;
    }

    public void setThrowTime(int throwTime) {
        this.throwTime = throwTime;
    }

    public int getTotalTime() {
        return lookTime+scratchTime+throwTime;
    }

    public DartMonkey(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radiusX = 0.4f;
        radiusY = 0.5f;
        radiusZ = 0.2f;
        sectorCount = 80;
        stackCount = 80;
        generate();
        setupVAOVBO();
        this.offsetX = 1.5f;
        this.offsetY = 0.0f;
        this.offsetZ = -3.0f;
        translateObject(offsetX, offsetY, offsetZ);

        lookTime = -1;
        scratchTime = -1;
        throwTime = 0;
        animDebug = 0;
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
        List<Object> children = new ArrayList<>();
        children.add(new DartMonkeyBelly(
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
        children.add(new DartMonkeyHead(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.44f,0.24f,0.12f,1.0f)
        ));
        children.add(new DartMonkeyArm1(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.44f,0.24f,0.12f,1.0f)
        ));
        children.add(new DartMonkeyArm2(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.44f,0.24f,0.12f,1.0f)
        ));
        children.add(new DartMonkeyLeg1(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.44f,0.24f,0.12f,1.0f)
        ));
        children.add(new DartMonkeyLeg2(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.44f,0.24f,0.12f,1.0f)
        ));
        children.add(new DartMonkeyTail(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.4f,0.2f,0.08f,1.0f)
        ));

        setChildObject(children);
    }

    public void rotateObject(Float degree, Float x,Float y,Float z){
        translateObject(-offsetX, -offsetY, -offsetZ);
        model = new Matrix4f().rotate(degree,x,y,z).mul(new Matrix4f(model));
        updateCenterPoint();
        translateObject(offsetX, offsetY, offsetZ);

        for(Object child:childObject) {
            child.translateObject(-offsetX, -offsetY, -offsetZ);
            child.rotateObject(degree, x, y, z);
            child.translateObject(offsetX, offsetY, offsetZ);
        }
    }
    public void look(){
        if (lookTime < 0) return;
//        System.out.println("look"+lookTime);
        Object head = childObject.get(1);

        translateObject(-offsetX, -offsetY, -offsetZ);
        if (lookTime > 70) {
            head.rotateObject(0.1f,0.0f,1.0f,0.0f);
        }
        else if (lookTime >= 50) ;
        else if (lookTime >= 30) {
            head.rotateObject(-0.1f,0.0f,1.0f,0.0f);
        }
        else if (lookTime >= 10) ;
        else {
            head.rotateObject(0.1f, 0.0f, 1.0f, 0.0f);
        }
        translateObject(offsetX, offsetY, offsetZ);
    }
    public void scratch(){
        if (scratchTime<0) return;
//        System.out.println("scratch"+scratchTime);
        Object belly = childObject.get(0);
        Object head = childObject.get(1);
        Object arm1 = childObject.get(2);
        Object arm2 = childObject.get(3);
        scratchTime--;
    }

    public void dartThrow(){
        if (throwTime < 0) return;
//        System.out.println("throw"+throwTime);
        DartMonkeyHead head = (DartMonkeyHead) childObject.get(1);
        DartMonkeyArm1 arm1 = (DartMonkeyArm1) childObject.get(2);
        DartMonkeyHand1 hand1 = (DartMonkeyHand1) arm1.childObject.get(0);

        if (throwTime > 70){
            arm1.rotateFromBody(-0.08f,1.0f,0.0f,0.0f,offsetX,offsetY,offsetZ); // -0.8
            rotateObject(-0.02f,1.0f,0.0f,0.0f); // -0.2
            head.rotateFromBody(-0.02f, 1.0f, 0.0f, 0.0f,offsetX,offsetY,offsetZ);
        }
        else if (throwTime >= 60){}
        else if (throwTime >= 50){
            arm1.rotateFromBody(0.2f,1.0f,0.0f,0.0f,offsetX,offsetY,offsetZ); // 1.2
            rotateObject(0.05f,1.0f,0.0f,0.0f); // 0.3
            head.rotateFromBody(0.05f, 1.0f, 0.0f, 0.0f,offsetX,offsetY,offsetZ);

            if (throwTime == 57){
                DartProjectile temp= (DartProjectile) hand1.childObject.get(0);
                temp.setSeen(0);
                hand1.childObject.add(new DartProjectileFly(
                        Arrays.asList(
                                new ShaderModuleData
                                        ("resources/shaders/scene.vert"
                                                , GL_VERTEX_SHADER),
                                new ShaderModuleData
                                        ("resources/shaders/scene.frag"
                                                , GL_FRAGMENT_SHADER)
                        ),
                        new ArrayList<>(),
                        new Vector4f(0.2f,0.2f,0.2f,1.0f)
                ));
            }
        }
        else if (throwTime >= 30){
            hand1.childObject.get(1).rotateObject(1.0f,0.0f,0.0f,0.0f);
        }
        else {
            arm1.rotateFromBody(-0.04f,1.0f,0.0f,0.0f,offsetX,offsetY,offsetZ);
            rotateObject(-0.01f,1.0f,0.0f,0.0f);
            head.rotateFromBody(-0.01f, 1.0f, 0.0f, 0.0f,offsetX,offsetY,offsetZ);

            if (throwTime == 8){
                DartProjectile temp= (DartProjectile) hand1.childObject.get(0);
                temp.setSeen(1);
                hand1.childObject.remove(1);
            }
        }
        throwTime--;
    }
}
