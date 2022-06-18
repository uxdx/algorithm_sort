import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

public class Sort {
    static void bubble_sort(int[] data) {
        // Size of data
        int size = data.length;
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                // If current element is bigger than next element,
                if (data[j] > data[j + 1]) {
                    // Swap with each other
                    swap(data, j, j+1);
                }
            }
            // print process
            if (i % 10000 == 0)
                System.out.println("Sorting... (" + i + "/" + size + ")");
        }
    }

    static void quick_sort(int[] data, int start, int end) {
        int part = partition(data, start, end);
        // if left side length > 0, sort them.
        if (start < part - 1)
            quick_sort(data, start, part - 1);
        // if right side length > 0, sort them.
        if (end > part)
            quick_sort(data, part, end);
    }
    
    private static int partition(int[] data, int start, int end) {
        // Select pivot
        int pivot = data[(start + end) / 2];
        while (start <= end) {
            // Select value bigger than pivot in left side.
            while (data[start] < pivot)
                start++;
            // Select value smaller than pivot in right side
            while (data[end] > pivot)
                end--;
            // Swap each other
            if (start <= end) {
                swap(data, start, end);
                start++;
                end--;
            }
        }
        // 
        return start;
    }
    
    private static void swap(int[] data, int start, int end) {
        // Swap each other
        int tmp = data[start];
        data[start] = data[end];
        data[end] = tmp;
    }

    static void radix_sort(int[] data, int maxDigits) {
        int size = data.length;
        // Make queues. 0~9
        ArrayList<Queue<Integer>> queues = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            queues.add(new LinkedList<Integer>());
        }
        // Repeat each digit
        Integer num;
        for (int digit = 0; digit < maxDigits; digit++) {
            System.out.println(digit);
            // Add all numbers into its proper queue.
            for (int i = 0; i < size; i++) {
                num = data[i];
                queues.get(digitAt(num, digit)).add(num);
            }
            // Poll 
            int idx = 0;
            for (int i = 0; i < 10; i++) {
                while ((num = queues.get(i).poll()) != null) {
                    data[idx] = num;
                    idx++;
                }
            }
            // Reset Queues.
            for (int i = 0; i < queues.size(); i++) {
                queues.get(i).clear();
            }
        }
    }

    private static int digitAt(int num, int digit) {
        // If the number is smaller than 10^digit, return 0
        if (num < Math.pow(10, (digit)))
            // Return number's index
            return 0;
        else {
        // If the number is bigger than 10^digit
            String str = Integer.toString(num);
            return Integer.parseInt(str.substring(str.length() - (1 + digit), str.length() - digit));
        }
    }
}
