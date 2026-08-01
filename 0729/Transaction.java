public class Transaction {
    private String id;
    private String account;
    private double amount;
    private int timeSerial;

    public Transaction(String id, String account, double amount, int timeSerial) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.timeSerial = timeSerial;
    }

    public String getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public double getAmount() {
        return amount;
    }

    public int getTimeSerial() {
        return timeSerial;
    }

    public String toDisplayString() {
        return "交易編號：" + id + "，帳號：" + account + "，金額：" + amount + "，時間序號：" + timeSerial;
    }
}
