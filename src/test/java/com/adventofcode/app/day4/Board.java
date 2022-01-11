package com.adventofcode.app.day4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void markNumberIfExists(int number) {
        for (BoardCell[] row : cells) {
            for (BoardCell boardCell : row) {
                if (boardCell.value == number) {
                    boardCell.isMarked = true;
                }
            }
        }
    }

    public boolean isGameFinished() {
        for (BoardCell[] cell : cells) {
            if (isRowFilled(cell)) {
                return true;
            }
        }

        Map<Integer, List<BoardCell>> columnMap = getColumnMap();
        return columnMap.values()
                .stream().anyMatch(this::isColumnFilled);
    }

    private Map<Integer, List<BoardCell>> getColumnMap() {
        Map<Integer, List<BoardCell>> columnMap = new HashMap<>();
        for (BoardCell[] cell : cells) {
            for (int col = 0; col < cell.length; col++) {
                final BoardCell boardCell = cell[col];
                columnMap.compute(col, (key, value) -> {
                    if (value == null) {
                        return List.of(boardCell);
                    } else {
                        value.add(boardCell);
                        return value;
                    }
                });
            }
        }
        return columnMap;
    }

    boolean isColumnFilled(List<BoardCell> columns) {
            return columns.stream().allMatch(cell -> cell.isMarked);
    }

    boolean isRowFilled(BoardCell[] boardRow) {
        return Arrays.stream(boardRow)
                .allMatch(cell -> cell.isMarked);
    }

    public int countUnmarkedNumbersSum() {
        return 0;
    }
}
