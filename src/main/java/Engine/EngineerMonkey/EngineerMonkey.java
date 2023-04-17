package Engine.EngineerMonkey;

import Engine.Balloon;
import Engine.Object;
import Engine.Pipe;
import Engine.ShaderProgram;
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
    Vector3f monkeySpawnPos;
    public int shakingTime1, destroyBalloon;
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
        monkeySpawnPos = new Vector3f(model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));
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
                new Vector4f(0.890f, 0.743f, 0.400f,1.0f)
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
                new Vector4f(0.890f, 0.743f, 0.400f,1.0f)
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

        getChildObject().add(new EngineerMonkeyTail(
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

        getChildObject().add(new EngineerMonkeyBalloonList(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.84f,0.2f,0.15f,1.0f)
        ));

    }

    public float getArmCurrAngle() {
        return getChildObject().get(5).currAngleX;
    }

    public void spawnBalloon() {

        getChildObject().get(7).getChildObject().add(new Balloon(
                Arrays.asList(
                        new ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.84f,0.2f,0.15f,1.0f)
        ));

        int ballonCount = getChildObject().get(7).getChildObject().size();
        Vector3f ballonCenter = new Vector3f(getChildObject().get(7).getChildObject().get(ballonCount - 1).model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));
        System.out.println(ballonCenter);
        Object ballon = getChildObject().get(7).getChildObject().get(ballonCount - 1);

        ballon.scaleObject(0.6f, 0.6f, 0.6f);
        ballon.translateObject(-1.7f, -0.2f, 0f);


    }



    public void aiming(boolean reverse) {
        if (currAngleY >= -2 && currAngleY <= 2) {

            System.out.println(monkeySpawnPos + " -- " + getCenterPoint());
            if (!(getCenterPoint().get(2) > monkeySpawnPos.z)) {
                Object arm2 = getChildObject().get(5);
                Vector3f Arm2 = new Vector3f(arm2.model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));

                if (reverse) {
                    armRotation = 0.8f;
                } else {
                    armRotation = -0.8f;
                }

                if (!(arm2.currAngleX >= 314.4 && arm2.currAngleX <= 360)) {
                    if (arm2.currAngleX + armRotation >= 45 || arm2.currAngleX + armRotation <= 0)
                        armRotation = 0f;
                }

                arm2.translateObject(-Arm2.x, -Arm2.y, -Arm2.z);
                arm2.rotateObject((float) Math.toRadians(-armRotation), 1f, 0f, 0f);
                arm2.translateObject(Arm2.x, Arm2.y, Arm2.z);
                arm2.currAngleX += armRotation;
                arm2.currAngleX = checkAngle(arm2.currAngleX);


                System.out.println("aiming - " + arm2.currAngleX);
            } else {
                walk(true);
                translateObject(0f, 0f, -0.01f);
            }
        } else {
            Vector3f monkeyCenter = new Vector3f(model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));
//            System.out.println(monkeyCenter);
            translateObject(-monkeyCenter.x, -monkeyCenter.y, -monkeyCenter.z);

            if (currAngleY > 180) {
                rotateObject((float) Math.toRadians(2), 0.0f, 1.0f, 0.0f);
                currAngleY += 2;
            } else if (currAngleY > 0) {
                rotateObject((float) Math.toRadians(-2), 0.0f, 1.0f, 0.0f);
                currAngleY -= 2;
            }

            translateObject(monkeyCenter.x, monkeyCenter.y, monkeyCenter.z);

            currAngleY = checkAngle(currAngleY);
            System.out.println(currAngleY);
        }
    }

    public void shootBullet() {
        List<Object> bullets = getBulletList();
//        System.out.println(bullets.getClass().getName());
        for (Object bullet: bullets) {
            bullet.translateObject(0f, 0f, 0.1f);
            System.out.println(bullet.getCenterPoint());
            if (bullet.getCenterPoint().get(2) > 6 && getChildObject().get(7).getChildObject().size() > 0) {
//                getChildObject().get(7).getChildObject().remove(0);
                setDestroyBalloon(15);
            }
        }

        bullets.removeIf(bullet -> bullet.getCenterPoint().get(2) >= 6);

    }

    public List<Object> getBulletList() {
        return getChildObject().get(5).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).getChildObject();
    }

    public Object getMagazine() {
        return getChildObject().get(5).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0).getChildObject().get(0);
    }

    public void reload() {
        Object bullets = getMagazine();
        System.out.println(bullets.getName());
        bullets.generateBullet();
        System.out.println("reload loading");
    }

    public void walk(boolean reverse) {
        Object leg1 = getChildObject().get(2);
        Object leg2 = getChildObject().get(3);
        Object arm1 = getChildObject().get(4);
        Object arm2 = getChildObject().get(5);

        Vector3f Leg1 = new Vector3f(leg1.model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));
        Vector3f Leg2= new Vector3f(leg2.model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));
        Vector3f Arm1 = new Vector3f(arm1.model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));
        Vector3f Arm2 = new Vector3f(arm2.model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));

        if (leg1.currAngleX >= 352 && leg1.currAngleX <= 353) {

            legRotation = 0.3f;
        } else if (leg1.currAngleX >= 8 && leg1.currAngleX <= 9){

            legRotation = -0.3f;
        }

        if (arm1.currAngleX >= 314.4 && arm1.currAngleX <= 315){
            armRotation = 0.8f;
        } else
        if (arm1.currAngleX >= 45 && arm1.currAngleX <= 46 ) {
            armRotation = -0.8f;
        }

        leg1.translateObject(-Leg1.x, -Leg1.y, -Leg1.z);
        leg1.rotateObject((float) Math.toRadians(legRotation), 1f, 0f, 0f);
        leg1.translateObject(Leg1.x, Leg1.y, Leg1.z);

        leg2.translateObject(-Leg2.x, -Leg2.y, -Leg2.z);
        leg2.rotateObject((float) Math.toRadians(-legRotation), 1f, 0f, 0f);
        leg2.translateObject(Leg2.x, Leg2.y, Leg2.z);
        leg1.currAngleX += legRotation;
        leg2.currAngleX += legRotation;
