import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class EventRegistrationSystem {

    private static final int CAPACITY = 3;

    private static ArrayList<Registration> allRegistrations = new ArrayList<Registration>();
    private static LinkedList<Registration> waitlistQueue = new LinkedList<Registration>();
    private static Stack<Registration> cancelStack = new Stack<Registration>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("========== 活動報名與候補系統（名額：" + CAPACITY + "） ==========");
            System.out.println("1. 新增報名");
            System.out.println("2. 取消報名");
            System.out.println("3. 復原最近取消紀錄");
            System.out.println("4. 依報名編號排序顯示所有紀錄");
            System.out.println("5. 依編號查詢");
            System.out.println("6. 依姓名查詢");
            System.out.println("7. 顯示狀態統計");
            System.out.println("8. 離開系統");
            System.out.print("請選擇功能：");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                addRegistration(sc);
            } else if (choice.equals("2")) {
                cancelRegistration(sc);
            } else if (choice.equals("3")) {
                restoreLastCancellation();
            } else if (choice.equals("4")) {
                showSortedById();
            } else if (choice.equals("5")) {
                searchById(sc);
            } else if (choice.equals("6")) {
                searchByName(sc);
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

    public static void addRegistration(Scanner sc) {
        System.out.print("請輸入報名編號：");
        String id = sc.nextLine();

        if (isDuplicateId(id)) {
            System.out.println("新增失敗：報名編號 " + id + " 已存在。");
            System.out.println();
            return;
        }

        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        int confirmedCount = countByStatus("已報名");

        if (confirmedCount < CAPACITY) {
            Registration reg = new Registration(id, name, "已報名");
            allRegistrations.add(reg);
            System.out.println("報名成功（已報名）：" + reg.toDisplayString());
        } else {
            Registration reg = new Registration(id, name, "候補中");
            allRegistrations.add(reg);
            waitlistQueue.offer(reg);
            System.out.println("名額已滿，加入候補：" + reg.toDisplayString());
        }
        System.out.println();
    }

    public static boolean isDuplicateId(String id) {
        for (int i = 0; i < allRegistrations.size(); i++) {
            if (allRegistrations.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static int countByStatus(String status) {
        int count = 0;
        for (int i = 0; i < allRegistrations.size(); i++) {
            if (allRegistrations.get(i).getStatus().equals(status)) {
                count++;
            }
        }
        return count;
    }

    public static void cancelRegistration(Scanner sc) {
        System.out.print("請輸入要取消的報名編號：");
        String id = sc.nextLine();

        Registration target = findActiveById(id);

        if (target == null) {
            System.out.println("取消失敗：查無編號 " + id + " 的有效報名資料。");
            System.out.println();
            return;
        }

        String originalStatus = target.getStatus();
        target.setPreCancelStatus(originalStatus);

        if (originalStatus.equals("候補中")) {
            waitlistQueue.remove(target);
        } else if (originalStatus.equals("已報名")) {
            if (!waitlistQueue.isEmpty()) {
                Registration promoted = waitlistQueue.poll();
                promoted.setStatus("已報名");
                System.out.println("候補遞補：" + promoted.toDisplayString());
            }
        }

        target.setStatus("已取消");
        cancelStack.push(target);

        System.out.println("已取消：" + target.toDisplayString());
        System.out.println();
    }

    public static Registration findActiveById(String id) {
        for (int i = 0; i < allRegistrations.size(); i++) {
            Registration reg = allRegistrations.get(i);
            if (reg.getId().equals(id) && !reg.getStatus().equals("已取消")) {
                return reg;
            }
        }
        return null;
    }

    public static void restoreLastCancellation() {
        if (cancelStack.isEmpty()) {
            System.out.println("目前沒有可復原的取消紀錄（Stack 為空）。");
            System.out.println();
            return;
        }

        Registration reg = cancelStack.pop();

        if (reg.getPreCancelStatus().equals("已報名")) {
            int confirmedCount = countByStatus("已報名");
            if (confirmedCount < CAPACITY) {
                reg.setStatus("已報名");
            } else {
                reg.setStatus("候補中");
                waitlistQueue.offer(reg);
            }
        } else {
            reg.setStatus("候補中");
            waitlistQueue.offer(reg);
        }

        System.out.println("已復原：" + reg.toDisplayString());
        System.out.println();
    }

    public static void showSortedById() {
        if (allRegistrations.isEmpty()) {
            System.out.println("目前尚無任何報名資料。");
            System.out.println();
            return;
        }

        Registration[] copy = allRegistrations.toArray(new Registration[0]);
        RegistrationAlgorithms.mergeSortById(copy, 0, copy.length - 1);

        System.out.println("========== 所有報名紀錄（依編號排序） ==========");
        for (int i = 0; i < copy.length; i++) {
            System.out.println(copy[i].toDisplayString());
        }
        System.out.println();
    }

    public static void searchById(Scanner sc) {
        if (allRegistrations.isEmpty()) {
            System.out.println("目前尚無任何報名資料。");
            System.out.println();
            return;
        }

        System.out.print("請輸入要查詢的編號：");
        String id = sc.nextLine();

        Registration[] sortedCopy = allRegistrations.toArray(new Registration[0]);
        RegistrationAlgorithms.mergeSortById(sortedCopy, 0, sortedCopy.length - 1);

        int index = RegistrationAlgorithms.binarySearchById(sortedCopy, id);
        if (index != -1) {
            System.out.println("查詢結果：" + sortedCopy[index].toDisplayString());
        } else {
            System.out.println("查無編號 " + id + " 的報名資料。");
        }
        System.out.println();
    }

    public static void searchByName(Scanner sc) {
        if (allRegistrations.isEmpty()) {
            System.out.println("目前尚無任何報名資料。");
            System.out.println();
            return;
        }

        System.out.print("請輸入要查詢的姓名：");
        String name = sc.nextLine();

        Registration result = RegistrationAlgorithms.sequentialSearchByName(allRegistrations, name);
        if (result != null) {
            System.out.println("查詢結果：" + result.toDisplayString());
        } else {
            System.out.println("查無姓名為 " + name + " 的報名資料。");
        }
        System.out.println();
    }

    public static void showStatistics() {
        System.out.println("========== 狀態統計 ==========");
        System.out.println("已報名人數：" + countByStatus("已報名") + "／" + CAPACITY);
        System.out.println("候補中人數：" + waitlistQueue.size());
        System.out.println("已取消人數：" + countByStatus("已取消"));
        System.out.println("全部紀錄數：" + allRegistrations.size());

        if (waitlistQueue.isEmpty()) {
            System.out.println("候補 Queue：目前為空");
        } else {
            System.out.print("候補 Queue（依候補順序）：");
            String queueContent = "";
            for (int i = 0; i < waitlistQueue.size(); i++) {
                queueContent = queueContent + waitlistQueue.get(i).getId();
                if (i < waitlistQueue.size() - 1) {
                    queueContent = queueContent + " -> ";
                }
            }
            System.out.println(queueContent);
        }
        System.out.println();
    }
}
