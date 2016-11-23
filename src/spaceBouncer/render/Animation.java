package spaceBouncer.render;

public class Animation {

    private Texture[] frames;
    private int framePointer;

    private double currentTime;
    private double lastTime;
    private double deltaTime;
    private double fps;

    public Animation(int amount, double fps, String fileName){
        this.framePointer = 0;
        this.currentTime = 0;
        this.lastTime = (double)System.nanoTime() / (double)1000000000L;
        this.deltaTime = 0;
        this.fps = 1/fps;

        this.frames = new Texture[amount];
        for(int i = 0; i < amount; i++){
            this.frames[i] = new Texture(fileName + i + ".png");
        }
    }

    public void bind(){
        this.currentTime = (double)System.nanoTime() / (double)1000000000L;
        this.deltaTime += currentTime - lastTime;

        if(deltaTime >= fps){
            deltaTime = 0;
            framePointer++;
        }

        if(framePointer >= frames.length){
            framePointer = 0;
        }

        this.lastTime = currentTime;

        frames[framePointer].bind();
    }

    public void unbind(){
        for(int i = 0; i < frames.length; i++){
            frames[i].unbind();
        }
    }

}
