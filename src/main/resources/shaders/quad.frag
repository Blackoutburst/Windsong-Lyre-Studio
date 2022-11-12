#version 410

in vec2 uv;
in vec2 vertPos;

uniform vec4 color;
uniform sampler2D text;

out vec4 FragColor;

void main() {
	FragColor = color * texture(text, uv);
}