import java.util.Scanner;

public class EmployeeSearchSystem {
    
    public static int binarySearchById(Employee[] employees, int targetId) {
        int low = 0;
        int high = employees.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (employees[mid].getId() == targetId) {
                return mid;
            } else if (employees[mid].getId() < targetId) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static boolean hasDuplicateId(Employee[] employees) {
        for (int i = 0; i < employees.length - 1; i++) {
            if (employees[i].getId() == employees[i + 1].getId()) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Employee[] employees = {
            new Employee(1001, "王小明", "研發部", "1234"),
            new Employee(1005, "林美玲", "行銷部", "1235"),
            new Employee(1012, "陳志豪", "財務部", "1236"),
            new Employee(1020, "張雅婷", "人資部", "1237"),
            new Employee(1033, "李建國", "研發部", "1238"),
            new Employee(1041, "黃淑芬", "業務部", "1239"),
            new Employee(1058, "吳俊傑", "客服部", "1240"),
            new Employee(1067, "劉家豪", "研發部", "1241")
        };

        Employee[] emptyEmployees = {};

        Employee[] duplicateEmployees = {
            new Employee(2001, "測試甲", "測試部", "2000"),
            new Employee(2001, "測試乙", "測試部", "2001"),
            new Employee(2010, "測試丙", "測試部", "2002")
        };

        if (hasDuplicateId(employees)) {
            System.out.println("警告：員工編號陣列中有重複編號，搜尋結果可能不是唯一對應");
        }

        System.out.println("空陣列查詢測試：");
        int emptyResult = binarySearchById(emptyEmployees, 1001);
        if (emptyResult == -1) {
            System.out.println("陣列為空，找不到任何員工資料");
        }

        System.out.println("");
        System.out.println("重複編號陣列查詢測試：");
        if (hasDuplicateId(duplicateEmployees)) {
            System.out.println("警告：此陣列含有重複編號 2001");
        }
        int duplicateResult = binarySearchById(duplicateEmployees, 2001);
        if (duplicateResult != -1) {
            System.out.println(duplicateEmployees[duplicateResult].toString());
        }

        while (true) {
            System.out.println("");
            System.out.println("請輸入要查詢的員工編號（輸入 -1 結束）：");
            String input = sc.nextLine();
            int targetId = Integer.parseInt(input);

            if (targetId == -1) {
                System.out.println("程式結束");
                break;
            }

            int index = binarySearchById(employees, targetId);

            if (index == -1) {
                System.out.println("找不到編號 " + targetId + " 的員工");
            } else {
                System.out.println(employees[index].toString());
            }
        }

        sc.close();
    }
}
