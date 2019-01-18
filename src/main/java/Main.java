import classes.BankCounter;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = "src/main/resources/FileInput.txt";

        BankCounter bankCounter = new BankCounter();
        List<String> result = bankCounter.fetchTimeMaxPeople(filePath);

        result.forEach(System.out::println);
    }
}
