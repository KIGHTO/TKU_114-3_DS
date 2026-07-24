public class LinkedListSearchRemove {
    
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public static void main(String[] args) {
        Node head = buildList();
        System.out.println("原始串列：");
        printList(head);

        System.out.println("contains(10)：" + contains(head, 10));
        System.out.println("contains(99)：" + contains(head, 99));

        System.out.println("刪除 head（10）：");
        head = removeValue(head, 10);
        printList(head);

        System.out.println("刪除中間（30）：");
        head = removeValue(head, 30);
        printList(head);

        System.out.println("刪除最後（40）：");
        head = removeValue(head, 40);
        printList(head);

        System.out.println("刪除找不到的值（99）：");
        head = removeValue(head, 99);
        printList(head);
    }

    public static Node buildList() {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        return head;
    }

    public static boolean contains(Node head, int value) {
        Node current = head;
        while (current != null) {
            if (current.data == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public static Node removeValue(Node head, int value) {
        if (head == null) {
            return null;
        }

        if (head.data == value) {
            return head.next;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.data == value) {
                current.next = current.next.next;
                return head;
            }
            current = current.next;
        }

        return head;
    }

    public static void printList(Node head) {
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
}
