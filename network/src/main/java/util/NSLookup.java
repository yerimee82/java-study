package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("도메인을 입력하세요 : ");
            String domain = scanner.nextLine();

            if (domain.equalsIgnoreCase("exit")) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            try {
                // getAllByName : 주어진 호스트의 모든 IP 주소를 가져옴. 배열에 담아서 반환
                InetAddress[] addresses = InetAddress.getAllByName(domain);
                System.out.println("도메인 " + domain + "의 IP 주소:");

                for (InetAddress address : addresses) {
                    System.out.println(address.getHostAddress());
                }
            } catch (UnknownHostException e) {
                System.out.println("도메인을 찾을 수 없습니다.");
            }
        }
        scanner.close();
    }
}

