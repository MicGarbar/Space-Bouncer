package spaceBouncer.states.features;

import spaceBouncer.deploy.GameLoop;

public interface TroposphereFeatures {

    float screenScaleFactor = GameLoop.HEIGHT / GameLoop.WIDTH;

    String vertexSource = "shader/vertex/troposphere.vert";
    String fragmentSource = "shader/fragment/troposphere.frag";
    String textureSource = "resources/troposphere.png";
    String sampler = "textureSampler";
    String projectionMatrix = "projectionMatrix";
    int activeTexture = 0;
/*
    float[] vertices = new float[]{
            -10.0f, -10.0f * screenScaleFactor, 0.0f,
            -10.0f,  10.0f * screenScaleFactor, 0.0f,
            10.0f,  10.0f * screenScaleFactor, 0.0f,
            10.0f, -10.0f * screenScaleFactor, 0.0f
    };
    */
    float[] vertices = new float[]{
            -16.0f, -9.0f, 0.0f,
            -16.0f, 9.0f, 0.0f,
            16.0f, 9.0f, 0.0f,
            16.0f, -9.0f, 0.0f
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
