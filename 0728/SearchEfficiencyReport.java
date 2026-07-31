public class SearchEfficiencyReport {
   
    public static int[] buildSortedArray(int size) {
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = i * 2 + 1;
        }

        return array;
    }

    public static void runSequentialSearch(int[] array, int target, String label) {
        int comparisonCount = 0;
        int foundIndex = -1;

        for (int i = 0; i < array.length; i++) {
            comparisonCount++;
            if (array[i] == target) {
                foundIndex = i;
                break;
            }
        }

        if (foundIndex == -1) {
            System.out.println("Sequential Search - " + label + "：找不到，比較次數 = " + comparisonCount);
        } else {
            System.out.println("Sequential Search - " + label + "：找到於索引 " + foundIndex + "，比較次數 = " + comparisonCount);
        }
    }

    public static void runBinarySearch(int[] array, int target, String label) {
        int low = 0;
        int high = array.length - 1;
        int comparisonCount = 0;
        int foundIndex = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisonCount++;

            if (array[mid] == target) {
                foundIndex = mid;
                break;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (foundIndex == -1) {
            System.out.println("Binary Search - " + label + "：找不到，比較次數 = " + comparisonCount);
        } else {
            System.out.println("Binary Search - " + label + "：找到於索引 " + foundIndex + "，比較次數 = " + comparisonCount);
        }
    }

    public static void main(String[] args) {
        int[] dataSizes = {16, 128, 1024};

        for (int i = 0; i < dataSizes.length; i++) {
            int size = dataSizes[i];
            int[] array = buildSortedArray(size);

            int firstValue = array[0];
            int lastValue = array[array.length - 1];
            int notExistValue = array[array.length - 1] + 100;

            System.out.println("========== 資料筆數：" + size + " ==========");
            runSequentialSearch(array, firstValue, "第一筆");
            runBinarySearch(array, firstValue, "第一筆");
            runSequentialSearch(array, lastValue, "最後一筆");
            runBinarySearch(array, lastValue, "最後一筆");
            runSequentialSearch(array, notExistValue, "不存在資料");
            runBinarySearch(array, notExistValue, "不存在資料");
            System.out.println("");
        }

        System.out.println("觀察結果：");
        System.out.println("Sequential Search 的比較次數會隨資料筆數線性成長，資料量越大越明顯，尤其是搜尋最後一筆或不存在資料時最為費時。");
        System.out.println("Binary Search 的比較次數成長速度遠比 Sequential Search 慢，即使資料筆數增加到 1024 筆，比較次數依然維持在個位數的成長幅度。");
        System.out.println("因此在已排序資料的情況下，Binary Search 的搜尋效率明顯優於 Sequential Search。");
    }
}
