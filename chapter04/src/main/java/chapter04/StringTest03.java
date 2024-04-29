package chapter04;

public class StringTest03 {
    public static void main(String[] args) {
//        String s1 = "Hello" + "World" + "java" + 17
        // StringBuffer 와 StringBuilder 의 차이 : 동기화 여부
        String s1 = new StringBuffer("Hello").append("World").append("java").append(17).toString();
        String s2 = new StringBuilder("Hello").append("World").append("java").append(17).toString();

//        String s3 = "";
//        for (int i = 0; i < 1000000; i++) {
//            // s3 += "h";   계속 생성됨.
//            s3 = new StringBuffer(s3).append("h").toString();
//        }

        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < 1000000; i++) {
            // s3 += "h";   계속 생성됨.
            sb.append("h");
        }
        String s4 = sb.toString();

        // String method 들..
        String s5 = "aBcAVCabcAbc";
        System.out.println(s5.length());
        System.out.println(s5.charAt(2));
        System.out.println(s5.indexOf("abc"));
        System.out.println(s5.indexOf("abc", 7));
        System.out.println(s5.substring(3));
        System.out.println(s5.substring(3, 5));


        String s6 = "        ab          cd";
        String s7 = "efg,hij,klm,nop,qrs";

        String s8 = s6.concat(s7);
        System.out.println(s8);
        System.out.println(s6.trim());   // 가운데에 있는 공백을 없애진 않음. 맨처음 공백만 없앰.
        System.out.println(s6.replaceAll(" ", ""));    // 공백을 모두 대체

        String[] tokens = s8.split(",");
        for (String s:
             tokens) {
            System.out.println(s);
        }

        String[] tokens2 = s7.split(" ");
        for (String s :
                tokens2) {
            System.out.println(s);
        }
    }
}
