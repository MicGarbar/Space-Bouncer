package spaceBouncer.deploy;

import org.lwjgl.glfw.GLFWVidMode;
import spaceBouncer.entity.effects.Dim;
import spaceBouncer.entity.info.InfoPicture;
import spaceBouncer.entity.info.WarningPicture;
import spaceBouncer.input.keyboard.KeyInput;
import spaceBouncer.render.BitmapFont;
import spaceBouncer.states.Earth;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GameLoop implements Runnable {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    final String TITLE = "Space Bouncer";
    final String THREAD_NAME = "Space Bouncer Thread";

    private GameThread gameThread;
    private Earth earth;
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

        glActiveTexture(GL_TEXTURE0);
        glEnable(GL_BLEND);

        bitmapFont = new BitmapFont();
        earth = new Earth();
        infoPicture = new InfoPicture();
        warningPicture = new WarningPicture();
        dim = new Dim();
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

        earth.update();

        if(earth.isLevelFailed()){
            dim.update();
        }

        infoPicture.setPicturePointer(earth.getPlayerHeight());
        warningPicture.setPicturePointer(earth.getPlayerHeight());
        infoPicture.update();
        warningPicture.update();
    }

    private void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        earth.render();

        if(earth.isLevelFailed()){
            dim.render();
            renderDefeatMessage();
        }

        bitmapFont.render(String.valueOf(earth.getPlayerHeight() + " m"), 0.6f, 0.35f);
        infoPicture.render();
        warningPicture.render();
        glfwSwapBuffers(windowID);
    }

    private void renderDefeatMessage() {
        bitmapFont.render("DEFEAT", -0.4f, 0.15f);
        bitmapFont.render("PRESS UP ARROW", -0.6f, 0.0f);
        bitmapFont.render("TO CONTINUE", -0.52f, -0.1f);
    }

    public static void main(String[] args){
        new GameLoop().start();
    }

}
