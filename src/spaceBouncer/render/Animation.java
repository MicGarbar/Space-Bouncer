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
        this.lastTime = (double)System.nanoTime() / (double)1000000000l;
        this.deltaTime = 0;
        this.fps = 1/fps;

        this.frames = new Texture[amount];
        for(int i = 0; i < amount; i++){
            this.frames[i] = new Texture(fileName + i + ".png");
        }

        /*
        this.frames[0] = new Texture("resources/troposphereAnimation/komp.png", 0, 0, 1280, 720);
        this.frames[1] = new Texture("resources/troposphereAnimation/komp.png", 1280, 0, 1280, 720);
        this.frames[2] = new Texture("resources/troposphereAnimation/komp.png", 0, 720, 1280, 720);
        this.frames[3] = new Texture("resources/troposphereAnimation/komp.png", 1280, 720, 1280, 720);
        */
    }

    public void bind(){
        this.currentTime = (double)System.nanoTime() / (double)1000000000l;
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
