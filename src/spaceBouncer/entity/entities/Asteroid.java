package spaceBouncer.entity.entities;

import spaceBouncer.entity.features.EntityFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Matrix;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

public class Asteroid extends Entity implements EntityFeatures {

    public Asteroid(){
        position = new Vector(-20.0f, 7.0f);
        vao = new VertexArrayObject(asteroidVertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        setSize(asteroidSize);
    }

    public void update() {
        if(start){
            position.x += deltaX;
            position.y = (float) (amplitude * (Math.sin(0.1*position.x)));
            rotationZ += 2;
        }
    }

    public void render() {
        startRender();
        shader.setMatrixUniform(modelMatrix, Matrix.translate(position).
                multiply(Matrix.rotateByZ(rotationZ)));
        finishRender();
    }

    public void setTexture(String texturePath){
        texture = new Texture(texturePath);
        shader.setSamplerUniform(sampler, activeTexture);
    }
}
