package spaceBouncer.entity.entities;

import spaceBouncer.entity.features.EntityFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Matrix;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

public class Bird extends Entity implements EntityFeatures {

    public Bird(){
        position = new Vector(-20.0f, 7.0f);
        vao = new VertexArrayObject(birdVertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        setSize(birdSize);
    }

    public void update(){
        if(start){
            position.x += deltaX;
            position.y = (float) (amplitude * Math.sin(0.5*position.x)) + 1.0f;
        }
    }

    public void render(){
        startRender();
        shader.setMatrixUniform(modelMatrix, Matrix.translate(position)
                .multiply(Matrix.rotateByY(rotationY)));
        finishRender();
    }

    public void setStart(boolean start){
        this.start = start;
    }

    public void setRotationY(int rotationY){
        this.rotationY = rotationY;
    }

    public int getRotationY() {
        return this.rotationY;
    }

    public void setPosition(Vector position){
        this.position = position;
    }

    public void setDeltaX(float deltaX){
        this.deltaX = deltaX;
    }

    public void setTriggerAttitude(int attitude){
        this.triggerAttitude = attitude;
    }

    public int getTriggerAttitude(){
        return this.triggerAttitude;
    }

    public void setTexture(String texturePath){
        texture = new Texture(texturePath);
        shader.setSamplerUniform(sampler, activeTexture);
    }
}
