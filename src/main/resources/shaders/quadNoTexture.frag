#version 410

in vec2 vertPos;

uniform vec4 color;
uniform float radius;

out vec4 FragColor;

void main() {
	FragColor = color;
}