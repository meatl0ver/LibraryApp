package raul.imashev.libraryapp.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import raul.imashev.libraryapp.room.enties.Author;

@Dao
public interface AuthorDao {
    @Insert
    void insertAuthor(Author author);

    @Query("SELECT * FROM author WHERE id=:inputId")
    LiveData<Author> getAuthorById(long inputId);
}
