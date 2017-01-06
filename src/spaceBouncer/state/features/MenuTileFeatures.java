package spaceBouncer.state.features;

public interface MenuTileFeatures {

    float tileSizeX = 5.0f;
    float tileSizeY = 1.0f;

    String vertexSource = "shader/vertex/menuTile.vert";
    String fragmentSource = "shader/fragment/menuTile.frag";
    String sampler = "textureSampler";
    String projectionMatrix = "projectionMatrix";
    String modelMatrix = "modelMatrix";

    String headingTextureSource = "resources/states/spaceBouncer.png";
    String startGameTextureSource = "resources/states/newGame.png";
    String statsTextureSource = "resources/states/stats.png";
    String quitTextureSource = "resources/states/exit.png";
    String backTextureSource = "resources/states/back.png";

    int activeTexture = 0;

    float[] vertices = new float[]{
            -tileSizeX, -tileSizeY, 0.2f,
            -tileSizeX,  tileSizeY, 0.2f,
            tileSizeX,  tileSizeY, 0.2f,
            tileSizeX, -tileSizeY, 0.2f
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
