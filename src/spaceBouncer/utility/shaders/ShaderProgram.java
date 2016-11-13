package spaceBouncer.utility.shaders;

import spaceBouncer.utility.loaders.File;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

    private ShaderProgram(){}

    public static int load(String vertPath, String fragPath){
        String vertexSource = File.load(vertPath);
        String fragmentSource = File.load(fragPath);
        return create(vertexSource, fragmentSource);
    }

    public static int create(String vert, String frag){
        int programID = glCreateProgram();
        int vertexID = glCreateShader(GL_VERTEX_SHADER);
        int fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

        glShaderSource(vertexID, vert);
        glShaderSource(fragmentID, frag);

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
