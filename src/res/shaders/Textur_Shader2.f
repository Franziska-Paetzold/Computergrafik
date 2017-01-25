//Fragment Shader 

varying vec2 uv;
varying vec4 color;
uniform sampler2D sampler;
void main()
{
/*meinShader(uv) */
gl_FragColor =texture2D(sampler, uv)*color;


}
/*
veck meinShader(vec2 uv)
{
 return...
}*/

