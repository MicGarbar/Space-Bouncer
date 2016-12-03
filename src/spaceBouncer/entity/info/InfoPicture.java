package spaceBouncer.entity.info;

import spaceBouncer.entity.features.InfoPictureFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.loaders.File;

public class InfoPicture implements InfoPictureFeatures {

    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private int picturePointer;

    public InfoPicture(){
        texture = new Texture(plainTextureSoure);
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        shader.setSamplerUniform(sampler, activeTexture);
    }

    public void update(){
        String currentPath = "resources/infoPictures/" + picturePointer + ".png";
        if(File.exists(currentPath)) {
            texture = new Texture(currentPath);
        } else return;
    }

    public void render(){
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
