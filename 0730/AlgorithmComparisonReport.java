import java.util.Random;

public class AlgorithmComparisonReport {

    private static long mergeComparisonCounter = 0;

    public static void main(String[] args) {
        
        int[] sizes = {16, 128, 1024};
        String[] patterns = {"已排序", "反向排序", "固定亂序"};

        System.out.println("========== 演算法比較次數報告 ==========");
        System.out.println("資料量\t資料型態\tSelection Sort\tInsertion Sort\tMerge Sort");

        for (int s = 0; s < sizes.length; s++) {
            int size = sizes[s];

            for (int p = 0; p < patterns.length; p++) {
                int[] originalData = generateData(size, patterns[p]);

                int[] selectionCopy = copyArray(originalData);
                long selectionCount = selectionSortCount(selectionCopy);

                int[] insertionCopy = copyArray(originalData);
                long insertionCount = insertionSortCount(insertionCopy);

                int[] mergeCopy = copyArray(originalData);
                long mergeCount = mergeSortCount(mergeCopy);

                System.out.println(size + "\t" + patterns[p] + "\t" + selectionCount + "\t" + insertionCount + "\t" + mergeCount);

                printConclusion(size, patterns[p], selectionCount, insertionCount, mergeCount);
            }
        }
    }

    public static void printConclusion(int size, String pattern, long selectionCount, long insertionCount, long mergeCount) {
        String bestAlgorithm = "";
        long bestCount = selectionCount;
        bestAlgorithm = "Selection Sort";

        if (insertionCount < bestCount) {
            bestCount = insertionCount;
            bestAlgorithm = "Insertion Sort";
        }
        if (mergeCount < bestCount) {
            bestCount = mergeCount;
            bestAlgorithm = "Merge Sort";
        }

        System.out.println("  觀察結論：資料量 " + size + " 筆、" + pattern + " 情況下，比較次數最少的是 " + bestAlgorithm + "（" + bestCount + " 次）。");
    }

    public static int[] generateData(int size, String pattern) {
        int[] data = new int[size];

        if (pattern.equals("已排序")) {
            for (int i = 0; i < size; i++) {
                data[i] = i;
            }
        } else if (pattern.equals("反向排序")) {
            for (int i = 0; i < size; i++) {
                data[i] = size - i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                data[i] = i;
            }
            Random random = new Random(42);
            for (int i = size - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
            }
        }

        return data;
    }

    public static int[] copyArray(int[] source) {
        int[] copy = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i];
        }
        return copy;
    }

    public static long selectionSortCount(int[] arr) {
        long comparisonCount = 0;

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
            }
        }

        return comparisonCount;
    }

    public static long insertionSortCount(int[] arr) {
        long comparisonCount = 0;

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int position = i - 1;

            while (position >= 0 && arr[position] > key) {
                comparisonCount++;
                arr[position + 1] = arr[position];
                position--;
            }
            if (position >= 0) {
                comparisonCount++;
            }

            arr[position + 1] = key;
        }

        return comparisonCount;
    }

    public static long mergeSortCount(int[] arr) {
        mergeComparisonCounter = 0;
        if (arr.length > 0) {
            mergeSort(arr, 0, arr.length - 1);
        }
        return mergeComparisonCounter;
    }

    private static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            mergeComparisonCounter++;
            if (arr[i] <= arr[j]) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = arr[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = arr[j];
            j++;
            k++;
        }

        for (int m = 0; m < temp.length; m++) {
            arr[left + m] = temp[m];
        }
    }
}
