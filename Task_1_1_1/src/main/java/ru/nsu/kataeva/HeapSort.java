package ru.nsu.kataeva;

/**
 * Класс для сортировки массива с использованием алгоритма пирамидальной сортировки.
 * Алгоритм состоит из двух основных этапов: построение max-кучи и последовательное извлечение
 * максимальных элементов с восстановлением свойств кучи.
 */
public class HeapSort {
    /**
     * Основной метод для демонстрации работы алгоритма сортировки.
     */
    public static void main(String[] args) {
        int[] arr = {1, 5, 3};
        heapSort(arr);
        printArray(arr);
    }

    /**
     * Метод для основной части сортировки.
     *
     * @param arr массив для сортировки
     */
    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 1) {
            return;
        }

        int n = arr.length;
        // Построение max-кучи: начинаем с последнего нелистового узла
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }
        for (int i = n - 1; i >= 0; i--) {
            int swap = arr[0];
            arr[0] = arr[i];
            arr[i] = swap;
            heapify(arr, 0, i);
            // Перемещаем текущий максимальный элемент в конец,
            // восстанавливаем свойства кучи для уменьшенного массива
        }
    }

    /**
     * Восстанавливает свойства max-кучи для поддерева с корнем в заданном индексе.
     * Рекурсивно просеивает элемент вниз до тех пор, пока свойства кучи не будут восстановлены.
     *
     * @param arr массив-куча
     * @param i   индекс корня поддерева
     * @param n   размер кучи
     */
    private static void heapify(int[] arr, int i, int n) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int maxi = i;

            if (left < n && arr[left] > arr[maxi]) {
                maxi = left;
            }
            if (right < n && arr[right] > arr[maxi]) {
                maxi = right;
            }
            if (maxi == i){
                break;
            }
            // Если после сравнения с потомками максимальный элемент - не текущий корень,
            // меняем местами и восстанавливаем свойства для измененного поддерева

            int swap = arr[maxi];
            arr[maxi] = arr[i];
            arr[i] = swap;

            i = maxi;

        }

    }

    /**
     * Выводит элементы массива в консоль через пробел.
     *
     * @param arr массив для вывода
     */
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.print("\n");
    }
}
