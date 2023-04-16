import Engine.*;
import Engine.DartMonkey.*;
import Engine.EngineerMonkey.*;
import Engine.NinjaMonkey.*;
import Engine.Object;

import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window =
            new Window
                    (1200, 1200, "Bloons TD Monkey");
    private ArrayList<DartMonkey> dartMonkey = new ArrayList<>();
    private ArrayList<Object> ninjaMonkey = new ArrayList<>();
    private ArrayList<Object> engineerMonkey = new ArrayList<>();

    private boolean leftMouseButton = false;

    float currAngle = 0;
    float angleDegree;
    long lastTime = 0;
    private MouseInput mouseInput;
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    Camera camera = new Camera();

    public void init() {
        window.init();
        GL.createCapabilities();
        mouseInput = window.getMouseInput();
        camera.setPosition(-1f, 0, 3f);
        camera.setRotation((float) Math.toRadians(0.0f), (float) Math.toRadians(0.0f));
        //code
        dartMonkey.add(new DartMonkey(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.44f, 0.24f, 0.12f, 1.0f)
        ));

        ninjaMonkey.add(new NinjaMonkey(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(245f, 0.0f, 0.0f, 1.0f)
        ));

        engineerMonkey.add(new EngineerMonkey(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.vert"
                                        , GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData
                                ("resources/shaders/scene.frag"
                                        , GL_FRAGMENT_SHADER)
                ),
                new ArrayList<>(),
                new Vector4f(0.44f, 0.24f, 0.12f, 1.0f)
        ));


    }

    public void input() {
        float cameraSpeed = 0.04f;

        if (window.isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(cameraSpeed);
        }

        if (window.isKeyPressed(GLFW_KEY_1)) {
            camera.moveRight(cameraSpeed);
            camera.addRotation(0.0f, -0.01f);
        }

        if (window.isKeyPressed(GLFW_KEY_2)) {
            camera.moveLeft(cameraSpeed);
            camera.addRotation(0.0f, 0.01f);
        }

        if (window.isKeyPressed(GLFW_KEY_3)) {
            camera.moveForward(cameraSpeed);
            camera.addRotation(0.01f, 0.0f);
        }

        if (window.isKeyPressed(GLFW_KEY_4)) {
            camera.moveBackwards(cameraSpeed);
            camera.addRotation(-0.01f, 0.0f);
        }

        if (window.isKeyPressed(GLFW_KEY_A)) {
            camera.moveLeft(cameraSpeed);
        }
        if (window.isKeyPressed(GLFW_KEY_SPACE)) {
            camera.moveUp(cameraSpeed);
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
            camera.moveDown(cameraSpeed);
        }
        if (window.isKeyPressed(GLFW_KEY_W)) {
            camera.moveForward(cameraSpeed);
        }
        if (window.isKeyPressed(GLFW_KEY_S)) {
            camera.moveBackwards(cameraSpeed);
        }
        if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            camera.addRotation(0.0f, 0.01f);
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            camera.addRotation(0.0f, -0.01f);
        }
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            camera.addRotation(-0.01f, 0.0f);
        }
        if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            camera.addRotation(0.01f, 0.0f);
        }

        if (window.isKeyPressed(GLFW_KEY_B)) {

            dartMonkey.get(0).rotateObject(0.1f, 0.0f, 1.0f, 0.0f);
        }

//      ============================== key for engineer Monkey =============================

//        bikin eror
        if (window.isKeyPressed(GLFW_KEY_Y)) {
            Object monkey = engineerMonkey.get(0);
            Vector3f monkeyCenter = new Vector3f(monkey.model.transformPosition(new Vector3f(0.0f, 0f, 0.0f)));
//            System.out.println(monkeyCenter);
            monkey.translateObject(-monkeyCenter.x, -monkeyCenter.y, -monkeyCenter.z);
            if (window.isKeyPressed(GLFW_KEY_LEFT_ALT)) {
                monkey.rotateObject((float) Math.toRadians(-2), 0.0f, 1.0f, 0.0f);
                engineerMonkey.get(0).currAngleY += -2;
            } else {
                monkey.rotateObject((float) Math.toRadians(2), 0.0f, 1.0f, 0.0f);
                engineerMonkey.get(0).currAngleY += 2;
            }
            monkey.translateObject(monkeyCenter.x, monkeyCenter.y, monkeyCenter.z);


            if (engineerMonkey.get(0).currAngleY > 360) {
                engineerMonkey.get(0).currAngleY -= 360;
            } else if (engineerMonkey.get(0).currAngleY < 0) {
                engineerMonkey.get(0).currAngleY += 360;
            }

            System.out.println(engineerMonkey.get(0).currAngleY);
        }

        if (window.isKeyPressed(GLFW_KEY_U)){
            engineerMonkey.get(0).walk(false);
            engineerMonkey.get(0).translateObject(0f, 0f, 0.01f);
//            System.out.println(engineerMonkey.get(0).getCenterPoint());
        }

        if (window.getMouseInput().isLeftButtonPressed()){
//            Date date = new Date();
            System.out.println(System.currentTimeMillis() + " -- " + lastTime);
            if (System.currentTimeMillis() > lastTime) {
                if (!leftMouseButton && engineerMonkey.get(0).getArmCurrAngle() >= 44) {
                    if (engineerMonkey.get(0).currAngleY >= -2 && engineerMonkey.get(0).currAngleY <= 2) {
                        engineerMonkey.get(0).reload();
                        lastTime = System.currentTimeMillis() + 250;

                        System.out.println("reload mag");
                    }
                }
            }

//            leftMouseButton = true;
        }

        if (window.getMouseInput().isLeftButtonRelease()){
            leftMouseButton = false;
        }

        if (window.isKeyPressed(GLFW_KEY_I)){

            if (window.isKeyPressed(GLFW_KEY_LEFT_ALT)) {
                engineerMonkey.get(0).aiming(false);
//                System.out.println("reverse");
            } else {
                engineerMonkey.get(0).aiming(true);
//                System.out.println("normal");
            }
        }
        if (window.isKeyPressed(GLFW_KEY_I)){

        }


