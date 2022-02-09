package raul.imashev.libraryapp.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import raul.imashev.libraryapp.room.enties.Book;

@Dao
public interface BookDao {
    @Query("SELECT * FROM book ORDER BY name")
    LiveData<List<Book>> getAllBooks();

    @Insert
    void insertBook(Book book);

    @Delete
    void deleteBook(Book book);

    @Query("SELECT * FROM book WHERE id=:inputId")
    Book getBookById(long inputId);
}
