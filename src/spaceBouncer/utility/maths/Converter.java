package spaceBouncer.utility.maths;

import spaceBouncer.deploy.GameLoop;

public class Converter {

    private static float orthographicTileWidth = 5.0f;
    private static float orthographicTileHeight = 1.0f;
    private static float orthographicToNormalFactorX = (float) GameLoop.WIDTH / 32.0f;
    private static float orthographicToNormalFactorY = (float) GameLoop.HEIGHT / 18.0f;
    private static float orthographicOffsetX = 640.0f;
    private static float orthographicOffsetY = 360.0f;

    private Converter() {}

    public static float fromOrthographicToNormalX(float orthographicX){
        return (orthographicX - orthographicTileWidth) * orthographicToNormalFactorX + orthographicOffsetX;
    }

    public static float fromOrthographicToNormalY(float orthographicY){
        return (-orthographicY - orthographicTileHeight) * orthographicToNormalFactorY + orthographicOffsetY;
    }

    public static float fromOrthographicToNormalWidth(float orthographicWidth){
        return orthographicWidth * orthographicToNormalFactorX * 2.0f;
    }

    public static float fromOrthographicToNormalHeight(float orthographicHeight){
        return orthographicHeight * orthographicToNormalFactorY * 2.0f;
    }

}
