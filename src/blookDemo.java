import six_class.Transaction;

import java.util.Comparator;
import java.util.Optional;

import static six_class.streamDemo.transactions;

/**
 * Created by Administrator on 2016/8/14 0014.
 */
public class blookDemo {
    public static void main(String[] args) {

    }

    private void test() {
//        Transaction mostExpensive=transactions.get(0);
//        if (mostExpensive==null){
//            throw new IllegalAccessException("Empty list of transactions");
//        }
//        for (Transaction t:transactions.subList(1,transactions.size())){
//            if (t.getValue()>mostExpensive.getValue()){
//                mostExpensive=t;
//            }
//
//     }
    }

    private void test1() {

        Optional<Transaction> mostExpensive = transactions.stream().max(Comparator.comparing(Transaction::getValue));
    }
    private void test2(){
    }
}
