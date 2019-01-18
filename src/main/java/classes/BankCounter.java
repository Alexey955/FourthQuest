package classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BankCounter {

    public List<String> fetchTimeMaxPeople(String filePath) throws IOException {

        List<List> minutesFromFile = fetchMinutesFromFile(filePath);

        List<Integer> minutesWithMaxPeople = fetchMinutesWithMaxPeople(minutesFromFile);

        List<List> intervalsMaxPeople = fetchIntervalsTimeMaxPeople(minutesWithMaxPeople);

        return fetchMaxTimeResult(intervalsMaxPeople);
    }

    private List<List> fetchMinutesFromFile(String filePath) throws IOException {

        Function<String, List<Integer>> minutesFromTimeFunc = x -> {
            String[] timeStr = x.split(":");
            int inHours = Integer.parseInt(timeStr[0]);
            int outMinutes = Integer.parseInt(timeStr[2]);

            timeStr = timeStr[1].split("\t");

            int inMinutes = Integer.parseInt(timeStr[0]);
            int outHours = Integer.parseInt(timeStr[1]);

            return new LinkedList<>(Arrays.asList(inHours*60 + inMinutes, outHours*60 + outMinutes));
        };

        List<List> minutesFromFile =  Files.lines(Paths.get(filePath))
                .map(minutesFromTimeFunc)
                .collect(Collectors.toList());

        return minutesFromFile;
    }

    private List<Integer> fetchMinutesWithMaxPeople(List<List> minutesFromFile) {

        List<List> minutesWithAmountMaxPeople = new LinkedList<>();
        List<Integer> subList;

        for(int i = 480; i < 1200; i++) {

            int sum = 0;
            for(List klientTime: minutesFromFile) {
                if(i >= (int)klientTime.get(0) && i <= (int)klientTime.get(1)) {
                    sum += 1;
                }
            }
            subList = new LinkedList<>();
            subList.add(i);
            subList.add(sum);

            minutesWithAmountMaxPeople.add(subList);
        }
        OptionalInt max = minutesWithAmountMaxPeople.stream().map(x -> x.get(1)).mapToInt(y -> (int) y).max();

        minutesWithAmountMaxPeople = minutesWithAmountMaxPeople.stream().filter(x -> (int)x.get(1) == max.getAsInt()).collect(Collectors.toList());

        return minutesWithAmountMaxPeople.stream().map(x -> (int) x.get(0)).collect(Collectors.toList());
    }

    private List<List> fetchIntervalsTimeMaxPeople(List<Integer> minutesWithMaxPeople) {

        List<List> intervalsMaxPeople = new LinkedList<>();
        List<Integer> subList = new LinkedList<>();;

        List<Integer> sortedMinutes = minutesWithMaxPeople.stream().sorted().collect(Collectors.toList());

        int sum = minutesWithMaxPeople.get(0);
        subList.add(sum);
        for(int i = 1; i < sortedMinutes.size(); i++) {

            if(sortedMinutes.get(i) - sortedMinutes.get(i - 1) == 1) {
                sum += 1;
            }else {
                subList.add(sum);
                intervalsMaxPeople.add(subList);
                subList = new LinkedList<>();
                sum = minutesWithMaxPeople.get(i/* + 1*/);
                subList.add(sum);
            }
            if(i == sortedMinutes.size() - 1) {
                if(sum == 1199) {
                    subList.add(sum + 1);
                }else {
                    subList.add(sum);
                }

                intervalsMaxPeople.add(subList);
            }
        }
        return intervalsMaxPeople;
    }

    private List<String> fetchMaxTimeResult(List<List> intervalsMaxPeople) {

        List<String> maxTimeResult = new LinkedList<>();

        for(List interval: intervalsMaxPeople) {
            int minutesFrom = (int) interval.get(0);
            int hoursFrom = minutesFrom/60;
            minutesFrom = (int) interval.get(0) - hoursFrom * 60;

            String timeFrom;
            if(minutesFrom < 10) {
                timeFrom = hoursFrom + " : 0" + minutesFrom;
            }else {
                timeFrom = hoursFrom + " : " + minutesFrom;
            }

            int minutesTo = (int) interval.get(1);
            int hoursTo = minutesTo/60;
            minutesTo = (int) interval.get(1) - hoursTo * 60;

            String timeTo;
            if(minutesTo < 10) {
                timeTo = hoursTo + " : 0" + minutesTo;
            }else {
                timeTo = hoursTo + " : " + minutesTo;
            }
            maxTimeResult.add(timeFrom + "  -  " + timeTo);
        }
        return maxTimeResult;
    }
}
