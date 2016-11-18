package spaceBouncer.entity;

import spaceBouncer.entity.features.PlayerFeatures;
import spaceBouncer.input.keyboard.KeyInput;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Matrix;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

import static org.lwjgl.glfw.GLFW.*;

public class Player implements PlayerFeatures {

    private Vector position;
    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private int rotation = 0;
    private float deltaX = 0.0f;
    private float deltaY = 0.0f;

    public Player(){
        position = new Vector();
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        texture = new Texture(textureSource);

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));
    }

    public void update(){
        position.x += deltaX;
        position.y += deltaY;

        if(KeyInput.isKeyDown(GLFW_KEY_UP)){
            deltaY = 0.15f;
        }
        else if(KeyInput.isKeyDown(GLFW_KEY_LEFT)){
            deltaX = -0.15f;
            deltaY -= 0.001f;
            rotation += 10;
        }
        else if(KeyInput.isKeyDown(GLFW_KEY_RIGHT)){
            deltaX = 0.15f;
            deltaY -= 0.001f;
            rotation -= 10;
        }

        deltaY -= 0.01f;
    }

    public void render(){
        shader.activate();
        shader.setMatrixUniform(modelMatrix, Matrix.translate(position).multiply(Matrix.rotate(rotation)));
        texture.bind();
        vao.bind();
        vao.draw();
        vao.unbind();
        texture.unbind();
        shader.deactivate();
    }

}
