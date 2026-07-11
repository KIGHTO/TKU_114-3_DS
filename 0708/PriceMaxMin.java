public class PriceMaxMin {
    public static void main(String[] args) {
        int p1 = 30;
        int p2 = 45;
        int p3 = 60;

        int max = p1;
        int min = p1;

        if (p2 > max) {
            max = p2;
        }
        if (p3 > max) {
            max = p3;
        }

        if (p2 < min) {
            min = p2;
        }
        if (p3 < min) {
            min = p3;
        }

        System.out.println("Max price: " + max);
        System.out.println("Min price: " + min);
    }
}
