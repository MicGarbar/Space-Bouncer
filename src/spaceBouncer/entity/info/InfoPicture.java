package spaceBouncer.entity.info;

import spaceBouncer.entity.features.InfoPictureFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.loaders.File;

public class InfoPicture extends Picture implements InfoPictureFeatures {

    public InfoPicture(){
        texture = new Texture(plainTextureSource);
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
        super.render();
    }

}
