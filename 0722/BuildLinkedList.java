public class BuildLinkedList {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(10);
        head.next = new Node(20);
        head.next.next = new Node(30);
        head.next.next.next = new Node(40);

        System.out.println("鏈結串列內容：");
        printList(head);

        int nodeCount = countNodes(head);
        int totalSum = sumNodes(head);

        System.out.println("節點數：" + nodeCount);
        System.out.println("總和：" + totalSum);
    }

    public static void printList(Node head) {
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

    public static int countNodes(Node head) {
        int count = 0;
        Node current = head;
        while (current != null) {
            count = count + 1;
            current = current.next;
        }
        return count;
    }

    public static int sumNodes(Node head) {
        int sum = 0;
        Node current = head;
        while (current != null) {
            sum = sum + current.data;
            current = current.next;
        }
        return sum;
    }
}
