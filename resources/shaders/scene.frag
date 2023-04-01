#version 330
uniform vec4 uni_color;
out vec4 frag_color;
void main() {
//    frag_color = vec4(1.0, 0.0, 0.0, 1.0);
    frag_color = uni_color;
}