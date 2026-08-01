public class SortingDebugReport {

    public static void main(String[] args) {
        // 測試1: {42, 18, 35, 7, 29} → 觸發 Selection Sort 內層範圍錯誤
        // 測試2: {30, 10, 20, 50, 5} → 觸發 Insertion Sort key 未保存錯誤
        // 測試3: {30, 10, 20, 50, 5} → 觸發 Insertion Sort 比較方向錯誤

        System.out.println("========== 錯誤一：內層範圍錯誤 ==========");
        int[] bugData1 = {42, 18, 35, 7, 29};
        int[] fixData1 = copyArray(bugData1);
        System.out.println("原始資料：" + arrayToString(bugData1));
        selectionSortInnerRangeBug(bugData1);
        System.out.println("錯誤版本結果：" + arrayToString(bugData1) + "（未正確排序）");
        selectionSortFixed(fixData1);
        System.out.println("修正版本結果：" + arrayToString(fixData1));
        System.out.println();

        System.out.println("========== 錯誤二：key 未保存 ==========");
        int[] bugData2 = {30, 10, 20, 50, 5};
        int[] fixData2 = copyArray(bugData2);
        System.out.println("原始資料：" + arrayToString(bugData2));
        insertionSortKeyNotSavedBug(bugData2);
        System.out.println("錯誤版本結果：" + arrayToString(bugData2) + "（資料遺失，出現重複值）");
        insertionSortFixed(fixData2);
        System.out.println("修正版本結果：" + arrayToString(fixData2));
        System.out.println();

        System.out.println("========== 錯誤三：比較方向錯誤 ==========");
        int[] bugData3 = {30, 10, 20, 50, 5};
        int[] fixData3 = copyArray(bugData3);
        System.out.println("原始資料：" + arrayToString(bugData3));
        insertionSortWrongDirectionBug(bugData3);
        System.out.println("錯誤版本結果：" + arrayToString(bugData3) + "（排序方向錯誤）");
        insertionSortFixed(fixData3);
        System.out.println("修正版本結果：" + arrayToString(fixData3));
        System.out.println();
    }

    // 錯誤原因：內層迴圈應該從 start + 1 開始比較，只在尚未排序的範圍內尋找最小值。
    // 這裡誤寫成從索引 0 開始，導致每一輪都重新掃描已排序完成的區域，
    // 使 minIndex 一直被拉回到索引 0，造成已排序好的資料被錯誤地換回來。
    public static void selectionSortInnerRangeBug(int[] arr) {
        for (int start = 0; start < arr.length - 1; start++) {
            int minIndex = start;
            for (int i = 0; i < arr.length; i++) {
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
    }

    // 修正方法：內層迴圈從 start + 1 開始，只在未排序區域中尋找最小值。
    public static void selectionSortFixed(int[] arr) {
        for (int start = 0; start < arr.length - 1; start++) {
            int minIndex = start;
            for (int i = start + 1; i < arr.length; i++) {
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
    }

    // 錯誤原因：沒有先用 key 變數保存 arr[i] 的原始值，直接在迴圈中比較並搬移 arr[i]。
    // 第一次搬移時 arr[position + 1]（也就是 arr[i]）就已經被前一個元素覆蓋，
    // 之後的比較與最終寫回動作使用的都不再是原始值，造成資料遺失並出現重複值。
    public static void insertionSortKeyNotSavedBug(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int position = i - 1;

            while (position >= 0 && arr[position] > arr[i]) {
                arr[position + 1] = arr[position];
                position--;
            }

            arr[position + 1] = arr[i];
        }
    }

    // 修正方法：先用 key 保存 arr[i] 的原始值，搬移過程中都使用 key 比較，
    // 最後再把 key 放回正確位置，避免資料在搬移過程中被覆蓋遺失。
    public static void insertionSortFixed(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int position = i - 1;

            while (position >= 0 && arr[position] > key) {
                arr[position + 1] = arr[position];
                position--;
            }

            arr[position + 1] = key;
        }
    }

    // 錯誤原因：比較條件誤寫成 arr[position] < key，方向與升冪排序相反。
    // 這會導致較大的元素被誤判為需要右移的對象，破壞插入排序原本應維持的
    // 左側已排序區持續有序的特性，使最終結果無法正確升冪排列。
    public static void insertionSortWrongDirectionBug(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int position = i - 1;

            while (position >= 0 && arr[position] < key) {
                arr[position + 1] = arr[position];
                position--;
            }

            arr[position + 1] = key;
        }
    }

    public static int[] copyArray(int[] source) {
        int[] copy = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i];
        }
        return copy;
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
