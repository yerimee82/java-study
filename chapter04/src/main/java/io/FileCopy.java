package io;

import java.io.*;

public class FileCopy {
    public static void main(String[] args) {
        InputStream is = null;
        OutputStream os = null;
        try {
            String path = FileReaderTest.class.getResource("").getPath();
            String desiredPath = path.replace("/target/classes/io/", "/");

            is = new FileInputStream(desiredPath + "poo.jpg");
            os = new FileOutputStream(desiredPath + "poo.copy.jpg");

            int data = -1;
            while((data = is.read()) != -1) {
                os.write(data);

            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found:" + e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(is != null) {
                    is.close();
                }
                if(is != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
