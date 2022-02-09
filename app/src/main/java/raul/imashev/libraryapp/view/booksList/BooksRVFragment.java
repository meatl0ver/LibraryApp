package raul.imashev.libraryapp.view.booksList;

import static raul.imashev.libraryapp.utils.Constants.AUTHOR_ID;
import static raul.imashev.libraryapp.utils.Constants.BOOK_ID;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

import raul.imashev.libraryapp.R;
import raul.imashev.libraryapp.room.enties.Book;
import raul.imashev.libraryapp.view.booksList.adapter.BooksAdapter;
import raul.imashev.libraryapp.viewModel.MainViewModel;

public class BooksRVFragment extends Fragment {
    private final ArrayList<Book> books = new ArrayList<>();
    private BooksAdapter adapter;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        //viewModel.startSetup(); метод для установки стартовых значений (для тестирования)

        setupAdapter();

        viewModel.getBooks().observe((LifecycleOwner) Objects.requireNonNull(this.getContext()), booksFromBD -> {
            books.addAll(booksFromBD);
            adapter.setBooks(books);
        });

        adapter.setOnBookClickListener(position -> {
            //при клике в Bundle записывается id автора и книги
            Bundle bundle = new Bundle();
            bundle.putLong(BOOK_ID, adapter.getBooks().get(position).getId());
            bundle.putLong(AUTHOR_ID, adapter.getBooks().get(position).getAuthorId());
            Navigation.findNavController(view).navigate(R.id.bookDetailFragment, bundle);
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_books_r_v, container, false);
    }

    private void setupAdapter() {
        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.booksRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new BooksAdapter();
        recyclerView.setAdapter(adapter);

    }
}