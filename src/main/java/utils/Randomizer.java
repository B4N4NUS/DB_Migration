package utils;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    private static long currentIndex = 0;
    private static final Random rnd = new Random();

    public static String getUniqueID() {
        int offsetStart = 33;
        int offsetEnd = 96;

        String current = "";

        current += (char) (offsetStart + currentIndex % (offsetEnd- offsetStart));
        current += (char) (offsetStart + currentIndex / (offsetEnd- offsetStart) % (offsetEnd- offsetStart));
        current += (char) (offsetStart + currentIndex / (offsetEnd- offsetStart) / (offsetEnd- offsetStart) % (offsetEnd- offsetStart));

        currentIndex++;
        return current;
    }

    public static int getRandomInt(int start, int end){
        return rnd.nextInt(end-start) + start;
    }

    public static LocalDate getRandomDate(int startYear, int endYear) {
        long start = LocalDate.of(startYear, 1, 1).toEpochDay();
        long end = LocalDate.of(endYear,  12, 31).toEpochDay();
        return LocalDate.ofEpochDay(ThreadLocalRandom.current().nextLong(start, end));
    }

    public static String getMedal() {
        return switch (rnd.nextInt(10)) {
            case 0 -> "GOLD";
            case 1 -> "SILVER";
            case 2 -> "BRONZE";
            default -> "NONE";
        };
    }
}
