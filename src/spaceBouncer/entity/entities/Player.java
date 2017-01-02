package spaceBouncer.entity.entities;

import spaceBouncer.deploy.GameLoop;
import spaceBouncer.entity.entities.Entity;
import spaceBouncer.entity.features.PlayerFeatures;
import spaceBouncer.input.keyboard.KeyInput;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Matrix;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Entity implements PlayerFeatures {

    private float height = 1000.0f * 100/15;
    private float deltaH = 0;
    private boolean bouncing = true;

    public Player(){
        position = new Vector();
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        texture = new Texture(textureSource);

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        setSize(playerSize);
    }

    public void update(){
        position.x += deltaX;
        position.y += deltaY;
        height += deltaH;

        if(KeyInput.isKeyDown(GLFW_KEY_UP)){
            deltaY = 0.15f;
            deltaH = 0.2f;
        }
        else if(KeyInput.isKeyDown(GLFW_KEY_LEFT)){
            deltaX = -0.15f;
            deltaY -= 0.001f;
            deltaH = 0;

            rotationZ = 20;
        }
        else if(KeyInput.isKeyDown(GLFW_KEY_RIGHT)){
            deltaX = 0.15f;
            deltaY -= 0.001f;
            deltaH = 0;

            rotationZ = -20;
        }

        deltaY -= 0.01f;
    }

    public void render(){
        startRender();
        shader.setMatrixUniform(modelMatrix, Matrix.translate(position)
                .multiply(Matrix.rotateByZ(rotationZ))
                .multiply(Matrix.rotateByY(rotationY)));
        finishRender();
    }

    public void fall(){
        deltaH = 0.0f;
        deltaY = 0.4f;
        bouncing = false;
    }

    public int getHeight(){
        return (int) this.height;
    }

    public boolean isBouncing() {
        return bouncing;
    }
}
