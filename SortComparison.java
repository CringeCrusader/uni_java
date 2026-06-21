import java.util.Random;

public class SortComparison {

    //Пузырьковая сортировка
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }

    //Quick sort
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            if (high - low < 10) {
                insertionSort(arr, low, high);
                return;
            }
            
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;
        
        if (arr[mid] < arr[low]) swap(arr, low, mid);
        if (arr[high] < arr[low]) swap(arr, low, high);
        if (arr[high] < arr[mid]) swap(arr, mid, high);
        
        swap(arr, mid, high);
        int pivot = arr[high];
        
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static int[] copyArray(int[] src) {
        int[] dest = new int[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000);
        }
        return arr;
    }

    public static void measureTime(int[] arr, String sortName, boolean isQuick) {
        int[] copy = copyArray(arr);
        long start = System.nanoTime();

        if (isQuick) {
            quickSort(copy, 0, copy.length - 1);
        } else {
            bubbleSort(copy);
        }

        long end = System.nanoTime();
        double timeMs = (end - start) / 1000000.0;

        System.out.printf("%-20s | Время: %10.3f мс%n",
                sortName, timeMs);
    }

    public static void main(String[] args) {
        int[] sizes = {10, 100, 1000};

        for (int size : sizes) {
            System.out.println("/\\/\\/\\/\\ Размер массива: " + size + " /\\/\\/\\/\\");
            int[] original = generateRandomArray(size);
            
            // Случайный массив
            System.out.println("  Случайный массив:");
            measureTime(original, "Bubble Sort", false);
            measureTime(original, "Quick Sort", true);
            
            // Отсортированный массив
            int[] sorted = copyArray(original);
            quickSort(sorted, 0, sorted.length - 1);
            System.out.println("  Отсортированный массив:");
            measureTime(sorted, "Bubble Sort", false);
            measureTime(sorted, "Quick Sort", true);
            
            System.out.println();
        }
    }
}