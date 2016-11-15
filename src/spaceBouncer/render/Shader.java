package spaceBouncer.render;

import spaceBouncer.utility.maths.Matrix;
import spaceBouncer.utility.shaders.ShaderProgram;

import static org.lwjgl.opengl.GL20.*;

public class Shader {

    private int shaderID;
    private boolean shaderEnabled;

    public Shader(String vertexPath, String fragmentPath){
        shaderID = ShaderProgram.load(vertexPath, fragmentPath);
        shaderEnabled = false;
    }

    public int getUniform(String name){
        int result = glGetUniformLocation(shaderID, name);

        if(result == -1){
            System.err.println("Nie zadeklarowano zmiennej typu uniform: " + name);
        }

        return result;
    }

    public void setSamplerUniform(String name, int value){
        if(!shaderEnabled) activate();
        glUniform1i(getUniform(name), value);
    }

    public void setMatrixUniform(String name, Matrix value){
        if(!shaderEnabled) activate();
        glUniformMatrix4fv(getUniform(name), false, value.toFloatBuffer());
    }

    public void activate(){
        glUseProgram(shaderID);
        shaderEnabled = true;
    }

    public void deactivate(){
        glUseProgram(0);
        shaderEnabled = false;
    }

}
