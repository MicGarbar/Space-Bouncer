package spaceBouncer.state.features;

import spaceBouncer.deploy.GameLoop;

public interface SpaceFeatures {

    float screenScaleFactor = (float) GameLoop.HEIGHT / (float) GameLoop.WIDTH;

    String vertexSource = "shader/vertex/space.vert";
    String fragmentSource = "shader/fragment/space.frag";
    String textureSource = "resources/space.png";
    String sampler = "textureSampler";
    String projectionMatrix = "projectionMatrix";

    int activeTexture = 0;

    /**
     * PROBES
     */

    int probesAmount = 0;

    String probeTexture0 = "resources/probe/probe0.png";
    String probeTexture1 = "resources/probe/probe1.png";
    String probeTexture2 = "resources/probe/probe2.png";
    String probeTexture3 = "resources/probe/probe3.png";

    String[] probeTextures = new String[]{
            probeTexture0, probeTexture1, probeTexture2, probeTexture3
    };

    /**
     * ASTEROIDS
     */

    int asteroidsAmount = 0;

    String asteroidTexture0 = "resources/asteroid/asteroid0.png";
    String asteroidTexture1 = "resources/asteroid/asteroid1.png";

    String[] asteroidTextures = new String[]{
            asteroidTexture0, asteroidTexture1
    };

    /**
     * COMETS
     */

    int cometsAmount = 5;

    String cometTexture0 = "resources/comet/comet0.png";
    String cometTexture1 = "resources/comet/comet1.png";
    String cometTexture2 = "resources/comet/comet2.png";

    String[] cometTextures = new String[]{
            cometTexture0, cometTexture1, cometTexture2
    };

    /**
     * SPACE ITSELF
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
