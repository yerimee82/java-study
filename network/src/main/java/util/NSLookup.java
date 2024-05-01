package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookup {
    public static void main(String[] args) {
        try {
            InetAddress[] inetAddresses = InetAddress.getAllByName("www.naver.com");
            System.out.println(inetAddresses);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }
}
