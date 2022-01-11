package com.adventofcode.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class AppApplicationTests {

    @Test
    void day1() {
        List<Integer> testData = TestData.getData();

        int counter = 0;
        for (int i = 1; i < testData.size(); i++) {
            int num1 = testData.get(i - 1);
            int num2 = testData.get(i);
            if (num2 > num1) {
                counter++;
            }
        }

        System.out.println("Result: " + counter);
    }

    @Test
    void day1part2() {
        List<Integer> testData = TestData.getData();

        int counter = 0;
        for (int i = 3; i < testData.size(); i++) {
            int num1 = testData.get(i - 3) + testData.get(i - 2) + testData.get(i - 1);
            int num2 = testData.get(i - 2) + testData.get(i - 1) + testData.get(i);
            if (num2 > num1) {
                counter++;
            }
        }

        System.out.println("Result: " + counter);
    }

    @Test
    void day2() {
        List<String> data = TestData.getData2();
        int horizontalPos = 0;
        int depth = 0;

        for (int i = 0; i < data.size(); i++) {
            String[] elements = data.get(i).split(" ");
            switch (elements[0]) {
                case "forward":
                    horizontalPos += Integer.parseInt(elements[1]);
                    break;
                case "down":
                    depth += Integer.parseInt(elements[1]);
                    break;
                case "up":
                    depth -= Integer.parseInt(elements[1]);
                    break;
                default:
                    throw new IllegalArgumentException("wrong data");
            }
        }

        System.out.println("Forward: " + horizontalPos);
        System.out.println("depth: " + depth);
        System.out.println("result: " + (horizontalPos * depth));
    }

    @Test
    void day2part2() {
        List<String> data = TestData.getData2();
        int horizontalPos = 0;
        int depth = 0;
        int aim = 0;

        for (int i = 0; i < data.size(); i++) {
            String[] elements = data.get(i).split(" ");
            switch (elements[0]) {
                case "forward":
                    horizontalPos += Integer.parseInt(elements[1]);
                    depth += aim * Integer.parseInt(elements[1]);
                    break;
                case "down":
                    aim += Integer.parseInt(elements[1]);
                    break;
                case "up":
                    aim -= Integer.parseInt(elements[1]);
                    break;
                default:
                    throw new IllegalArgumentException("wrong data");
            }
        }

        System.out.println("Forward: " + horizontalPos);
        System.out.println("depth: " + depth);
        System.out.println("result: " + (horizontalPos * depth));
    }

    @Test
    void day3part1() {
        List<String> data = TestData.getData3();
        int gamma = 0;
        int epsilon = 0;

        //        0  1  2 3 4 5 6 7 8
        //        15 3 35

        Function<Integer, Integer> gammaFunction = el -> el > (data.size() / 2) ? 1 : 0;
        Function<Integer, Integer> epsilonFunction = el -> el > (data.size() / 2) ? 0 : 1;

        Map<Integer, Integer> map = new HashMap<>();

        for (int line = 0; line < data.size(); line++) {
            String entry = data.get(line);
            String[] elements = entry.split("");
            for (int column = 0; column < elements.length; column++) {
                Integer element = Integer.parseInt(elements[column]);
                map.compute(column, (k, v) -> v == null ? element : v + element);
            }
        }
        String gammaDigit = getBinaryDigit(map, gammaFunction);
        String epsilonDigit = getBinaryDigit(map, epsilonFunction);

        gamma = Integer.parseInt(gammaDigit, 2);
        epsilon = Integer.parseInt(epsilonDigit, 2);

        int result = gamma * epsilon;
        System.out.println("result: " + result);
    }

    private String getBinaryDigit(Map<Integer, Integer> map, Function<Integer, Integer> func) {

        return map.values().stream()
                .map(func)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    @Test
    void day3part2() {
        List<String> exampleData = Arrays.asList(
                "00100",
                "11110",
                "10110",
                "10111",
                "10101",
                "01111",
                "00111",
                "11100",
                "10000",
                "11001",
                "00010",
                "01010"
        );

        List<String> data = TestData.getData3();

        List<String> oxygenList = new ArrayList<>(data);
        List<String> co2List = new ArrayList<>(data);
        for (int index = 0; index < data.get(0).length(); index++) {
            oxygenList = recursive(index, false, oxygenList);
            co2List = recursive(index, true, co2List);
        }
        System.out.println(oxygenList);
        System.out.println(co2List);
        int oxygen = Integer.parseInt(oxygenList.get(0), 2);
        int co2 = Integer.parseInt(co2List.get(0), 2);
        System.out.println("oxygen: " + oxygen);
        System.out.println("co2: " + co2);
        System.out.println("result: " + oxygen * co2);
    }

    List<String> recursive(int index, boolean lookForMin, List<String> numbers) {
        if (numbers.size() == 1) {
            return numbers;
        }
        Comparator<Map.Entry<Character, List<String>>> initialCompare =
                Comparator.comparingInt(e -> e.getValue().size());

        Comparator<Map.Entry<Character, List<String>>> comparator =
                initialCompare
                        .thenComparing((e1, e2) -> lookForMin ? 0 : 1);

        return numbers.stream()
                .collect(Collectors.groupingBy(el -> el.charAt(index)))
                .entrySet()
                .stream()
                .min(lookForMin ? comparator : comparator.reversed())
                .orElseThrow()
                .getValue();
    }

}
