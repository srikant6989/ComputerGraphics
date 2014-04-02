// Light color
uniform vec4 lightColor;

// Diffuse reflection color
uniform vec4 diffuseColor;
uniform vec4 SpecularColor;
uniform float spect;

// Vectors "attached" to vertex and get sent to fragment shader
varying vec3 lPos;
varying vec3 vPos;
varying vec3 vNorm;


void main()
{        
    // calculate your vectors
    vec3 L = normalize (lPos - vPos);
    vec3 N = normalize (vNorm);

    vec3 reflection = normalize(reflect(-L,N));
    vec3 v = normalize(-vPos);
    
    
     // calculate components

        float temp;
    temp = dot(v, reflection);
    if ( temp < 0.0)
    	temp = 0.0;

    vec4 diffuse = lightColor * diffuseColor * (dot(N, L));
    vec4 specular = lightColor * SpecularColor * pow(temp, spect);

    // set the final color
    gl_FragColor = diffuse + specular;

}
