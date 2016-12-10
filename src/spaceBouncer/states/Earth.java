package spaceBouncer.states;

import spaceBouncer.entity.Balloon;
import spaceBouncer.entity.Plane;
import spaceBouncer.entity.Player;
import spaceBouncer.entity.generators.BalloonGenerator;
import spaceBouncer.entity.generators.PlaneGenerator;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.states.features.EarthFeatures;
import spaceBouncer.utility.projections.Camera;

public class Earth implements EarthFeatures {

    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private Player player;

    private PlaneGenerator planeGenerator;
    private BalloonGenerator balloonGenerator;

    public Earth(){
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        texture = new Texture(textureSource);

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        player = new Player();

        planeGenerator = new PlaneGenerator.PlaneGeneratorBuilder()
                .withPlanesAmount(planesAmount)
                .withTextures(planeTextures)
                .build();

        balloonGenerator = new BalloonGenerator.BalloonGeneratorBuilder()
                .withBalloonsAmount(balloonsAmount)
                .withTextures(balloonTextures)
                .build();
    }

    public void update(){
        balloonGenerator.update();
        planeGenerator.update();
        player.update();

        for(Plane plane :  planeGenerator.getPlaneList()){
            if(player.getHeight() >= plane.getTriggerAttitude()){
                plane.setStart(true);
            }
        }

        for(Balloon balloon : balloonGenerator.getBalloonList()){
            if(player.getHeight() >= balloon.getTriggerAttitude()){
                balloon.setStart(true);
            }
        }
    }

    public void render(){
        shader.activate();
        texture.bind();
        vao.bind();
        vao.draw();
        vao.unbind();
        texture.unbind();
        shader.deactivate();

        balloonGenerator.render();
        planeGenerator.render();
        player.render();
    }

    public int getPlayerHeight(){
        return this.player.getHeight();
    }

}
