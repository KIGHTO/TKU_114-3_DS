public class PassCounter {
    public static void main(String[] args) {
        int s1 = 80;
        int s2 = 55;
        int s3 = 70;

        int count = 0;

        if (s1>=60) {
            count++;
        }
        if (s2>=60) {
            count++;
        }
        if (s3>=60) {
            count++;
        }

        System.out.println("Pass count: " + count);
    }
}
