import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Equipment> equipmentList = new ArrayList<>();

        while (true) {
            System.out.println("");
            System.out.println("1. 新增設備");
            System.out.println("2. 借用設備");
            System.out.println("3. 歸還設備");
            System.out.println("4. 列出可借用設備");
            System.out.println("5. 列出全部設備");
            System.out.println("0. 結束程式");
            System.out.println("請選擇功能:");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                addEquipment(sc, equipmentList);
            } else if (choice.equals("2")) {
                borrowEquipment(sc, equipmentList);
            } else if (choice.equals("3")) {
                returnEquipment(sc, equipmentList);
            } else if (choice.equals("4")) {
                listAvailable(equipmentList);
            } else if (choice.equals("5")) {
                listAll(equipmentList);
            } else if (choice.equals("0")) {
                System.out.println("程式結束。");
                break;
            } else {
                System.out.println("選項錯誤,請重新輸入。");
            }
        }
    }

    public static void addEquipment(Scanner sc, ArrayList<Equipment> equipmentList) {
        System.out.println("請輸入設備代碼:");
        String code = sc.nextLine();

        if (findByCode(equipmentList, code) != null) {
            System.out.println("代碼重複,新增失敗。");
            return;
        }

        System.out.println("請輸入設備名稱:");
        String name = sc.nextLine();

        equipmentList.add(new Equipment(code, name));
        System.out.println("新增成功。");
    }

    public static void borrowEquipment(Scanner sc, ArrayList<Equipment> equipmentList) {
        System.out.println("請輸入要借用的設備代碼:");
        String code = sc.nextLine();
        Equipment equipment = findByCode(equipmentList, code);

        if (equipment == null) {
            System.out.println("找不到這個設備。");
            return;
        }

        if (equipment.isBorrowed()) {
            System.out.println("這個設備已經被借出,借用失敗。");
            return;
        }

        equipment.setBorrowed(true);
        System.out.println("借用成功。");
    }

    public static void returnEquipment(Scanner sc, ArrayList<Equipment> equipmentList) {
        System.out.println("請輸入要歸還的設備代碼:");
        String code = sc.nextLine();
        Equipment equipment = findByCode(equipmentList, code);

        if (equipment == null) {
            System.out.println("找不到這個設備。");
            return;
        }

        if (!equipment.isBorrowed()) {
            System.out.println("這個設備目前沒有被借出。");
            return;
        }

        equipment.setBorrowed(false);
        System.out.println("歸還成功。");
    }

    public static void listAvailable(ArrayList<Equipment> equipmentList) {
        boolean found = false;

        for (int i = 0; i < equipmentList.size(); i++) {
            if (!equipmentList.get(i).isBorrowed()) {
                System.out.println(equipmentList.get(i));
                found = true;
            }
        }

        if (!found) {
            System.out.println("目前沒有可借用的設備。");
        }
    }

    public static void listAll(ArrayList<Equipment> equipmentList) {
        if (equipmentList.size() == 0) {
            System.out.println("目前沒有任何設備。");
            return;
        }

        for (int i = 0; i < equipmentList.size(); i++) {
            System.out.println(equipmentList.get(i));
        }
    }

    public static Equipment findByCode(ArrayList<Equipment> equipmentList, String code) {
        for (int i = 0; i < equipmentList.size(); i++) {
            if (equipmentList.get(i).getCode().equalsIgnoreCase(code)) {
                return equipmentList.get(i);
            }
        }

        return null;
    }
}
