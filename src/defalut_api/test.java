package defalut_api;

/**
 * Created by Administrator on 2016/8/14 0014.
 */
public class test {
    interface A {
        default void hello() {
            System.out.println("Hello from A");
        }
    }

    interface B {
        default void hello() {
            System.out.println("Hello from B");
        }
    }

    public class C implements A, B {
        @Override
        public void hello() {
            B.super.hello();
        }
    }
}
