import java.util.ArrayList;
import java.util.*;
 
public class AllOccurrenceSearch {
   
 
    public static ArrayList<Integer> findAllOccurrences(int[] numbers, int target) {
        ArrayList<Integer> matchIndices = new ArrayList<Integer>();
        int comparisonCount = 0;
 
        for (int i = 0; i < numbers.length; i++) {
            comparisonCount++;
            if (numbers[i] == target) {
                matchIndices.add(i);
            }
        }
 
        System.out.println("比較次數：" + comparisonCount);
        return matchIndices;
    }
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int[] numbers = {2, 5, 9, 9, 5, 8, 2, 5};
 
        System.out.println("目前資料清單（未排序）：");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("索引 " + i + " -> 數值 " + numbers[i]);
        }
 
        while (true) {
            System.out.println("");
            System.out.println("請輸入要搜尋的數值（輸入 -999 結束）：");
            String input = sc.nextLine();
            int target = Integer.parseInt(input);
 
            if (target == -999) {
                System.out.println("程式結束");
                break;
            }
 
            ArrayList<Integer> matchIndices = findAllOccurrences(numbers, target);
 
            if (matchIndices.size() == 0) {
                System.out.println("找不到數值 " + target);
            } else {
                System.out.println("找到數值 " + target + "，共出現 " + matchIndices.size() + " 次");
                System.out.println("出現索引：" + matchIndices.toString());
            }
        }
 
        sc.close();
    }
}
