import java.util.Scanner;

public class RangeSearchSystem {
    
    public static int findFirstOccurrence(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (numbers[mid] == target) {
                result = mid;
                high = mid - 1;
            } else if (numbers[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    public static int findLastOccurrence(int[] numbers, int target) {
        int low = 0;
        int high = numbers.length - 1;
        int result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (numbers[mid] == target) {
                result = mid;
                low = mid + 1;
            } else if (numbers[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numbers = {2, 4, 4, 4, 7, 9, 9, 12, 15, 15, 15, 15, 18, 20};

        System.out.println("目前資料清單（已排序，含重複資料）：");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("索引 " + i + " -> 數值 " + numbers[i]);
        }

        while (true) {
            System.out.println("");
            System.out.println("請輸入要搜尋的數值（輸入 -1 結束）：");
            String input = sc.nextLine();
            int target = Integer.parseInt(input);

            if (target == -1) {
                System.out.println("程式結束");
                break;
            }

            int firstIndex = findFirstOccurrence(numbers, target);
            int lastIndex = findLastOccurrence(numbers, target);

            if (firstIndex == -1) {
                System.out.println("找不到數值 " + target + "，回傳 [-1, -1]");
            } else {
                int occurrenceCount = lastIndex - firstIndex + 1;
                System.out.println("數值 " + target + " 的索引範圍為 [" + firstIndex + ", " + lastIndex + "]");
                System.out.println("出現次數為 " + occurrenceCount);
            }
        }

        sc.close();
    }
}
