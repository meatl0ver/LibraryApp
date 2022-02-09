package raul.imashev.libraryapp.view.booksList.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import raul.imashev.libraryapp.R;
import raul.imashev.libraryapp.room.enties.Book;

public class BooksAdapter extends RecyclerView.Adapter<ViewHolder> {
     OnBookClickListener onBookClickListener;

    List<Book> books;

    public BooksAdapter() {
        books = new ArrayList<>();
    }

    public void setBooks(List<Book> bookList) {
        this.books = bookList;
        for (int i = 0; i < bookList.size(); i++) {
            notifyItemChanged(i);
        }
    }

    public List<Book> getBooks() {
        return books;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(this, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = books.get(position).getName();
        holder.textViewName.setText(name);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setOnBookClickListener(OnBookClickListener onCountryClickListener) {
        this.onBookClickListener = onCountryClickListener;
    }
}
