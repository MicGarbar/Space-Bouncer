package spaceBouncer.render;

import spaceBouncer.utility.buffers.Buffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class VertexArrayObject {

    private final int VERTEX_LOCATION = 0;
    private final int TEXTURE_LOCATION = 1;

    private int vertexArrayObject;
    private int vertexBufferObject;
    private int indicesBufferObject;
    private int textureBuffedObject;
    private int indicesAmount;

    public VertexArrayObject(float[] vertices, byte[] indices, float[] textureCoordinates){
        indicesAmount = indices.length;

        extractVAO();
        extractVBO(vertices);
        extractTBO(textureCoordinates);
        extractIBO(indices);
        unbindAll();
    }

    private void unbindAll() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    private void extractIBO(byte[] indices) {
        indicesBufferObject = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferObject);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Buffer.toByteBuffer(indices), GL_STATIC_DRAW);
    }

    private void extractTBO(float[] textureCoordinates) {
        textureBuffedObject = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, textureBuffedObject);
        glBufferData(GL_ARRAY_BUFFER, Buffer.toFloatBuffer(textureCoordinates), GL_STATIC_DRAW);
        glVertexAttribPointer(TEXTURE_LOCATION, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(TEXTURE_LOCATION);
    }

    private void extractVBO(float[] vertices) {
        vertexBufferObject = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vertexBufferObject);
        glBufferData(GL_ARRAY_BUFFER, Buffer.toFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(VERTEX_LOCATION, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(VERTEX_LOCATION);
    }

    private void extractVAO() {
        vertexArrayObject = glGenVertexArrays();
        glBindVertexArray(vertexArrayObject);
    }

    public void bind(){
        glBindVertexArray(vertexArrayObject);
        if(indicesBufferObject > 0)
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferObject);
    }

    public void unbind(){
        if(indicesBufferObject > 0)
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void draw(){
        if(indicesBufferObject > 0)
            glDrawElements(GL_TRIANGLES, indicesAmount, GL_UNSIGNED_BYTE, 0);
        else
            glDrawArrays(GL_TRIANGLES, 0, indicesAmount);
    }

}
