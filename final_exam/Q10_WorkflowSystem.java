import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
 
class Q10_Task {
    private String id;
    private String title;
 
    public Q10_Task(String id, String title) {
        this.id = id;
        this.title = title;
    }
 
    public String getId() {
        return id;
    }
 
    public String getTitle() {
        return title;
    }
 
    @Override
    public String toString() {
        return id + " " + title;
    }
}
 
public class Q10_WorkflowSystem {
    private ArrayList<Q10_Task> allTasks = new ArrayList<>();
    private Deque<Q10_Task> waiting = new ArrayDeque<>();
    private Deque<Q10_Task> completed = new ArrayDeque<>();
 
    public static void main(String[] args) {
        Q10_WorkflowSystem system = new Q10_WorkflowSystem();
        system.addTask(new Q10_Task("T201", "Backup"));
        system.addTask(new Q10_Task("T105", "Update"));
        system.addTask(new Q10_Task("T330", "Report"));
 
        System.out.println("下一筆 : " + system.peekNext());
        System.out.println("完成 : " + system.processNext());
        System.out.println("完成 : " + system.processNext());
        System.out.println("復原 : " + system.undoLast());
        System.out.println("下一筆 : " + system.peekNext());
        System.out.println("搜尋 : " + system.findById("t330"));
        System.out.println("等待數 : " + system.waitingCount());
        System.out.println("完成數 : " + system.completedCount());
    }
 
    public boolean addTask(Q10_Task task) {
        if (task == null) {
            return false;
        }
 
        String id = task.getId();
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
 
        for (Q10_Task existing : allTasks) {
            if (existing.getId().equalsIgnoreCase(id)) {
                return false;
            }
        }
 
        allTasks.add(task);
        waiting.offerLast(task);
        return true;
    }
 
    public Q10_Task processNext() {
        Q10_Task task = waiting.pollFirst();
        if (task == null) {
            return null;
        }
        completed.push(task);
        return task;
    }
 
    public Q10_Task undoLast() {
        Q10_Task task = completed.pollFirst();
        if (task == null) {
            return null;
        }
        waiting.offerFirst(task);
        return task;
    }
 
    public Q10_Task peekNext() {
        return waiting.peekFirst();
    }
 
    public Q10_Task findById(String id) {
        for (Q10_Task task : allTasks) {
            if (task.getId().equalsIgnoreCase(id)) {
                return task;
            }
        }
        return null;
    }
 
    public int waitingCount() {
        return waiting.size();
    }
 
    public int completedCount() {
        return completed.size();
    }
}
