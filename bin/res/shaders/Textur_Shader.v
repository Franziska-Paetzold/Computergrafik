/*varying vec2 uv;
varying vec4 color;
 void main ()
{
	uv =gl_MultiTexCoord0.xy;
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex; 
	color =gl_Color;
}

varying vec3 N;
varying vec3 v;

void main(void)
{

   v = vec3(gl_ModelViewMatrix * gl_Vertex);       
   N = normalize(gl_NormalMatrix * gl_Normal);
   gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;
}*/


varying vec3 N;
varying vec3 v;
void main(void)  
{     
   v = vec3(gl_ModelViewMatrix * gl_Vertex);       
   N = normalize(gl_NormalMatrix * gl_Normal);
   gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex;  
}