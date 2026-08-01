public class Book {
    private String id;
    private String title;
    private String category;
    private int borrowCount;

    public Book(String id, String title, String category, int borrowCount) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.borrowCount = borrowCount;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public void increaseBorrowCount() {
        borrowCount++;
    }

    public String toDisplayString() {
        return "編號：" + id + "，書名：" + title + "，分類：" + category + "，借閱次數：" + borrowCount;
    }
}
