package edu.pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static final String HARRY_POTTER_TEXT_PATH_STRING = "src/edu/pro/txt/harry.txt";
    public static final int TOP_WORDS_TO_SHOW = 30;

    public static void main(String[] args) throws IOException {
        LocalDateTime startAppTime = LocalDateTime.now();
        Files.readAllLines(Paths.get(HARRY_POTTER_TEXT_PATH_STRING), StandardCharsets.ISO_8859_1)
                .stream()
                .parallel()
                .flatMap(textLine -> Arrays.stream(textLine.split("[^A-Za-z ]")))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .limit(TOP_WORDS_TO_SHOW)
                .forEach(word -> System.out.println(word.getKey() + " " + word.getValue()));
            LocalDateTime endAppTime = LocalDateTime.now();
            System.out.println("App execution duration: " + ChronoUnit.MILLIS.between(startAppTime, endAppTime) + " milliseconds");
        }
}
