package ru.bfu.ipmit.eugene;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class RotateMatrixTests {

    @Test
    void shouldRotateMatrix() {
        System.out.println("Выполнение 3-1:");

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        int[][] actualResult = MainClass.rotateMatrix(matrix);

        int[][] expectedResult = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
        assertArrayEquals(actualResult, expectedResult);

        System.out.println();
    }

    @Test
    void shouldRotateMatrix1() {
        System.out.println("Выполнение 3-2:");

        int[][] matrix = {{1, 5},{2, 4}};

        int[][] actualResult = MainClass.rotateMatrix(matrix);

        int[][] expectedResult = {{2, 1}, {4, 5}};
        assertArrayEquals(actualResult, expectedResult);

        System.out.println();
    }

    @Test
    void shouldRotateMatrix2() {
        System.out.println("Выполнение 3-3:");

        int[][] matrix = {};

        int[][] actualResult = MainClass.rotateMatrix(matrix);

        int[][] expectedResult = {};
        assertArrayEquals(actualResult, expectedResult);

        System.out.println();
    }

}
