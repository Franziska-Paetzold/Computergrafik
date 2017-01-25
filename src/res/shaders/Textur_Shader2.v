varying vec2 uv;
varying vec4 color;
 void main ()
{
	uv =gl_MultiTexCoord0.xy;
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex; 
	color =gl_Color;
}


