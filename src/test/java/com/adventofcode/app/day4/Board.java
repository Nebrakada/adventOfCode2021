package com.adventofcode.app.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Board {

    private BoardCell[][] cells = new BoardCell[5][5];

    public boolean isFinished = false;

    public Board(List<String> rows) {
//        System.out.println("inside board: " + rows);
        for (int row = 0; row < rows.size(); row++) {
            String[] rowElements = rows.get(row).trim().split("  ?");
            for (int col = 0; col < rowElements.length; col++) {
                cells[row][col] = BoardCell.of(Integer.parseInt(rowElements[col].trim()), false);
            }
        }
    }

    @Override
    public String toString() {
        return "Board{" + Arrays.deepToString(cells) + "}\n";
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
                        List<BoardCell> boardCells = new ArrayList<>();
                        boardCells.add(boardCell);
                        return boardCells;
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
//        System.out.println("counting: " + this.toString());
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .filter(cell -> !cell.isMarked)
                .map(cell-> cell.value)
                .reduce(0, Integer::sum);
    }
}
