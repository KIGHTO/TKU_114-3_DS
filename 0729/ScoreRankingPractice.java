public class ScoreRankingPractice {

    public static void main(String[] args) {
        

        String[] names = {"小明", "小華", "小美", "小強", "小芳", "小傑", "小玲", "小宇"};
        int[] scores = {78, 92, 65, 92, 40, 58, 78, 100};

        selectionSortDescending(names, scores);
        printRanking(names, scores);
    }

    public static void selectionSortDescending(String[] names, int[] scores) {
        for (int start = 0; start < scores.length - 1; start++) {
            int maxIndex = start;
            for (int i = start + 1; i < scores.length; i++) {
                if (scores[i] > scores[maxIndex]) {
                    maxIndex = i;
                }
            }
            if (maxIndex != start) {
                int tempScore = scores[start];
                scores[start] = scores[maxIndex];
                scores[maxIndex] = tempScore;

                String tempName = names[start];
                names[start] = names[maxIndex];
                names[maxIndex] = tempName;
            }
        }
    }

    public static void printRanking(String[] names, int[] scores) {
        System.out.println("========== 成績降冪排名 ==========");

        int rank = 1;
        for (int i = 0; i < scores.length; i++) {
            if (i > 0 && scores[i] != scores[i - 1]) {
                rank = i + 1;
            }

            String passStatus = "";
            if (scores[i] >= 60) {
                passStatus = "及格";
            } else {
                passStatus = "不及格";
            }

            System.out.println("名次：" + rank + "，姓名：" + names[i] + "，分數：" + scores[i] + "，" + passStatus);
        }
    }
}
