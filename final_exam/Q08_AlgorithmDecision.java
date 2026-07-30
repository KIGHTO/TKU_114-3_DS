public class Q08_AlgorithmDecision {
    public static void main(String[] args) {
        int[] data = new int[64];
        for (int index = 0; index < data.length; index++) {
            data[index] = (index + 1) * 3;
        }
 
        System.out.println("已排序 : " +
            isSortedAscending(data));
        System.out.println("循序比較次數 : " +
            sequentialChecks(data, 192));
        System.out.println("二分比較次數 : " +
            binaryChecks(data, 192));
        System.out.println("建議 : " +
            chooseSearch(true, data.length, 5));
    }
 
    public static boolean isSortedAscending(int[] data) {
        for (int index = 1; index < data.length; index++) {
            if (data[index] < data[index - 1]) {
                return false;
            }
        }
        return true;
    }
 
    public static int sequentialChecks(int[] data, int target) {
        int comparisons = 0;
 
        for (int index = 0; index < data.length; index++) {
            comparisons++;
            if (data[index] == target) {
                break;
            }
        }
        return comparisons;
    }
 
    public static int binaryChecks(int[] data, int target) {
        int comparisons = 0;
        int low = 0;
        int high = data.length - 1;
 
        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;
 
            if (data[mid] == target) {
                break;
            } else if (data[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return comparisons;
    }
 
    public static String chooseSearch(
        boolean sorted,
        int dataSize,
        int expectedSearches
    ) {
        if (!sorted) {
            return "SEQUENTIAL";
        }
 
        if (dataSize >= 32 && expectedSearches >= 2) {
            return "BINARY";
        } else {
            return "SEQUENTIAL";
        }
    }
}
