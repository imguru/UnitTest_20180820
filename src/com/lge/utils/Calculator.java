package com.lge.utils;
// https://github.com/imguru/UnitTest_20180820


// Production Code
// => SUT 또는 CUT 라고 부릅니다.
// => System Under Test
// => Class(Code) Under Test
public class Calculator {
    private int value;

    public void add(int n) {
        value += n;
    }

    public void sub(int n) {
        value -= n;
    }

    public int display() {
        return 0;
        // return value;
    }
}

