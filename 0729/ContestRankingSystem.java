public class ContestRankingSystem {

    public static void main(String[] args) {
        

        Contestant[] contestants = {
            new Contestant("C01", "小明", 85, 120),
            new Contestant("C02", "小華", 92, 150),
            new Contestant("C03", "小美", 85, 95),
            new Contestant("C04", "小強", 78, 200),
            new Contestant("C05", "小芳", 92, 110),
            new Contestant("C06", "小傑", 60, 300),
            new Contestant("C07", "小玲", 85, 100),
            new Contestant("C08", "小宇", 100, 80)
        };

        System.out.println("========== 排序前 ==========");
        printContestants(contestants);

        insertionSortByRule(contestants);

        System.out.println("========== 最終排名 ==========");
        printRanking(contestants);
    }

    public static void insertionSortByRule(Contestant[] contestants) {
        for (int i = 1; i < contestants.length; i++) {
            Contestant key = contestants[i];
            int position = i - 1;

            while (position >= 0 && isLowerPriority(contestants[position], key)) {
                contestants[position + 1] = contestants[position];
                position--;
            }

            contestants[position + 1] = key;
        }
    }

    public static boolean isLowerPriority(Contestant a, Contestant b) {
        if (a.getScore() != b.getScore()) {
            return a.getScore() < b.getScore();
        } else {
            return a.getCompletionSeconds() > b.getCompletionSeconds();
        }
    }

    public static void printContestants(Contestant[] contestants) {
        for (int i = 0; i < contestants.length; i++) {
            System.out.println(contestants[i].toDisplayString());
        }
        System.out.println();
    }

    public static void printRanking(Contestant[] contestants) {
        for (int i = 0; i < contestants.length; i++) {
            System.out.println("名次：" + (i + 1) + "，" + contestants[i].toDisplayString());
        }
        System.out.println();
    }
}
