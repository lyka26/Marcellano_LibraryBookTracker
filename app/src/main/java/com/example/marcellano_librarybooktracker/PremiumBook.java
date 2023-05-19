package com.example.marcellano_librarybooktracker;

// PremiumBook class, extending Book
public class PremiumBook extends Book {
    public PremiumBook(String bookCode, String title, String author) {
        super(bookCode, title, author);
    }

    @Override
    public double calculateBorrowingCost() {
        int numberOfDays = getNumberOfDaysBorrowed();
        if (numberOfDays > 7) {
            return (7 * 50.0) + ((numberOfDays - 7) * 50.0) + (25.0 * (numberOfDays - 7));
        } else {
            return numberOfDays * 50.0;
        }
    }
}
