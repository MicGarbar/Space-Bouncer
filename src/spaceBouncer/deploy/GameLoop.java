package spaceBouncer.deploy;

import org.lwjgl.glfw.GLFWVidMode;
import spaceBouncer.entity.effects.Dim;
import spaceBouncer.entity.info.InfoPicture;
import spaceBouncer.entity.info.WarningPicture;
import spaceBouncer.input.keyboard.KeyInput;
import spaceBouncer.input.mouse.MouseInput;
import spaceBouncer.input.mouse.MousePositionInput;
import spaceBouncer.render.BitmapFont;
import spaceBouncer.state.states.Earth;
import spaceBouncer.state.states.MainMenu;
import spaceBouncer.state.states.Space;
import spaceBouncer.state.statesEnum.State;
import spaceBouncer.utility.loaders.File;
import spaceBouncer.utility.projections.Message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GameLoop implements Runnable {

    public static State gameState;

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    final String TITLE = "Space Bouncer";
    final String THREAD_NAME = "Space Bouncer Thread";
    final String DATE_FORMAT = "dd/MM/yyyy HH:mm";
    final String SPACE_STATS_FILE = "files/spaceStats.txt";
    final String EARTH_STATS_FILE = "files/earthStats.txt";

    private GameThread gameThread;
    private MainMenu mainMenu;
    private Earth earth;
    private Space space;
    private BitmapFont bitmapFont;
    private InfoPicture infoPicture;
    private WarningPicture warningPicture;
    private Dim dim;

    private long windowID;

    private void start(){
        gameThread = new GameThread(this, THREAD_NAME);
        gameThread.start();
    }

    private void init(){
        if(!glfwInit()){
            System.err.println("Inicjalizacja GLFW się nie powiodła!");
            System.exit(-1);
        }

        windowID = glfwCreateWindow(WIDTH, HEIGHT, TITLE, NULL, NULL);
        if(windowID == NULL){
            System.err.println("Inicjalizacja okna się nie powiodła!");
            System.exit(-1);
        }

        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(windowID, (vidMode.width() - WIDTH)/2, (vidMode.height() - HEIGHT)/2);

        glfwMakeContextCurrent(windowID);
        glfwShowWindow(windowID);
        createCapabilities();

        glfwSetKeyCallback(windowID, new KeyInput());
        glfwSetMouseButtonCallback(windowID, new MouseInput());
        glfwSetCursorPosCallback(windowID, new MousePositionInput());

        glActiveTexture(GL_TEXTURE0);
        glEnable(GL_BLEND);

        bitmapFont = new BitmapFont();
        mainMenu = new MainMenu();
        earth = new Earth();
        space = new Space();
        infoPicture = new InfoPicture();
        warningPicture = new WarningPicture();
        dim = new Dim();

        gameState = State.MAIN_MENU;
    }

    @Override
    public void run(){
        init();

        long lastTime = System.nanoTime();
        float timeInNano = 1000000000.0f / 60.0f;
        float deltaTime = 0.0f;

        while(gameThread.isGameRunning()){
            long currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / timeInNano;
            lastTime = currentTime;

            if(deltaTime >= 1.0f){
                update();
                deltaTime--;
            }

            render();

            if(glfwWindowShouldClose(windowID))
                gameThread.setGameRunning(false);
        }

        glfwDestroyWindow(windowID);
        glfwTerminate();
    }

    private void update(){
        glfwPollEvents();

        switch (gameState){
            case MAIN_MENU: mainMenu.update(); break;
            case THE_EARTH: earthUpdate(); break;
            case SPACE: spaceUpdate(); break;
            case QUIT: gameThread.setGameRunning(false); break;
        }
    }

    private void spaceUpdate() {
        space.update();

        if(space.isLevelFailed()){
            dim.update();

            if(KeyInput.isKeyDown(GLFW_KEY_SPACE)){
                gameState = State.MAIN_MENU;
                newGame();
                saveStats(space.getPlayerDistance(), SPACE_STATS_FILE);
            }
        }

        if(space.isLevelAccomplished()){
            dim.update();

            if(KeyInput.isKeyDown(GLFW_KEY_SPACE)){
                gameState = State.MAIN_MENU;
                newGame();
                saveStats(space.getPlayerDistance(), SPACE_STATS_FILE);
            }
        }

        infoPicture.setPicturePointer(space.getPlayerDistance());
        warningPicture.setPicturePointer(space.getPlayerDistance());
        infoPicture.update();
        warningPicture.update();
    }

    private void earthUpdate() {
        earth.update();

        if(earth.isLevelFailed()){
            dim.update();

            if(KeyInput.isKeyDown(GLFW_KEY_SPACE)){
                gameState = State.MAIN_MENU;
                newGame();
                saveStats(earth.getPlayerHeight(), EARTH_STATS_FILE);
            }
        }

        if(earth.isLevelAccomplished()){
            dim.update();

            if(KeyInput.isKeyDown(GLFW_KEY_SPACE)){
                gameState = State.SPACE;
                nextLevel();
                saveStats(earth.getPlayerHeight(), EARTH_STATS_FILE);
            }
        }

        infoPicture.setPicturePointer(earth.getPlayerHeight());
        warningPicture.setPicturePointer(earth.getPlayerHeight());
        infoPicture.update();
        warningPicture.update();
    }

    private void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        switch (gameState){
            case MAIN_MENU: mainMenu.render(); break;
            case THE_EARTH: earthRender(); break;
            case SPACE: spaceRender(); break;
        }

        glfwSwapBuffers(windowID);
    }

    private void spaceRender() {
        space.render();

        if(space.isLevelFailed()){
            dim.setColor(0.1f, 0.1f, 1.0f);
            dim.render();
            Message.defeatMessage(bitmapFont);
        }

        if(space.isLevelAccomplished()){
            dim.setColor(0.1f, 1.0f, 0.1f);
            dim.render();
            Message.spaceAccomplished(bitmapFont);
        }

        bitmapFont.render("mln km", 0.55f, 0.28f);
        bitmapFont.render(String.valueOf(space.getPlayerDistance()), 0.65f, 0.35f);
        infoPicture.render();
        warningPicture.render();
    }

    private void earthRender() {
        earth.render();

        if(earth.isLevelFailed()){
            dim.setColor(0.1f, 0.1f, 1.0f);
            dim.render();
            Message.defeatMessage(bitmapFont);
        }

        if(earth.isLevelAccomplished()){
            dim.setColor(0.1f, 1.0f, 0.1f);
            dim.render();
            Message.earthAccomplished(bitmapFont);
        }

        bitmapFont.render(String.valueOf(earth.getPlayerHeight() + " m"), 0.6f, 0.35f);
        infoPicture.render();
        warningPicture.render();
    }

    private void newGame(){
        earth = new Earth();
        earth.setPlayerHeight(0);
        earth.setLevelAccomplished(false);
        infoPicture.reset();
        warningPicture.reset();
    }

    private void nextLevel(){
        space = new Space();
        space.setPlayerDistance(0);
        space.setLevelAccomplished(false);
        infoPicture.reset();
        warningPicture.reset();
    }

    private void saveStats(int stats, String file){
        File.save(String.valueOf(new SimpleDateFormat(DATE_FORMAT).format(new Date()) + ": " + stats), file);
    }

    public static void main(String[] args){
        new GameLoop().start();
    }

}
