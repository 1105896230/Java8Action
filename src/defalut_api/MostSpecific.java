package defalut_api;

/**
 * Created by Administrator on 2016/8/14 0014.
 */
public class MostSpecific {
    public static void main(String... args) {
        new E().hello();
//        new E().hello();
//        new G().hello();
    }
    static interface A{
        public default void hello() {
            System.out.println("Hello from A");
        }
    }

    static interface B extends A{
        public default void hello() {
            System.out.println("Hello from B");
        }
    }

    static class C implements B, A {}

    static class D implements A{}

    static class E extends D implements B, A{}

    static class F implements B, A {
        public void hello() {
            System.out.println("Hello from F");
        }
    }

    static class G extends F implements B, A{}
}
