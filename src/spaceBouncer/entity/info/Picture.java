package spaceBouncer.entity.info;

import spaceBouncer.deploy.GameLoop;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.state.statesEnum.State;
import spaceBouncer.utility.loaders.File;

public abstract class Picture {

    protected final String plain = "resources/plain.png";
    protected final String infoPictures = "infoPictures";
    protected final String warningPictures = "warningPictures";

    protected VertexArrayObject vao;
    protected Shader shader;
    protected Texture texture;

    protected int picturePointer;

    protected void update(String picturePath){
        String path = "";
        if(GameLoop.gameState == State.THE_EARTH) path = "resources/" + picturePath + "/earth/";
        else if(GameLoop.gameState == State.SPACE) path = "resources/" + picturePath + "/space/";

        String currentPath = path + picturePointer + ".png";
        if(File.exists(currentPath)) {
            texture = new Texture(currentPath);
        } else {
            return;
        }
    }

    protected void render(){
        shader.activate();
        texture.bind();
        vao.bind();
        vao.draw();
        vao.unbind();
        texture.unbind();
        shader.deactivate();
    }

    public void reset(){
        texture = new Texture(plain);
    }

    public void setPicturePointer(int picturePointer){
        this.picturePointer = picturePointer;
    }

}
