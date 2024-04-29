package prob01;

public class PrinterTest {

	public static void main(String[] args) {
		Printer printer = new Printer();

		printer.println2( 10 );
		printer.println2( true );
		printer.println2( 5.7 );
		printer.println2( "홍길동" );

		printer.println2(10, "홍길동");
		printer.println2(10, true, "홍길동");


		System.out.println(printer.sum(1));
		System.out.println(printer.sum(1,2,3));
	}
}