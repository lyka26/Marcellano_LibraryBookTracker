package com.example.marcellano_librarybooktracker;

// Abstract Book class
public abstract class Book {
    private String bookCode;
    private String title;
    private String author;
    private int numberOfDaysBorrowed;
    private boolean isBorrowed;

    // Constructor
    public Book(String bookCode, String title, String author) {
        this.bookCode = bookCode;
        this.title = title;
        this.author = author;
        this.numberOfDaysBorrowed = 0;
        this.isBorrowed = false;
    }

    // Getters and setters
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

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    // Abstract method to calculate the borrowing cost
    public abstract double calculateBorrowingCost();
}

