package top.oneyi.thirdpart.codec;

import com.alipay.remoting.serialization.SerializerManager;
import top.oneyi.thirdpart.checksum.ByteCheckSum;


public class BodyCodec implements IBodyCodec {

    @Override
    public <T> byte[] serialize(T obj) throws Exception {
        //1. jdk 序列化 //2. json //3.自定义算法（Hessian2）
        return SerializerManager.getSerializer(SerializerManager.Hessian2).serialize(obj);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) throws Exception {
        return SerializerManager.getSerializer(SerializerManager.Hessian2).deserialize(bytes,clazz.getName());
    }


    public static void main(String[] args) throws Exception {
        String a = "李四";
        BodyCodec bodyCodec = new BodyCodec();
        byte[] serialize = bodyCodec.serialize(a);
        String deserialize = bodyCodec.deserialize(serialize, String.class);
        System.out.println("deserialize = " + deserialize);


    }

}
