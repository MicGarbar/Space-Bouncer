package spaceBouncer.states;

import spaceBouncer.entity.Player;
import spaceBouncer.render.Animation;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.states.features.TroposphereFeatures;
import spaceBouncer.utility.projections.Camera;

public class Troposphere implements TroposphereFeatures {

    private VertexArrayObject vao;
    private Shader shader;
    private Animation animation;

    private Player player;

    public Troposphere(){
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        animation = new Animation(framesAmount, textureFps, textureSource);

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        player = new Player();
    }

    public void update(){
        player.update();
    }

    public void render(){
        shader.activate();
        animation.bind();
        vao.bind();
        vao.draw();
        vao.unbind();
        animation.unbind();
        shader.deactivate();

        player.render();
    }

}
