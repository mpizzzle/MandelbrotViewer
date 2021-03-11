#version 120
uniform float cam_x;
uniform float cam_y;
uniform float zoom;
uniform float fold;
uniform int iterations;

vec2 zValue = vec2(0.0, 0.0);
float pi = 3.1415926;
float mu = 0.0;

vec2 zFunction(vec2 z, vec2 c) {
    return vec2(z.x * z.x - z.y * z.y + c.x, 2 * z.x * z.y + c.y);
}

void main() {
    for (float n = 1; n < iterations + 1; n++) {
        zValue = zFunction(zValue, vec2(gl_TexCoord[0].x / zoom + cam_x, gl_TexCoord[0].y / zoom + cam_y));
        mu = n + 1 - (log(log(length(zValue))) / log(2));

        if (length(zValue) <= 20000) {
            gl_FragColor = vec4(0, 0, 0, 1);
        }
        else {
            gl_FragColor = vec4(sin(mu / (3 * fold)), sin(mu / (5 * fold)), sin(mu / (7 * fold)), 1);
            break;
        }
    }
}
