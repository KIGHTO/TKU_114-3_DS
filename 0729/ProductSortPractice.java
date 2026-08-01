public class ProductSortPractice {

    public static void main(String[] args) {
        

        Product[] products = {
            new Product("P01", "滑鼠", 299, 50),
            new Product("P02", "鍵盤", 590, 30),
            new Product("P03", "螢幕", 3200, 10),
            new Product("P04", "耳機", 590, 25),
            new Product("P05", "隨身碟", 199, 100),
            new Product("P06", "滑鼠墊", 199, 80),
            new Product("P07", "網路攝影機", 890, 15),
            new Product("P08", "喇叭", 590, 12)
        };

        System.out.println("========== 排序前 ==========");
        printProducts(products);

        insertionSortByPrice(products);

        System.out.println("========== 排序後（依價格升冪） ==========");
        printProducts(products);
    }

    public static void insertionSortByPrice(Product[] products) {
        for (int i = 1; i < products.length; i++) {
            Product key = products[i];
            int position = i - 1;

            while (position >= 0 && products[position].getPrice() > key.getPrice()) {
                products[position + 1] = products[position];
                position--;
            }

            products[position + 1] = key;
        }
    }

    public static void printProducts(Product[] products) {
        for (int i = 0; i < products.length; i++) {
            System.out.println(products[i].toDisplayString());
        }
        System.out.println();
    }
}
