package spaceBouncer.utility.loaders;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Image {

    private Image() {}

    public static BufferedImage load(String filePath){
        BufferedImage image = null;

        try {
            image = ImageIO.read(new FileInputStream(filePath));
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println("Wczytywanie pliku graficznego nie powdiodło się z nastepującym wyjątkiem: " + ioe.getMessage());
            System.exit(-1);
        }

        return image;
    }

}
