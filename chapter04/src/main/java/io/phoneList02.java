package io;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class phoneList02 {
    public static void main(String[] args) {
        Scanner sc = null;
        try {
            String path = FileReaderTest.class.getResource("").getPath();
            String desiredPath = path.replace("/target/classes/io/", "/");
            File file = new File(desiredPath + "phone.txt");
            if (!file.exists()) {
                System.out.println("file not found");
                return;
            }

            System.out.println("=== 파일정보 ===");
            System.out.println(file.getAbsolutePath());
            System.out.println(file.length() + "Bytes");

            Date d = new Date(file.lastModified());
            System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(d));
            System.out.println(file.lastModified());

            System.out.println("=== 전화번호 ===");
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String name = sc.next();
                String phone01 = sc.next();
                String phone02 = sc.next();
                String phone03 = sc.next();

                System.out.println(name + ":"+phone01+"-"+phone02+"-"+phone03);
            }
        } catch (FileNotFoundException e) {
            System.out.println("error:" + e);
        } finally {
            if (sc != null){
                sc.close();
            }
        }

    }
}
