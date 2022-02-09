package raul.imashev.libraryapp.view.booksList.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import raul.imashev.libraryapp.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    BooksAdapter booksAdapter;
    TextView textViewName;


    public ViewHolder(BooksAdapter booksAdapter, @NonNull View itemView) {
        super(itemView);
        this.booksAdapter = booksAdapter;
        textViewName = itemView.findViewById(R.id.bookName);
        itemView.setOnClickListener(view -> {
            if (booksAdapter.onBookClickListener != null) {
                booksAdapter.onBookClickListener.onBookClick(getAdapterPosition());
            }
        });
    }
}
