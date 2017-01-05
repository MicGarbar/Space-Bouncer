package spaceBouncer.entity.info;

import spaceBouncer.entity.features.PictureFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;

public class WarningPicture extends Picture implements PictureFeatures {

    public WarningPicture(){
        texture = new Texture(plainTextureSource);
        vao = new VertexArrayObject(warningPictureVertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        shader.setSamplerUniform(sampler, activeTexture);
    }

    public void update(){
        super.update(warningPictures);
    }

    public void render(){
        super.render();
    }

}
