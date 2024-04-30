package io;

import java.io.*;

public class FileReaderTest {
    public static void main(String[] args) {
        Reader in = null;  // 문자 기반
        InputStream is = null;  // 바이트 기반
        int count = 0;
        try {

            String path = FileReaderTest.class.getResource("").getPath();
            String desiredPath = path.replace("/target/classes/io/", "/");

            in = new FileReader(desiredPath+"test.txt");
            is = new FileInputStream(desiredPath+"test.txt");

            int data;
            while((data = in.read()) != -1) {
                System.out.print((char)data);
                count++;
            }

            System.out.println("");
            System.out.println("count:" + count);
            System.out.println("==================================");

            count = 0;
            data = -1;
            while((data = is.read()) != -1) {
                System.out.println((char)data);
                count++;
            }

            System.out.println("");
            System.out.println("count:" + count);

        } catch (IOException e) {
            System.out.println("file not found");
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
                if(is != null) {
                    is.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
