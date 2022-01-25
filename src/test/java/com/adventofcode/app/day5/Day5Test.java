package com.adventofcode.app.day5;

import static com.adventofcode.app.TestData.getData5;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class Day5Test {

    @Test
    public void day5Test() {
        List<Line> lines = getLines(getData5());
        List<Line> horizontalLines = getHorizontalLines(lines);
        List<Line> verticalLines = getVerticalLines(lines);

        // create board
        Board board = new Board();

        // fill board with filtered numbers;
        board.fillLines(horizontalLines);
        board.fillLines(verticalLines);

        board.countIntersections();
    }

    private List<Line> getLines(List<String> testData) {
        return testData.stream()
                .map(testLine -> testLine.split(" -> "))
                .map(splitLine -> Line.createLine(splitLine[0], splitLine[1]))
                .collect(Collectors.toList());
    }

    List<Line> getHorizontalLines(List<Line> lines) {
        return lines.stream()
                .filter(l -> l.start.x.equals(l.ending.x))
                .collect(Collectors.toList());
    }

    List<Line> getVerticalLines(List<Line> lines) {
        return lines.stream()
                .filter(l -> l.start.y.equals(l.ending.y))
                .collect(Collectors.toList());
    }
}
