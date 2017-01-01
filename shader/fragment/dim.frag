#version 330 core

layout (location = 0) out vec4 outputColor;

uniform float dimRecallTime;

void main(){
    if(dimRecallTime > 1.0)
    		discard;
    outputColor = vec4(0.1, 0.1, 1.0, 1.0 - dimRecallTime);
}