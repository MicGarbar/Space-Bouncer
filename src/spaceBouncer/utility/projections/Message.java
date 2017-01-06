package spaceBouncer.utility.projections;

import spaceBouncer.render.BitmapFont;
import spaceBouncer.utility.loaders.File;

public class Message {

    private Message() {}

    public static void defeatMessage(BitmapFont bitmapFont){
        bitmapFont.render("PRZEGRANA", -0.45f, 0.15f, 0.15f, true);
        bitmapFont.render("NACISNIJ SPACJE,", -0.6f, -0.05f, 0.15f, true);
        bitmapFont.render("ABY WROCIC DO MENU", -0.65f, -0.15f, 0.15f, true);
    }

    public static void earthAccomplished(BitmapFont bitmapFont){
        bitmapFont.render("GRATULACJE !!!", -0.55f, 0.15f, 0.15f, true);
        bitmapFont.render("POZIOM UKONCZONY !!!", -0.7f, 0.05f, 0.15f, true);
        bitmapFont.render("NACISNIJ SPACJE,", -0.6f, -0.2f, 0.15f, true);
        bitmapFont.render("ABY WYRUSZYC W KOSMOS", -0.75f, -0.3f, 0.15f, true);
    }

    public static void spaceAccomplished(BitmapFont bitmapFont){
        bitmapFont.render("GRATULACJE !!!", -0.55f, 0.15f, 0.15f, true);
        bitmapFont.render("POZIOM UKONCZONY !!!", -0.7f, 0.05f, 0.15f, true);
        bitmapFont.render("NACISNIJ SPACJE,", -0.6f, -0.2f, 0.15f, true);
        bitmapFont.render("ABY WROCIC DO MENU", -0.65f, -0.3f, 0.15f, true);
    }

    public static void stats(BitmapFont bitmapFont){
        bitmapFont.render("STATYSTYKI", -0.3f, 0.35f, 0.2f, false);
        bitmapFont.render("ZIEMIA", -0.55f, 0.2f, 0.15f, false);
        bitmapFont.render("KOSMOS", 0.25f, 0.2f, 0.15f, false);
        for(int i = 1; i <= 8; i++){
            if(i <= File.linesAmount("files/earthStats.txt")) {
                bitmapFont.render(File.sortListByScore("files/earthStats.txt").get(i - 1),
                        -0.8f, 0.1f - (((float) i) * 0.05f), 0.1f, false);
            }
            if(i <= File.linesAmount("files/spaceStats.txt")) {
                bitmapFont.render(File.sortListByScore("files/spaceStats.txt").get(i - 1),
                        0.1f, 0.1f - (((float) i) * 0.05f), 0.1f, false);
            }
        }
    }

}
