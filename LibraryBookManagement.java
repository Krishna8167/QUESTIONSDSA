import java.util.*;

class Book {
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        if (!isIssued) {
            isIssued = true;
            System.out.println("✅ Book '" + title + "' has been issued.");
        } else {
            System.out.println("⚠️ Book '" + title + "' is already issued.");
        }
    }

    public void returnBook() {
        if (isIssued) {
            isIssued = false;
            System.out.println("📘 Book '" + title + "' has been returned.");
        } else {
            System.out.println("⚠️ Book '" + title + "' was not issued.");
        }
    }

    @Override
    public String toString() {
        return "Book{" + "title='" + title + '\'' + ", author='" + author + '\'' +
                ", issued=" + (isIssued ? "Yes" : "No") + '}';
    }
}

class Library {
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("📚 Book added: " + book.getTitle());
    }

    public void issueBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.issueBook();
                return;
            }
        }
        System.out.println("❌ Book not found: " + title);
    }

    public void returnBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                book.returnBook();
                return;
            }
        }
        System.out.println("❌ Book not found: " + title);
    }

    public void showAllBooks() {
        System.out.println("\n📖 Library Inventory:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook(new Book("1984", "George Orwell"));
        library.addBook(new Book("The Alchemist", "Paulo Coelho"));
        library.addBook(new Book("The Pragmatic Programmer", "Andrew Hunt"));

        library.showAllBooks();

        library.issueBook("1984");
        library.returnBook("1984");

        library.issueBook("Unknown Book");
        library.showAllBooks();
    }
}
