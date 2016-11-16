package spaceBouncer.utility.shaders;

import spaceBouncer.utility.loaders.File;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

    private ShaderProgram(){}

    public static int load(String vertexPath, String fragmentPath){
        String vertexSource = File.load(vertexPath);
        String fragmentSource = File.load(fragmentPath);
        return create(vertexSource, fragmentSource);
    }

    public static int create(String vertexSource, String fragmentSource){
        int programID = glCreateProgram();
        int vertexID = glCreateShader(GL_VERTEX_SHADER);
        int fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vertexID, vertexSource);
        glShaderSource(fragmentID, fragmentSource);

        glCompileShader(vertexID);
        if(glGetShaderi(vertexID, GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Nieudana kompilacja dla vertex shader z następującym błędem: ");
            System.err.println(glGetShaderInfoLog(vertexID));
            System.exit(-1);
        }

        glCompileShader(fragmentID);
        if(glGetShaderi(fragmentID, GL_COMPILE_STATUS) == GL_FALSE){
            System.err.println("Nieudana kompilacja dla fragment shader z następującym błędem: ");
            System.err.println(glGetShaderInfoLog(fragmentID));
            System.exit(-1);
        }

        glAttachShader(programID, vertexID);
        glAttachShader(programID, fragmentID);
        glLinkProgram(programID);
        glValidateProgram(programID);

        glDeleteShader(vertexID);
        glDeleteShader(fragmentID);

        return programID;
    }

}
