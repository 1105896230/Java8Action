package future;

import java.util.concurrent.*;

/**
 * Created by Administrator on 2016/8/18 0018.
 */
public class future {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Double> submit = executorService.submit(new Callable<Double>() {
            @Override
            public Double call() throws Exception {
                Thread.sleep(3000);
                return new Double(3);
            }
        });
        try {
            System.out.println("wait");
            Double aDouble = submit.get(4, TimeUnit.SECONDS);
            System.out.println(aDouble);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }


}
