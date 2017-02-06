//prozeduraler shader
varying vec2 uv;
varying vec4 color;
uniform sampler2D sampler;
void main()
{ 
gl_FragColor =texture2D(sampler, uv)*color;
}

