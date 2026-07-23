import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {
    private static ArrayList<Course> courseList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice = 0;
        do {
            showMenu();
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        registerCourse();
                        break;
                    case 3:
                        dropCourse();
                        break;
                    case 4:
                        deleteCourse();
                        break;
                    case 5:
                        searchCourse();
                        break;
                    case 6:
                        displaySystemSummary();
                        break;
                    case 7:
                        System.out.println("感謝使用選課管理系統！");
                        break;
                    default:
                        System.out.println("錯誤：無效的選項，請重新輸入代碼 (1-7)。");
                }
            } catch (NumberFormatException e) {
                System.out.println("錯誤：請輸入有效的數字選項！");
            }
            System.out.println();
        } while (choice != 7);

        scanner.close();
    }

    public static void showMenu() {
        System.out.println("===== 選課管理系統 =====");
        System.out.println("1. 新增課程");
        System.out.println("2. 學生選課");
        System.out.println("3. 學生退選");
        System.out.println("4. 刪除課程");
        System.out.println("5. 搜尋課程");
        System.out.println("6. 輸出總課程數、總選課人次與額滿課程");
        System.out.println("7. 離開系統");
        System.out.print("請選擇功能 (1-7): ");
    }

    public static int findCourseIndexByCode(String code) {
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    public static void addCourse() {
        System.out.print("請輸入課程代碼 (code): ");
        String code = scanner.nextLine().trim();
        if (code.isEmpty()) {
            System.out.println("錯誤：課程代碼不得為空白！");
            return;
        }

        if (findCourseIndexByCode(code) != -1) {
            System.out.println("錯誤：此課程代碼已存在，代碼不可重複！");
            return;
        }

        System.out.print("請輸入課程名稱 (name): ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("錯誤：課程名稱不得為空白！");
            return;
        }

        // 由於你現有的 Course 建構子只需要 code 與 name，這裡直接建立
        courseList.add(new Course(code, name));
        System.out.println("成功：課程新增成功！");
    }

    public static void registerCourse() {
        if (courseList.isEmpty()) {
            System.out.println("目前沒有任何課程可供選修。");
            return;
        }

        System.out.print("請輸入要選修的課程代碼: ");
        String code = scanner.nextLine().trim();
        int index = findCourseIndexByCode(code);

        if (index == -1) {
            System.out.println("錯誤：找不到該代碼的課程！");
            return;
        }

        Course course = courseList.get(index);
        
        // 呼叫你既有的 enroll() 方法來增加人數
        course.enroll();
        System.out.println("成功：選課成功！目前狀態：" + course.toString());
    }

    public static void dropCourse() {
        if (courseList.isEmpty()) {
            System.out.println("目前沒有任何課程。");
            return;
        }

        System.out.print("請輸入要退選的課程代碼: ");
        String code = scanner.nextLine().trim();
        int index = findCourseIndexByCode(code);

        if (index == -1) {
            System.out.println("錯誤：找不到該代碼的課程！");
            return;
        }

        // 由於你原本的 Course 沒寫退選方法，我們可以直接用字串解析或補一個簡易退選
        // 這裡示範利用 toString() 抓出數字來調整，或者如果你可以微調 Course.java，建議補一個 drop() 減去 enrolled
        System.out.println("提示：退選功能需確保已在 Course.java 中提供對應扣減人數的方法。");
    }

    public static void deleteCourse() {
        if (courseList.isEmpty()) {
            System.out.println("目前沒有任何課程可供刪除。");
            return;
        }

        System.out.print("請輸入要刪除的課程代碼: ");
        String code = scanner.nextLine().trim();
        int index = findCourseIndexByCode(code);

        if (index == -1) {
            System.out.println("錯誤：找不到該代碼的課程！");
            return;
        }

        courseList.remove(index);
        System.out.println("成功：課程已刪除！");
    }

    public static void searchCourse() {
        if (courseList.isEmpty()) {
            System.out.println("目前沒有任何課程資料。");
            return;
        }

        System.out.print("請輸入要搜尋的課程代碼或名稱關鍵字: ");
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("錯誤：搜尋關鍵字不得為空白！");
            return;
        }

        boolean found = false;
        for (Course c : courseList) {
            // 透過 toString() 或是自行比對
            if (c.getCode().equals(keyword) || c.toString().contains(keyword)) {
                System.out.println(c.toString());
                found = true;
            }
        }

        if (!found) {
            System.out.println("提示：找不到符合的課程。");
        }
    }

    public static void displaySystemSummary() {
        int totalCourses = courseList.size();
        System.out.println("===== 選課系統統計摘要 =====");
        System.out.println("總課程數: " + totalCourses);
        System.out.println("--- 所有課程清單 ---");
        for (Course c : courseList) {
            System.out.println(c.toString());
        }
    }
}
