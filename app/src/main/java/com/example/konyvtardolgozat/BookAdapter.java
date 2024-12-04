package com.example.konyvtardolgozat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Book> bookList;

    public BookAdapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false);
        }

        TextView textViewTitle = convertView.findViewById(R.id.textViewTitle);
        TextView textViewAuthor = convertView.findViewById(R.id.textViewAuthor);
        Button buttonDelete = convertView.findViewById(R.id.buttonDelete);
        CardView cardView = convertView.findViewById(R.id.cardView);

        Book book = bookList.get(position);
        textViewTitle.setText(book.getTitle());
        textViewAuthor.setText(book.getAuthor());

        buttonDelete.setOnClickListener(view -> {
            bookList.remove(position);
            notifyDataSetChanged();
        });

        return convertView;
    }
}