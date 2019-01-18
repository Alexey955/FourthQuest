import classes.BankCounter;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BankCounter bankCounter = new BankCounter();
        List<String> result = bankCounter.fetchTimeMaxPeople(args[0]);

        result.forEach(System.out::println);
    }
}
