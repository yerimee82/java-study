package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedOutputStreamTest {
    public static void main(String[] args) {
        BufferedOutputStream bos = null;
        try {
            // 경로 획득
            String path = FileReaderTest.class.getResource("").getPath();
            String desiredPath = path.replace("/target/classes/io/", "/");
            // 기반 스트림
            FileOutputStream fos = new FileOutputStream(desiredPath +"hello.txt");

            // 보조 스트림
            bos = new BufferedOutputStream(fos);

            // for (int i = 'a'; i < 'z'; i++) {
            for (int i = 'a'; i < 'z'; i++) {
                bos.write(i);
            }

        } catch (FileNotFoundException e) {
            System.out.println("file not found:" + e);
        } catch (IOException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if(bos != null){
                    bos.close();   // close 에 flush 가 들어 있음 -> 자동 flush
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
