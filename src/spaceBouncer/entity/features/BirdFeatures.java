package spaceBouncer.entity.features;

public interface BirdFeatures {

    float birdSize = 1.0f;

    String vertexSource = "shader/vertex/bird.vert";
    String fragmentSource = "shader/fragment/bird.frag";
    String sampler = "textureSampler";
    String modelMatrix = "modelMatrix";
    String projectionMatrix = "projectionMatrix";

    int activeTexture = 0;

    float[] vertices = new float[]{
            -birdSize, -birdSize, 0.2f,
            -birdSize,  birdSize, 0.2f,
            birdSize,  birdSize, 0.2f,
            birdSize, -birdSize, 0.2f
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
