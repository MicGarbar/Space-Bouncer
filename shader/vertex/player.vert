#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 texture;

uniform mat4 modelMatrix = mat4(1.0);

out vec2 textureCoordinates;

void main(){
    gl_Position = modelMatrix * position;
    textureCoordinates = texture;
}