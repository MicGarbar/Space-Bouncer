package spaceBouncer.entity.info;

import spaceBouncer.entity.features.WarningPictureFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;
import spaceBouncer.utility.loaders.File;

public class WarningPicture extends Picture implements WarningPictureFeatures {

    public WarningPicture(){
        texture = new Texture(plainTextureSource);
        vao = new VertexArrayObject(vertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        shader.setSamplerUniform(sampler, activeTexture);
    }

    public void update(){
        String currentPath = "resources/warningPictures/" + picturePointer + ".png";
        if(File.exists(currentPath)) {
            texture = new Texture(currentPath);
        } else return;
    }

    public void render(){
        super.render();
    }

}
