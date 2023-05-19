package com.example.marcellano_librarybooktracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    TextInputLayout bookCodeInput , numberOfDaysBorrowedInput, titleInput, totalPriceInput;
    Button borrowButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize FirebaseApp
        FirebaseApp.initializeApp(this);

        // Initialize FirebaseFirestore
        db = FirebaseFirestore.getInstance();

        bookCodeInput = findViewById(R.id.bookCodeInputLayout);
        numberOfDaysBorrowedInput = findViewById(R.id.numberOfDaysBorrowedInputLayout);
        titleInput = findViewById(R.id.titleInputLayout);
        totalPriceInput = findViewById(R.id.totalPriceInputLayout);
        borrowButton = findViewById(R.id.borrowButton);

        // Borrow button click listener
        Button borrowButton = findViewById(R.id.borrowButton);
        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookCode = bookCodeInput.getEditText().getText().toString();
                String numberOfDaysStr = numberOfDaysBorrowedInput.getEditText().getText().toString();

                // Check if book code and number of days inputs are empty
                if (bookCode.isEmpty() || numberOfDaysStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter book code and number of days.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parse the string representation of the number of days to an integer
                int numberOfDays = Integer.parseInt(numberOfDaysStr);

                // Check in PremiumBooks collection
                Query premiumQuery = db.collection("PremiumBooks").whereEqualTo("bookCode", bookCode);
                premiumQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot querySnapshot = task.getResult();
                            if (!querySnapshot.isEmpty()) {
                                // Book found in PremiumBooks collection
                                DocumentSnapshot document = querySnapshot.getDocuments().get(0);
                                String title = document.getString("title");
                                String author = document.getString("author");

                                Book book = new PremiumBook(bookCode, title, author);
                                book.setNumberOfDaysBorrowed(numberOfDays);

                                double borrowingCost = book.calculateBorrowingCost();

                                // Set the title of the book
                                TextView titleTextView = titleInput.getEditText();
                                if (titleTextView != null) {
                                    titleTextView.setText(title);
                                }

                                // Set the borrowing cost of the book
                                TextView totalPriceTextView = totalPriceInput.getEditText();
                                if (totalPriceTextView != null) {
                                    totalPriceTextView.setText(String.valueOf(borrowingCost));
                                }
                                return; // Exit the method
                            } else {
                                // If not found in PremiumBooks collection, check RegularBooks collection
                                Query regularQuery = db.collection("RegularBooks").whereEqualTo("bookCode", bookCode);
                                regularQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            QuerySnapshot querySnapshot = task.getResult();
                                            if (!querySnapshot.isEmpty()) {
                                                // Book found in RegularBooks collection
                                                DocumentSnapshot document = querySnapshot.getDocuments().get(0);
                                                String title = document.getString("title");
                                                String author = document.getString("author");

                                                Book book = new RegularBook(bookCode, title, author);
                                                book.setNumberOfDaysBorrowed(numberOfDays);

                                                double borrowingCost = book.calculateBorrowingCost();

                                                // Set the title of the book
                                                TextView titleTextView = titleInput.getEditText();
                                                if (titleTextView != null) {
                                                    titleTextView.setText(title);
                                                }

                                                // Set the borrowing cost of the book
                                                TextView totalPriceTextView = totalPriceInput.getEditText();
                                                if (totalPriceTextView != null) {
                                                    totalPriceTextView.setText(String.valueOf(borrowingCost));
                                                }

                                                return; // Exit the method
                                            }
                                        } else {
                                            // Error fetching books from RegularBooks collection
                                            Toast.makeText(MainActivity.this, "Error fetching books.", Toast.LENGTH_SHORT).show();
                                        }

                                        // Book not found in either collection
                                        Toast.makeText(MainActivity.this, "No records or book not found.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }
                });
            }
        });
    }
}