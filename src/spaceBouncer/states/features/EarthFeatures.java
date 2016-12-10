package spaceBouncer.states.features;

import spaceBouncer.deploy.GameLoop;
import spaceBouncer.utility.maths.Vector;

import java.util.Random;

public interface EarthFeatures {

    Random rand = new Random();
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

    int planesAmount = 5;

    String planeTexture0 = "resources/plane/airplane.png";
    String planeTexture1 = "resources/plane/plane.png";

    int[] planeHeightMilestones = new int[]{
            // planes at an attitude between:
            rand.nextInt(500) + 9000, // 9000 - 9500
            rand.nextInt(300) + 9600, // 9600 - 9900
            rand.nextInt(400) + 10000, // 10000 - 10400
            rand.nextInt(500) + 10500, // 10500 - 11000
            rand.nextInt(500) + 11100 // 11100 - 11500
    };

    int[] planeRotateMilestones = new int[]{
            0, 180, 0, 180, 0
    };

    float[] planeDeltaMilestones = new float[]{
            0.02f, -0.05f, 0.04f, -0.02f, 0.07f
    };

    Vector[] planePositionMilestones = new Vector[]{
            new Vector(-20.0f, 4.0f),
            new Vector(20.0f, 7.0f),
            new Vector(-20.0f, 5.0f),
            new Vector(20.0f, 3.5f),
            new Vector(-20.0f, 6.5f)
    };

    String[] planeTextures = new String[]{
            planeTexture0, planeTexture1
    };

    /**
     * BALLOONS
     */

    // multiple of 5
    int balloonsAmount = 5;

    String balloonTexture0 = "resources/balloon/balloon0.png";
    String balloonTexture1 = "resources/balloon/balloon1.png";
    String balloonTexture2 = "resources/balloon/balloon2.png";
    String balloonTexture3 = "resources/balloon/balloon3.png";

    int[] balloonHeightMilestones = new int[]{
            // balloon at an attitude between:
            rand.nextInt(20) + 800, // 800 - 820
            rand.nextInt(50) + 830, // 830 - 880
            rand.nextInt(50) + 900, // 900 - 950
            rand.nextInt(50) + 970, // 970 - 1020
            rand.nextInt(60) + 1040 // 1040 - 1100
    };

    float[] balloonDeltaMilestones = new float[]{
            0.02f, -0.05f, 0.04f, -0.02f, 0.07f
    };

    Vector[] balloonPositionMilestones = new Vector[]{
            new Vector(-20.0f, 7.0f),
            new Vector(20.0f, 8.0f),
            new Vector(-20.0f, 5.0f),
            new Vector(20.0f, 6.0f),
            new Vector(-20.0f, 6.5f)
    };

    String[] balloonTextures = new String[]{
            balloonTexture0, balloonTexture1, balloonTexture2, balloonTexture3
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
