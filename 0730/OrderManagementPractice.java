import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.*;
import java.util.Stack;

public class OrderManagementPractice {

    private static ArrayList<Order> allOrders = new ArrayList<Order>();
    private static Queue<Order> waitingQueue = new LinkedList<Order>();
    private static Stack<Order> completedStack = new Stack<Order>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("========== 訂單管理系統 ==========");
            System.out.println("1. 新增訂單");
            System.out.println("2. 處理下一筆待處理訂單");
            System.out.println("3. 顯示下一筆待處理訂單");
            System.out.println("4. 依金額降冪顯示所有訂單");
            System.out.println("5. 依顧客姓名搜尋全部訂單");
            System.out.println("6. 顯示已完成訂單堆疊內容");
            System.out.println("7. 離開系統");
            System.out.print("請選擇功能：");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                addOrder(sc);
            } else if (choice.equals("2")) {
                processNextOrder();
            } else if (choice.equals("3")) {
                peekNextOrder();
            } else if (choice.equals("4")) {
                showOrdersByAmountDescending();
            } else if (choice.equals("5")) {
                searchByCustomerName(sc);
            } else if (choice.equals("6")) {
                showCompletedStack();
            } else if (choice.equals("7")) {
                System.out.println("系統已結束。");
                break;
            } else {
                System.out.println("輸入錯誤，請重新輸入。");
            }
        }

        sc.close();
    }

    public static void addOrder(Scanner sc) {
        System.out.print("請輸入訂單編號：");
        String orderId = sc.nextLine();

        if (isDuplicateId(orderId)) {
            System.out.println("新增失敗：訂單編號 " + orderId + " 已存在。");
            System.out.println();
            return;
        }

        System.out.print("請輸入顧客姓名：");
        String customerName = sc.nextLine();
        System.out.print("請輸入金額：");
        double amount = Double.parseDouble(sc.nextLine());

        Order newOrder = new Order(orderId, customerName, amount);
        allOrders.add(newOrder);
        waitingQueue.offer(newOrder);

        System.out.println("新增成功：" + newOrder.toDisplayString());
        System.out.println();
    }

    public static boolean isDuplicateId(String orderId) {
        for (int i = 0; i < allOrders.size(); i++) {
            if (allOrders.get(i).getOrderId().equals(orderId)) {
                return true;
            }
        }
        return false;
    }

    public static void processNextOrder() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有待處理訂單（Queue 為空）。");
            System.out.println();
            return;
        }

        Order order = waitingQueue.poll();
        order.setStatus("已完成");
        completedStack.push(order);

        System.out.println("已處理：" + order.toDisplayString());
        System.out.println();
    }

    public static void peekNextOrder() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有待處理訂單（Queue 為空）。");
        } else {
            System.out.println("下一筆待處理訂單：" + waitingQueue.peek().toDisplayString());
        }
        System.out.println();
    }

    public static void showOrdersByAmountDescending() {
        if (allOrders.isEmpty()) {
            System.out.println("目前尚無任何訂單資料。");
            System.out.println();
            return;
        }

        Order[] copy = allOrders.toArray(new Order[0]);
        OrderAlgorithms.mergeSortByAmountDescending(copy, 0, copy.length - 1);

        System.out.println("========== 所有訂單（依金額降冪） ==========");
        for (int i = 0; i < copy.length; i++) {
            System.out.println(copy[i].toDisplayString());
        }
        System.out.println();
    }

    public static void searchByCustomerName(Scanner sc) {
        System.out.print("請輸入要搜尋的顧客姓名：");
        String name = sc.nextLine();

        java.util.List<Order> matches = OrderAlgorithms.searchByCustomerName(allOrders, name);

        if (matches.isEmpty()) {
            System.out.println("查無顧客 " + name + " 的訂單資料。");
        } else {
            System.out.println("========== 顧客 " + name + " 的訂單 ==========");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println(matches.get(i).toDisplayString());
            }
        }
        System.out.println();
    }

    public static void showCompletedStack() {
        if (completedStack.isEmpty()) {
            System.out.println("目前沒有已完成訂單（Stack 為空）。");
            System.out.println();
            return;
        }

        System.out.println("========== 已完成訂單（由最近完成排到最早完成） ==========");
        for (int i = completedStack.size() - 1; i >= 0; i--) {
            System.out.println(completedStack.get(i).toDisplayString());
        }
        System.out.println();
    }
}
