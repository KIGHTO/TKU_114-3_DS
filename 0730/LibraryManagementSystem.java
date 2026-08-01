import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class LibraryManagementSystem {

    private static ArrayList<Book> allBooks = new ArrayList<Book>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("========== 圖書借閱資料管理系統 ==========");
            System.out.println("1. 新增書籍");
            System.out.println("2. 依編號升冪顯示所有書籍");
            System.out.println("3. 依借閱次數降冪顯示所有書籍");
            System.out.println("4. 依編號查詢書籍");
            System.out.println("5. 依分類找出全部書籍");
            System.out.println("6. 離開系統");
            System.out.print("請選擇功能：");

            String choice = sc.nextLine();

            if (choice.equals("1")) {
                addBook(sc);
            } else if (choice.equals("2")) {
                showSortedById();
            } else if (choice.equals("3")) {
                showSortedByBorrowCount();
            } else if (choice.equals("4")) {
                searchById(sc);
            } else if (choice.equals("5")) {
                searchByCategory(sc);
            } else if (choice.equals("6")) {
                System.out.println("系統已結束。");
                break;
            } else {
                System.out.println("輸入錯誤，請重新輸入。");
            }
        }

        sc.close();
    }

    public static void addBook(Scanner sc) {
        System.out.print("請輸入書籍編號：");
        String id = sc.nextLine();

        if (isDuplicateId(id)) {
            System.out.println("新增失敗：書籍編號 " + id + " 已存在。");
            System.out.println();
            return;
        }

        System.out.print("請輸入書名：");
        String title = sc.nextLine();
        System.out.print("請輸入分類：");
        String category = sc.nextLine();
        System.out.print("請輸入借閱次數：");
        int borrowCount = Integer.parseInt(sc.nextLine());

        allBooks.add(new Book(id, title, category, borrowCount));
        System.out.println("新增成功。");
        System.out.println();
    }

    public static boolean isDuplicateId(String id) {
        for (int i = 0; i < allBooks.size(); i++) {
            if (allBooks.get(i).getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static void showSortedById() {
        if (allBooks.isEmpty()) {
            System.out.println("目前尚無任何書籍資料。");
            System.out.println();
            return;
        }

        Book[] copy = allBooks.toArray(new Book[0]);
        BookAlgorithms.mergeSortByIdAscending(copy, 0, copy.length - 1);

        System.out.println("========== 所有書籍（依編號升冪） ==========");
        printBooks(copy);
    }

    public static void showSortedByBorrowCount() {
        if (allBooks.isEmpty()) {
            System.out.println("目前尚無任何書籍資料。");
            System.out.println();
            return;
        }

        Book[] copy = allBooks.toArray(new Book[0]);
        BookAlgorithms.mergeSortByBorrowCountDescending(copy, 0, copy.length - 1);

        System.out.println("========== 所有書籍（依借閱次數降冪） ==========");
        printBooks(copy);
    }

    public static void searchById(Scanner sc) {
        if (allBooks.isEmpty()) {
            System.out.println("目前尚無任何書籍資料。");
            System.out.println();
            return;
        }

        System.out.print("請輸入要查詢的書籍編號：");
        String targetId = sc.nextLine();

        Book[] sortedCopy = allBooks.toArray(new Book[0]);
        BookAlgorithms.mergeSortByIdAscending(sortedCopy, 0, sortedCopy.length - 1);

        int index = BookAlgorithms.binarySearchById(sortedCopy, targetId);
        if (index != -1) {
            System.out.println("查詢結果：" + sortedCopy[index].toDisplayString());
        } else {
            System.out.println("查無編號 " + targetId + " 的書籍。");
        }
        System.out.println();
    }

    public static void searchByCategory(Scanner sc) {
        if (allBooks.isEmpty()) {
            System.out.println("目前尚無任何書籍資料。");
            System.out.println();
            return;
        }

        System.out.print("請輸入要查詢的分類：");
        String category = sc.nextLine();

        List<Book> matches = BookAlgorithms.sequentialSearchByCategory(allBooks, category);

        if (matches.isEmpty()) {
            System.out.println("查無分類為 " + category + " 的書籍。");
        } else {
            System.out.println("========== 分類「" + category + "」的書籍 ==========");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println(matches.get(i).toDisplayString());
            }
        }
        System.out.println();
    }

    public static void printBooks(Book[] books) {
        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i].toDisplayString());
        }
        System.out.println();
    }
}
