package com.adventofcode.app.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.adventofcode.app.TestData;

public class Day4Test {

    @Test
    void name() {
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
        for (Integer digit : digits) {
            for (Board board : boards) {
                board.markNumberIfExists(digit);
                if (board.isGameFinished()) {
                    System.out.println("\nWinning board\n" + board);
                    System.out.println(digit * board.countUnmarkedNumbersSum());
                }
            }
        }
    }
}
