package com.adventofcode.app.day4;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.adventofcode.app.TestData;

public class Day4Test {

    @Test
    void name() {
        List<String> data = TestData.getData4();
        String[] digits = data.get(0).split(",");
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

        // check if game is finished

        // if finished, count end result
    }
}
