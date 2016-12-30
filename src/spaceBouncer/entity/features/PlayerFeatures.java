package spaceBouncer.entity.features;

public interface PlayerFeatures {

    float playerSize = 1.5f;

    String vertexSource = "shader/vertex/entity.vert";
    String fragmentSource = "shader/fragment/entity.frag";
    String textureSource = "resources/player.png";
    String sampler = "textureSampler";
    String modelMatrix = "modelMatrix";
    String projectionMatrix = "projectionMatrix";

    int activeTexture = 0;

    float[] vertices = new float[]{
            -playerSize, -playerSize, 0.2f,
            -playerSize,  playerSize, 0.2f,
            playerSize,  playerSize, 0.2f,
            playerSize, -playerSize, 0.2f
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
