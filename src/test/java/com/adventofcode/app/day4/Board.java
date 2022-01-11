package com.adventofcode.app.day4;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Board {

    private BoardCell[][] cells = new BoardCell[5][5];

    public Board(List<String> rows) {
        System.out.println("inside board: " + rows);
        for (int row = 0; row < rows.size(); row++) {
            String[] rowElements = rows.get(row).trim().split("  ?");
            for (int col = 0; col < rowElements.length; col++) {
                cells[row][col] = BoardCell.of(Integer.parseInt(rowElements[col].trim()), false);
            }
        }
    }

    @Override
    public String toString() {
        return "Board{" +
                "cells=" + Arrays.toString(cells) +
                '}';
    }

    public int countUnmarkedNumbersSum() {
        return 0;
    }
}
