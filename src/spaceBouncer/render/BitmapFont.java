package spaceBouncer.render;

import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.BufferUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;

public class BitmapFont {

    private String bitmapFontSource = "resources/fontSheet.png";
    private int fontTexture;

    public BitmapFont(){
        setUpFontTexture();
    }

    private void setUpFontTexture(){
        fontTexture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, fontTexture);

        try {
            PNGDecoder decoder = new PNGDecoder(new FileInputStream(bitmapFontSource));
            ByteBuffer buffer = BufferUtils.createByteBuffer(4 * decoder.getWidth() * decoder.getHeight());
            decoder.decode(buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA);
            buffer.flip();

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, decoder.getWidth(), decoder.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);
        } catch (IOException ioe) {
            System.err.println("Nie znaleziono pliku o podanej lokalizacji: " + bitmapFontSource);
            System.exit(-1);
        }

        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void render(String stringToRender, float x, float y, float size, boolean withFontBackground){
        if(withFontBackground) renderFontBackground();

        renderString(stringToRender, fontTexture, 16, x, y, size, size);
    }

    private void renderFontBackground() {
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glColor4f(0, 0, 0, 0.6f);

        glBegin(GL_QUADS);
        glVertex2f(0.45f, 1.0f);
        glVertex2f(1.0f, 1.0f);
        glVertex2f(1.0f, -1.0f);
        glVertex2f(0.45f, -1.0f);
        glEnd();
    }

    private void renderString(String text, int fontTexture, int gridDimension, float x, float y,
                              float glyphWidth, float glyphHeight){
        glPushAttrib(GL_TEXTURE_BIT | GL_ENABLE_BIT | GL_COLOR_BUFFER_BIT);
        glEnable(GL_CULL_FACE);
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, fontTexture);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glEnable(GL_BLEND);
        glBlendFunc(GL_ONE, GL_ONE);

        glPushMatrix();
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);

        glColor3f(1, 1, 1);

        for(int i = 0; i < text.length(); i++){
            int asciiCode = (int) text.charAt(i);
            float cellSize = 1.0f / gridDimension;
            float cellX = (asciiCode % gridDimension) * cellSize;
            float cellY = (asciiCode / gridDimension) * cellSize;

            glTexCoord2f(cellX, cellY + cellSize);
            glVertex2f(i * glyphWidth / 3, y);

            glTexCoord2f(cellX + cellSize, cellY + cellSize);
            glVertex2f(i * glyphWidth / 3 + glyphWidth / 2, y);

            glTexCoord2f(cellX + cellSize, cellY);
            glVertex2f(i * glyphWidth / 3 + glyphWidth / 2, y + glyphHeight);

            glTexCoord2f(cellX, cellY);
            glVertex2f(i * glyphWidth / 3, y + glyphHeight);
        }

        glEnd();
        glPopMatrix();
        glPopAttrib();
    }

}
