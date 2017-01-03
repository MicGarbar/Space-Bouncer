package spaceBouncer.entity.entities;

import spaceBouncer.entity.features.EntityFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.utility.maths.Matrix;
import spaceBouncer.utility.projections.Camera;

public class Planet extends Entity implements EntityFeatures {

    public Planet(){
        shader = new Shader(vertexSource, fragmentSource);

        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));
    }

    public void update() {
        if(start){
            position.y -= deltaY;
        }
    }

    public void render() {
        startRender();
        shader.setMatrixUniform(modelMatrix, Matrix.translate(position));
        finishRender();
    }

    public void setTexture(String texturePath){
        texture = new Texture(texturePath);
        shader.setSamplerUniform(sampler, activeTexture);
    }
}
