
attribute vec4 vPosition;
uniform vec3 theta;
uniform vec3 trans;
uniform vec3 scale;
uniform vec3 eye;
uniform vec3 lookAt;
uniform vec3 up;
uniform float view_id;

void main()
{
    vec3 angles = radians (theta);
    vec3 c = cos (angles);
    vec3 s = sin (angles);
    
    vec3 n = normalize(eye-lookAt);
   vec3 u = normalize(cross(up,n));
   vec3 v = cross(n,u);

   
   float left = -1.5;
   float right = 1.0;
   float top = 1.5;
   float bottom = -1.5;
   float near = 1.0;
   float far = 8.5;


  

 mat4 view_mat = mat4(u.x,v.x,n.x,0.0,
                      u.y, v.y, n.y,0.0,
                      u.z,v.z,n.z,0.0,
                      dot(-u,eye),  dot(-v,eye),  dot(-n,eye),  1.0);

 mat4 proj_mat;
    if(view_id==2.0)
    {
     proj_mat = mat4( 2/(right-left),0,0,0
                        ,0,2/(top-bottom),0,0,
                        0,0,-2/(far-near),0,
                        -(right+left)/(right-left),-(top+bottom)/(top-bottom),-(far+near)/(far-near),1);

    }
    if(view_id==1.0)  {
  	 proj_mat = mat4( 2*near/(right-left),0,0,0,
                          0,2*near/(top-bottom),0,0,
                          (right+left)/(right-left),(top+bottom)/(top-bottom),-(far+near)/(far-near),-1,
                          0,0,-2*far*near/(far-near),0 );
    }
    
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
          
          mat4 transMat = mat4( 1.0, 0.0, 0.0, 0.0,
			0.0, 1.0, 0.0, 0.0,
			0.0, 0.0, 1.0, 0.0,
			trans.x, trans.y, trans.z, 1.0);
            
    
    mat4 scaleMat = mat4 (scale.x,  0.0,     0.0,     0.0,
                          0.0,      scale.y, 0.0,     0.0, 
                          0.0,      0.0,     scale.z, 0.0,
                          0.0,      0.0,     0.0,     1.0);
    
    gl_Position =  proj_mat*view_mat*transMat * rz * ry * rx *scaleMat*vPosition;

}
    
   
    