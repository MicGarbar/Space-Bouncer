package spaceBouncer.entity.features;

public interface PlaneFeatures {

    float planeSize = 1.5f;

    String vertexSource = "shader/vertex/plane.vert";
    String fragmentSource = "shader/fragment/plane.frag";
    String textureSource = "resources/plane.png";
    String sampler = "textureSampler";
    String modelMatrix = "modelMatrix";
    String projectionMatrix = "projectionMatrix";

    int activeTexture = 0;

    float[] vertices = new float[]{
            -planeSize, -planeSize, 0.2f,
            -planeSize,  planeSize, 0.2f,
            planeSize,  planeSize, 0.2f,
            planeSize, -planeSize, 0.2f
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
