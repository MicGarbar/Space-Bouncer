package spaceBouncer.state.features;

import spaceBouncer.deploy.GameLoop;

public interface MainMenuFeatures {

    float screenScaleFactor = (float) GameLoop.HEIGHT / (float) GameLoop.WIDTH;

    String vertexSource = "shader/vertex/menu.vert";
    String fragmentSource = "shader/fragment/menu.frag";
    String textureSource = "resources/menu.png";
    String sampler = "textureSampler";
    String projectionMatrix = "projectionMatrix";

    String newGameTextureSource = "resources/states/newGame.png";
    String statsTextureSource = "resources/states/stats.png";
    String exitTextureSource = "resources/states/exit.png";
    String newGameBrightTextureSource = "resources/states/newGameBright.png";
    String statsBrightTextureSource = "resources/states/statsBright.png";
    String exitBrightTextureSource = "resources/states/exitBright.png";

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
