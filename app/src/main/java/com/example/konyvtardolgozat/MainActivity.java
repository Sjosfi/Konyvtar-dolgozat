package com.example.konyvtardolgozat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextAuthor, editTextPages;
    private Button buttonAdd;
    private ListView listViewBooks;
    private ArrayList<Book> bookList = new ArrayList<>();
    private BookAdapter bookAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        editTextPages = findViewById(R.id.editTextPages);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewBooks = findViewById(R.id.listViewBooks);

        bookAdapter = new BookAdapter(this, bookList);
        listViewBooks.setAdapter(bookAdapter);

        buttonAdd.setOnClickListener(view -> addBook());

        listViewBooks.setOnItemClickListener((parent, view, position, id) -> {
            Book selectedBook = bookList.get(position);
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("book", selectedBook);
            startActivity(intent);
        });

        listViewBooks.setOnItemLongClickListener((parent, view, position, id) -> {
            confirmDelete(position);
            return true;
        });
    }

    private void addBook() {
        String title = editTextTitle.getText().toString();
        String author = editTextAuthor.getText().toString();
        String pages = editTextPages.getText().toString();

        if (title.isEmpty() || author.isEmpty() || pages.isEmpty()) {
            Toast.makeText(this, "Minden mező kitöltése kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        int pageCount;
        try {
            pageCount = Integer.parseInt(pages);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Oldalszám érvénytelen!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pageCount < 50) {
            Toast.makeText(this, "Az oldalszám legalább 50 kell legyen!", Toast.LENGTH_SHORT).show();
            return;
        }

        Book newBook = new Book(title, author, pageCount);
        bookList.add(newBook);
        bookAdapter.notifyDataSetChanged();

        editTextTitle.setText("");
        editTextAuthor.setText("");
        editTextPages.setText("");
    }

    private void confirmDelete(int position) {
        new AlertDialog.Builder(this)
                .setTitle("Törlés")
                .setMessage("Biztosan törölni szeretnéd a könyvet?")
                .setPositiveButton("Igen", (dialog, which) -> {
                    bookList.remove(position);
                    bookAdapter.notifyDataSetChanged();
                })
                .setNegativeButton("Nem", null)
                .show();
    }
}