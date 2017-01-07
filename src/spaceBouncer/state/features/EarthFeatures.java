package spaceBouncer.state.features;

import spaceBouncer.deploy.GameLoop;

public interface EarthFeatures {

    float screenScaleFactor = (float) GameLoop.HEIGHT / (float) GameLoop.WIDTH;

    int FINISH_HEIGHT = 12000;

    String vertexSource = "shader/vertex/earth.vert";
    String fragmentSource = "shader/fragment/earth.frag";
    String textureSource = "resources/earth.png";
    String sampler = "textureSampler";
    String projectionMatrix = "projectionMatrix";

    int activeTexture = 0;

    /**
     * PLANES
     */

    int planesAmount = 30;

    float planeCollisionFactor = -0.65f;

    String planeTexture0 = "resources/plane/airplane.png";
    String planeTexture1 = "resources/plane/plane.png";

    String[] planeTextures = new String[]{
            planeTexture0, planeTexture1
    };

    /**
     * BALLOONS
     */

    int balloonsAmount = 30;

    float balloonCollisionFactor = -0.5f;

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

    int cloudsAmount = 30;

    float cloudCollisionHazeFactor = 0.3f;

    String cloudTexture0 = "resources/cloud/cloud0.png";
    String cloudTexture1 = "resources/cloud/cloud1.png";
    String cloudTexture2 = "resources/cloud/cloud2.png";
    String cloudTexture3 = "resources/cloud/cloud3.png";

    String[] cloudTextures = new String[]{
            cloudTexture0, cloudTexture1, cloudTexture2, cloudTexture3
    };

    /**
     * BIRDS
     */

    int birdsAmount = 30;

    float birdCollisionFactor = -0.3f;

    String birdTexture0 = "resources/bird/bird0.png";
    String birdTexture1 = "resources/bird/bird1.png";
    String birdTexture2 = "resources/bird/bird2.png";
    String birdTexture3 = "resources/bird/bird3.png";

    String[] birdTextures = new String[]{
            birdTexture0, birdTexture1, birdTexture2, birdTexture3
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
