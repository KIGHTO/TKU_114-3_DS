import java.util.*;

public class WhileInputDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();

        while (number != 0) {
            System.out.println(number);
            number = sc.nextInt();
        }

        System.out.println("End");
        sc.close();
    }
}

