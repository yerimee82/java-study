package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Localhost {
    public static void main(String[] args) {
        try {
            // InetAddress : 호스트 이름과 호스트 주소(IP)를 반환하는 메서드
            // 로컬호스트의 InetAddress 객체를 가져옴.
            InetAddress inetAddress = InetAddress.getLocalHost();    // 내 컴퓨터의 정보를 알 수 있는 메소드

            // PC 이름
            String hostName = inetAddress.getHostName();
            // IP 주소
            String hostIpAddress = inetAddress.getHostAddress();

            System.out.println(hostName);
            System.out.println(hostIpAddress);

            // IP 주소를 바이트 배열로 가져옴.
            byte[] IpAddresses = inetAddress.getAddress();
            for (byte IpAddress:
                 IpAddresses) {
                System.out.println(IpAddress);   // 한 바이트씩 출력
            }

        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
