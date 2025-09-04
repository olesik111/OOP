package ru.nsu.kataeva;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class HeapSortTest {

    @Test
    void mainTest(){
        HeapSort.main(new String[]{});
    }
    @Test
    void nullCheck() {
        int[] arr = null;
        assertDoesNotThrow(() -> HeapSort.heapSort(arr));
    }

    @Test
    void empty() {
        int[] arr = {};
        int[] expected = {};
        HeapSort.heapSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void single() {
        int[] arr = {1};
        int[] expected = {1};
        HeapSort.heapSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void sortedArray() {
        int[] arr = {1, 2, 3};
        int[] expected = {1, 2, 3};
        HeapSort.heapSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void reverseSorted() {
        int[] arr = {3, 2, 1};
        int[] expected = {1, 2, 3};
        HeapSort.heapSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void justArray() {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = {11, 12, 22, 25, 34, 64, 90};
        HeapSort.heapSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void duplicates() {
        int[] arr = {5, 2, 8, 2, 5, 8, 1};
        int[] expected = {1, 2, 2, 5, 5, 8, 8};
        HeapSort.heapSort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void big() {
        Random random = new Random();
        int[] arrRand = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arrRand[i] = random.nextInt(100);
        }
        int[] expected = arrRand.clone();
        Arrays.sort(expected);

        HeapSort.heapSort(arrRand);

        assertArrayEquals(expected, arrRand);
    }


    @Test
    void minus() {
        int[] arr = {-3, 1, -4, 1, -5, 9, 2, 6};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        HeapSort.heapSort(arr);

        assertArrayEquals(expected, arr);
    }
}