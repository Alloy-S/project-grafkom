package Engine.DartMonkey;

import Engine.Camera;
import Engine.Object;
import Engine.Projection;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_POLYGON;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;

public class DartMonkeyArm1 extends Object {
    float radius;
    float offsetX, offsetY, offsetZ;

    List<List<Vector3f>> totalVertices = new ArrayList<>();
    public DartMonkeyArm1(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color) {
        super(shaderModuleDataList, vertices, color);
        vertices.clear();

        radius = 0.1f;
        vertices.add(new Vector3f(0.0f,0.0f,0.0f));
        vertices.add(new Vector3f(-1.4f,0.25f,0.0f));
        vertices.add(new Vector3f(-0.7f,0.2f,0.0f));
        vertices.add(new Vector3f(-1.1f,1.0f,0.0f));
        generate();
        setupVAOVBO();
        this.offsetX = 0.0f;
        this.offsetY = 0.2f;
        this.offsetZ = 0.0f;
//        rotateObject(-0.2f,1.0f,0.0f,0.0f);
        translateObject(offsetX, offsetY, offsetZ);
    }

    public void generate(){
        List<Vector3f> tempVertices = new ArrayList<>();
        for (int i=0; i<vertices.size(); i++){
            tempVertices.add(vertices.get(i));
        }
        vertices.clear();
        double interval = 0.05;
        for (double i=0; i<=1; i+=interval){
            vertices.add(new Vector3f(calculateBezierPoint((float) i, tempVertices)));
        }
        generate2();

        List<Object> children = new ArrayList<>();
        children.add(new DartMonkeyHand1(
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
        setChildObject(children);
    }

    public static Vector3f calculateBezierPoint(float t, List<Vector3f> points) {
        int n = points.size() - 1;
        float x = 0, y = 0, z = 0;

        for (int i = 0; i <= n; i++) {
            double coefficient = calculateCoefficient(n, i, t);
            x += coefficient * points.get(i).x;
            y += coefficient * points.get(i).y;
            z += coefficient * points.get(i).z;
        }

        return new Vector3f(x, y, z);
    }

    private static double calculateCoefficient(int n, int i, double t) {
        return binomialCoefficient(n, i) * Math.pow(t, i) * Math.pow(1 - t, n - i);
    }

    private static int binomialCoefficient(int n, int k) {
        if (k == 0 || k == n) {
            return 1;
        } else {
            return binomialCoefficient(n - 1, k - 1) + binomialCoefficient(n - 1, k);
        }
    }


    public void generate2(){
        // https://stackoverflow.com/questions/61047848/plot-a-point-in-3d-space-perpendicular-to-a-line-vector

        List<Vector3f> tempVertices = new ArrayList<>();
        for (int i=0; i<vertices.size(); i++){
            tempVertices.add(vertices.get(i));
        }
        vertices.clear();

        for (int i=0; i<tempVertices.size()-1; i++){
            List<Vector3f> segmentVertices = new ArrayList<>();

            Vector3f p1 = tempVertices.get(i);
            Vector3f p2 = tempVertices.get(i+1);
            float dx = p2.x - p1.x;
            float dy = p2.y - p1.y;
            float dz = p2.z - p1.z;

            float d = (float) Math.sqrt(dx*dx + dy*dy + dz*dz);
            if(d==0) d=1;

            // normal vector
            List<Float> p = new ArrayList<>();
            float dxx = dx/d;
            float dyy = dy/d;
            float dzz = dz/d;
            if (Math.abs(dxx) >= Math.abs(dyy) && Math.abs(dyy) >= Math.abs(dzz)){p = List.of(dyy, -dxx, 0f);} // dxx dyy dzz
            if (Math.abs(dxx) >= Math.abs(dzz) && Math.abs(dzz) >= Math.abs(dyy)){p = List.of(dzz, 0f, -dxx);} // dxx dzz dyy
            if (Math.abs(dyy) >= Math.abs(dxx) && Math.abs(dxx) >= Math.abs(dzz)){p = List.of(-dyy, dxx, 0f);} // dyy dxx dzz
            if (Math.abs(dyy) >= Math.abs(dzz) && Math.abs(dzz) >= Math.abs(dxx)){p = List.of(0f, dzz, -dyy);} // dyy dzz dxx
            if (Math.abs(dzz) >= Math.abs(dxx) && Math.abs(dxx) >= Math.abs(dyy)){p = List.of(-dzz, 0f, dxx);} // dzz dxx dyy
            if (Math.abs(dzz) >= Math.abs(dyy) && Math.abs(dyy) >= Math.abs(dxx)){p = List.of(0f, -dzz, dyy);} // dzz dyy dxx

            Vector3f pp = new Vector3f(p.get(0), p.get(1), p.get(2));
            pp.normalize();

            Vector3f v3f = new Vector3f(dx/d, dy/d, dz/d);
            Vector3f b = new Vector3f();
            v3f.cross(pp.x, pp.y, pp.z, b);

            for(float k = 0; k<360; k+=10){
                double theta = Math.toRadians(k);
                float x = p1.x + radius*(float)Math.cos(theta)*pp.x + radius*(float)Math.sin(theta)*b.x;
                float y = p1.y + radius*(float)Math.cos(theta)*pp.y + radius*(float)Math.sin(theta)*b.y;
                float z = p1.z + radius*(float)Math.cos(theta)*pp.z + radius*(float)Math.sin(theta)*b.z;
                segmentVertices.add(new Vector3f(x,y,z));
            }
            List<Vector3f> orderedSegmentVertices = new ArrayList<>();
            //change this shit kalo ngebug
            int start = 0;
            if(i>=8) start-=18;

            start += 10*segmentVertices.size();
            start %= segmentVertices.size();
            orderedSegmentVertices.addAll(segmentVertices.subList(start, segmentVertices.size()));
            orderedSegmentVertices.addAll(segmentVertices.subList(0, start+1));

            //change this shit buat debug
//            if(7 <= i && i <= 8)
            totalVertices.add(orderedSegmentVertices);
        }


    }

    public void draw(Camera camera, Projection projection){
        for(int i=0; i<totalVertices.size()-1; i++){
            vertices.clear();
            for(int j=0; j<totalVertices.get(i).size(); j++){
                vertices.add(totalVertices.get(i).get( j % totalVertices.get(i+1).size() ));
                vertices.add(totalVertices.get(i+1).get( j % totalVertices.get(i+1).size() ));
                vertices.add(totalVertices.get(i+1).get( (j+1) % totalVertices.get(i+1).size() ));
                vertices.add(totalVertices.get(i).get( (j+1) % totalVertices.get(i+1).size() ));
                vertices.add(totalVertices.get(i).get( j % totalVertices.get(i+1).size() ));
            }
            drawSegment(camera, projection);
        }

        for(Object child:childObject){
            child.draw(camera,projection);
        }
    }
    public void drawSegment(Camera camera, Projection projection){
        setupVAOVBO();
        drawSetup(camera, projection);
        glLineWidth(10); //ketebalan garis
        glPointSize(10); //besar kecil vertex
        glDrawArrays(GL_POLYGON, 0, vertices.size());
    }

    public void rotateObject(Float degree, Float x,Float y,Float z){
        translateObject(-offsetX, -offsetY, -offsetZ);
        model = new Matrix4f().rotate(degree,x,y,z).mul(new Matrix4f(model));
        updateCenterPoint();
        translateObject(offsetX, offsetY, offsetZ);
        for(Object child:childObject){
//            child.translateObject(-offsetX, -offsetY, -offsetZ);
            child.rotateObject(degree,x,y,z);
//            child.translateObject(offsetX, offsetY, offsetZ);
        }
    }
}