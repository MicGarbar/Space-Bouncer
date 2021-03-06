package spaceBouncer.entity.entities;

import spaceBouncer.entity.features.EntityFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Matrix;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

public class Cloud extends Entity implements EntityFeatures {

    public Cloud(){
        position = new Vector(-20.0f, 7.0f);
        vao = new VertexArrayObject(cloudVertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        setSize(cloudSize);
    }

    public void update(){
        if(start){
            position.x += deltaX;
            position.y -= deltaY;
        }
    }

    public void render(){
        startRender();
        shader.setMatrixUniform(modelMatrix, Matrix.translate(position));
        finishRender();
    }

    public void setStart(boolean start){
        this.start = start;
    }

    public int getTriggerAttitude() {
        return triggerAttitude;
    }

    public void setTriggerAttitude(int triggerAttitude) {
        this.triggerAttitude = triggerAttitude;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }

    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }

    public void setTexture(String texturePath){
        texture = new Texture(texturePath);
        shader.setSamplerUniform(sampler, activeTexture);
    }
}
