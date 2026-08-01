public class InsertionSortPractice {

    public static void main(String[] args) {

        int[] data1 = {30, 10, 20, 50, 40, 5};
        int shifts1 = runInsertionSort(data1, "測試1：一般未排序資料");

        int[] data2 = {5, 10, 20, 30, 40, 50};
        int shifts2 = runInsertionSort(data2, "測試2：已排序資料");

        int[] data3 = {50, 40, 30, 20, 10, 5};
        int shifts3 = runInsertionSort(data3, "測試3：反向排序資料");

        System.out.println("========== 移動次數比較 ==========");
        System.out.println("一般未排序資料右移次數：" + shifts1);
        System.out.println("已排序資料右移次數：" + shifts2);
        System.out.println("反向排序資料右移次數：" + shifts3);

        if (shifts3 >= shifts1 && shifts3 >= shifts2) {
            System.out.println("結論：反向排序資料移動次數最多，因為每個元素都要移動到最前面。");
        } else if (shifts1 >= shifts2 && shifts1 >= shifts3) {
            System.out.println("結論：一般未排序資料移動次數最多。");
        } else {
            System.out.println("結論：已排序資料移動次數最多。");
        }
    }

    public static int runInsertionSort(int[] arr, String title) {
        System.out.println("========== " + title + " ==========");
        System.out.println("原始陣列：" + arrayToString(arr));

        int comparisonCount = 0;
        int shiftCount = 0;

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int position = i - 1;

            System.out.println("第 " + i + " 輪，key=" + key + "，插入前陣列=" + arrayToString(arr));

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
            System.out.println("  key=" + key + " 插入位置：" + (position + 1) + "，插入後陣列=" + arrayToString(arr));
        }

        System.out.println("排序後陣列：" + arrayToString(arr));
        System.out.println("比較次數：" + comparisonCount);
        System.out.println("元素右移次數：" + shiftCount);
        System.out.println();

        return shiftCount;
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
