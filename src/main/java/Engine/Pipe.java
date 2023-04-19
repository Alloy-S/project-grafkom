package Engine;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Pipe extends Object{
    Vector3f center, radius;
    float height;
    float lineWidth;
    float putaran;

    public Pipe(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, Vector3f center, Vector3f radius, float height, float lineWidth, float putaran) {
        super(shaderModuleDataList, vertices, color);
        this.center = center;
        this.radius = radius;
        this.height = height;
        this.lineWidth = lineWidth;
        this.putaran = putaran;
        createSilinder();
        setupVAOVBO();

    }

    private void createSilinder() {
        vertices.clear();

            for (float angle = 0; angle < putaran; angle += 0.1) {

//                buat lingkaran luar
                float x1 = (float) (radius.x * Math.cos(Math.toRadians(angle)) + center.x);
                float y1 = (float) (radius.y * Math.sin(Math.toRadians(angle)) + center.y);
//                buat lingkaran dalam
                float x2 = (float) ((radius.x - lineWidth) * Math.cos(Math.toRadians(angle)) + center.x);
                float y2 = (float) ((radius.y - lineWidth) * Math.sin(Math.toRadians(angle)) + center.y);
//                lingkaran bawah luar
                vertices.add(new Vector3f(x1, y1, center.z - height));
//                lingkaran atas luar
                vertices.add(new Vector3f(x1, y1, center.z + height));
//                lingkaran bawah dalam
                vertices.add(new Vector3f(x2, y2, center.z + height));
//                lingkaran bawah dalam
                vertices.add(new Vector3f(x2, y2, center.z - height));


        }
    }

    public void draw(Camera camera, Projection projection) {
        drawSetup(camera, projection);
        // draw the vertices
        glLineWidth(1);
        glPointSize(0);
        glDrawArrays(GL_LINE_LOOP, 0, vertices.size());
//        glDrawArrays(GL_POLYGON, 0, vertices.size());
//        glDrawArrays(GL_TRIANGLE_FAN, 0, vertices.size());
        for(Object child:childObject){
            child.draw(camera,projection);
        }
    }

    public void rotateObject(Float degree, Float x,Float y,Float z){
        super.rotateObject(degree, x, y, z);

//        System.out.println(centerPoint);


    }
}
