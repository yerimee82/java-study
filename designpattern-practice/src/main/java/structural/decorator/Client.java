package structural.decorator;

public class Client {
    public static void main(String[] args) {
        System.out.println(new ConcretComponent("Hello World").operation());
        System.out.println(new BracesDecorator(new ConcretComponent("Hello World")).operation());
        System.out.println(new ParenthesesDecorator(new BracesDecorator(new ConcretComponent("Hello World"))).operation());

        System.out.println(new ParenthesesDecorator(new ConcretComponent("Hello World")).operation());
        System.out.println(new BracesDecorator(new ParenthesesDecorator(new ConcretComponent("Hello World"))).operation());
    }
}
