package com.adventofcode.app.day5;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Line {
    Point start;
    Point ending;

    public static Line createLine(String coordinatesStart, String coordinatesEnd) {
        final Point start = getPoint(coordinatesStart);
        final Point end = getPoint(coordinatesEnd);
        return new Line(start, end);
    }

    private static Point getPoint(String coodrdinates) {
        final String[] splitedCoodrdinates = coodrdinates.split(",");
        return new Point(Integer.parseInt(splitedCoodrdinates[0]), Integer.parseInt(splitedCoodrdinates[1]));
    }

    @Override
    public String toString() {
        return "[" + start + " -> " + ending + "]\n";
    }
}
