package spaceBouncer.states;

import spaceBouncer.entity.Plane;
import spaceBouncer.entity.Player;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.states.features.TroposphereFeatures;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

import java.util.ArrayList;
import java.util.List;

public class Troposphere implements TroposphereFeatures {

    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private Player player;
    private List<Plane> planeList;

    public Troposphere(){
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        texture = new Texture(textureSource);

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        player = new Player();
        planeList = new ArrayList<>();
        // zamiast tego zrobić coś w stylu PlaneGenerator
        // nastepnie to samo dla np. chmur, balonów i innych obiektów,
        // które będą się powtarzać i z którymi będzie można wejść w kolizję
        for(int i = 0; i < planesAmount; i++){
            Plane plane = new Plane();
            plane.setPosition(planePositionMilestones[i]);
            plane.setRotationY(planeRotateMilestones[i]);
            plane.setDeltaX(planeDeltaMilestones[i]);
            planeList.add(plane);
        }
    }

    public void update(){
        for(int i = 0; i < planeList.size(); i++){
            planeList.get(i).update();
        }
        player.update();

        for(int i = 0; i < planesAmount; i++){
            if(player.getHeight() >= planeHeightMilestones[i]){
                planeList.get(i).setStart(true);
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

        for(int i = 0; i < planeList.size(); i++){
            planeList.get(i).render();
        }
        player.render();
    }

    public int getPlayerHeight(){
        return this.player.getHeight();
    }

}
