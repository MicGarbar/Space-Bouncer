package spaceBouncer.entity.features;

public interface PlayerFeatures {

    String vertexSource = "shader/vertex/player.vert";
    String fragmentSource = "shader/fragment/player.frag";
    String textureSource = "resources/player.png";
    String sampler = "textureSampler";
    int activeTexture = 1;

    float[] vertices = new float[]{
            -0.5f, -0.5f, 0.2f,
            -0.5f,  0.5f, 0.2f,
            0.5f,  0.5f, 0.2f,
            0.5f, -0.5f, 0.2f
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
