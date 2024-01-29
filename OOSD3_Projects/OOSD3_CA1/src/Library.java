import java.util.ArrayList;

public class Library { // Library class

    ArrayList<Book> books = new ArrayList<Book>(); // Array list of book objects

    public int findBookIndex(String ISBN) { // Method to find book index, returns -1 if not found
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equalsIgnoreCase(ISBN)) {
                return i;
            }
        }
        return -1;
    }

    public Book loanBook(int index) { // Method to loan book, returns null if not found
        if (index >= 0 && index < books.size()) {
            Book book = books.get(index);
            books.remove(index);
            return book;
        } else {
            return null;
        }
    }

    public void returnBook(Book book) { // Method to return book
        books.add(book);
    }

    public static void main(String args[]) { // Main method

        Library library = new Library(); // Library object
        System.out.println("Library created\n");
        Book book1 = new Book("Jack", "Sheehy", "Ab11228", 2003); // Book object / hardcoded values
        System.out.println("Created: " + book1.toString() + " \n");
        library.books.add(book1); // Add book1 to library
        System.out.println("Added Book: " + book1.getIsbn() + " to library\n");

        Member member1 = new Member(library); // Member objects
        Member member2 = new Member(library);

        member1.start(); // Start both threads
        member2.start();

    }
}

class Member extends Thread { // Member class which extends Thread class
    Library library; // Library instance variable
    String isbn = "Ab11228"; // Hardcoded ISBN value

    public Member(Library library) { // Member constructor
        this.library = library;
    }

    public void run() {
        int index = library.findBookIndex(isbn); // Call findBookIndex method + isbn value
        System.out.println("Book found at index: " + index + "\n");
        if (index != -1) {
            Book borrowedBook = library.loanBook(index); // If book is found call loanBook method
            System.out.println("Book borrowed: " + borrowedBook.toString() + "\n");
            try {
                Thread.sleep(2000); // Sleep for 2000ms
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Thread interrupted");
            }
            library.returnBook(borrowedBook); // Call returnBook method
            System.out.println("Book returned: " + borrowedBook.toString() + "\n");
        }
    }
}