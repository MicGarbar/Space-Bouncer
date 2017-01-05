package spaceBouncer.utility.projections;

import spaceBouncer.render.BitmapFont;

public class Message {

    private Message() {}

    public static void defeatMessage(BitmapFont bitmapFont){
        bitmapFont.render("PRZEGRANA", -0.45f, 0.15f);
        bitmapFont.render("NACISNIJ SPACJE,", -0.6f, -0.05f);
        bitmapFont.render("ABY WROCIC DO MENU", -0.65f, -0.15f);
    }

    public static void earthAccomplished(BitmapFont bitmapFont){
        bitmapFont.render("GRATULACJE !!!", -0.55f, 0.15f);
        bitmapFont.render("POZIOM UKONCZONY !!!", -0.7f, 0.05f);
        bitmapFont.render("NACISNIJ SPACJE,", -0.6f, -0.2f);
        bitmapFont.render("ABY WYRUSZYC W KOSMOS", -0.75f, -0.3f);
    }

    public static void spaceAccomplished(BitmapFont bitmapFont){
        bitmapFont.render("GRATULACJE !!!", -0.55f, 0.15f);
        bitmapFont.render("POZIOM UKONCZONY !!!", -0.7f, 0.05f);
        bitmapFont.render("NACISNIJ SPACJE,", -0.6f, -0.2f);
        bitmapFont.render("ABY WROCIC DO MENU", -0.65f, -0.3f);
    }

}
