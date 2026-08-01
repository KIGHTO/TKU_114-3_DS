public class MergeSortPractice {

    public static void main(String[] args) {
     
        runMergeSort(new int[]{41, 12, 35, 8, 27, 19, 50, 3}, "測試1：一般未排序資料");
        runMergeSort(new int[]{}, "測試2：空陣列");
        runMergeSort(new int[]{7}, "測試3：單筆資料");
        runMergeSort(new int[]{3, 8, 12, 19, 27, 35, 41, 50}, "測試4：已排序資料");
        runMergeSort(new int[]{50, 41, 35, 27, 19, 12, 8, 3}, "測試5：反向排序資料");
    }

    public static void runMergeSort(int[] arr, String title) {
        System.out.println("========== " + title + " ==========");
        System.out.println("原始陣列：" + arrayToString(arr));

        if (arr.length > 0) {
            mergeSort(arr, 0, arr.length - 1);
        }

        System.out.println("排序後陣列：" + arrayToString(arr));
        System.out.println();
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        System.out.println("拆分範圍 [" + left + ", " + right + "] → 左半 [" + left + ", " + mid + "]，右半 [" + (mid + 1) + ", " + right + "]");

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
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

        System.out.println("合併範圍 [" + left + ", " + right + "] 結果：" + arrayToString(temp));
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
