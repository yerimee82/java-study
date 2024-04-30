package io;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class phoneList01 {
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            String path = FileReaderTest.class.getResource("").getPath();
            String desiredPath = path.replace("/target/classes/io/", "/");
            File file = new File(desiredPath + "phone.txt");
            if(!file.exists()){
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

            // 1. 기반 스트림
            FileInputStream fis = new FileInputStream(file);

            // 2. 보조 스트림(byte|byte|byte => char)
            InputStreamReader isr = new InputStreamReader(fis, "utf-8");

            // 3. 보조 스트림(byte|byte|byte => "charcharchar")
            br = new BufferedReader(isr);

            //4. 처리
            String line = null;
            while((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line, "\t ");

                int index = 0;
                while(st.hasMoreElements()) {
                    String token = st.nextToken();

                    if(index == 0) { // 이름
                        System.out.print(token + ":");
                    } else if(index == 1) { // 전화번호1
                        System.out.print(token + "-");
                    } else if(index == 2) { // 전화번호2
                        System.out.print(token + "-");
                    } else { // 전화번호3
                        System.out.print(token + "\n");
                    }

                    index++;
                }

            }

        } catch (UnsupportedEncodingException e) {
            // 로그 남기기, 사과하기, 끝내기..
            System.out.println("error:" + e);
        } catch (IOException e) {
            System.out.println("error:" + e);
        } finally {
            try {
                if(br != null) {
                    br.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
