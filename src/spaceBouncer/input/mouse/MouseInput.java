package spaceBouncer.input.mouse;

import org.lwjgl.glfw.GLFWMouseButtonCallback;

import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseInput extends GLFWMouseButtonCallback {

    public static boolean[] buttons = new boolean[3];

    @Override
    public void invoke(long window, int button, int action, int mods) {
        buttons[button] = action != GLFW_RELEASE;
    }

    public static boolean isMousePressed(int button){
        return buttons[button];
    }
}
