public class Book {
    private String authorFirstName;
    private String authorSurname;
    private String isbn;
    private int publishYear;

    public Book(String authorFirstName, String authorSurname, String isbn, int publishYear) {
        this.authorFirstName = authorFirstName;
        this.authorSurname = authorSurname;
        this.isbn = isbn;
        this.publishYear = publishYear;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    @Override
    public String toString() {
        return "Book [authorFirstName=" + authorFirstName + ", authorSurname=" + authorSurname + ", isbn=" + isbn
                + ", publishYear=" + publishYear + "]";
    }

}
