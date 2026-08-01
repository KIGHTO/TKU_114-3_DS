public class Registration {
    private String id;
    private String name;
    private String status;
    private String preCancelStatus;

    public Registration(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.preCancelStatus = "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPreCancelStatus() {
        return preCancelStatus;
    }

    public void setPreCancelStatus(String preCancelStatus) {
        this.preCancelStatus = preCancelStatus;
    }

    public String toDisplayString() {
        return "編號：" + id + "，姓名：" + name + "，狀態：" + status;
    }
}