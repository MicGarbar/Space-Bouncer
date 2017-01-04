package spaceBouncer.entity.effects;

import spaceBouncer.entity.features.HazeFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

import java.util.HashMap;
import java.util.Map;

public class Haze implements HazeFeatures {

    private Vector position;
    private VertexArrayObject vao;
    private Shader shader;

    private float hazeRecallTime = 1.0f;
    private float red = 1.0f;
    private float green = 1.0f;
    private float blue = 1.0f;

    public Haze(){
        position = new Vector(0.0f, 0.0f);
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));
    }

    public void update(){
        hazeRecallTime += 0.01f;
    }

    public void render(){
        shader.activate();
        shader.setFloatUniform("hazeRecallTime", hazeRecallTime);
        shader.setFloatUniform("red", red);
        shader.setFloatUniform("green", green);
        shader.setFloatUniform("blue", blue);
        vao.bind();
        vao.draw();
        vao.unbind();
        shader.deactivate();
    }

    public void setHazeRecallTime(float hazeRecallTime) {
        this.hazeRecallTime = hazeRecallTime;
    }

    public void setColor(float red, float green, float blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
}
