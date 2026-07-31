import java.util.Scanner;
 
public class ProductIdSearchPractice {
  
    public static int linearSearch(int[] productIds, int target) {
        int comparisonCount = 0;
 
        for (int i = 0; i < productIds.length; i++) {
            comparisonCount++;
            if (productIds[i] == target) {
                System.out.println("實際比較次數：" + comparisonCount);
                return i;
            }
        }
 
        System.out.println("實際比較次數：" + comparisonCount);
        return -1;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int[] productIds = {3021, 5567, 1289, 8842, 4453, 9901, 2276, 6634};
 
        System.out.println("目前商品編號清單（未排序）：");
        for (int i = 0; i < productIds.length; i++) {
            System.out.println("索引 " + i + " -> 編號 " + productIds[i]);
        }
 
        while (true) {
            System.out.println("");
            System.out.println("請輸入要搜尋的商品編號（輸入 -1 結束）：");
            String input = sc.nextLine();
            int target = Integer.parseInt(input);
 
            if (target == -1) {
                System.out.println("程式結束");
                break;
            }
 
            int index = linearSearch(productIds, target);
 
            if (index == -1) {
                System.out.println("找不到商品編號 " + target);
            } else {
                System.out.println("找到商品編號 " + target + "，索引為 " + index);
            }
        }
 
        sc.close();
    }
}
