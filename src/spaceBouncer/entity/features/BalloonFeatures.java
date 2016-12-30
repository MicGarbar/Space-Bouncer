package spaceBouncer.entity.features;

public interface BalloonFeatures {

    float balloonSize = 1.5f;

    String vertexSource = "shader/vertex/entity.vert";
    String fragmentSource = "shader/fragment/entity.frag";
    String sampler = "textureSampler";
    String modelMatrix = "modelMatrix";
    String projectionMatrix = "projectionMatrix";

    int activeTexture = 0;

    float[] vertices = new float[]{
            -balloonSize, -balloonSize, 0.2f,
            -balloonSize,  balloonSize, 0.2f,
            balloonSize,  balloonSize, 0.2f,
            balloonSize, -balloonSize, 0.2f
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
