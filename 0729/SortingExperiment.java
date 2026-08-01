public class SortingExperiment {

    public static void main(String[] args) {

        int[] sortedData = {10, 20, 30, 40, 50, 60, 70, 80};
        int[] reversedData = {80, 70, 60, 50, 40, 30, 20, 10};
        int[] randomData = {45, 12, 78, 3, 60, 25, 90, 33};

        runExperiment(sortedData, "已排序資料");
        runExperiment(reversedData, "反向排序資料");
        runExperiment(randomData, "隨機排列資料");
    }

    public static void runExperiment(int[] originalData, String label) {
        System.out.println("========== " + label + "：" + arrayToString(originalData) + " ==========");

        int[] selectionCopy = copyArray(originalData);
        int[] selectionStats = selectionSort(selectionCopy);

        int[] insertionCopy = copyArray(originalData);
        int[] insertionStats = insertionSort(insertionCopy);

        System.out.println("Selection Sort → 比較次數：" + selectionStats[0] + "，交換次數：" + selectionStats[1]);
        System.out.println("Insertion Sort → 比較次數：" + insertionStats[0] + "，移動次數：" + insertionStats[1]);

        if (label.equals("已排序資料")) {
            System.out.println("觀察結論：資料已排序時，Insertion Sort 幾乎不需要移動，效率明顯優於 Selection Sort。");
        } else if (label.equals("反向排序資料")) {
            System.out.println("觀察結論：資料反向排序時，Insertion Sort 的比較與移動次數會大幅增加，接近最差情況。");
        } else {
            System.out.println("觀察結論：隨機資料下，兩種演算法的比較次數相近，但 Selection Sort 的交換次數通常較少。");
        }

        System.out.println();
    }

    public static int[] copyArray(int[] source) {
        int[] copy = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i];
        }
        return copy;
    }

    public static int[] selectionSort(int[] arr) {
        int comparisonCount = 0;
        int swapCount = 0;

        for (int start = 0; start < arr.length - 1; start++) {
            int minIndex = start;
            for (int i = start + 1; i < arr.length; i++) {
                comparisonCount++;
                if (arr[i] < arr[minIndex]) {
                    minIndex = i;
                }
            }
            if (minIndex != start) {
                int temp = arr[start];
                arr[start] = arr[minIndex];
                arr[minIndex] = temp;
                swapCount++;
            }
        }

        return new int[]{comparisonCount, swapCount};
    }

    public static int[] insertionSort(int[] arr) {
        int comparisonCount = 0;
        int shiftCount = 0;

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int position = i - 1;

            while (position >= 0 && arr[position] > key) {
                comparisonCount++;
                arr[position + 1] = arr[position];
                position--;
                shiftCount++;
            }
            if (position >= 0) {
                comparisonCount++;
            }

            arr[position + 1] = key;
        }

        return new int[]{comparisonCount, shiftCount};
    }

    public static String arrayToString(int[] arr) {
        String result = "[";
        for (int i = 0; i < arr.length; i++) {
            result = result + arr[i];
            if (i < arr.length - 1) {
                result = result + ", ";
            }
        }
        result = result + "]";
        return result;
    }
}
