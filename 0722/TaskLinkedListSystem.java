public class TaskLinkedListSystem {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("新增一般工作：寫報告");
        taskList.addNormal(201, "寫報告");
        taskList.printAllTasks();

        System.out.println("新增一般工作：開會");
        taskList.addNormal(202, "開會");
        taskList.printAllTasks();

        System.out.println("新增緊急工作：修 Bug");
        taskList.addUrgent(203, "修 Bug");
        taskList.printAllTasks();

        System.out.println("完成工作 202");
        taskList.completeTask(202);
        taskList.printAllTasks();

        boolean completedNotFound = taskList.completeTask(999);
        System.out.println("完成不存在的工作 999：" + completedNotFound);

        System.out.println("列出未完成工作：");
        taskList.printUnfinishedTasks();

        System.out.println("刪除工作 203");
        taskList.removeTask(203);
        taskList.printAllTasks();

        boolean removedNotFound = taskList.removeTask(999);
        System.out.println("刪除不存在的工作 999：" + removedNotFound);

        System.out.println("工作總數：" + taskList.getSize());
        System.out.println("未完成數量：" + taskList.getUnfinishedCount());
    }
}
