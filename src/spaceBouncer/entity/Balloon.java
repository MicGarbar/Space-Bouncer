package spaceBouncer.entity;

import spaceBouncer.entity.features.BalloonFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Matrix;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

public class Balloon implements BalloonFeatures{

    private Vector position;
    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private boolean start = false;

    private float deltaX = 0;

    public Balloon(){
        position = new Vector(-20.0f, 7.0f);
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));
    }

    public void update(){
        if(start){
            position.x += deltaX;
            position.y = (float) Math.abs((float) 5*Math.sin(0.3*position.x));
        }
    }

    public void render(){
        shader.activate();
        shader.setMatrixUniform(modelMatrix, Matrix.translate(position));
        texture.bind();
        vao.bind();
        vao.draw();
        vao.unbind();
        texture.unbind();
        shader.deactivate();
    }

    public void setStart(boolean start){
        this.start = start;
    }

    public void setPosition(Vector position){
        this.position = position;
    }

    public void setDeltaX(float deltaX){
        this.deltaX = deltaX;
    }

    public void setTexture(String texturePath){
        texture = new Texture(texturePath);
        shader.setSamplerUniform(sampler, activeTexture);
    }

}
