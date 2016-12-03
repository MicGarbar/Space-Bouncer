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

    int planesAmount = 5;

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
            new Vector(-20.0f, 7.0f),
            new Vector(20.0f, 8.0f),
            new Vector(-20.0f, 5.0f),
            new Vector(20.0f, 6.0f),
            new Vector(-20.0f, 6.5f)
    };

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