//        ======================= key for dart Monkey =============================

        // THIS IS FRAME BASED BUT OPENGL ISNT
        // JANGAN LEBIH DARI 2x PER RUN
        if (window.isKeyPressed(GLFW_KEY_H) && dartMonkey.get(0).getTotalTime() < 0) {
            dartMonkey.get(0).setLookTime(80);
        }
        if (window.isKeyPressed(GLFW_KEY_J) && dartMonkey.get(0).getTotalTime() < 0) {
            dartMonkey.get(0).setScratchTime(80);
        }
        if (window.isKeyPressed(GLFW_KEY_K) && dartMonkey.get(0).getTotalTime() < 0) {
            dartMonkey.get(0).setThrowTime(80);
        }

//       ======================= key for ninja Monkey =============================
        if (window.isKeyPressed(GLFW_KEY_5)) {
            ninjaMonkey.get(0).rotateObject(0.1f, 0.0f, 1.0f, 0.0f);
        }

        if (window.isKeyPressed(GLFW_KEY_6)) {
            ninjaMonkey.get(0).getChildObject().get(3).getChildObject().get(0).rotateShuriken();
        }

        if (window.isKeyPressed(GLFW_KEY_7)) {
            System.out.print(currAngle);
            angleDegree = 90f;
            while (currAngle < angleDegree) {
                ninjaMonkey.get(0).rotateObject((float) Math.toRadians(1), 0.0f, 1.0f, 0.0f);
                currAngle += 1f;
                if (currAngle >= angleDegree) {
                    System.out.print(currAngle);
                    break;
                }
            }
        }
        if (window.isKeyPressed(GLFW_KEY_8)) {
            angleDegree = -90f;
            System.out.print(currAngle);
            while (currAngle > angleDegree) {
                ninjaMonkey.get(0).rotateObject((float) Math.toRadians(-1), 0.0f, 1.0f, 0.0f);
                currAngle -= 1f;
                if (currAngle <= angleDegree) {
                    System.out.print(currAngle);
                    break;
                }
            }
        }
        if (window.isKeyPressed(GLFW_KEY_9)) {
            angleDegree = 0;
            while (currAngle < angleDegree || currAngle > angleDegree) {
                if (currAngle > angleDegree) {
                    ninjaMonkey.get(0).rotateObject((float) Math.toRadians(-1), 0.0f, 1.0f, 0.0f);
                    currAngle -= 1f;
                    if (currAngle <= angleDegree) {
                        System.out.print(currAngle);
                        break;
                    }
                }
                else {
                    ninjaMonkey.get(0).rotateObject((float) Math.toRadians(1), 0.0f, 1.0f, 0.0f);
                    currAngle += 1f;
                    if (currAngle >= angleDegree) {
                        System.out.print(currAngle);
                        break;
                    }
                }
            }
        }
        if (window.isKeyPressed(GLFW_KEY_0)) {
            angleDegree = 180;
            System.out.print(currAngle);
            while (currAngle < angleDegree || currAngle > angleDegree) {
                if (currAngle > angleDegree) {
                    ninjaMonkey.get(0).rotateObject((float) Math.toRadians(1), 0.0f, -1.0f, 0.0f);
                    currAngle -= 1f;
                    if (currAngle <= angleDegree) {
                        System.out.print(currAngle);
                        break;
                    }
                }
                else {
                    ninjaMonkey.get(0).rotateObject((float) Math.toRadians(-1), 0.0f, -1.0f, 0.0f);
                    currAngle += 1f;
                    if (currAngle >= angleDegree) {
                        System.out.print(currAngle);
                        break;
                    }
                }
            }
        }
//       ======================= key for ninja Monkey =============================
    }

    public void loop(){
        while (window.isOpen()) {
            window.update();
            glClearColor(0.0f,
                    0.0f, 0.0f,
                    0.0f);
            GL.createCapabilities();
            input();

            //code
//            dartMonkey.get(0).look();
//            dartMonkey.get(0).scratch();
//            dartMonkey.get(0).dartThrow();
//
//            for(Object object: dartMonkey){
//                object.draw(camera,projection);
//            }

            for(Object object: engineerMonkey){
                object.draw(camera,projection);
            }
//
//            engineerMonkey.get(0).aiming(true);
//

            if (engineerMonkey.get(0).getBulletList().size() > 0) {
                engineerMonkey.get(0).shootBullet();
            }

            for(Object object: ninjaMonkey){
                object.draw(camera,projection);
            }

            // Restore state
            glDisableVertexAttribArray(0);

            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
    public void run() {

        init();
        loop();

        // Terminate GLFW and
        // free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    public static void main(String[] args) {
        new Main().run();
    }
}