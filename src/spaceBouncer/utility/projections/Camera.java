package spaceBouncer.utility.projections;

import spaceBouncer.utility.maths.Matrix;

public class Camera {

    private Camera() {}

    public static Matrix orthographicProjection(float left, float right, float bottom, float top, float near, float far){
        Matrix result = Matrix.identity();

        result.elements[0] = 2.0f / (right - left);
        result.elements[5] = 2.0f / (top - bottom);
        result.elements[10] = 2.0f / (near - far);
        result.elements[12] = (left + right) / (left - right);
        result.elements[13] = (bottom + top) / (bottom - top);
        result.elements[14] = (far + near) / (far - near);

        return result;
    }

}
