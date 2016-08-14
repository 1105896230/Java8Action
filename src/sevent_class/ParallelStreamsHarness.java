package sevent_class;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

/**
 * Created by Administrator on 2016/8/14 0014.
 */
public class ParallelStreamsHarness {
    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();
    //    一个方法运行的时间
    public static <T, R> long measurePerf(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    public static void main(String[] args) {
        System.out.println("Iterative Sum done in: " + measurePerf(ParallelStreams::iterativeSum, 10_000_000L) + " msecs");
        System.out.println("Sequential Sum done in: " + measurePerf(ParallelStreams::sequentialSum, 10_000_000L) + " msecs");
        System.out.println("Sequential Sum done in: " + measurePerf(ParallelStreams::parallelSum, 10_000_000L) + " msecs");
        System.out.println("Iterative Sum done in: " + measurePerf(ParallelStreams::rangedSum, 10_000_000L) + " msecs");
        System.out.println("Iterative Sum done in: " + measurePerf(ParallelStreams::parallelRangedSum, 10_000_000L) + " msecs");
    }
}
