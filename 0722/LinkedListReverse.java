public class LinkedListReverse {
  
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Node emptyList = null;
        System.out.println("空串列反轉：");
        emptyList = reverse(emptyList);
        printList(emptyList);

        Node singleNode = new Node(10);
        System.out.println("單一節點反轉：");
        singleNode = reverse(singleNode);
        printList(singleNode);

        Node multiNode = buildList();
        System.out.println("多節點反轉前：");
        printList(multiNode);
        multiNode = reverse(multiNode);
        System.out.println("多節點反轉後：");
        printList(multiNode);
    }

    public static Node buildList() {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);
        return head;
    }

    public static Node reverse(Node head) {
        Node previous = null;
        Node current = head;

        while (current != null) {
            Node nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous;
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
