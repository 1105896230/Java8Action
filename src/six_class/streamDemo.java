package six_class;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by Administrator on 2016/8/9 0009.
 */
public class streamDemo {
    public static List<Transaction> transactions = Arrays.asList( new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0) );
    public static void main(String[] args){
        groupImperatively();
        groupFunctionally();
    }

    /**
     * 传统指令式子的写法，将货币分组
     */
    private static void groupImperatively() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();
        for (Transaction transaction : transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }

        System.out.println(transactionsByCurrencies);
    }

    /**
     * 收集器的好处
     */
    private static void groupFunctionally() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
                .collect(groupingBy(Transaction::getCurrency));
        System.out.println(transactionsByCurrencies);
    }
}
