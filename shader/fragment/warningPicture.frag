#version 330 core

layout (location = 0) out vec4 outputColor;

in vec2 textureCoordinates;

uniform sampler2D textureSampler;

void main(){
    outputColor = texture(textureSampler, textureCoordinates);
}