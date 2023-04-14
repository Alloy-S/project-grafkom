import Engine.*;
import Engine.EngineerMonkey.EngineerMonkey;
import Engine.Object;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window =
            new Window
    (1200,1200,"Hello World");
    private ArrayList<Object> dartMonkey= new ArrayList<>();
    private ArrayList<Object> ninjaMonkey= new ArrayList<>();
    private ArrayList<Object> engineerMonkey= new ArrayList<>();

    private MouseInput mouseInput;
    Projection projection = new Projection(window.getWidth(),window.getHeight());
    Camera camera = new Camera();
    public void init(){
        window.init();
        GL.createCapabilities();
        mouseInput = window.getMouseInput();
        camera.setPosition(0,0,1f);
        camera.setRotation((float)Math.toRadians(0.0f),(float)Math.toRadians(0.0f));
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
            new Vector4f(0.44f,0.24f,0.12f,1.0f)
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
                new Vector4f(0.44f,0.24f,0.12f,1.0f)
        ));



    }
    public void input(){
        if (window.isKeyPressed(GLFW_KEY_D)) {
            camera.moveRight(0.02f);
        }
        if (window.isKeyPressed(GLFW_KEY_A)){
            camera.moveLeft(0.02f);
        }
        if (window.isKeyPressed(GLFW_KEY_SPACE)) {
            camera.moveUp(0.02f);
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)){
            camera.moveDown(0.02f);
        }
        if (window.isKeyPressed(GLFW_KEY_W)) {
            camera.moveForward(0.02f);
        }
        if (window.isKeyPressed(GLFW_KEY_S)){
            camera.moveBackwards(0.02f);
        }
        if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            camera.addRotation(0.0f,0.01f);
        }
        if (window.isKeyPressed(GLFW_KEY_LEFT)){
            camera.addRotation(0.0f,-0.01f);
        }
        if (window.isKeyPressed(GLFW_KEY_UP)) {
            camera.addRotation(-0.01f,0.0f);
        }
        if (window.isKeyPressed(GLFW_KEY_DOWN)){
            camera.addRotation(0.01f,0.0f);
        }

        if (window.isKeyPressed(GLFW_KEY_I)){
            dartMonkey.get(0).rotateObject(0.1f,0.0f,1.0f,0.0f);
        }

        if (window.isKeyPressed(GLFW_KEY_U)){
            engineerMonkey.get(0).rotateObject(0.1f,0.0f,1.0f,0.0f);
        }
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
            for(Object object: dartMonkey){
                object.draw(camera,projection);
            }

            for(Object object: engineerMonkey){
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