public class SubtotalCalculator {
    public static void main(String[] args) {
        int subtotal = calculateSubtotal(30, 3);
        System.out.println("Subtotal: " + subtotal);
    }

    public static int calculateSubtotal(int price, int quantity) {
        int subtotal = price * quantity;
        return subtotal;
    }
}
