public class Contact {
    private String id;
    private String name;
    private String phone;
    private String email;

    // Constructor
    public Contact(String id, String name, String phone, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Custom method to display contact details nicely
    public void displayContact() {
        System.out.println("代碼: " + id + " | 姓名: " + name + " | 電話: " + phone + " | 電子郵件: " + email);
    }
}
