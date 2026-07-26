import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
import java.util.Stack;

public class DeliveryProcessingSystem {
    static Queue<DeliveryTask> waitingQueue = new LinkedList<>();
    static Stack<DeliveryTask> completedStack = new Stack<>();
    static int nextId = 1;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("");
            System.out.println("===== 配送工作流程系統 =====");
            System.out.println("1. 新增配送工作");
            System.out.println("2. 完成下一筆");
            System.out.println("3. 查看下一筆");
            System.out.println("4. 復原最近完成");
            System.out.println("5. 查看等待數與完成數");
            System.out.println("6. 查看所有處理紀錄");
            System.out.println("7. 結束程式");
            System.out.println("請選擇功能：");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                addTask(sc);
            } else if (choice.equals("2")) {
                completeNextTask();
            } else if (choice.equals("3")) {
                viewNextTask();
            } else if (choice.equals("4")) {
                undoLastCompleted();
            } else if (choice.equals("5")) {
                viewCounts();
            } else if (choice.equals("6")) {
                viewAllRecords();
            } else if (choice.equals("7")) {
                System.out.println("程式結束");
                break;
            } else {
                System.out.println("輸入錯誤，請重新選擇");
            }
        }

        sc.close();
    }

    public static void addTask(Scanner sc) {
        System.out.println("請輸入配送內容：");
        String description = sc.nextLine();

        if (description.equals("")) {
            System.out.println("配送內容不可為空");
            return;
        }

        DeliveryTask task = new DeliveryTask(nextId, description);
        waitingQueue.offer(task);
        System.out.println("新增成功，編號為：" + nextId);
        nextId++;
    }

    public static void completeNextTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有待配送的工作");
            return;
        }

        DeliveryTask task = waitingQueue.poll();
        completedStack.push(task);
        System.out.println("已完成：" + task.getId() + " 號，內容：" + task.getDescription());
    }

    public static void viewNextTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有待配送的工作");
            return;
        }

        DeliveryTask task = waitingQueue.peek();
        System.out.println("下一筆為：" + task.getId() + " 號，內容：" + task.getDescription());
    }

    public static void undoLastCompleted() {
        if (completedStack.isEmpty()) {
            System.out.println("目前沒有可復原的完成紀錄");
            return;
        }

        DeliveryTask task = completedStack.pop();
        waitingQueue.offer(task);
        System.out.println("已復原：" + task.getId() + " 號，內容：" + task.getDescription());
    }

    public static void viewCounts() {
        System.out.println("等待數：" + waitingQueue.size());
        System.out.println("完成數：" + completedStack.size());
    }

    public static void viewAllRecords() {
        if (completedStack.isEmpty()) {
            System.out.println("目前沒有處理紀錄");
            return;
        }

        System.out.println("===== 所有處理紀錄 =====");

        for (int i = 0; i < completedStack.size(); i++) {
            DeliveryTask task = completedStack.get(i);
            System.out.println(task.getId() + " 號，內容：" + task.getDescription());
        }
    }
}
