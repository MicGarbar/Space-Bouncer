package spaceBouncer.states.features;

import spaceBouncer.deploy.GameLoop;
import spaceBouncer.utility.maths.Vector;

public interface TroposphereFeatures {

    float screenScaleFactor = (float) GameLoop.HEIGHT / (float) GameLoop.WIDTH;

    String vertexSource = "shader/vertex/troposphere.vert";
    String fragmentSource = "shader/fragment/troposphere.frag";
    String textureSource = "resources/troposphere.png";
    String sampler = "textureSampler";
    String projectionMatrix = "projectionMatrix";

    int activeTexture = 0;

    int planesAmount = 5;

    int[] planeHeightMilestones = new int[]{
            90, 193, 315, 881, 2000
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
