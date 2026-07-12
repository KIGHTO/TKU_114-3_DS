import java.util.*;

public class PersonalProfileApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        System.out.print("請輸入姓名: ");
        String name = sc.next();
 
        int age = readPositiveInt(sc, "請輸入年齡: ");
        double height = readPositiveDouble(sc, "請輸入身高（公尺）: ");
        double weight = readPositiveDouble(sc, "請輸入體重（公斤）: ");
 
        boolean adult = isAdult(age);
        double bmi = calculateBmi(height, weight);
        String level = getBmiLevel(bmi);
 
        printReport(name, age, adult, height, weight, bmi, level);
 
        sc.close();
    }
 
    public static int readPositiveInt(Scanner sc, String message) {
        int value = 0;
        boolean valid = false;
 
        while (!valid) {
            System.out.print(message);
            value = sc.nextInt();
 
            if (value > 0) {
                valid = true;
            } else {
                System.out.println("數值必須大於 0，請重新輸入。");
            }
        }
 
        return value;
    }
 
    public static double readPositiveDouble(Scanner sc, String message) {
        double value = 0;
        boolean valid = false;
 
        while (!valid) {
            System.out.print(message);
            value = sc.nextDouble();
 
            if (value > 0) {
                valid = true;
            } else {
                System.out.println("數值必須大於 0，請重新輸入。");
            }
        }
 
        return value;
    }
 
    public static double calculateBmi(double height, double weight) {
        double bmi = weight / (height * height);
        return bmi;
    }
 
    public static String getBmiLevel(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24) {
            return "Normal";
        } else if (bmi < 27) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
 
    public static boolean isAdult(int age) {
        if (age >= 18) {
            return true;
        } else {
            return false;
        }
    }
 
    public static void printReport(String name, int age, boolean adult, double height, double weight, double bmi, String level) {
        System.out.println();
        System.out.println("=== Personal Health Report ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Adult: " + adult);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("BMI: " + bmi);
        System.out.println("Level: " + level);
    }
}

