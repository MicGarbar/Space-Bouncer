package spaceBouncer.entity.features;

public interface EntityFeatures {

    float asteroidSize = 1.2f;
    float balloonSize = 1.5f;
    float birdSize = 1.0f;
    float cloudSize = 1.5f;
    float cometSize = 1.5f;
    float hubbleSize = 2.0f;
    float ISSSize = 2.0f;
    float planeSize = 1.5f;
    float playerSize = 1.5f;
    float probeSize = 1.2f;
    float voyagerSize = 1.5f;

    String vertexSource = "shader/vertex/entity.vert";
    String fragmentSource = "shader/fragment/entity.frag";
    String sampler = "textureSampler";
    String modelMatrix = "modelMatrix";
    String projectionMatrix = "projectionMatrix";

    String hubbleTextureSource = "resources/hubble.png";
    String ISSTextureSource = "resources/ISS.png";
    String playerTextureSource = "resources/player.png";
    String voyagerTextureSource = "resources/voyager.png";

    int activeTexture = 0;

    float[] asteroidVertices = new float[]{
            -asteroidSize, -asteroidSize, 0.2f,
            -asteroidSize,  asteroidSize, 0.2f,
            asteroidSize,  asteroidSize, 0.2f,
            asteroidSize, -asteroidSize, 0.2f
    };

    float[] balloonVertices = new float[]{
            -balloonSize, -balloonSize, 0.2f,
            -balloonSize,  balloonSize, 0.2f,
            balloonSize,  balloonSize, 0.2f,
            balloonSize, -balloonSize, 0.2f
    };

    float[] birdVertices = new float[]{
            -birdSize, -birdSize, 0.2f,
            -birdSize,  birdSize, 0.2f,
            birdSize,  birdSize, 0.2f,
            birdSize, -birdSize, 0.2f
    };

    float[] cloudVertices = new float[]{
            -cloudSize, -cloudSize, 0.2f,
            -cloudSize,  cloudSize, 0.2f,
            cloudSize,  cloudSize, 0.2f,
            cloudSize, -cloudSize, 0.2f
    };

    float[] cometVertices = new float[]{
            -cometSize, -cometSize, 0.2f,
            -cometSize,  cometSize, 0.2f,
            cometSize,  cometSize, 0.2f,
            cometSize, -cometSize, 0.2f
    };

    float[] hubbleVertices = new float[]{
            -hubbleSize, -hubbleSize, 0.2f,
            -hubbleSize,  hubbleSize, 0.2f,
            hubbleSize,  hubbleSize, 0.2f,
            hubbleSize, -hubbleSize, 0.2f
    };

    float[] ISSVertices = new float[]{
            -ISSSize, -ISSSize, 0.2f,
            -ISSSize,  ISSSize, 0.2f,
            ISSSize,  ISSSize, 0.2f,
            ISSSize, -ISSSize, 0.2f
    };

    float[] planeVertices = new float[]{
            -planeSize, -planeSize, 0.2f,
            -planeSize,  planeSize, 0.2f,
            planeSize,  planeSize, 0.2f,
            planeSize, -planeSize, 0.2f
    };

    float[] playerVertices = new float[]{
            -playerSize, -playerSize, 0.2f,
            -playerSize,  playerSize, 0.2f,
            playerSize,  playerSize, 0.2f,
            playerSize, -playerSize, 0.2f
    };

    float[] probeVertices = new float[]{
            -probeSize, -probeSize, 0.2f,
            -probeSize,  probeSize, 0.2f,
            probeSize,  probeSize, 0.2f,
            probeSize, -probeSize, 0.2f
    };

    float[] voyagerVertices = new float[]{
            -voyagerSize, -voyagerSize, 0.2f,
            -voyagerSize,  voyagerSize, 0.2f,
            voyagerSize,  voyagerSize, 0.2f,
            voyagerSize, -voyagerSize, 0.2f
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
