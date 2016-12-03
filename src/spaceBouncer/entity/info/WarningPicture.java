package spaceBouncer.entity.info;

import spaceBouncer.entity.features.WarningPictureFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.loaders.File;

public class WarningPicture implements WarningPictureFeatures {

    private VertexArrayObject vao;
    private Shader shader;
    private Texture texture;

    private int picturePointer;

    public WarningPicture(){
        texture = new Texture(plainTextureSource);
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        shader.setSamplerUniform(sampler, activeTexture);
    }

    public void update(){
        if(picturePointer >= 8950){
            texture = new Texture("resources/warningPictures/planeWarning.png");
        }
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
