package spaceBouncer.entity;

import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.maths.Vector;

public abstract class Entity {

    protected Vector position;
    protected VertexArrayObject vao;
    protected Shader shader;
    protected Texture texture;

    protected boolean start = false;

    protected int rotationZ = 0;
    protected int rotationY = 0;

    protected float deltaX = 0;
    protected float deltaY = 0;

    protected int triggerAttitude = 0;

    public abstract void update();
    public abstract void render();

    public void startRender(){
        shader.activate();
        texture.bind();
        vao.bind();
        vao.draw();
    }

    public void finishRender(){
        vao.unbind();
        texture.unbind();
        shader.deactivate();
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public int getTriggerAttitude() {
        return triggerAttitude;
    }

    public void setTriggerAttitude(int triggerAttitude) {
        this.triggerAttitude = triggerAttitude;
    }
}
