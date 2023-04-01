import Engine.*;
import Engine.Object;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main3 {
    private Window window = new Window(800, 800, "Hello World");
    ArrayList<Sphere> objects = new ArrayList<>();


    private boolean leftBottonMouse = false;

    public void init() {
        window.init();
        GL.createCapabilities();

        // code here
        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ), new ArrayList<>(),
                new Vector4f(0.940f, 0.928f, 0.216f, 1f),
                new Vector3f(0f, 0f, 0f),
                new Vector3f(0.1f, 0.1f, 0.1f)
        ));

        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ), new ArrayList<>(),
                new Vector4f(0.590f, 0.555f, 0.555f, 1f),
                new Vector3f(0f, 0f, 0f),
                new Vector3f(0.1f, 0.1f, 0.1f)
        ));

        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ), new ArrayList<>(),
                new Vector4f(0.890f, 0.265f, 0.169f, 1f),
                new Vector3f(0f, 0f, 0f),
                new Vector3f(0.1f, 0.1f, 0.1f)
        ));

        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ), new ArrayList<>(),
                new Vector4f(0.216f, 0.940f, 0.831f, 1f),
                new Vector3f(0f, 0f, 0f),
                new Vector3f(0.1f, 0.1f, 0.1f)
        ));

        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ), new ArrayList<>(),
                new Vector4f(0.880f, 0.00f, 0.0147f, 1f),
                new Vector3f(0f, 0f, 0f),
                new Vector3f(0.1f, 0.1f, 0.1f)
        ));

        objects.add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ), new ArrayList<>(),
                new Vector4f(0.380f, 0.357f, 0.358f, 1f),
                new Vector3f(0f, 0f, 0f),
                new Vector3f(0.1f, 0.1f, 0.1f)
        ));

        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ), new ArrayList<>(),
                new Vector4f(0.380f, 0.357f, 0.358f, 1f),
                new Vector3f(0f, 0f, 0f),
                new Vector3f(0.1f, 0.1f, 0.1f)
        ));

        objects.get(0).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ), new ArrayList<>(),
                new Vector4f(0.380f, 0.357f, 0.358f, 1f),
                new Vector3f(0f, 0f, 0f),
                new Vector3f(0.1f, 0.1f, 0.1f)
        ));

        objects.get(0).getChildObject().get(1).getChildObject().add(new Sphere(
                Arrays.asList(
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
                ), new ArrayList<>(),
                new Vector4f(0.380f, 0.357f, 0.358f, 1f),
                new Vector3f(0f, 0f, 0f),
                new Vector3f(0.1f, 0.1f, 0.1f)
        ));

        objects.get(0).getChildObject().get(0).translateObject(0.25f, 0f, 0f);
        objects.get(0).getChildObject().get(1).translateObject(0.4f, 0f, 0f);
        objects.get(0).getChildObject().get(1).getChildObject().get(0).translateObject(0.4f, 0f, 0f);
//        objects.get(0).translateObject(0.0f, 0f, 0f);
        objects.get(0).scaleObject(1f, 1f, 1f);
