public class InventorySearchPractice {

    public static void main(String[] args) {
        
        int[] ids = {305, 118, 452, 27, 390, 210, 65, 480, 133, 275, 8, 360};

        System.out.println("排序前：" + arrayToString(ids));

        mergeSort(ids, 0, ids.length - 1);

        System.out.println("排序後：" + arrayToString(ids));
        System.out.println();

        int firstId = ids[0];
        int lastId = ids[ids.length - 1];
        int missingId = 999;

        runSearch(ids, firstId, "測試2：搜尋第一筆編號");
        runSearch(ids, lastId, "測試3：搜尋最後一筆編號");
        runSearch(ids, missingId, "測試4：搜尋不存在的編號");
    }

    public static void runSearch(int[] ids, int target, String title) {
        System.out.println("========== " + title + " ==========");
        int index = binarySearch(ids, target);
        if (index != -1) {
            System.out.println("搜尋編號 " + target + " → 找到，索引為 " + index);
        } else {
            System.out.println("搜尋編號 " + target + " → 查無此編號");
        }
        System.out.println();
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
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
