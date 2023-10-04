$input v_texcoord0, v_color0

#include <bgfx_shader.sh>

SAMPLER2D(s_GlyphTexture, 0);
uniform vec4 TintColor;
uniform vec4 HudOpacity;
uniform vec4 OutlineCutoff;
uniform vec4 GlyphCutoff;
uniform vec4 GlyphSmoothRadius;
uniform vec4 ShadowSmoothRadius;
uniform vec4 ShadowColor;
uniform vec4 OutlineColor;
uniform vec4 ShadowOffset;
float median(float a, float b, float c) {
    return max(min(a, b), min(max(a, b), c));
}
void main() {
    vec4 diffuse;
    vec4 glyphColor = texture2D(s_GlyphTexture, v_texcoord0);
#if SMOOTH
    const float center = 0.4; //tweaked to fix high res packs 0.5
    const float radius = 0.1; //0.0
    glyphColor.a = smoothstep(center - radius, center + radius, glyphColor.a);
#endif
#if ALPHA_TEST
    if(glyphColor.a < 0.5) {
        discard;
    }
#endif
#if MSDF
    vec4 resultColor = v_color0;
    vec2 uv = v_texcoord0;
    float sampleDistance = median(glyphColor.r, glyphColor.g, glyphColor.b);

    float innerEdgeAlpha = smoothstep(max(0.0, GlyphCutoff.x - GlyphSmoothRadius.x), min(1.0, GlyphCutoff.x + GlyphSmoothRadius.x), sampleDistance);
    resultColor = mix(OutlineColor, resultColor, innerEdgeAlpha);
    float outerEdgeAlpha = smoothstep(max(0.0, OutlineCutoff.x - GlyphSmoothRadius.x), min(1.0, OutlineCutoff.x + GlyphSmoothRadius.x), sampleDistance);
    resultColor = vec4(resultColor.rgb, resultColor.a * outerEdgeAlpha);
    const float GlyphUvSize = 1.0 / 16.0;

    vec2 topLeft = floor(uv / GlyphUvSize) * GlyphUvSize;
    vec2 bottomRight = topLeft + vec2(GlyphUvSize, GlyphUvSize);

    vec4 shadowSample = texture2D(s_GlyphTexture, clamp(uv - ShadowOffset.xy, topLeft, bottomRight));
    float shadowAlpha = smoothstep(max(0.0, OutlineCutoff.x - ShadowSmoothRadius.x), min(1.0, OutlineCutoff.x + ShadowSmoothRadius.x), shadowSample.a);

    diffuse = mix(vec4(ShadowColor.rgb, ShadowColor.a * shadowAlpha), resultColor, outerEdgeAlpha) * TintColor;
    diffuse.a = diffuse.a * HudOpacity.x;
#else
    diffuse = v_color0 * glyphColor * TintColor;
    diffuse.a = diffuse.a * HudOpacity.x;
    if(diffuse.r == 1.0 && diffuse.g == 1.0 && diffuse.b == 0.0) {
        diffuse.rgb=vec3(0.25, 0.25, 0.25);
    }
    if(diffuse.r > 0.249 && diffuse.r < 0.253 && diffuse.r==diffuse.g && diffuse.b == 0.0) {
        diffuse.rgb=vec3(0.06, 0.06, 0.06);
    }
#endif
    if (diffuse.r==diffuse.g && diffuse.r==diffuse.b && diffuse.g==diffuse.b) {
        if(diffuse.r>0.296 && diffuse.r<0.3) {
            diffuse.rgb=vec3(0.9, 0.9, 0.9);
        }
        if(diffuse.r>0.12 && diffuse.r<0.124) {
            diffuse.rgb=vec3(1.0, 1.0, 1.0);
        }
    }

    gl_FragColor = diffuse;
}
