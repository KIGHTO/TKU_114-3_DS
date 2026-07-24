public class NumberHistoryList {
    private static class Node {
        int data;
        Node next;
 
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
 
    private Node head;
    private int size;
 
    public NumberHistoryList() {
        head = null;
        size = 0;
    }
 
    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size = size + 1;
    }
 
    public void addLast(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size = size + 1;
    }
 
    public boolean search(int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }
 
    public boolean removeValue(int value) {
        if (head == null) {
            return false;
        }
 
        if (head.data == value) {
            head = head.next;
            size = size - 1;
            return true;
        }
 
        Node current = head;
        while (current.next != null) {
            if (current.next.data == value) {
                current.next = current.next.next;
                size = size - 1;
                return true;
            }
            current = current.next;
        }
 
        return false;
    }
 
    public void printList() {
        if (head == null) {
            System.out.println("空串列");
            return;
        }
 
        Node current = head;
        String result = "";
        while (current != null) {
            result = result + current.data;
            if (current.next != null) {
                result = result + " -> ";
            }
            current = current.next;
        }
        System.out.println(result);
    }
 
    public int getSize() {
        return size;
    }
 
    public void printStatistics() {
        if (head == null) {
            System.out.println("空串列，沒有統計資料");
            return;
        }
 
        int sum = 0;
        int max = head.data;
        int min = head.data;
        Node current = head;
        while (current != null) {
            sum = sum + current.data;
            if (current.data > max) {
                max = current.data;
            }
            if (current.data < min) {
                min = current.data;
            }
            current = current.next;
        }
 
        System.out.println("size：" + size);
        System.out.println("總和：" + sum);
        System.out.println("最大值：" + max);
        System.out.println("最小值：" + min);
    }
 
    public static void main(String[] args) {
        NumberHistoryList list = new NumberHistoryList();
 
        System.out.println("測試 1：addLast(10)");
        list.addLast(10);
        list.printList();
 
        System.out.println("測試 2：addLast(20)");
        list.addLast(20);
        list.printList();
 
        System.out.println("測試 3：addFirst(5)");
        list.addFirst(5);
        list.printList();
 
        System.out.println("測試 4：addLast(30)");
        list.addLast(30);
        list.printList();
 
        System.out.println("測試 5：search(20)：" + list.search(20));
 
        System.out.println("測試 6：search(99)：" + list.search(99));
 
        System.out.println("測試 7：removeValue(5)");
        list.removeValue(5);
        list.printList();
 
        System.out.println("測試 8：removeValue(99)");
        boolean removed = list.removeValue(99);
        list.printList();
        System.out.println("刪除結果：" + removed);
 
        System.out.println("統計資訊：");
        list.printStatistics();
 
        System.out.println("測試空串列統計：");
        NumberHistoryList emptyList = new NumberHistoryList();
        emptyList.printStatistics();
    }
}
 