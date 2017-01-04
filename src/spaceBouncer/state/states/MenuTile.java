package spaceBouncer.state.states;

import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.state.features.MenuTileFeatures;
import spaceBouncer.state.statesEnum.MenuState;
import spaceBouncer.utility.maths.Matrix;
import spaceBouncer.utility.maths.Vector;
import spaceBouncer.utility.projections.Camera;

public class MenuTile implements MenuTileFeatures {

    private Vector position;
    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private MenuState menuState;

    public MenuTile(MenuState menuState, float x, float y){
        position = new Vector(x, y);
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        this.menuState = menuState;

        switch (menuState){
            case HEADING: texture = new Texture(headingTextureSource); break;
            case START_GAME: texture = new Texture(startGameTextureSource); break;
            case STATS: texture = new Texture(statsTextureSource); break;
            case QUIT: texture = new Texture(quitTextureSource); break;
        }

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));
    }

    public void render(){
        shader.activate();
        shader.setMatrixUniform(modelMatrix, Matrix.translate(position));
        texture.bind();
        vao.bind();
        vao.draw();
        vao.unbind();
        texture.unbind();
        shader.deactivate();
    }

    public Vector getPosition() {
        return position;
    }

    public MenuState getMenuState() {
        return menuState;
    }

    public void setTexture(String texturePath){
        texture = new Texture(texturePath);
        shader.setSamplerUniform(sampler, activeTexture);
    }
}
