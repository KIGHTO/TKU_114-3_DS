public class Equipment {
    private String code;
    private String name;
    private boolean borrowed;

    public Equipment(String code, String name) {
        this.code = code;
        this.name = name;
        this.borrowed = false;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    @Override
    public String toString() {
        String status;

        if (borrowed) {
            status = "已借出";
        } else {
            status = "可借用";
        }

        return code + " | " + name + " | " + status;
    }
}
