varying vec4 color;
varying vec2 texCoord;
uniform sampler2D texture;
uniform vec3 ValueOfTransalation;
void main()
{    

	 if(ValueOfTransalation.x == 0)
	 {
	 gl_FragColor = texture2D(texture, texCoord);
	 }
	 else
	 {
     gl_FragColor = color ;	 	 
	 }
	 
}
