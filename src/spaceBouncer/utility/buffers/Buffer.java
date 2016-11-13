package spaceBouncer.utility.buffers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Buffer {

    private Buffer() {}

    public static FloatBuffer toFloatBuffer(float[] data){
        FloatBuffer buffer = ByteBuffer.allocateDirect(data.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        buffer.put(data).flip();
        return buffer;
    }

    public static IntBuffer toIntBuffer(int[] data){
        IntBuffer buffer = ByteBuffer.allocateDirect(data.length << 2).order(ByteOrder.nativeOrder()).asIntBuffer();
        buffer.put(data).flip();
        return buffer;
    }

    public static ByteBuffer toByteBuffer(byte[] data){
        ByteBuffer buffer = ByteBuffer.allocateDirect(data.length).order(ByteOrder.nativeOrder());
        buffer.put(data).flip();
        return buffer;
    }

}
