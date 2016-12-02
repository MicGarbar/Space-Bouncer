package spaceBouncer.entity;

import spaceBouncer.entity.features.PlaneFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Matrix;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

public class Plane implements PlaneFeatures {

    private Vector position;
    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private boolean start = false;
    private int rotationY = 0;

    private float deltaX = 0;

    public Plane(){
        position = new Vector(-20.0f, 7.0f);
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        texture = new Texture(textureSource);

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));
    }

    public void update(){
        if(start){
            position.x += deltaX;
            position.y = (float) Math.sin(position.x) + 5.0f;
        }
    }

    public void render(){
        shader.activate();
        shader.setMatrixUniform(modelMatrix, Matrix.translate(position)
                .multiply(Matrix.rotateByY(rotationY)));
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

    public void setRotationY(int rotationY){
        this.rotationY = rotationY;
    }

    public void setPosition(Vector position){
        this.position = position;
    }

    public void setDeltaX(float deltaX){
        this.deltaX = deltaX;
    }

}
