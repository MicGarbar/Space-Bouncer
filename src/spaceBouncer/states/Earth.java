package spaceBouncer.states;

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
                .withAttitudeMilestones(planeHeightMilestones)
                .withRotateMilestones(planeRotateMilestones)
                .withDeltaMilestones(planeDeltaMilestones)
                .withPositionMilestones(planePositionMilestones)
                .withTextures(planeTextures)
                .build();

        balloonGenerator = new BalloonGenerator.BalloonGeneratorBuilder()
                .withBalloonsAmount(balloonsAmount)
                .withAttitudeMilestones(balloonHeightMilestones)
                .withDeltaMilestones(balloonDeltaMilestones)
                .withPositionMilestones(balloonPositionMilestones)
                .withTextures(balloonTextures)
                .build();
    }

    public void update(){
        balloonGenerator.update();
        planeGenerator.update();
        player.update();

        for(int i = 0; i < planesAmount; i++){
            if(player.getHeight() >= planeHeightMilestones[i]){
                planeGenerator.getPlaneList().get(i).setStart(true);
            }
        }

        for(int i = 0; i < balloonsAmount; i++){
            if(player.getHeight() >= balloonHeightMilestones[i]){
                balloonGenerator.getBalloonList().get(i).setStart(true);
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
