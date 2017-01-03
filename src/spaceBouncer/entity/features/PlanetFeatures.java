package spaceBouncer.entity.features;

public interface PlanetFeatures {

    int planetsAmount = 8;

    float mercurySize = 2.0f;
    float venusSize = 2.5f;
    float earthSize = 3.0f;
    float marsSize = 2.2f;
    float jupiterSize = 6.0f;
    float saturnSize = 5.5f;
    float uranusSize = 4.0f;
    float neptuneSize = 4.0f;

    float[] planetsSize = new float[]{
            mercurySize, venusSize, earthSize, marsSize,
            jupiterSize, saturnSize, uranusSize, neptuneSize
    };

    float mercuryVelocity = 0.06f;
    float venusVelocity = 0.03f;
    float earthVelocity = 0.03f;
    float marsVelocity = 0.05f;
    float jupiterVelocity = 0.01f;
    float saturnVelocity = 0.015f;
    float uranusVelocity = 0.02f;
    float neptuneVelocity = 0.02f;

    float[] planetVelocities = new float[]{
            mercuryVelocity, venusVelocity, earthVelocity,
            marsVelocity, jupiterVelocity, saturnVelocity,
            uranusVelocity, neptuneVelocity
    };

    int mercuryTriggerAttitude = 57;
    int venusTriggerAttitude = 108;
    int earthTriggerAttitude = 149;
    int marsTriggerAttitude = 228;
    int jupiterTriggerAttitude = 778;
    int saturnTriggerAttitude = 1426;
    int uranusTriggerAttitude = 2870;
    int neptuneTriggerAttitude = 4498;

    int[] planetsTriggerAttitude = new int[]{
            mercuryTriggerAttitude, venusTriggerAttitude, earthTriggerAttitude,
            marsTriggerAttitude, jupiterTriggerAttitude, saturnTriggerAttitude,
            uranusTriggerAttitude, neptuneTriggerAttitude
    };

    String mercuryTextureSource = "resources/planet/mercury.png";
    String venusTextureSource = "resources/planet/venus.png";
    String earthTextureSource = "resources/planet/earth.png";
    String marsTextureSource = "resources/planet/mars.png";
    String jupiterTextureSource = "resources/planet/jupiter.png";
    String saturnTextureSource = "resources/planet/saturn.png";
    String uranusTextureSource = "resources/planet/uranus.png";
    String neptuneTextureSource = "resources/planet/neptune.png";

    String[] planetsTextureSources = new String[]{
            mercuryTextureSource, venusTextureSource, earthTextureSource,
            marsTextureSource, jupiterTextureSource, saturnTextureSource,
            uranusTextureSource, neptuneTextureSource
    };

    float[] mercuryVertices = new float[]{
            -mercurySize, -mercurySize, 0.2f,
            -mercurySize,  mercurySize, 0.2f,
            mercurySize,  mercurySize, 0.2f,
            mercurySize, -mercurySize, 0.2f
    };

    float[] venusVertices = new float[]{
            -venusSize, -venusSize, 0.2f,
            -venusSize,  venusSize, 0.2f,
            venusSize,  venusSize, 0.2f,
            venusSize, -venusSize, 0.2f
    };

    float[] earthVertices = new float[]{
            -earthSize, -earthSize, 0.2f,
            -earthSize,  earthSize, 0.2f,
            earthSize,  earthSize, 0.2f,
            earthSize, -earthSize, 0.2f
    };

    float[] marsVertices = new float[]{
            -marsSize, -marsSize, 0.2f,
            -marsSize,  marsSize, 0.2f,
            marsSize,  marsSize, 0.2f,
            marsSize, -marsSize, 0.2f
    };

    float[] jupiterVertices = new float[]{
            -jupiterSize, -jupiterSize, 0.2f,
            -jupiterSize,  jupiterSize, 0.2f,
            jupiterSize,  jupiterSize, 0.2f,
            jupiterSize, -jupiterSize, 0.2f
    };

    float[] saturnVertices = new float[]{
            -saturnSize, -saturnSize, 0.2f,
            -saturnSize,  saturnSize, 0.2f,
            saturnSize,  saturnSize, 0.2f,
            saturnSize, -saturnSize, 0.2f
    };

    float[] uranusVertices = new float[]{
            -uranusSize, -uranusSize, 0.2f,
            -uranusSize,  uranusSize, 0.2f,
            uranusSize,  uranusSize, 0.2f,
            uranusSize, -uranusSize, 0.2f
    };

    float[] neptuneVertices = new float[]{
            -neptuneSize, -neptuneSize, 0.2f,
            -neptuneSize,  neptuneSize, 0.2f,
            neptuneSize,  neptuneSize, 0.2f,
            neptuneSize, -neptuneSize, 0.2f
    };

    float[][] planetsVertices = new float[][]{
            mercuryVertices, venusVertices, earthVertices, marsVertices,
            jupiterVertices, saturnVertices, uranusVertices, neptuneVertices
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
