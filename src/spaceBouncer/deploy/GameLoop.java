package spaceBouncer.deploy;

import org.lwjgl.glfw.GLFWVidMode;
import spaceBouncer.entity.Player;
import spaceBouncer.input.keyboard.KeyInput;
import spaceBouncer.states.Troposphere;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL.createCapabilities;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GameLoop implements Runnable {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    public static final String TITLE = "Space Bouncer";
    public static final String THREAD_NAME = "Space Bouncer Thread";

    private GameThread gameThread;
    private Troposphere troposphere;

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

        glClearColor(0.8f, 0.9f, 0.4f, 1.0f);

        troposphere = new Troposphere();
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
        troposphere.update();
    }

    private void render(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        troposphere.render();
        glfwSwapBuffers(windowID);
    }

    public static void main(String[] args){
        new GameLoop().start();
    }

}
