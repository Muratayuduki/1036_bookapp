package model;

import java.io.Serializable;

public class BookBean implements Serializable {
    private int bookId;
    private String title;
    private String author;
    private String registeredBy;
    private String userName; // 登録者名

    public BookBean() {}

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getRegisteredBy() { return registeredBy; }
    public void setRegisteredBy(String registeredBy) { this.registeredBy = registeredBy; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
}
