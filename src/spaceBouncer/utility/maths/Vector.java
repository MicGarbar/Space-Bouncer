package spaceBouncer.utility.maths;

public class Vector {

    public float x;
    public float y;
    public float z;

    public Vector(){
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public Vector(float x, float y){
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    public Vector(float x, float y, float z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
