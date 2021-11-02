package ru.bfu.ipmit.eugene;

        import org.junit.jupiter.api.Test;

        import static org.junit.jupiter.api.Assertions.assertFalse;
        import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArePermutationsTests {

    @Test
    void shouldReturnTrueIfStringsArePermutations() {
        System.out.print("Тест 1-1");

        String firstString = "ab1";
        String secondString = "b1a";

        assertTrue(MainClass.arePermutations(firstString, secondString));

        System.out.println();
    }

    @Test
    void shouldReturnTrueIfStringsArePermutations1() {
        System.out.print("Тест 1-2");

        String firstString = "qwerty123";
        String secondString = "1q3w2etyr";

        assertTrue(MainClass.arePermutations(firstString, secondString));

        System.out.println();
    }

    @Test
    void shouldReturnTrueIfStringsArePermutations2() {
        System.out.print("Тест 1-3");

        String firstString = "";
        String secondString = "1548fdr";
        System.out.println();
        assertFalse(MainClass.arePermutations(firstString, secondString));

        System.out.println();
    }

}