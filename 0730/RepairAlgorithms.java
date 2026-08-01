import java.util.ArrayList;
import java.util.List;

public class RepairAlgorithms {

    public static void mergeSortByPriorityDescending(RepairTask[] tasks, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSortByPriorityDescending(tasks, left, mid);
        mergeSortByPriorityDescending(tasks, mid + 1, right);
        merge(tasks, left, mid, right);
    }

    private static void merge(RepairTask[] tasks, int left, int mid, int right) {
        RepairTask[] temp = new RepairTask[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (tasks[i].getPriorityLevel() >= tasks[j].getPriorityLevel()) {
                temp[k] = tasks[i];
                i++;
            } else {
                temp[k] = tasks[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = tasks[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = tasks[j];
            j++;
            k++;
        }

        for (int m = 0; m < temp.length; m++) {
            tasks[left + m] = temp[m];
        }
    }

    public static RepairTask searchById(List<RepairTask> tasks, String id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId().equals(id)) {
                return tasks.get(i);
            }
        }
        return null;
    }

    public static List<RepairTask> searchByEquipmentName(List<RepairTask> tasks, String equipmentName) {
        List<RepairTask> matches = new ArrayList<RepairTask>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getEquipmentName().equals(equipmentName)) {
                matches.add(tasks.get(i));
            }
        }
        return matches;
    }
}
