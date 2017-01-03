package spaceBouncer.entity.entities;

import spaceBouncer.entity.features.EntityFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Matrix;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

import java.util.Random;

public class Voyager extends Entity implements EntityFeatures {

    public Voyager(){
        position = new Vector(-20.0f, 7.0f);
        vao = new VertexArrayObject(voyagerVertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        texture = new Texture(voyagerTextureSource);

        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        Random random = new Random();

        setSize(voyagerSize);
        setTriggerAttitude(random.nextInt(3000) + 900);
        setPosition(new Vector(Math.random() > 0.5 ? -20.0f : 20.0f, random.nextFloat()*10));
        setDeltaX(getPosition().x < 0 ? random.nextFloat()*0.1f : random.nextFloat()*0.1f - 0.1f);
        setRotationZ(0);
    }

    public void update() {
        if(start){
            position.x += deltaX;
            rotationZ += 1;
        }
    }

    public void render() {
        startRender();
        shader.setMatrixUniform(modelMatrix, Matrix.translate(position).
                multiply(Matrix.rotateByZ(rotationZ)));
        finishRender();
    }
}
