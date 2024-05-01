package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Localhost {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();    // 내 컴퓨터의 정보를 알 수 있는 메소드
            // PC 이름
            String hostName = inetAddress.getHostName();
            // IP 주소
            String hostIpAddress = inetAddress.getHostAddress();

            System.out.println(hostName);
            System.out.println(hostIpAddress);

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
