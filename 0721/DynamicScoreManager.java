import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
 
public class DynamicScoreManager {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();
 
        System.out.println("請輸入成績(0~100),輸入 -1 結束:");
 
        while (true) {
            int score;
 
            try {
                score = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("輸入錯誤,請輸入數字。");
                sc.nextLine();
                continue;
            }
 
            if (score == -1) {
                break;
            }
 
            if (score < 0 || score > 100) {
                System.out.println("成績超出範圍,請輸入 0 到 100 之間的數字。");
                continue;
            }
 
            scores.add(score);
        }
 
        if (scores.size() == 0) {
            System.out.println("沒有輸入任何成績。");
            return;
        }
 
        int count = scores.size();
        double average = calculateAverage(scores);
        int max = findMax(scores);
        int min = findMin(scores);
        ArrayList<Integer> passingList = getPassingList(scores);
 
        System.out.println("筆數:" + count);
        System.out.println("平均:" + average);
        System.out.println("最高:" + max);
        System.out.println("最低:" + min);
        System.out.println("及格名單:" + passingList);
    }
 
    public static double calculateAverage(ArrayList<Integer> scores) {
        int sum = 0;
 
        for (int i = 0; i < scores.size(); i++) {
            sum = sum + scores.get(i);
        }
 
        return (double) sum / scores.size();
    }
 
    public static int findMax(ArrayList<Integer> scores) {
        int max = scores.get(0);
 
        for (int i = 1; i < scores.size(); i++) {
            if (scores.get(i) > max) {
                max = scores.get(i);
            }
        }
 
        return max;
    }
 
    public static int findMin(ArrayList<Integer> scores) {
        int min = scores.get(0);
 
        for (int i = 1; i < scores.size(); i++) {
            if (scores.get(i) < min) {
                min = scores.get(i);
            }
        }
 
        return min;
    }
 
    public static ArrayList<Integer> getPassingList(ArrayList<Integer> scores) {
        ArrayList<Integer> passingList = new ArrayList<>();
 
        for (int i = 0; i < scores.size(); i++) {
            if (scores.get(i) >= 60) {
                passingList.add(scores.get(i));
            }
        }
 
        return passingList;
    }
}
 