//        objects.get(1).scaleObject(0.5f, 0.5f, 0.5f);

        objects.get(1).scaleObject(0.08f, 0.08f, 0.08f);
        objects.get(1).translateObject(0.25f, 0f, 0f);


        objects.get(2).scaleObject(0.12f, 0.12f, 0.12f);
        objects.get(2).translateObject(0.38f, 0f, 0f);


        objects.get(3).scaleObject(0.18f, 0.18f, 0.18f);
        objects.get(3).translateObject(0.55f, 0f, 0f);


        objects.get(4).scaleObject(0.13f, 0.13f, 0.13f);
        objects.get(4).translateObject(0.73f, 0f, 0f);

        objects.get(5).scaleObject(0.08f, 0.08f, 0.08f);
        objects.get(5).translateObject(0.62f, 0.1f, 0f);
    }

    public void input() {
        if (window.isKeyPressed(GLFW_KEY_W)) {
            objects.get(0).rotateObject((float) Math.toRadians(0.1), 0f,0f, 1f);

            for (Object child: objects.get(0).getChildObject()) {
                Vector3f tempCenterPoint = child.updateCenterPoint();
                child.translateObject(tempCenterPoint.x * -1, tempCenterPoint.y * -1, tempCenterPoint.z * -1);
                child.rotateObject((float) Math.toRadians(0.5), 0f, 0f, 1f);
                child.translateObject(tempCenterPoint.x, tempCenterPoint.y, tempCenterPoint.z);
            }
        }
        if (window.isKeyPressed(GLFW_KEY_G)) {
            System.out.println("W");
////            objects.get(0).translateObject(0.005f, 0f, 0f);
////            objects.get(1).rotateObject((float) Math.toRadians((0.25f * objects.get(1).counter) * -1), 0f, 1f, 0f);
//            Vector3f merkuri = new Vector3f(objects.get(1).model.transformPosition(new Vector3f(0.0f,0.0f,0.0f)));
//            objects.get(1).translateObject(-merkuri.x, 0f, 0f);
//            objects.get(2).translateObject(-0.38f, 0f, 0f);
//            objects.get(3).translateObject(-0.55f, 0f, 0f);
//            objects.get(4).translateObject(-0.73f, 0f, 0f);
//
//
//            objects.get(1).rotateObject((float) Math.toRadians(0.25),0f, 1f, 0f);
//            objects.get(2).rotateObject((float) Math.toRadians(0.25),0f, 1f, 0f);
//            objects.get(3).rotateObject((float) Math.toRadians(0.25),0f, 1f, 0f);
//            objects.get(4).rotateObject((float) Math.toRadians(0.25),0f, 1f, 0f);
//
//            objects.get(1).rotateObject((float) Math.toRadians((0.25f * objects.get(1).counter)), 0f, 1f, 0f);
//            objects.get(1).translateObject(0.25f, 0f, 0f);
//            objects.get(2).translateObject(0.38f, 0f, 0f);
//            objects.get(3).translateObject(0.55f, 0f, 0f);
//            objects.get(4).translateObject(0.73f, 0f, 0f);
//            objects.get(2).translateObject(0.4f, 0f, 0f);
//            objects.get(0).scaleObject(0.9f, 0.9f, 0.9f);


            //            matahari
//            objects.get(0).translateObject(0.5f,0.0f,0.0f);
            Vector3f matahari = new Vector3f(objects.get(0).model.transformPosition(new Vector3f(0.0f,0.0f,0.0f)));
            objects.get(0).translateObject(-matahari.x,-matahari.y,0f);
            objects.get(0).rotateObject((float) Math.toRadians(0.5f),0f,0f,1f);
            objects.get(0).translateObject(matahari.x,matahari.y,0f);

//            merkuri
            Vector3f merkuri = new Vector3f(objects.get(1).model.transformPosition(new Vector3f(0.0f,0.0f,0.0f)));
            objects.get(1).translateObject(-merkuri.x,-merkuri.y,0f);
            objects.get(1).rotateObject((float) Math.toRadians(0.5f),0f,0f,1f);
            objects.get(1).translateObject(merkuri.x,merkuri.y,0f);

//            venus
            Vector3f venus = new Vector3f(objects.get(2).model.transformPosition(new Vector3f(0.0f,0.0f,0.0f)));
            objects.get(2).translateObject(-venus.x,-venus.y,0f);
            objects.get(2).rotateObject((float) Math.toRadians(0.5f),0f,0f,1f);
            objects.get(2).translateObject(venus.x,venus.y,0f);
//            mars
            Vector3f mars = new Vector3f(objects.get(3).model.transformPosition(new Vector3f(0.0f,0.0f,0.0f)));
            objects.get(3).translateObject(-mars.x,-mars.y,0f);
            objects.get(3).rotateObject((float) Math.toRadians(0.5f),0f,0f,1f);
            objects.get(3).translateObject(mars.x,mars.y,0f);
//            bumi
            Vector3f bumi = new Vector3f(objects.get(4).model.transformPosition(new Vector3f(0.0f,0.0f,0.0f)));
            objects.get(4).translateObject(-bumi.x,-bumi.y,0f);
            objects.get(4).rotateObject((float) Math.toRadians(0.5f),0f,0f,1f);
            objects.get(4).translateObject(bumi.x,bumi.y,0f);
            //            bulan

            Vector3f bulan = new Vector3f(objects.get(5).model.transformPosition(new Vector3f(0.0f,0.0f,0.0f)));
            objects.get(5).translateObject(-bulan.x,-bulan.y,0f);
            objects.get(5).rotateObject((float) Math.toRadians(0.5f),0f,0f,1f);
            objects.get(5).translateObject(bulan.x,bulan.y,0f);
        }

        if (window.isKeyPressed(GLFW_KEY_F)) {
//            objects.get(1).translateObject(0.005f, 0f, 0f);

            objects.get(0).rotateObject((float) Math.toRadians(0.5),0f, 1f, 0f);
            objects.get(1).counter++;

            objects.get(1).rotateObject((float) Math.toRadians(0.5),0f, 1f, 0f);

//            objects.get(2).translateObject(0.002f, 0f, 0f);
            objects.get(2).rotateObject((float) Math.toRadians(0.5),0f, 1f, 0f);
//            objects.get(3).translateObject(0.007f, 0f, 0f);
            objects.get(3).rotateObject((float) Math.toRadians(0.5),0f, 1f, 0f);

            objects.get(4).rotateObject((float) Math.toRadians(0.5),0f, 1f, 0f);

            objects.get(5).rotateObject((float) Math.toRadians(0.5),0f, 1f, 0f);
        }

        if (window.isKeyPressed(GLFW_KEY_H)) {
//            objects.get(3).translateObject(-0.55f, 0f, 0f);
//            objects.get(5).translateObject(-0.55f, 0f, 0f);
//
//            objects.get(5).rotateObject((float) Math.toRadians(0.5),0f, 1f, 0f);
//
//            objects.get(3).translateObject(0.55f, 0f, 0f);
//            objects.get(5).translateObject(0.55f, 0f, 0f);

            Vector3f posisi = objects.get(3).model.transformPosition(new Vector3f(0,0,0));
            objects.get(5).translateObject(-posisi.x,-posisi.y,0f);
            objects.get(5).rotateObject((float) Math.toRadians(0.5f),0f,0.0f,1.0f);
            objects.get(5).translateObject(posisi.x,posisi.y,0f);
        }
    }
    public void loop() {
        while (window.isOpen()) {
            window.update();
            glClearColor(0f, 0f, 0f, 1.0f);
            input();
            GL.createCapabilities();
//            input();


            // code here
            for (Sphere object : objects) {
                object.draw();
            }

//            for (Rectangle object : objectsRectangle) {
//                object.draw();
//            }
//
//            for (Circle object : objectsCircle) {
//                object.draw();
//            }
//
//            for (Star object : objectsStar) {
//                object.draw();
//            }
//
//            for (Persegi object : objectPersegi) {
//                object.draw();
//            }
//
//            for (Triangle object : objectsTriangle) {
//                object.draw();
//            }
//
//            for (Object object : objectsPointControl) {
//                object.drawLine();
//            }


            // restore stage
            glDisableVertexAttribArray(0);
            // poll for window events
            // the key callback above will only be
            //invoked during this call
            glfwPollEvents();
        }
    }



    public void run() {
        init();
        loop();

        // terminate GLFW and free the error callback

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }


    public static void main(String[] args) {
        new Main3().run();
    }
}

