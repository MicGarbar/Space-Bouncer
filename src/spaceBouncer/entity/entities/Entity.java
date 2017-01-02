package spaceBouncer.entity.entities;

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

    protected float size = 0;

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

    public void setPosition(Vector position){
        this.position = position;
    }

    public Vector getPosition() {
        return position;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(float deltaY) {
        this.deltaY = deltaY;
    }

    public int getRotationZ() {
        return rotationZ;
    }

    public void setRotationZ(int rotationZ) {
        this.rotationZ = rotationZ;
    }

    public int getRotationY() {
        return rotationY;
    }

    public void setRotationY(int rotationY) {
        this.rotationY = rotationY;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(float deltaX) {
        this.deltaX = deltaX;
    }
}
