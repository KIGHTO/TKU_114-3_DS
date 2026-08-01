public class RepairTask {
    private String id;
    private String equipmentName;
    private int priorityLevel;
    private String status;

    public RepairTask(String id, String equipmentName, int priorityLevel) {
        this.id = id;
        this.equipmentName = equipmentName;
        this.priorityLevel = priorityLevel;
        this.status = "等待中";
    }

    public String getId() {
        return id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toDisplayString() {
        return "編號：" + id + "，設備名稱：" + equipmentName + "，優先等級：" + priorityLevel + "，狀態：" + status;
    }
}
