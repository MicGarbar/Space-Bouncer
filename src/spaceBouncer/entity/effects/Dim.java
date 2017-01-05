package spaceBouncer.entity.effects;

import spaceBouncer.entity.features.DimFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

public class Dim implements DimFeatures {

    private Vector position;
    private VertexArrayObject vao;
    private Shader shader;

    private float dimRecallTime = 1.0f;
    private float deltaRecallTime = -0.01f;
    private float red = 1.0f;
    private float green = 1.0f;
    private float blue = 1.0f;

    public Dim(){
        position = new Vector(0.0f, 0.0f);
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));
    }

    public void update(){

        if(dimRecallTime <= 0.3f) deltaRecallTime = 0.01f;
        else if(dimRecallTime >= 0.8f) deltaRecallTime = -0.01f;

        dimRecallTime += deltaRecallTime;
    }

    public void render(){
        shader.activate();
        shader.setFloatUniform("dimRecallTime", dimRecallTime);
        shader.setFloatUniform("red", red);
        shader.setFloatUniform("green", green);
        shader.setFloatUniform("blue", blue);
        vao.bind();
        vao.draw();
        vao.unbind();
        shader.deactivate();
    }

    public void setColor(float red, float green, float blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

}
