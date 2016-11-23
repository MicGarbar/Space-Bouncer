package spaceBouncer.render;

import spaceBouncer.utility.buffers.Buffer;
import spaceBouncer.utility.loaders.Image;

import java.awt.image.BufferedImage;

import static org.lwjgl.opengl.GL11.*;

public class Texture {

    private int width;
    private int height;
    private int textureID;

    public Texture(String filePath){
        textureID = loadTexture(filePath);
    }

    public Texture(String filePath, int x, int y, int width, int height){
        textureID = loadSubTexture(filePath, x, y, width, height);
    }

    private int loadSubTexture(String filePath, int x, int y, int width, int height){
        BufferedImage image = Image.load(filePath);
        BufferedImage subImage = image.getSubimage(x, y, width, height);

        return getTextureInRGBA(subImage);
    }

    private int loadTexture(String filePath){
        BufferedImage image = Image.load(filePath);

        return getTextureInRGBA(image);
    }

    private int getTextureInRGBA(BufferedImage image){
        width = image.getWidth();
        height = image.getHeight();

        int[] ARGBData = new int[width * height];
        image.getRGB(0, 0, width, height, ARGBData, 0, width);

        int[] RGBAData = new int[width * height];
        for(int i = 0; i < width * height; i++){
            int a = (ARGBData[i] & 0xff000000) >> 24;
            int r = (ARGBData[i] & 0xff0000) >> 16;
            int g = (ARGBData[i] & 0xff00) >> 8;
            int b = (ARGBData[i] & 0xff);

            RGBAData[i] = a << 24 | b << 16 | g << 8 | r;
        }

        int texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, Buffer.toIntBuffer(RGBAData));

        return texture;
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, textureID);
    }

    public void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }

}
