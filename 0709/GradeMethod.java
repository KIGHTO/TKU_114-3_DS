public class GradeMethod {
    public static void main(String[] args) {
        double average = calculateAverage(85, 90, 78);
        String grade = getGrade(average);

        System.out.println("Average: " + average);
        System.out.println("Grade: " + grade);
    }

    public static double calculateAverage(int javaScore, int englishScore, int mathScore) {
        double average = (javaScore + englishScore + mathScore) / 3.0;
        return average;
    }

    public static String getGrade(double average) {
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
