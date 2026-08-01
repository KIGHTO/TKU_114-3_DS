public class MergeArrayPractice {

    public static void main(String[] args) {
        
        int[] arrA1 = {1, 5, 8, 8, 12};
        int[] arrB1 = {-3, 0, 5, 20};
        runMerge(arrA1, arrB1, "測試1：一般合併（含重複值及負數）");

        int[] arrA2 = {2, 4, 6};
        int[] arrB2 = {};
        runMerge(arrA2, arrB2, "測試2：其中一個陣列為空");

        int[] arrA3 = {};
        int[] arrB3 = {};
        runMerge(arrA3, arrB3, "測試3：兩個陣列皆為空");
    }

    public static void runMerge(int[] arrA, int[] arrB, String title) {
        System.out.println("========== " + title + " ==========");
        System.out.println("陣列A：" + arrayToString(arrA));
        System.out.println("陣列B：" + arrayToString(arrB));

        int[] result = mergeSortedArrays(arrA, arrB);

        System.out.println("合併結果：" + arrayToString(result));
        System.out.println("結果長度：" + result.length + "（應等於 " + (arrA.length + arrB.length) + "）");
        System.out.println();
    }

    public static int[] mergeSortedArrays(int[] arrA, int[] arrB) {
        int[] result = new int[arrA.length + arrB.length];
        int i = 0;
        int j = 0;
        int k = 0;

        while (i < arrA.length && j < arrB.length) {
            if (arrA[i] <= arrB[j]) {
                result[k] = arrA[i];
                i++;
            } else {
                result[k] = arrB[j];
                j++;
            }
            k++;
        }

        while (i < arrA.length) {
            result[k] = arrA[i];
            i++;
            k++;
        }

        while (j < arrB.length) {
            result[k] = arrB[j];
            j++;
            k++;
        }

        return result;
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