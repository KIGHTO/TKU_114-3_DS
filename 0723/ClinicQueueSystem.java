import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class ClinicQueueSystem {
    static Queue<Patient> waitingQueue = new LinkedList<>();
    static List<Patient> servedList = new ArrayList<>();
    static Set<Integer> usedNumbers = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("");
            System.out.println("===== 診所叫號系統 =====");
            System.out.println("1. 掛號");
            System.out.println("2. 叫號");
            System.out.println("3. 查看下一位");
            System.out.println("4. 查看等待清單");
            System.out.println("5. 查看各科別等待人數與總服務人數");
            System.out.println("6. 結束程式");
            System.out.println("請選擇功能：");
            String choice = sc.nextLine();

            if (choice.equals("1")) {
                registerPatient(sc);
            } else if (choice.equals("2")) {
                callPatient();
            } else if (choice.equals("3")) {
                viewNext();
            } else if (choice.equals("4")) {
                viewWaitingList();
            } else if (choice.equals("5")) {
                viewDepartmentSummary();
            } else if (choice.equals("6")) {
                System.out.println("程式結束");
                break;
            } else {
                System.out.println("輸入錯誤，請重新選擇");
            }
        }

        sc.close();
    }

    public static void registerPatient(Scanner sc) {
        System.out.println("請輸入號碼：");
        String numberInput = sc.nextLine();
        int number;

        try {
            number = Integer.parseInt(numberInput);
        } catch (NumberFormatException e) {
            System.out.println("號碼格式錯誤，請輸入數字");
            return;
        }

        if (usedNumbers.contains(number)) {
            System.out.println("號碼重複，請重新輸入");
            return;
        }

        System.out.println("請輸入姓名：");
        String name = sc.nextLine();

        System.out.println("請輸入科別：");
        String department = sc.nextLine();

        Patient patient = new Patient(number, name, department);
        waitingQueue.offer(patient);
        usedNumbers.add(number);
        System.out.println("掛號成功，號碼為：" + number);
    }

    public static void callPatient() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的病患");
            return;
        }

        Patient patient = waitingQueue.poll();
        servedList.add(patient);
        System.out.println("叫號：" + patient.getNumber() + " 號，姓名：" + patient.getName() + "，科別：" + patient.getDepartment());
    }

    public static void viewNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的病患");
            return;
        }

        Patient patient = waitingQueue.peek();
        System.out.println("下一位為：" + patient.getNumber() + " 號，姓名：" + patient.getName() + "，科別：" + patient.getDepartment());
    }

    public static void viewWaitingList() {
        if (waitingQueue.isEmpty()) {
            System.out.println("目前沒有等待中的病患");
            return;
        }

        System.out.println("===== 等待清單 =====");

        for (Patient patient : waitingQueue) {
            System.out.println(patient.getNumber() + " 號，姓名：" + patient.getName() + "，科別：" + patient.getDepartment());
        }
    }

    public static void viewDepartmentSummary() {
        System.out.println("===== 各科別等待人數 =====");

        List<String> departmentList = new ArrayList<>();

        for (Patient patient : waitingQueue) {
            String department = patient.getDepartment();

            if (!departmentList.contains(department)) {
                departmentList.add(department);
            }
        }

        for (int i = 0; i < departmentList.size(); i++) {
            String department = departmentList.get(i);
            int count = 0;

            for (Patient patient : waitingQueue) {
                if (patient.getDepartment().equals(department)) {
                    count++;
                }
            }

            System.out.println(department + "：" + count + " 人等待");
        }

        System.out.println("總服務人數：" + servedList.size());
    }
}
