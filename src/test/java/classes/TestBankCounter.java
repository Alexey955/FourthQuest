package classes;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestBankCounter {

    @Test
    public void testMinAndMax() throws IOException {

        String filePath = "src/test/java/resources/TestFileOne.txt";

        BankCounter bankCounter = new BankCounter();
        List<String> maxPeopleTimes = bankCounter.fetchTimeMaxPeople(filePath);

        Assert.assertEquals(2, maxPeopleTimes.size());
        Assert.assertEquals("10 : 00  -  11 : 00",maxPeopleTimes.get(0));
        Assert.assertEquals("19 : 20  -  20 : 00",maxPeopleTimes.get(1));
    }

    @Test
    public void testMiddle() throws IOException {

        String filePath = "src/test/java/resources/TestFileTwo.txt";

        BankCounter bankCounter = new BankCounter();
        List<String> maxPeopleTimes = bankCounter.fetchTimeMaxPeople(filePath);

        Assert.assertEquals(1, maxPeopleTimes.size());
        Assert.assertEquals("14 : 05  -  15 : 03",maxPeopleTimes.get(0));
    }

    @Test
    public void testMiddleTwo() throws IOException {

        String filePath = "src/test/java/resources/TestFileThree.txt";

        BankCounter bankCounter = new BankCounter();
        List<String> maxPeopleTimes = bankCounter.fetchTimeMaxPeople(filePath);

        Assert.assertEquals(2, maxPeopleTimes.size());
        Assert.assertEquals("14 : 05  -  15 : 03",maxPeopleTimes.get(0));
        Assert.assertEquals("18 : 02  -  18 : 55",maxPeopleTimes.get(1));
    }
}
