package spaceBouncer.state.features;

import spaceBouncer.deploy.GameLoop;

public interface StatsFeatures {

    float screenScaleFactor = (float) GameLoop.HEIGHT / (float) GameLoop.WIDTH;

    String vertexSource = "shader/vertex/menu.vert";
    String fragmentSource = "shader/fragment/menu.frag";
    String textureSource = "resources/stats.png";
    String sampler = "textureSampler";
    String projectionMatrix = "projectionMatrix";

    String backTextureSource = "resources/states/back.png";
    String backBrightTextureSource = "resources/states/backBright.png";

    int activeTexture = 0;

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
