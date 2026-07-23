public class CartItem {
    private String id;
    private String name;
    private double price;
    private int quantity;

    
    public CartItem(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
    public double getTotalPrice() {
        return price * quantity;
    }

   
    public void displayItem() {
        System.out.println("代碼: " + id + " | 名稱: " + name + " | 單價: " + price + " | 數量: " + quantity + " | 小計: " + getTotalPrice());
    }
}
