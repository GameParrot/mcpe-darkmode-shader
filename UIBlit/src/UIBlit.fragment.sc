$input v_texcoord0

#include <bgfx_shader.sh>

SAMPLER2D(s_MatTexture, 0);
uniform vec4 TintColor;
uniform vec4 HudOpacity;

void main() {
    vec4 tex = texture2D(s_MatTexture, v_texcoord0);
    vec4 diffuse = tex * TintColor;
    diffuse.a = diffuse.a * HudOpacity.x;
	bool stayLight = false;
#if BGFX_SHADER_LANGUAGE_GLSL < 300 && BGFX_SHADER_LANGUAGE_GLSL
	vec2 size = vec2(0.0, 0.0);
#else
	vec2 size = vec2(textureSize(s_MatTexture, 0));
#endif

	if (size.x < 200.0 && size.y < 200.0 && !(size.x==15.0)) {
		if (size.x==24.0&&size.y==24.0) {
			diffuse.rgb/=32.0;
		}
		if (size.x==20.0&&size.y==22.0) {
			diffuse.rgb/=8.0;
		}

		if (diffuse.r==diffuse.g && diffuse.r==diffuse.b && diffuse.g==diffuse.b) {
			if (diffuse.r == 1.0) {
				if((size.x==4.0&&size.y==7.0) || size.x == 15.0 || size.x == 22.0 || size.x >= 32.0){
					diffuse.rgb = vec3(1.0,1.0,1.0);
					stayLight = true;
				} else {
					diffuse.rgb = vec3(0.2,0.2,0.2);
				}
			}
			if (diffuse.r > 0.498 && diffuse.r < 0.502) {
				diffuse.rgb = vec3(0.15,0.15,0.15);
			}
			if (diffuse.r > 0.28 && diffuse.r < 0.284) {
				diffuse.rgb = vec3(0.1,0.1,0.1);
			}
			if (diffuse.r > 0.296 && diffuse.r < 0.302 && size.x == 4.0 && size.y == 7.0) {
				diffuse.rgb = vec3(0.8,0.8,0.8);
			}
			if (diffuse.r > 0.873 && diffuse.r < 0.877) {
				diffuse.rgb = vec3(0.13,0.13,0.13);
			}
			if (diffuse.r > 0.896 && diffuse.r < 0.9) {
				diffuse.rgb = vec3(0.12,0.12,0.12);
			}
			if (diffuse.r > 0.457 && diffuse.r < 0.461) {
				diffuse.rgb = vec3(0.07,0.07,0.07);
			}
			if (diffuse.r > 0.418 && diffuse.r < 0.422) {
				diffuse.rgb = vec3(0.01,0.01,0.01);
			}
			if (diffuse.r > 0.249 && diffuse.r < 0.253) {
				diffuse.rgb = vec3(0.1,0.1,0.1);
			}
			if (diffuse.r > 0.57 && diffuse.r < 0.59) {
				diffuse.rgb = vec3(0.2,0.2,0.2);
			}
			if (diffuse.r > 0.54 && diffuse.r < 0.55) {
				diffuse.rgb = vec3(0.1,0.1,0.1);
			}
			if (diffuse.r > 0.76 && diffuse.r < 0.78) {
				diffuse.rgb = vec3(0.05,0.05,0.05);
			}
			if (diffuse.r > 0.68 && diffuse.r < 0.684) {
				diffuse.rgb = vec3(0.15,0.15,0.15);
			}
			if (diffuse.r > 0.225 && diffuse.r < 0.23) {
				diffuse.rgb = vec3(0.02,0.02,0.02);
			}
			if (diffuse.r > 0.95 && !stayLight && !(size.x==10.0&&size.y==10.0)) {
				diffuse.rgb = vec3(0.0,0.0,0.0);
			}
		}
		if (diffuse.r > 0.19 && diffuse.r < 0.195 && diffuse.g > 0.195 && diffuse.g < 0.2 && diffuse.b > 0.198 && diffuse.b < 0.202) {
			diffuse.rgb = vec3(0.02,0.02,0.02);
		}
		if (diffuse.r > 0.782 && diffuse.r < 0.786 && diffuse.g > 0.786 && diffuse.g < 0.79 && diffuse.b > 0.794 && diffuse.b < 0.798) {
			diffuse.rgb = vec3(0.1,0.1,0.1);
		}
		if (diffuse.r > 0.343 && diffuse.r < 0.347 && diffuse.g > 0.335 && diffuse.g < 0.339 && diffuse.b > 0.347 && diffuse.b < 0.35) {
			diffuse.rgb = vec3(0.05,0.05,0.05);
		}
		if (diffuse.r > 0.245 && diffuse.r < 0.249 && diffuse.g > 0.235 && diffuse.g < 0.237 && diffuse.b > 0.245 && diffuse.b < 0.249) {
			diffuse.rgb = vec3(0.05,0.05,0.05);
		}
		if (diffuse.r > 0.096 && diffuse.r < 0.1 && diffuse.g > 0.123 && diffuse.g < 0.127 && diffuse.b > 0.131 && diffuse.b < 0.135) {
			diffuse.rgb = vec3(1.0,1.0,1.0);
		}
		if (diffuse.r > 0.676 && diffuse.r < 0.68 && diffuse.g > 0.673 && diffuse.g < 0.677 && diffuse.b > 0.676 && diffuse.b < 0.68) {
			diffuse.rgb = vec3(0.11,0.11,0.11);
		}
		if (diffuse.r > 0.312 && diffuse.r < 0.316 && diffuse.g > 0.304 && diffuse.g < 0.308 && diffuse.b > 0.312 && diffuse.b < 0.316) {
			diffuse.rgb = vec3(0.105,0.105,0.105);
		}
		if (diffuse.r > 0.273 && diffuse.r < 0.277 && diffuse.g > 0.269 && diffuse.g < 0.273 && diffuse.b > 0.273 && diffuse.b < 0.277) {
			diffuse.rgb = vec3(0.8,0.8,0.8);
		}
		if (diffuse.r==diffuse.b&&diffuse.r>0.288&&diffuse.r<0.292&&diffuse.g>0.28&&diffuse.g<0.284){
			diffuse.rgb = vec3(0.11,0.11,0.11);
		}
		if (diffuse.r==diffuse.b&&diffuse.r>0.34&&diffuse.r<0.4&&diffuse.g>0.34&&diffuse.g<0.4){
			diffuse.rgb = vec3(0.1,0.1,0.1);
		}
		if (diffuse.r==diffuse.b&&diffuse.r>0.745&&diffuse.r<0.755&&diffuse.g>0.745&&diffuse.g<0.755){
			diffuse.rgb = vec3(0.03,0.03,0.03);
		}
	}
	if (size.x==1937.0&&size.y==333.0){
		diffuse.rgb/=4.0;
	}
    gl_FragColor = diffuse;
}
