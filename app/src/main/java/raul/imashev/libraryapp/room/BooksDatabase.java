package raul.imashev.libraryapp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import raul.imashev.libraryapp.room.dao.AuthorDao;
import raul.imashev.libraryapp.room.dao.BookDao;
import raul.imashev.libraryapp.room.enties.Author;
import raul.imashev.libraryapp.room.enties.Book;

@Database(entities = {Author.class, Book.class}, version = 1, exportSchema = false)
public abstract class BooksDatabase extends RoomDatabase {
    private static final String DB_NAME = "database";
    private static BooksDatabase database;
    private static final Object LOCK = new Object();

    public static BooksDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, BooksDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();

            }
        }
        return database;
    }

    public abstract AuthorDao authorDao();

    public abstract BookDao bookDao();
}
