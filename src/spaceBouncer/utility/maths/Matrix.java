package spaceBouncer.utility.maths;

import spaceBouncer.utility.buffers.Buffer;

import java.nio.FloatBuffer;

public class Matrix {

    public static final int SIZE = 16;
    public float[] elements = new float[SIZE];

    public Matrix(){}

    public static Matrix identity(){
        Matrix result = new Matrix();

        for(int i = 0; i < SIZE; i++){
            result.elements[i] = 0.0f;
        }

        result.elements[0] = 1.0f;
        result.elements[5] = 1.0f;
        result.elements[10] = 1.0f;
        result.elements[15] = 1.0f;

        return result;
    }

    public static Matrix orthographic(float left, float right, float bottom, float top, float near, float far){
        Matrix result = identity();

        result.elements[0] = 2.0f / (right - left);
        result.elements[5] = 2.0f / (top - bottom);
        result.elements[10] = 2.0f / (near - far);
        result.elements[12] = (left + right) / (left - right);
        result.elements[13] = (bottom + top) / (bottom - top);
        result.elements[14] = (far + near) / (far - near);

        return result;
    }

    public static Matrix translate(Vector vector){
        Matrix result = identity();

        result.elements[12] = vector.x;
        result.elements[13] = vector.y;
        result.elements[14] = vector.z;

        return result;
    }

    public static Matrix rotate(float angle){
        Matrix results = identity();

        float r = (float) Math.toRadians(angle);
        float cos = (float) Math.cos(r);
        float sin = (float) Math.sin(r);

        results.elements[0] = cos;
        results.elements[1] = sin;
        results.elements[4] = -sin;
        results.elements[5] = cos;

        return results;
    }

    public Matrix multiply(Matrix matrix){
        Matrix results = new Matrix();

        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 4; y++){
                float sum = 0.0f;
                for(int z = 0; z < 4; z++){
                    sum += this.elements[y + z * 4] * matrix.elements[z + x * 4];
                }
                results.elements[y + x * 4] = sum;
            }
        }
        return results;
    }

    public FloatBuffer toFloatBuffer(){
        return Buffer.toFloatBuffer(elements);
    }

}
