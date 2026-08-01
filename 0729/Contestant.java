public class Contestant {
    private String id;
    private String name;
    private int score;
    private int completionSeconds;

    public Contestant(String id, String name, int score, int completionSeconds) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.completionSeconds = completionSeconds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getCompletionSeconds() {
        return completionSeconds;
    }

    public String toDisplayString() {
        return "編號：" + id + "，姓名：" + name + "，分數：" + score + "，完成秒數：" + completionSeconds;
    }
}
