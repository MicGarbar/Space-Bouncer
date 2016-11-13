package spaceBouncer.deploy;

public class GameThread {

    private Runnable gameLoop;
    private boolean gameRunning;
    private String threadName;

    public GameThread(Runnable gameLoop, String threadName){
        this.gameLoop = gameLoop;
        this.threadName = threadName;
        this.gameRunning = false;
    }

    public void start(){
        gameRunning = true;
        Thread thread = new Thread(gameLoop, threadName);
        thread.start();
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }
}
