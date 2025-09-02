package ru.nsu.kataeva;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

class HeapSortTest {

    @Test
    void Null() {
        int[] arr = null;
        assertDoesNotThrow(() -> HeapSort.heapsort(arr));
    }

    @Test
    void Empty() {
        int[] arr = {};
        int[] expected = {};
        HeapSort.heapsort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void Single() {
        int[] arr = {1};
        int[] expected = {1};
        HeapSort.heapsort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void Sorted() {
        int[] arr = {1, 2, 3};
        int[] expected = {1, 2, 3};
        HeapSort.heapsort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void ReverseSorted() {
        int[] arr = {3, 2, 1};
        int[] expected = {1, 2, 3};
        HeapSort.heapsort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void JustArray() {
        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        int[] expected = {11, 12, 22, 25, 34, 64, 90};
        HeapSort.heapsort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void Duplicates() {
        int[] arr = {5, 2, 8, 2, 5, 8, 1};
        int[] expected = {1, 2, 2, 5, 5, 8, 8};
        HeapSort.heapsort(arr);
        assertArrayEquals(expected, arr);
    }

    @Test
    void Big() {
        Random random = new Random();
        int[] arr_rand = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr_rand[i] = random.nextInt(100);
        }
        int[] expected = arr_rand.clone();
        Arrays.sort(expected);

        HeapSort.heapsort(arr_rand);

        assertArrayEquals(expected, arr_rand);
    }



    @Test
    void Minus() {
        int[] arr = {-3, 1, -4, 1, -5, 9, 2, 6};
        int[] expected = arr.clone();
        Arrays.sort(expected);

        HeapSort.heapsort(arr);

        assertArrayEquals(expected, arr);
    }
}