package spaceBouncer.entity.features;

public interface PictureFeatures {

    String vertexSource = "shader/vertex/picture.vert";
    String fragmentSource = "shader/fragment/picture.frag";
    String sampler = "textureSampler";
    String plainTextureSource = "resources/plain.png";

    int activeTexture = 0;

    float[] infoPictureVertices = new float[]{
            0.5f, 0.0f, 0.3f,
            0.5f, 0.5f, 0.3f,
            0.95f, 0.5f, 0.3f,
            0.95f, 0.0f, 0.3f
    };

    float[] warningPictureVertices = new float[]{
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
