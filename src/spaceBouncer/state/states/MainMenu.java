package spaceBouncer.state.states;

import org.lwjgl.system.CallbackI;
import spaceBouncer.deploy.GameLoop;
import spaceBouncer.input.mouse.MouseInput;
import spaceBouncer.input.mouse.MousePositionInput;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.state.features.MainMenuFeatures;
import spaceBouncer.state.statesEnum.MenuState;
import spaceBouncer.state.statesEnum.State;
import spaceBouncer.utility.maths.Converter;
import spaceBouncer.utility.projections.Camera;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_1;

public class MainMenu implements MainMenuFeatures {

    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private List<MenuTile> menuTileList;
    private List<Rectangle2D.Float> menuTileRectList;

    public MainMenu(){
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);
        texture = new Texture(textureSource);

        shader.setSamplerUniform(sampler, activeTexture);
        shader.setMatrixUniform(projectionMatrix, Camera.orthographicProjection(-16.0f, 16.0f, -9.0f, 9.0f, -1.0f, 1.0f));

        menuTileList = new ArrayList<>();
        menuTileRectList = new ArrayList<>();

        menuTileList.add(new MenuTile(MenuState.HEADING, 4.0f, 4.0f));
        menuTileList.add(new MenuTile(MenuState.START_GAME, -7.0f, -1.0f));
        menuTileList.add(new MenuTile(MenuState.STATS, -7.0f, -3.5f));
        menuTileList.add(new MenuTile(MenuState.QUIT, -7.0f, -6.0f));

        for(MenuTile menuTile : menuTileList){
            menuTileRectList.add(new Rectangle2D.Float(
                    Converter.fromOrthographicToNormalX(menuTile.getPosition().x),
                    Converter.fromOrthographicToNormalY(menuTile.getPosition().y),
                    Converter.fromOrthographicToNormalWidth(5.0f),
                    Converter.fromOrthographicToNormalHeight(1.0f)));
        }
    }

    public void update(){
        listenForChangeState();
        listenForChangeColor();
    }

    private void listenForChangeState() {
        if(MouseInput.isMousePressed(GLFW_MOUSE_BUTTON_1)){
            for(int i = 0; i < menuTileList.size(); i++){
                if(MousePositionInput.isCursorIn(menuTileRectList.get(i))){
                    switch (menuTileList.get(i).getMenuState()){
                        case START_GAME: GameLoop.gameState = State.SPACE; break;
                        case STATS: System.out.println("STATS"); break;
                        case QUIT: System.out.println("QUIT"); break;
                    }
                }
            }
        }
    }

    private void listenForChangeColor() {
        for(int i = 0; i < menuTileList.size(); i++){
            if(MousePositionInput.isCursorIn(menuTileRectList.get(i))){
                extractTextureSource(i, newGameBrightTextureSource, statsBrightTextureSource, exitBrightTextureSource);
            }
            else {
                extractTextureSource(i, newGameTextureSource, statsTextureSource, exitTextureSource);
            }
        }
    }

    private void extractTextureSource(int i, String newGameTextureSource, String statsTextureSource, String exitTextureSource) {
        switch (menuTileList.get(i).getMenuState()) {
            case START_GAME:
                menuTileList.get(i).setTexture(newGameTextureSource);
                break;
            case STATS:
                menuTileList.get(i).setTexture(statsTextureSource);
                break;
            case QUIT:
                menuTileList.get(i).setTexture(exitTextureSource);
                break;
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

        for(MenuTile menuTile : menuTileList){
            menuTile.render();
        }
    }

}
