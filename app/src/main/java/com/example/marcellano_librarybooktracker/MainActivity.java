package com.example.marcellano_librarybooktracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView bookCodeInput , numberOfDaysBorrowedInput, titleInput, totalPriceInput;
    Button borrowButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookCodeInput = findViewById(R.id.bookCodeInputLayout);
        numberOfDaysBorrowedInput = findViewById(R.id.numberOfDaysBorrowedInputLayout);
        titleInput = findViewById(R.id.titleInputLayout);
        totalPriceInput = findViewById(R.id.totalPriceInputLayout);
        borrowButton = findViewById(R.id.borrowButton);

        borrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*CollectionReference colRef;
                if (checkBookIfPremium(bookCode)){
                    colRef = db.collection(("PremiumBooks"));
                } else {
                    colRef = db.collection(("RegularBooks"));
                }*/
            }
        });
    }
    private boolean checkBookIfPremium(String bookCode){
        CollectionReference colRef = db.collection("PremiumBooks");
        Query query = colRef.whereEqualTo("bookCode",bookCode);
        QuerySnapshot querySnapshot = query.get().getResult();
        return !querySnapshot.isEmpty();
    }

}