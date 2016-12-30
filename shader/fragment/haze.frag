#version 330 core

layout (location = 0) out vec4 outputColor;

uniform float hazeRecallTime;

void main(){
    if(hazeRecallTime > 1.0)
    		discard;
    outputColor = vec4(1.0, 1.0, 1.0, 1.0 - hazeRecallTime);
}