package com.example.marcellano_librarybooktracker;

public class Book {
    private String bookCode;
    private String title;
    private String author;
    private int numberOfDaysBorrowed;
    private boolean isBorrowed;


    public Book(String bookCode, String title, String author, int numberOfDaysBorrowed, boolean isBorrowed) {
        this.bookCode = bookCode;
        this.title = title;
        this.author = author;
        this.numberOfDaysBorrowed = numberOfDaysBorrowed;
        this.isBorrowed = isBorrowed;
    }

    public String getBookCode() {
        return bookCode;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumberOfDaysBorrowed() {
        return numberOfDaysBorrowed;
    }

    public void setNumberOfDaysBorrowed(int numberOfDaysBorrowed) {
        this.numberOfDaysBorrowed = numberOfDaysBorrowed;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }


}

