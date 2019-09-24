#version 120
uniform float cam_x;
uniform float cam_y;
uniform float zoom;
uniform float fold;
uniform int iterations;

vec2 zValue = vec2(0.0, 0.0);
float pi = 3.1415926;

vec2 zFunction(vec2 z, vec2 c) {
    return vec2(z.x * z.x - z.y * z.y + c.x, 2 * z.x * z.y + c.y);
}

void main() {
    for (float n = 1; n < iterations + 1; n++) {
        zValue = zFunction(zValue, vec2(float(gl_TexCoord[0].x - 0.5) / zoom + cam_x, float(gl_TexCoord[0].y - 0.5) / zoom + cam_y));
        if (sqrt(pow(zValue.x, 2) + pow(zValue.y, 2)) <= 2) {
            gl_FragColor = vec4(0, 0, 0, 1);
        }
        else {
            gl_FragColor = vec4(sin(n/fold), cos(n/fold + pi/2), cos(n/fold), 1);
            break;
        }
    }
}
