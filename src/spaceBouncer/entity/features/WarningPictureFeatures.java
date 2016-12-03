package spaceBouncer.entity.features;

public interface WarningPictureFeatures {

    String vertexSource = "shader/vertex/warningPicture.vert";
    String fragmentSource = "shader/fragment/warningPicture.frag";
    String sampler = "textureSampler";
    String plainTextureSource = "resources/plain.png";

    int activeTexture = 0;

    float[] vertices = new float[]{
            0.5f, -0.8f, 0.3f,
            0.5f, -0.2f, 0.3f,
            0.95f, -0.2f, 0.3f,
            0.95f, -0.8f, 0.3f
    };

    byte[] indices = new byte[]{
            0, 1, 2,
            2, 3, 0
    };

    float[] textureCoordinates = new float[]{
            0, 1,
            0, 0,
            1, 0,
            1, 1
    };
}
