public class PricePrinter {
    public static void main(String[] args) {
        printItem("Black tea", 30);
        printItem("Green tea", 35);
        printItem("Coffee", 50);
    }

    public static void printItem(String itemName, int price) {
        System.out.println(itemName + ": " + price);
    }
}
