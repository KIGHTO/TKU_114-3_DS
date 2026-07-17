public class ProductDataManager {
    private static String[] names = new String[20];
    private static int[] prices = new int[20];
    private static int[] stocks = new int[20];
    private static int count = 0;

    public static void main(String[] args) {
        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8",
            "Webcam,1500,3",
            "Speaker,990,15",
            "Mousepad,150,50"
        };

        for (String record : records) {
            parseAndStore(record);
        }

        displayTable();
        searchProduct("ou");
        displayLowStock(10);
        displayTotalInventoryValue();
        
        addNewProduct("Mic,800,2");
        addNewProduct("Error,abc,def");
    }

    public static void parseAndStore(String record) {
        try {
            String[] parts = record.split(",");
            if (parts.length != 3) throw new Exception("格式錯誤");
            
            names[count] = parts[0];
            prices[count] = Integer.parseInt(parts[1]);
            stocks[count] = Integer.parseInt(parts[2]);
            count++;
        } catch (Exception e) {
            System.out.println("錯誤: " + record + " -> " + e.getMessage());
        }
    }

    public static void displayTable() {
        System.out.println("--- 商品清單 ---");
        for (int i = 0; i < count; i++) {
            System.out.printf("%-10s | %d | %d%n", names[i], prices[i], stocks[i]);
        }
    }

    public static void searchProduct(String keyword) {
        System.out.println("--- 搜尋: " + keyword + " ---");
        for (int i = 0; i < count; i++) {
            if (names[i].toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(names[i]);
            }
        }
    }

    public static void displayLowStock(int threshold) {
        System.out.println("--- 低庫存 ---");
        for (int i = 0; i < count; i++) {
            if (stocks[i] < threshold) System.out.println(names[i]);
        }
    }

    public static void displayTotalInventoryValue() {
        long total = 0;
        for (int i = 0; i < count; i++) {
            total += (long) prices[i] * stocks[i];
        }
        System.out.println("總價值: " + total);
    }

    public static void addNewProduct(String input) {
        parseAndStore(input);
    }
}
