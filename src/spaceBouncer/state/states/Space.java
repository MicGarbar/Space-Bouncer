package spaceBouncer.state.states;

import spaceBouncer.entity.entities.Entity;
import spaceBouncer.entity.entities.Player;
import spaceBouncer.entity.generators.AsteroidGenerator;
import spaceBouncer.entity.generators.CometGenerator;
import spaceBouncer.entity.generators.Generator;
import spaceBouncer.entity.generators.ProbeGenerator;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.state.features.SpaceFeatures;
import spaceBouncer.utility.projections.Camera;

import java.util.ArrayList;
import java.util.List;

public class Space implements SpaceFeatures {

    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private Player player;

    private ProbeGenerator probeGenerator;
    private AsteroidGenerator asteroidGenerator;
    private CometGenerator cometGenerator;

    private List<Generator> generatorList;

    private boolean levelFailed = false;

    public Space(){
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        texture = new Texture(textureSource);

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        player = new Player();
        generatorList = new ArrayList<>();

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
    }

    public boolean isLevelFailed(){
        return this.levelFailed;
    }

    public int getPlayerDistance(){
        return (int) (this.player.getHeight() * 0.15);
    }

}
