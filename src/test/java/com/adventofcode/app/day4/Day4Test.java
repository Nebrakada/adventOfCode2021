package com.adventofcode.app.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.adventofcode.app.TestData;

public class Day4Test {

    @Test
    void day4part1() {
        List<String> data = TestData.getData4();

        List<Integer> digits = Arrays.stream(data.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Board board;
        List<Board> boards = new ArrayList<>();

        for (int i = 2; i < data.size(); i = i + 6) {
            String row = data.get(i);
            if (!row.equals("\n")) {
                // create board
                List<String> sublistData = data.subList(i, i + 5);
                board = new Board(sublistData);
                boards.add(board);
            }
        }

        System.out.println("boards: " + boards);

        // start game - for every input number mark value if exists in board
        markSelectedNumbers(digits, boards);

        // check if game is finished


        // if finished, count end result
    }

    private void markSelectedNumbers(List<Integer> digits, List<Board> boards) {
        List<Board> winBoards = new ArrayList<>();
        List<Integer> winDigits = new ArrayList<>();
        for (Integer digit : digits) {
            for (Board board : boards) {
                if (board.isFinished) {
                    continue;
                }
                board.markNumberIfExists(digit);
                if (board.isGameFinished()) {
                    int unmarkedSum = board.countUnmarkedNumbersSum();
                    System.out.println(digit* unmarkedSum);
                    winBoards.add(board);
                    board.isFinished = true;
                    if (!winDigits.contains(digit)) {
                        winDigits.add(digit);
                    }

                }
            }
        }

        Integer win1digit = winDigits.get(0);
        System.out.println("win 1: " + win1digit);
        Integer winLastDigit = winDigits.get(winDigits.size() - 1);
        System.out.println("win last: " + winLastDigit);
        System.out.println("result 1: " + win1digit * winBoards.get(0).countUnmarkedNumbersSum());
        System.out.println("result last: " + winLastDigit * winBoards.get(winBoards.size() -1 ).countUnmarkedNumbersSum());
    }
}
