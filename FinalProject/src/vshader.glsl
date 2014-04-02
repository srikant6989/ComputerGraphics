attribute vec4 vPosition;
attribute vec3 vNormal;
attribute vec2 vTexCoord;


varying vec4 color;
varying vec2 texCoord;

uniform vec4 ambientProduct;
uniform vec4 diffuseProduct;
uniform vec4 specularProduct;
uniform vec4 lightPosition;
uniform float shininess;

uniform vec3 ValueOfTheta;
uniform vec3 ValueOfTransalation;
uniform vec3 ValueOfScale;
uniform vec3 ValueOfEye;
uniform vec3 ValueOfLook;
uniform vec3 ValueOfUpVector;

void main()
{

    vec3 angles = radians(ValueOfTheta);
    vec3 c = cos (angles);
    vec3 s = sin (angles);

vec3 n = normalize(ValueOfEye-ValueOfLook);
vec3 u = normalize(cross(ValueOfUpVector,n));
vec3 v = normalize(cross(n,u));
    

mat4 view_mat = mat4( u.x, v.x, n.x, 0.0,
		     u.y, v.y, n.y, 0.0,
		     u.z, v.z, n.z, 0.0,
		     dot(-u,ValueOfEye), dot(-v,ValueOfEye), dot(-n,ValueOfEye), 1.0  );

 mat4 proj_mat = mat4(	2.0/tan(15.0), 0.0, 0.0, 0.0,
 			 0.0, 1.0/tan(15.0), 0.0, 0.0,
		  	0.0, 0.0, (10.0+0.5)/(0.5-10.0), -1.0,
		 	 0.0, 0.0, (2.0* 10.0 * 0.5)/(0.5-10.0), 0.0);
    
    mat4 rx = mat4 (1.0,  0.0,  0.0,  0.0, 
                    0.0,  c.x,  s.x,  0.0,
                    0.0, -s.x,  c.x,  0.0,
                    0.0,  0.0,  0.0,  1.0);
                    
    mat4 ry = mat4 (c.y,  0.0, -s.y,  0.0, 
                    0.0,  1.0,  0.0,  0.0,
                    s.y,  0.0,  c.y,  0.0,
                    0.0,  0.0,  0.0,  1.0);

    mat4 rz = mat4 (c.z, -s.z,  0.0,  0.0, 
                    s.z,  c.z,  0.0,  0.0,
                    0.0,  0.0,  1.0,  0.0,
                    0.0,  0.0,  0.0,  1.0);

    mat4 transMat = mat4 ( 1.0, 0.0, 0.0, 0.0,
			   0.0, 1.0, 0.0, 0.0,
			   0.0, 0.0, 1.0, 0.0,
			   ValueOfTransalation.x, ValueOfTransalation.y, ValueOfTransalation.z, 1.0);
            
    mat4 scaleMat = mat4 (ValueOfScale.x,  0.0,     0.0,     0.0,
                          0.0,      ValueOfScale.y, 0.0,     0.0, 
                          0.0,      0.0,     ValueOfScale.z, 0.0,
                          0.0,      0.0,     0.0,     1.0);

	 
 vec3 pos = (view_mat * transMat * rz * ry * rx * scaleMat * vPosition).xyz;
 vec3 L = normalize( lightPosition.xyz - pos);
 vec3 E = normalize(-pos);
 vec3 H = normalize(L+E);
          

 vec3 N= normalize(view_mat * transMat * rz * ry * rx * scaleMat * vec4(vNormal,0.0)).xyz;


vec4 ambient = ambientProduct;
float Kd = max(dot(L,N),0.0);
vec4 diffuse = Kd*diffuseProduct;
float Ks = pow(max(dot(N,H),0.0),shininess);

vec4 specular = Ks * specularProduct;
if(dot(L,N) < 0.0)
  specular = vec4(0.0,0.0,0.0,1.0);  

texCoord = vTexCoord;
gl_Position = (proj_mat * view_mat * transMat * rz * ry * rx * scaleMat * vPosition);

     color = ambient + diffuse + specular;
     color.a=1.0;


}
