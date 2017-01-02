package spaceBouncer.state.states;

import spaceBouncer.entity.effects.Haze;
import spaceBouncer.entity.entities.Entity;
import spaceBouncer.entity.entities.Player;
import spaceBouncer.entity.generators.*;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.state.features.EarthFeatures;
import spaceBouncer.utility.physics.Physics;
import spaceBouncer.utility.projections.Camera;

import java.util.ArrayList;
import java.util.List;

public class Earth implements EarthFeatures {

    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private Player player;
    private Haze haze;

    private PlaneGenerator planeGenerator;
    private BalloonGenerator balloonGenerator;
    private CloudGenerator cloudGenerator;
    private BirdGenerator birdGenerator;

    private List<Generator> generatorList;

    private boolean levelFailed = false;

    public Earth(){
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        texture = new Texture(textureSource);

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        player = new Player();
        haze = new Haze();
        generatorList = new ArrayList<>();

        planeGenerator = new PlaneGenerator.PlaneGeneratorBuilder()
                .withPlanesAmount(planesAmount)
                .withTextures(planeTextures)
                .build();
        generatorList.add(planeGenerator);

        balloonGenerator = new BalloonGenerator.BalloonGeneratorBuilder()
                .withBalloonsAmount(balloonsAmount)
                .withTextures(balloonTextures)
                .build();
        generatorList.add(balloonGenerator);

        cloudGenerator = new CloudGenerator.CloudGeneratorBuilder()
                .withCloudsAmount(cloudsAmount)
                .withTextures(cloudTextures)
                .build();
        generatorList.add(cloudGenerator);

        birdGenerator = new BirdGenerator.BirdGeneratorBuilder()
                .withBirdsAmount(birdsAmount)
                .withTextures(birdTextures)
                .build();
        generatorList.add(birdGenerator);
    }

    public void update(){
        for(Generator generator : generatorList){
            generator.update();
            for(Entity entity : generator.getEntities()){
                if(player.getHeight() >= entity.getTriggerAttitude()){
                    entity.setStart(true);
                }
            }
        }

        player.update();
        haze.update();

        checkCollision();
        checkIfFallen();
    }

    public void render(){
        shader.activate();
        texture.bind();
        vao.bind();
        vao.draw();
        vao.unbind();
        texture.unbind();
        shader.deactivate();

        for(Generator generator : generatorList){
            generator.render();
        }

        player.render();
        haze.render();
    }

    private void checkIfFallen(){
        if(Physics.fallen(player) && player.isBouncing()){
            player.fall();
            levelFailed = true;
        }
    }

    private void checkCollision(){
        for(int i = 0; i < generatorList.size(); i++){
            for(int j = 0; j < generatorList.get(i).getEntities().size(); j++){
                if(Physics.collision(player, generatorList.get(i).getEntities().get(j))){
                    player.setDeltaY(getCollisionFactor(i));
                }
            }
        }
    }

    private float getCollisionFactor(int i) {
        float collisionFactor = 0.0f;
        if(generatorList.get(i) instanceof PlaneGenerator) collisionFactor = planeCollisionFactor;
        if(generatorList.get(i) instanceof BalloonGenerator) collisionFactor = balloonCollisionFactor;
        if(generatorList.get(i) instanceof BirdGenerator) collisionFactor = birdCollisionFactor;
        if(generatorList.get(i) instanceof CloudGenerator) haze.setHazeRecallTime(cloudCollisionHazeFactor);
        return collisionFactor;
    }

    public int getPlayerHeight(){
        return this.player.getHeight();
    }

    public boolean isLevelFailed() {
        return levelFailed;
    }
}