//        System.out.println(rotation + " -- " + leg1.currAngle);
        arm1.translateObject(-Arm1.x, -Arm1.y, -Arm1.z);
        arm1.rotateObject((float) Math.toRadians(armRotation), 1f, 0f, 0f);
        arm1.translateObject(Arm1.x, Arm1.y, Arm1.z);

        arm2.translateObject(-Arm2.x, -Arm2.y, -Arm2.z);
        arm2.rotateObject((float) Math.toRadians(-armRotation), 1f, 0f, 0f);
        arm2.translateObject(Arm2.x, Arm2.y, Arm2.z);
        arm1.currAngleX += armRotation;
        arm2.currAngleX += armRotation;

        leg1.currAngleX = checkAngle(leg1.currAngleX);
        leg2.currAngleX = checkAngle(leg2.currAngleX);
        arm1.currAngleX = checkAngle(arm1.currAngleX);
        arm2.currAngleX = checkAngle(arm2.currAngleX);


//        System.out.println("walk - " + arm2.currAngleX);
    }

    public void setShakingTime1(int shakingTime1) {
        this.shakingTime1 = shakingTime1;
    }

    public void setDestroyBalloon(int destroyBalloon) {
        this.destroyBalloon = destroyBalloon;
    }

    public void shakingHeadUpDown() {
        if (shakingTime1 < 0) return;
            Vector3f head = new Vector3f(getChildObject().get(1).model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));

        if (shakingTime1 > 70) {
            getChildObject().get(1).translateObject(-head.x, -head.y, -head.z);
            getChildObject().get(1).rotateObject((float) Math.toRadians(1), 1f, 0f, 0f);
            getChildObject().get(1).translateObject(head.x, head.y, head.z);
        } else if (shakingTime1 > 40) {
            getChildObject().get(1).translateObject(-head.x, -head.y, -head.z);
            getChildObject().get(1).rotateObject((float) Math.toRadians(-1), 1f, 0f, 0f);
            getChildObject().get(1).translateObject(head.x, head.y, head.z);
        } else if (shakingTime1 > 20) {
            getChildObject().get(1).translateObject(-head.x, -head.y, -head.z);
            getChildObject().get(1).rotateObject((float) Math.toRadians(1), 1f, 0f, 0f);
            getChildObject().get(1).translateObject(head.x, head.y, head.z);
        } else {
            getChildObject().get(1).translateObject(-head.x, -head.y, -head.z);
            getChildObject().get(1).rotateObject((float) Math.toRadians(-1), 1f, 0f, 0f);
            getChildObject().get(1).translateObject(head.x, head.y, head.z);
        }

        shakingTime1--;
        System.out.println(shakingTime1);
    }

    public void destroyBalloon() {
        if (destroyBalloon < 0) return;
        if (getChildObject().get(7).getChildObject().size() > 0) {
            Object balloon = getChildObject().get(7).getChildObject().get(0);

            if (destroyBalloon == 7) {
                balloon.scaleObject(1.5f, 1.5f, 1.5f);
                balloon.translateObject(0.8f,0f,0f);
            }

            if (destroyBalloon == 5) {
                getChildObject().get(7).getChildObject().remove(0);
            }
        }
        destroyBalloon--;
    }

    public float checkAngle(float angle) {
        System.out.println("checked -- " + angle);
        if (angle > 360) {
            angle -= 360;
        } else if (angle < 0) {
            angle += 360;
        }
        return angle;
    }
}
