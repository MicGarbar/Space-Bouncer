package spaceBouncer.entity.features;

public interface CloudFeatures {

    float cloudSize = 1.5f;

    String vertexSource = "shader/vertex/balloon.vert";
    String fragmentSource = "shader/fragment/balloon.frag";
    String sampler = "textureSampler";
    String projectionMatrix = "projectionMatrix";
    String modelMatrix = "modelMatrix";

    int activeTexture = 0;

    float[] vertices = new float[]{
            -cloudSize, -cloudSize, 0.2f,
            -cloudSize,  cloudSize, 0.2f,
            cloudSize,  cloudSize, 0.2f,
            cloudSize, -cloudSize, 0.2f
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
