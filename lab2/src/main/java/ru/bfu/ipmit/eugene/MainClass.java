package ru.bfu.ipmit.eugene;

import java.util.Arrays;
import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Какую задачу вы хотите запустить? Введите цифру от 1 до 3.");
        System.out.println();
        System.out.println("1 - Метод getKSmallestNumbers возвращает k наименьших элементов в массиве array, отсортированных в порядке возрастания");
        System.out.println();
        System.out.println("2 - Метод arePermutations проверяет, является ли первая строка перестановкой второй, то есть может ли первая строка\n" +
                           " быть получена из второй путем перестановки символов.");
        System.out.println();
        System.out.println("3 - Метод rotateMatrix совершает поворот элементов квадратной матрицы на 90 градусов по часовой стрелке");
        System.out.println();

        System.out.print("Введите номер задачи: ");
        int answer = in.nextInt();

        if (answer == 1) {
            Scanner input = new Scanner(System.in);

            System.out.print("Введите количество элементов массива: ");
            byte sizeArray = input.nextByte();
            byte[] array = new byte[sizeArray];
            System.out.println("Заполните массив элементами: ");

            for (int i = 0; i < sizeArray; i++) {
                System.out.print("Длемент " + i + ": ");
                array[i] = input.nextByte();
            }

            System.out.print("Введите целочисленный параметр k (0 <= k <= n) : ");
            int k = input.nextInt();

            if (k == 0) {
                System.out.println("Так как вы ввели k = 0, наименьшие элементы массива не будут показаны!");
                System.out.print("Введите целочисленный параметр k (0 <= k <= n)");
                return;
            } else if(k > sizeArray) {
                System.out.println("Так как вы ввели k больше, чем размер массива, наименьшие элементы массива не будут показаны!");
                System.out.print("Введите целочисленный параметр k (0 <= k <= n)");
                return;
            } else if(k < 0) {
                System.out.println("Так как вы ввели k меньше 0, наименьшие элементы массива не будут показаны!");
                System.out.print("Введите целочисленный параметр k (0 <= k <= n)");
                return;
            }
            getKSmallestNumbers(array, k);
        } else if (answer == 2) {
            Scanner input = new Scanner(System.in);
            boolean onlyLatinFirst;

            System.out.print("Введите первую строку: ");
            String firstString = input.nextLine();
            onlyLatinFirst = firstString.matches("^[a-zA-Z0-9]+$");
            if (!onlyLatinFirst) {
                System.out.println("Строка должна состоять только из латинский букв и цифр");
                return;
            }

            System.out.print("Введите вторую строку: ");
            String secondString = input.nextLine();

            arePermutations(firstString, secondString);
        } else if (answer == 3) {
            Scanner input = new Scanner(System.in);

            System.out.print("Введите размер квадратной матрицы: ");
            int sizeMatrix = input.nextInt();

            if (sizeMatrix == 0 || sizeMatrix == 1) {
                System.out.print("Матрица не может быть нулевого или единичного размера! Введите другую размерномть матрицы.");
                return;
            } else {
                int[][] matrix = new int[sizeMatrix][sizeMatrix];

                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[i].length; j++) {
                        System.out.print("Введите элемент matrix[" + i + "][" + j + "]: ");
                        matrix[i][j] = input.nextInt();
                    }
                }
                in.close();

                rotateMatrix(matrix);
            }
        } else {
            System.out.println("Вы ввели неверный номер! Введите другой номер");
        }
        }



    static boolean arePermutations(String firstString, String secondString) {

        if (firstString.length() < secondString.length() || firstString.length() > secondString.length()) {
            System.out.println("Строка - " + firstString + " не является перестановкой строки - " + secondString);
            return false;
        } else if (firstString.length() == secondString.length()) {

            char[] chArrayFirst = firstString.toCharArray();
            char[] chArraySecond = secondString.toCharArray();
            String result = firstString;

            for (int i = 0; i < secondString.length(); i++) {
                for (int j = 0; j < firstString.length(); j++) {
                    if (chArrayFirst[i] == chArraySecond[j]) {
                        result = result.replaceAll(String.valueOf(chArrayFirst[i]), " ");
                    }
                }
            }

            System.out.println(result.trim());
            if (result.trim().equals("")) {
                System.out.println("Строка - " + firstString + " является перестановкой строки - " + secondString);
                return true;
            } else {
                System.out.println("Строка - " + firstString + " не является перестановкой строки - " + secondString);
                return false;
            }
        }
        return false;
    }

    static int[][] rotateMatrix(int[][] matrix) {

        int[][] result = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                result[i][j] = matrix[matrix.length - j - 1][i];
                System.out.printf("%3d", result[i][j]);
            }
            System.out.println();
        }
        return result;
    }

    static byte[] getKSmallestNumbers(byte[] array, int k) {
        System.out.print("Исходный массив при k=" + k + ": ");

        for (int i = 0; i < array.length; i++) {
            System.out.print(" " + array[i]);
        }
        System.out.println();

        Arrays.sort(array);

        System.out.print("Результирующий массив: ");

        byte[] newArray = Arrays.copyOfRange(array, 0, k);

        for (int i = 0; i < newArray.length; i++) {
            System.out.print(" " + newArray[i]);
        }
        System.out.println();

        return newArray;
    }
}
