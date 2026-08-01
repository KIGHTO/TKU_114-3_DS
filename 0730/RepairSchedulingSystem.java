import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.*;
import java.util.Stack;
import java.util.ArrayList;

public class RepairSchedulingSystem {

    private static ArrayList<RepairTask> allTasks = new ArrayList<RepairTask>();
    private static Queue<RepairTask> waitingQueue = new LinkedList<RepairTask>();
    private static Stack<RepairTask> completedStack = new Stack<RepairTask>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("========== 維修工作排程系統 ==========");
            System.out.println("1. 新增維修工作");
            System.out.println("2. 處理下一筆等待中工作");
            System.out.println("3. 復原最後完成的工作");
            System.out.println("4. 依優先等級降冪顯示所有工作");
            System.out.println("5. 依編號搜尋工作");
            System.out.println("6. 依設備名稱搜尋全部工作");
            System.out.println("7. 顯示工作統計");
            System.out.println("8. 離開系統");
            System.out.print("請選擇功能：");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                addTask(sc);
            } else if (choice.equals("2")) {
                processNextTask();
            } else if (choice.equals("3")) {
                restoreLastCompleted();
            } else if (choice.equals("4")) {
                showSortedByPriority();
            } else if (choice.equals("5")) {
                searchById(sc);
            } else if (choice.equals("6")) {
                searchByEquipmentName(sc);
            } else if (choice.equals("7")) {
                showStatistics();
            } else if (choice.equals("8")) {
                System.out.println("系統已結束。");
                break;
            } else {
                System.out.println("輸入錯誤，請重新輸入。");
            }
        }

        sc.close();
    }

    public static void addTask(Scanner sc) {
        System.out.print("請輸入工作編號：");
        String id = sc.nextLine();
        System.out.print("請輸入設備名稱：");
        String equipmentName = sc.nextLine();
        System.out.print("請輸入優先等級（數字越大越優先）：");
        int priorityLevel = Integer.parseInt(sc.nextLine());

        RepairTask newTask = new RepairTask(id, equipmentName, priorityLevel);
        allTasks.add(newTask);
        waitingQueue.offer(newTask);

        System.out.println("新增成功：" + newTask.toDisplayString());
        System.out.println();
    }

    public static void processNextTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的工作（Queue 為空）。");
            System.out.println();
            return;
        }

        RepairTask task = waitingQueue.poll();
        task.setStatus("已完成");
        completedStack.push(task);

        System.out.println("已完成：" + task.toDisplayString());
        System.out.println();
    }

    public static void restoreLastCompleted() {
        if (completedStack.isEmpty()) {
            System.out.println("目前沒有已完成工作可復原（Stack 為空）。");
            System.out.println();
            return;
        }

        RepairTask task = completedStack.pop();
        task.setStatus("等待中");
        waitingQueue.offer(task);

        System.out.println("已復原：" + task.toDisplayString());
        System.out.println();
    }

    public static void showSortedByPriority() {
        if (allTasks.isEmpty()) {
            System.out.println("目前尚無任何工作資料。");
            System.out.println();
            return;
        }

        RepairTask[] copy = allTasks.toArray(new RepairTask[0]);
        RepairAlgorithms.mergeSortByPriorityDescending(copy, 0, copy.length - 1);

        System.out.println("========== 所有工作（依優先等級降冪，相同等級依登記順序） ==========");
        for (int i = 0; i < copy.length; i++) {
            System.out.println(copy[i].toDisplayString());
        }
        System.out.println();
    }

    public static void searchById(Scanner sc) {
        if (allTasks.isEmpty()) {
            System.out.println("目前尚無任何工作資料。");
            System.out.println();
            return;
        }

        System.out.print("請輸入要搜尋的工作編號：");
        String id = sc.nextLine();

        RepairTask result = RepairAlgorithms.searchById(allTasks, id);
        if (result != null) {
            System.out.println("查詢結果：" + result.toDisplayString());
        } else {
            System.out.println("查無編號 " + id + " 的工作。");
        }
        System.out.println();
    }

    public static void searchByEquipmentName(Scanner sc) {
        if (allTasks.isEmpty()) {
            System.out.println("目前尚無任何工作資料。");
            System.out.println();
            return;
        }

        System.out.print("請輸入要搜尋的設備名稱：");
        String equipmentName = sc.nextLine();

        List<RepairTask> matches = RepairAlgorithms.searchByEquipmentName(allTasks, equipmentName);
        if (matches.isEmpty()) {
            System.out.println("查無設備「" + equipmentName + "」的工作。");
        } else {
            System.out.println("========== 設備「" + equipmentName + "」的工作 ==========");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println(matches.get(i).toDisplayString());
            }
        }
        System.out.println();
    }

    public static void showStatistics() {
        System.out.println("========== 工作統計 ==========");
        System.out.println("等待中工作數：" + waitingQueue.size());
        System.out.println("已完成工作數：" + completedStack.size());
        System.out.println("全部工作數：" + allTasks.size());
        System.out.println();
    }
}
