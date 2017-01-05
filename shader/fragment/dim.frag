#version 330 core

layout (location = 0) out vec4 outputColor;

uniform float dimRecallTime;
uniform float red;
uniform float blue;
uniform float green;

void main(){
    if(dimRecallTime > 1.0)
    		discard;
    outputColor = vec4(red, green, blue, 1.0 - dimRecallTime);
}