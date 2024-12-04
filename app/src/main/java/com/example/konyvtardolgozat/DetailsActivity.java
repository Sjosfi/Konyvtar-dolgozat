package com.example.konyvtardolgozat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    private TextView textViewTitle, textViewAuthor, textViewPages, textViewYear;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewAuthor = findViewById(R.id.textViewAuthor);
        textViewPages = findViewById(R.id.textViewPages);
        textViewYear = findViewById(R.id.textViewYear);
        buttonBack = findViewById(R.id.buttonBack);

        Book book = (Book) getIntent().getSerializableExtra("book");

        if (book != null) {
            textViewTitle.setText(book.getTitle());
            textViewAuthor.setText(book.getAuthor());
            textViewPages.setText(String.valueOf(book.getPages()));

            Random random = new Random();
            int randomYear = 1900 + random.nextInt(123); // 1900-2023 közötti év
            textViewYear.setText("Év: " + randomYear);
        }

        buttonBack.setOnClickListener(view -> finish());
    }
}
