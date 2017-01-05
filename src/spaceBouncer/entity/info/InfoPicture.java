package spaceBouncer.entity.info;

import spaceBouncer.entity.features.PictureFeatures;
import spaceBouncer.render.Shader;
import spaceBouncer.render.Texture;
import spaceBouncer.render.VertexArrayObject;

public class InfoPicture extends Picture implements PictureFeatures {

    public InfoPicture(){
        texture = new Texture(plainTextureSource);
        vao = new VertexArrayObject(infoPictureVertices, indices, textureCoordinates);
        shader = new Shader(vertexSource, fragmentSource);

        shader.setSamplerUniform(sampler, activeTexture);
    }

    public void update(){
        super.update(infoPictures);
    }

    public void render(){
        super.render();
    }

}
