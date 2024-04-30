package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class phoneList01 {
    public static void main(String[] args) {
        try {
            String path = FileReaderTest.class.getResource("").getPath();
            String desiredPath = path.replace("/target/classes/io/", "/");
            File file = new File("phone.txt");
            if(!file.exists()){
                System.out.println("file not found");
                return;
            }
            FileInputStream fis = new FileInputStream(file);
            fis.read();
        } catch (FileNotFoundException e) {
            // 로그 남기기, 사과하기, 끝내기..
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
