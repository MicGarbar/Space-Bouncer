package spaceBouncer.state.states;

import spaceBouncer.deploy.GameLoop;
import spaceBouncer.input.mouse.MouseInput;
import spaceBouncer.input.mouse.MousePositionInput;
import spaceBouncer.render.BitmapFont;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.state.features.StatsFeatures;
import spaceBouncer.state.statesEnum.MenuState;
import spaceBouncer.state.statesEnum.State;
import spaceBouncer.utility.maths.Converter;
import spaceBouncer.utility.projections.Camera;

import java.awt.geom.Rectangle2D;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;

public class Stats implements StatsFeatures {

    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private MenuTile menuTile;
    private Rectangle2D.Float menuTileRect;

    public Stats(){
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        texture = new Texture(textureSource);

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        menuTile = new MenuTile(MenuState.BACK, 8.0f, -7.0f);
        menuTileRect = new Rectangle2D.Float(
                Converter.fromOrthographicToNormalX(menuTile.getPosition().x),
                Converter.fromOrthographicToNormalY(menuTile.getPosition().y),
                Converter.fromOrthographicToNormalWidth(5.0f),
                Converter.fromOrthographicToNormalHeight(1.0f));
    }

    public void update(){
        listenForChangeState();
        listenForChangeColor();
    }

    private void listenForChangeState(){
        if(MouseInput.isMousePressed(GLFW_MOUSE_BUTTON_1)){
            if(MousePositionInput.isCursorIn(menuTileRect)){
                GameLoop.gameState = State.MAIN_MENU;
            }
        }
    }

    private void listenForChangeColor(){
        if(MousePositionInput.isCursorIn(menuTileRect)){
            menuTile.setTexture(backBrightTextureSource);
        }
        else {
            menuTile.setTexture(backTextureSource);
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

        menuTile.render();
    }

}
