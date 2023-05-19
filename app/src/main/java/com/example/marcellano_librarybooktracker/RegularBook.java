package com.example.marcellano_librarybooktracker;

// RegularBook class, extending Book
public class RegularBook extends Book {
    public RegularBook(String bookCode, String title, String author) {
        super(bookCode, title, author);
    }

    @Override
    public double calculateBorrowingCost() {
        return getNumberOfDaysBorrowed() * 20.0;
    }
}
