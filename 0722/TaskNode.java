public class TaskNode {
    int taskCode;
    String description;
    boolean isCompleted;
    TaskNode next;
 
    public TaskNode(int taskCode, String description) {
        this.taskCode = taskCode;
        this.description = description;
        this.isCompleted = false;
        this.next = null;
    }
}
 