package spaceBouncer.entity.features;

public interface DimFeatures {

    String vertexSource = "shader/vertex/dim.vert";
    String fragmentSource = "shader/fragment/dim.frag";
    String sampler = "textureSampler";
    String projectionMatrix = "projectionMatrix";

    float[] vertices = new float[]{
            -16.0f, -9.0f, 0.2f,
            -16.0f,  9.0f, 0.2f,
            16.0f,  9.0f, 0.2f,
            16.0f, -9.0f, 0.2f
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
