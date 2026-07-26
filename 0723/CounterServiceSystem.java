import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class CounterServiceSystem {
    static Queue<Customer> waitingQueue = new LinkedList<>();
    static List<Customer> servedList = new ArrayList<>();
    static int nextNumber = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("");
            System.out.println("===== 櫃台叫號系統 =====");
            System.out.println("1. 取號");
            System.out.println("2. 叫號");
            System.out.println("3. 查看下一位");
            System.out.println("4. 查看等待人數");
            System.out.println("5. 查看處理紀錄");
            System.out.println("6. 結束程式");
            System.out.println("請選擇功能：");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                takeNumber(sc);
            } else if (choice.equals("2")) {
                callNumber();
            } else if (choice.equals("3")) {
                viewNext();
            } else if (choice.equals("4")) {
                viewWaitingCount();
            } else if (choice.equals("5")) {
                viewServedRecords();
            } else if (choice.equals("6")) {
                System.out.println("程式結束");
                break;
            } else {
                System.out.println("輸入錯誤，請重新選擇");
            }
        }

        sc.close();
    }

    public static void takeNumber(Scanner sc) {
        System.out.println("請輸入姓名：");
        String name = sc.nextLine();

        if (name.equals("")) {
            System.out.println("姓名不可為空");
            return;
        }

        Customer customer = new Customer(nextNumber, name);
        waitingQueue.offer(customer);
        System.out.println("取號成功，號碼為：" + nextNumber);
        nextNumber++;
    }

    public static void callNumber() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的號碼");
            return;
        }

        Customer customer = waitingQueue.poll();
        servedList.add(customer);
        System.out.println("叫號：" + customer.number + " 號，姓名：" + customer.name);
    }

    public static void viewNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的號碼");
            return;
        }

        Customer customer = waitingQueue.peek();
        System.out.println("下一位為：" + customer.number + " 號，姓名：" + customer.name);
    }

    public static void viewWaitingCount() {
        System.out.println("目前等待人數：" + waitingQueue.size());
    }

    public static void viewServedRecords() {
        if (servedList.isEmpty()) {
            System.out.println("目前沒有處理紀錄");
            return;
        }

        System.out.println("===== 處理紀錄 =====");

        for (int i = 0; i < servedList.size(); i++) {
            Customer customer = servedList.get(i);
            System.out.println(customer.number + " 號，姓名：" + customer.name);
        }
    }

    static class Customer {
        int number;
        String name;

        Customer(int number, String name) {
            this.number = number;
            this.name = name;
        }
    }
}
