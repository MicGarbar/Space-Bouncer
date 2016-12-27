package spaceBouncer.states.features;

import spaceBouncer.deploy.GameLoop;

import java.util.Random;

public interface EarthFeatures {

    float screenScaleFactor = (float) GameLoop.HEIGHT / (float) GameLoop.WIDTH;

    String vertexSource = "shader/vertex/earth.vert";
    String fragmentSource = "shader/fragment/earth.frag";
    String textureSource = "resources/earth.png";
    String sampler = "textureSampler";
    String projectionMatrix = "projectionMatrix";

    int activeTexture = 0;

    /**
     * PLANES
     */

    int planesAmount = 10;

    String planeTexture0 = "resources/plane/airplane.png";
    String planeTexture1 = "resources/plane/plane.png";

    String[] planeTextures = new String[]{
            planeTexture0, planeTexture1
    };

    /**
     * BALLOONS
     */

    int balloonsAmount = 10;

    String balloonTexture0 = "resources/balloon/balloon0.png";
    String balloonTexture1 = "resources/balloon/balloon1.png";
    String balloonTexture2 = "resources/balloon/balloon2.png";
    String balloonTexture3 = "resources/balloon/balloon3.png";

    String[] balloonTextures = new String[]{
            balloonTexture0, balloonTexture1, balloonTexture2, balloonTexture3
    };

    /**
     * CLOUDS
     */

    int cloudsAmount = 10;

    String cloudTexture0 = "resources/cloud/cloud0.png";
    String cloudTexture1 = "resources/cloud/cloud1.png";
    String cloudTexture2 = "resources/cloud/cloud2.png";
    String cloudTexture3 = "resources/cloud/cloud3.png";

    String[] cloudTextures = new String[]{
            cloudTexture0, cloudTexture1, cloudTexture2, cloudTexture3
    };

    /**
     * EARTH ITSELF
     */

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
