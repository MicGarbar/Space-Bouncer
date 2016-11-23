package spaceBouncer.states.features;

import spaceBouncer.deploy.GameLoop;

public interface TroposphereFeatures {

    float screenScaleFactor = (float) GameLoop.HEIGHT / (float) GameLoop.WIDTH;

    String vertexSource = "shader/vertex/troposphere.vert";
    String fragmentSource = "shader/fragment/troposphere.frag";
    String textureSource = "resources/troposphereAnimation/troposphere";
    String sampler = "textureSampler";
    String projectionMatrix = "projectionMatrix";

    int activeTexture = 0;
    int framesAmount = 4;
    double textureFps = 1.2;

    float[] vertices = new float[]{
            -16.0f, -16.0f * screenScaleFactor, 0.0f,
            -16.0f, 16.0f * screenScaleFactor, 0.0f,
            16.0f, 16.0f * screenScaleFactor, 0.0f,
            16.0f, -16.0f * screenScaleFactor, 0.0f
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
