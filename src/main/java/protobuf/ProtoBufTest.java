package protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufTest {


    public static void main(String[] args) throws InvalidProtocolBufferException {

        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("zhangsan")
                .setAge(20)
                .setAddress("Beijing")
                .build();

        byte[] student2ByteArray = student.toByteArray();

        DataInfo.Student student2 = DataInfo.Student.parseFrom(student2ByteArray);

        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student.getAddress());

    }
}
