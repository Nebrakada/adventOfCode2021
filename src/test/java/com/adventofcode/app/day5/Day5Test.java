package com.adventofcode.app.day5;

import static com.adventofcode.app.TestData.getData5;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class Day5Test {

    @Test
    public void day5Test(){
        List<Line> lines = getLines(getData5());
    }

    private List<Line> getLines(List<String> testData) {
        return testData.stream()
                .map(testLine -> testLine.split(" -> "))
                .map(splitLine -> Line.createLine(splitLine[0], splitLine[1]))
                .collect(Collectors.toList());
    }

    List<Line> getHorizontalOrVerticalLines(){
        return null;
    }

}
