package raul.imashev.libraryapp.view.bookDetail;

import static raul.imashev.libraryapp.utils.Constants.AUTHOR_ID;
import static raul.imashev.libraryapp.utils.Constants.BOOK_ID;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Objects;

import raul.imashev.libraryapp.R;
import raul.imashev.libraryapp.room.enties.Book;
import raul.imashev.libraryapp.viewModel.MainViewModel;


public class BookDetailFragment extends Fragment {
    private EditText textViewName;
    private EditText textViewDetail;
    private EditText textViewAuthor;
    private Button button;
    private boolean isEditable = false; //переменная, отображающая состояние "редактировать"/"сохранить"

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_book_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        Book book;
        if (getArguments() != null) {
            book = viewModel.getBookById(getArguments().getLong(BOOK_ID, -1));
        viewModel.getAuthorById(book.getAuthorId()).observe((LifecycleOwner) Objects.requireNonNull(getContext()), author -> textViewAuthor.setText(author.getName()));
        setupView(book);
        }


        button.setOnClickListener(v -> {
            if (!isEditable) {
                makeEditable();
            } else {
                //если было заполнено пустое поле
                if (textViewName.getText().toString().trim().equals("") || textViewDetail.getText().toString().trim().equals("")) {
                    Toast.makeText(getContext(), "Ошибка! Поля должны быть заполнены!", Toast.LENGTH_LONG).show();
                } else {
                    //если данные введены корректно, появляется Toast с сообщением "Сохранено" и происходит переход на первый фрагмент
                    isEditable = false;
                    Book newBook = new Book(0, getArguments().getLong(AUTHOR_ID, -1), textViewName.getText().toString(), textViewDetail.getText().toString());
                    Book book1 = new Book(getArguments().getLong(BOOK_ID, -1), 0, textViewName.getText().toString(), textViewDetail.getText().toString());
                    Toast.makeText(getContext(), "Сохранено", Toast.LENGTH_LONG).show();
                    viewModel.deleteBook(book1);
                    viewModel.insertBook(newBook);
                    Navigation.findNavController(view).navigate(R.id.booksRVFragment);
                }
            }
        });
    }

    private void setupView(Book book) {
        textViewName = Objects.requireNonNull(getView()).findViewById(R.id.bookTitleET);
        textViewDetail = getView().findViewById(R.id.bookDescriptionET);
        textViewAuthor = getView().findViewById(R.id.bookAuthorET);
        textViewName.setText(book.getName());
        textViewDetail.setText(book.getDescription());
        button = getView().findViewById(R.id.editBtn);
    }

    private void makeEditable() {
        textViewName.setClickable(true);
        textViewName.setCursorVisible(true);
        textViewName.setFocusable(true);
        textViewName.setFocusableInTouchMode(true);

        textViewDetail.setClickable(true);
        textViewDetail.setCursorVisible(true);
        textViewDetail.setFocusable(true);
        textViewDetail.setFocusableInTouchMode(true);
        button.setText("Сохранить");
        isEditable = true;
    }
}