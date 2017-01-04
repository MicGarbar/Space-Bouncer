package spaceBouncer.state.states;

import spaceBouncer.entity.effects.Haze;
import spaceBouncer.entity.entities.*;
import spaceBouncer.entity.generators.*;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.state.features.SpaceFeatures;
import spaceBouncer.utility.physics.Physics;
import spaceBouncer.utility.projections.Camera;

import java.util.ArrayList;
import java.util.List;

public class Space implements SpaceFeatures {

    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private Player player;
    private Voyager voyager;
    private Hubble hubble;
    private ISS internationalSpaceStation;

    private ProbeGenerator probeGenerator;
    private AsteroidGenerator asteroidGenerator;
    private CometGenerator cometGenerator;
    private PlanetGenerator planetGenerator;

    private List<Generator> generatorList;
    private List<Entity> uniqueEntities;

    private Haze haze;

    private boolean levelFailed = false;
    private boolean extraAsteroidsInSaturn = false;

    public Space(){
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        texture = new Texture(textureSource);

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        player = new Player();
        haze = new Haze();
        voyager = new Voyager();
        hubble = new Hubble();
        internationalSpaceStation = new ISS();
        generatorList = new ArrayList<>();
        uniqueEntities = new ArrayList<>();

        uniqueEntities.add(voyager);
        uniqueEntities.add(hubble);
        uniqueEntities.add(internationalSpaceStation);

        probeGenerator = new ProbeGenerator.ProbeGeneratorBuilder()
                .withAmount(probesAmount)
                .withTextures(probeTextures)
                .build();
        generatorList.add(probeGenerator);

        asteroidGenerator = new AsteroidGenerator.AsteroidGeneratorBuilder()
                .withAmount(asteroidsAmount)
                .withTextures(asteroidTextures)
                .build();
        generatorList.add(asteroidGenerator);

        cometGenerator = new CometGenerator.CometGeneratorBuilder()
                .withAmount(cometsAmount)
                .withTextures(cometTextures)
                .build();
        generatorList.add(cometGenerator);

        planetGenerator = new PlanetGenerator();
        generatorList.add(planetGenerator);
    }

    public void update(){
        for(Generator generator : generatorList){
            generator.update();
            for(Entity entity : generator.getEntities()){
                if(getPlayerDistance() >= entity.getTriggerAttitude()){
                    entity.setStart(true);
                }
            }
        }

        for(Entity entity : uniqueEntities){
            entity.update();
            if(getPlayerDistance() >= entity.getTriggerAttitude()){
                entity.setStart(true);
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

        for(Entity entity : uniqueEntities){
            entity.render();
        }

        player.render();
        haze.render();
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

    private void checkIfFallen(){
        if(Physics.fallen(player) && player.isBouncing()){
            player.fall();
            levelFailed = true;
        }
    }

    private float getCollisionFactor(int i) {
        float collisionFactor = 0.0f;
        if(generatorList.get(i) instanceof ProbeGenerator) collisionFactor = probeCollisionFactor;
        if(generatorList.get(i) instanceof CometGenerator) collisionFactor = cometCollisionFactor;
        if(generatorList.get(i) instanceof AsteroidGenerator) collisionFactor = asteroidCollisionFactor;
        if(generatorList.get(i) instanceof PlanetGenerator){
            for(int j = 0; j < generatorList.get(i).getEntities().size(); j++){
                if(Physics.collision(player, generatorList.get(i).getEntities().get(j))){
                    planetCollisionService(j);
                }
            }
        }
        return collisionFactor;
    }

    private void planetCollisionService(int planet) {
        switch (planet){
            case MERCURY: break;
            case VENUS: break;
            case EARTH: break;
            case MARS: break;
            case JUPITER:
                haze.setHazeRecallTime(0.4f);
                haze.setColor(1.0f, 0.55f, 0.13f);
                break;
            case SATURN:
                if(!extraAsteroidsInSaturn) asteroidGenerator.addExtraAsteroids(extraAsteroidsWhenSaturnHit);
                extraAsteroidsInSaturn = true;
                break;
            case URANUS:
                haze.setHazeRecallTime(0.1f);
                haze.setColor(0.13f, 0.85f ,1.0f);
                break;
            case NEPTUNE:
                haze.setHazeRecallTime(0.3f);
                haze.setColor(0.13f, 0.0f, 1.0f);
                break;
        }
    }

    public boolean isLevelFailed(){
        return this.levelFailed;
    }

    public int getPlayerDistance(){
        return (int) (this.player.getHeight() * 0.25);
    }

}
