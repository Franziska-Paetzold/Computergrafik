//prozeduraler shader
varying vec2 uv;
varying vec4 color;
uniform sampler2D sampler;
void main()
{ 
/*meinShader(uv);*/
gl_FragColor =texture2D(sampler, uv)*color;
}

/*
vec4 meinShader(vec2 uv){
uv++;
return uv;
}
*/