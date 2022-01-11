package com.adventofcode.app.day4;

import lombok.AllArgsConstructor;

@AllArgsConstructor(staticName = "of")
public class BoardCell {

    public int value;
    public boolean isMarked;

    @Override
    public String toString() {
        return value + (isMarked ? "*" : "");
    }
}
