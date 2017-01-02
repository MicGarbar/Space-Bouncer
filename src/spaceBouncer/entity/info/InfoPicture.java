package spaceBouncer.entity.info;

import spaceBouncer.deploy.GameLoop;
import spaceBouncer.entity.features.InfoPictureFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.state.statesEnum.State;
import spaceBouncer.utility.loaders.File;

public class InfoPicture extends Picture implements InfoPictureFeatures {

    public InfoPicture(){
        texture = new Texture(plainTextureSource);
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        shader.setSamplerUniform(sampler, activeTexture);
    }

    public void update(){
        String path = "";
        if(GameLoop.gameState == State.THE_EARTH) path = "resources/infoPictures/";
        else if(GameLoop.gameState == State.SPACE) path = "resources/infoPictures/space/";

        String currentPath = path + picturePointer + ".png";
        if(File.exists(currentPath)) {
            texture = new Texture(currentPath);
        } else return;
    }

    public void render(){
        super.render();
    }

}
