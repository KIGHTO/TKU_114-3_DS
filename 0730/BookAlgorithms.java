import java.util.ArrayList;
import java.util.List;

public class BookAlgorithms {

    public static void mergeSortByIdAscending(Book[] books, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSortByIdAscending(books, left, mid);
        mergeSortByIdAscending(books, mid + 1, right);
        mergeById(books, left, mid, right);
    }

    private static void mergeById(Book[] books, int left, int mid, int right) {
        Book[] temp = new Book[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (books[i].getId().compareTo(books[j].getId()) <= 0) {
                temp[k] = books[i];
                i++;
            } else {
                temp[k] = books[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = books[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = books[j];
            j++;
            k++;
        }

        for (int m = 0; m < temp.length; m++) {
            books[left + m] = temp[m];
        }
    }

    public static void mergeSortByBorrowCountDescending(Book[] books, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSortByBorrowCountDescending(books, left, mid);
        mergeSortByBorrowCountDescending(books, mid + 1, right);
        mergeByBorrowCount(books, left, mid, right);
    }

    private static void mergeByBorrowCount(Book[] books, int left, int mid, int right) {
        Book[] temp = new Book[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;

        while (i <= mid && j <= right) {
            if (books[i].getBorrowCount() >= books[j].getBorrowCount()) {
                temp[k] = books[i];
                i++;
            } else {
                temp[k] = books[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {
            temp[k] = books[i];
            i++;
            k++;
        }

        while (j <= right) {
            temp[k] = books[j];
            j++;
            k++;
        }

        for (int m = 0; m < temp.length; m++) {
            books[left + m] = temp[m];
        }
    }

    public static int binarySearchById(Book[] sortedBooks, String targetId) {
        int left = 0;
        int right = sortedBooks.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int compareResult = sortedBooks[mid].getId().compareTo(targetId);
            if (compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static List<Book> sequentialSearchByCategory(List<Book> books, String category) {
        List<Book> matches = new ArrayList<Book>();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getCategory().equals(category)) {
                matches.add(books.get(i));
            }
        }
        return matches;
    }
}
