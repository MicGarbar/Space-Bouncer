package spaceBouncer.entity.entities;

import spaceBouncer.entity.features.EntityFeatures;
import spaceBouncer.input.keyboard.KeyInput;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Matrix;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

import static org.lwjgl.glfw.GLFW.*;

public class Player extends Entity implements EntityFeatures {

    private float height = 990.0f;//4990.0f * 100/25;
    private float deltaH = 0;
    private boolean bouncing = true;

    public Player(){
        position = new Vector();
        vao = new VertexArrayObject(playerVertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        texture = new Texture(playerTextureSource);

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        setSize(playerSize);
    }

    public void update(){
        position.x += deltaX;
        position.y += deltaY;
        height += deltaH;

        if(bouncing) {
            if (KeyInput.isKeyDown(GLFW_KEY_UP)) {
                deltaY = 0.15f;
                deltaH = 0.2f;
            } else if (KeyInput.isKeyDown(GLFW_KEY_LEFT)) {
                deltaX = -0.15f;
                deltaY -= 0.001f;
                deltaH = 0;

                rotationZ += 0;
            } else if (KeyInput.isKeyDown(GLFW_KEY_RIGHT)) {
                deltaX = 0.15f;
                deltaY -= 0.001f;
                deltaH = 0;

                rotationZ += 0;
            }
        }

        deltaY -= 0.01f;
    }

    public void render(){
        startRender();
        shader.setMatrixUniform(modelMatrix, Matrix.translate(position)
                .multiply(Matrix.rotateByZ(rotationZ)));
        finishRender();
    }

    public void fall(){
        deltaH = 0.0f;
        deltaY = 0.4f;
        bouncing = false;
    }

    public void promote(){
        deltaH = 0.0f;
        deltaY += 0.02f;
        bouncing = false;
    }

    public int getHeight(){
        return (int) this.height;
    }

    public void setHeight(int height){
        this.height = height;
    }

    public boolean isBouncing() {
        return bouncing;
    }
}
