package spaceBouncer.entity.effects;

import spaceBouncer.entity.features.HazeFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

public class Haze implements HazeFeatures {

    private Vector position;
    private VertexArrayObject vao;
    private Shader shader;

    private float hazeRecallTime = 1.0f;

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
        vao.bind();
        vao.draw();
        vao.unbind();
        shader.deactivate();
    }

    public void setHazeRecallTime(float hazeRecallTime) {
        this.hazeRecallTime = hazeRecallTime;
    }
}
