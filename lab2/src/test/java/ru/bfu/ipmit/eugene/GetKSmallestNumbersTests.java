package ru.bfu.ipmit.eugene;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class GetKSmallestNumbersTests {

    @Test
    void shouldReturnKSmallestNumbers() {
        System.out.println("Выполнение 2-1");

        byte[] array = {8, 9, 1, 10};
        int k = 2;

        byte[] actualResult = MainClass.getKSmallestNumbers(array, k);

        byte[] expectedResult = {1, 8};
        assertArrayEquals(actualResult, expectedResult);

        System.out.println();
    }

    @Test
    void shouldReturnKSmallestNumbers1() {
        System.out.println("Выполнение 2-2");
        byte[] array = {80, 90, 10, 40};
        int k = 0;

        byte[] actualResult = MainClass.getKSmallestNumbers(array, k);

        byte[] expectedResult = {};
        assertArrayEquals(actualResult, expectedResult);

        System.out.println();
    }

    @Test
    void shouldReturnKSmallestNumbers2() {
        System.out.println("Выполнение 2-3");

        byte[] array = {25, 39, 7, 10, 87};
        int k = 1;

        byte[] actualResult = MainClass.getKSmallestNumbers(array, k);

        byte[] expectedResult = {7};
        assertArrayEquals(actualResult, expectedResult);

        System.out.println();
    }

}
