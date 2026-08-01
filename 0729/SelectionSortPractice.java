public class SelectionSortPractice {

    public static void main(String[] args) {
       

        int[] data1 = {42, 18, 35, 7, 29, 14};
        runSelectionSort(data1, "測試1：一般資料");

        int[] data2 = {};
        runSelectionSort(data2, "測試2：空陣列");

        int[] data3 = {99};
        runSelectionSort(data3, "測試3：單一元素陣列");
    }

    public static void runSelectionSort(int[] arr, String title) {
        System.out.println("========== " + title + " ==========");
        System.out.println("原始陣列：" + arrayToString(arr));

        int comparisonCount = 0;
        int swapCount = 0;

        for (int start = 0; start < arr.length - 1; start++) {
            int minIndex = start;
            System.out.println("第 " + (start + 1) + " 輪，start=" + start + "，陣列=" + arrayToString(arr));

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
                System.out.println("  選中索引 " + minIndex + "（值=" + arr[start] + "），與索引 " + start + " 交換");
            } else {
                System.out.println("  選中索引 " + minIndex + "，已在正確位置，不需交換");
            }
        }

        System.out.println("排序後陣列：" + arrayToString(arr));
        System.out.println("比較次數：" + comparisonCount);
        System.out.println("實際交換次數：" + swapCount);
        System.out.println();
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
