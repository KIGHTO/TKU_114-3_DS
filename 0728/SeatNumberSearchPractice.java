import java.util.Scanner;
 
public class SeatNumberSearchPractice {
 
    public static int binarySearch(int[] seatNumbers, int target) {
        int low = 0;
        int high = seatNumbers.length - 1;
 
        while (low <= high) {
            int mid = (low + high) / 2;
 
            System.out.println("low = " + low + ", mid = " + mid + ", high = " + high);
 
            if (seatNumbers[mid] == target) {
                return mid;
            } else if (seatNumbers[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
 
        return -1;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int[] seatNumbers = {101, 105, 112, 118, 123, 129, 134, 140, 145, 151, 158, 163};
 
        System.out.println("目前座位編號清單（已排序）：");
        for (int i = 0; i < seatNumbers.length; i++) {
            System.out.println("索引 " + i + " -> 座位 " + seatNumbers[i]);
        }
 
        while (true) {
            System.out.println("");
            System.out.println("請輸入要搜尋的座位編號（輸入 -1 結束）：");
            String input = sc.nextLine();
            int target = Integer.parseInt(input);
 
            if (target == -1) {
                System.out.println("程式結束");
                break;
            }
 
            int index = binarySearch(seatNumbers, target);
 
            if (index == -1) {
                System.out.println("找不到座位編號 " + target);
            } else {
                System.out.println("找到座位編號 " + target + "，索引為 " + index);
            }
        }
 
        sc.close();
    }
}
