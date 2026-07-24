public class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        head = null;
        size = 0;
    }

    public void addUrgent(int taskCode, String description) {
        TaskNode newNode = new TaskNode(taskCode, description);
        newNode.next = head;
        head = newNode;
        size = size + 1;
    }

    public void addNormal(int taskCode, String description) {
        TaskNode newNode = new TaskNode(taskCode, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size = size + 1;
    }

    public boolean completeTask(int taskCode) {
        TaskNode current = head;
        while (current != null) {
            if (current.taskCode == taskCode) {
                current.isCompleted = true;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean removeTask(int taskCode) {
        if (head == null) {
            return false;
        }

        if (head.taskCode == taskCode) {
            head = head.next;
            size = size - 1;
            return true;
        }

        TaskNode current = head;
        while (current.next != null) {
            if (current.next.taskCode == taskCode) {
                current.next = current.next.next;
                size = size - 1;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public void printAllTasks() {
        if (head == null) {
            System.out.println("工作項目列表是空的");
            return;
        }

        TaskNode current = head;
        while (current != null) {
            String status = "";
            if (current.isCompleted) {
                status = "已完成";
            } else {
                status = "未完成";
            }
            System.out.println("[" + current.taskCode + "] " + current.description + "（" + status + "）");
            current = current.next;
        }
    }

    public void printUnfinishedTasks() {
        if (head == null) {
            System.out.println("工作項目列表是空的");
            return;
        }

        boolean hasUnfinished = false;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted) {
                System.out.println("[" + current.taskCode + "] " + current.description);
                hasUnfinished = true;
            }
            current = current.next;
        }

        if (!hasUnfinished) {
            System.out.println("所有工作都已完成");
        }
    }

    public int getSize() {
        return size;
    }

    public int getUnfinishedCount() {
        int count = 0;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted) {
                count = count + 1;
            }
            current = current.next;
        }
        return count;
    }
}
