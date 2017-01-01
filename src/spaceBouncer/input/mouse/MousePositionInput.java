package spaceBouncer.input.mouse;

import org.lwjgl.glfw.GLFWCursorPosCallback;

import java.awt.geom.Rectangle2D;

public class MousePositionInput extends GLFWCursorPosCallback {

    static double mouseX;
    static double mouseY;

    @Override
    public void invoke(long window, double xpos, double ypos) {
        mouseX = xpos;
        mouseY = ypos;
    }

    public static boolean isCursorIn(Rectangle2D.Float rectangle2D){
        return rectangle2D.contains(mouseX, mouseY);
    }

}
