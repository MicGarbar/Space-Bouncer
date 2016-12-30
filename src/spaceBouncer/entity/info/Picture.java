package spaceBouncer.entity.info;

import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;

public abstract class Picture {

    protected VertexArrayObject vao;
    protected Shader shader;
    protected Texture texture;

    protected int picturePointer;

    protected abstract void update();

    protected void render(){
        shader.activate();
        texture.bind();
        vao.bind();
        vao.draw();
        vao.unbind();
        texture.unbind();
        shader.deactivate();
    }

    public void setPicturePointer(int picturePointer){
        this.picturePointer = picturePointer;
    }

}
