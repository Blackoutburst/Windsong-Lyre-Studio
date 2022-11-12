#version 410

layout(location = 0) in vec2 position;

uniform mat4 model;
uniform mat4 projection;

out vec2 vertPos;

void main() {
    vertPos = position;
    gl_Position = projection * model * vec4(position, 0.0, 1.0);
}