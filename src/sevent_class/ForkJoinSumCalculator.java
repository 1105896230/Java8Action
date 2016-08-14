package sevent_class;

/**
 * Created by Administrator on 2016/8/14 0014.
 */

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

import static sevent_class.ParallelStreamsHarness.FORK_JOIN_POOL;


public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    //不能再将任务分解为子任务的数组大小
    public static final long THRESHOLD = 10_000;
    //要求和的数组
    private final long[] numbers;
    //起始位置
    private final int start;
    //结束位置
    private final int end;

    //创建主任务
    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    //私有的构造函数用来创建子任务
    private ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        //计算求和大大小
        int length = end - start;
        //如果计算的大小小于阈值顺序计算
        if (length <= THRESHOLD) {
            return computeSequentially();
        }
        //在任务分成左右两个
        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();//如果第一个人子任务为操作就等待
        //合并结果
        return leftResult + rightResult;
    }

    //如果子任务不可以在分时计算结果
    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return FORK_JOIN_POOL.invoke(task);
    }
}