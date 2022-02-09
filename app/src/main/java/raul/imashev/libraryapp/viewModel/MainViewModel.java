package raul.imashev.libraryapp.viewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import raul.imashev.libraryapp.room.BooksDatabase;
import raul.imashev.libraryapp.room.enties.Author;
import raul.imashev.libraryapp.room.enties.Book;

public class MainViewModel extends AndroidViewModel {
    private static BooksDatabase database;
    private final LiveData<List<Book>> books;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = BooksDatabase.getInstance(getApplication());
        books = database.bookDao().getAllBooks();
    }

    public LiveData<List<Book>> getBooks() {
        return books;
    }

    public Book getBookById(Long id) {
        try {
            return new GetBookTask().execute(id).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<Author> getAuthorById(long id) {
        return database.authorDao().getAuthorById(id);
    }

    public void insertBook(Book book) {
        new InsertTask().execute(book);
    }

    private static class InsertTask extends AsyncTask<Book, Void, Void> {
        @Override
        protected Void doInBackground(Book... books) {
            if (books != null && books.length > 0) {
                database.bookDao().insertBook(books[0]);
            }
            return null;
        }
    }

    public void deleteBook(Book book) {
        new DeleteBookTask().execute(book);
    }

    private static class DeleteBookTask extends AsyncTask<Book, Void, Void> {
        @Override
        protected Void doInBackground(Book... books) {
            if (books != null && books.length > 0) {
                database.bookDao().deleteBook(books[0]);
            }
            return null;
        }
    }


    public void insertAuthor(Author author) {
        new InsertAuthorTask().execute(author);
    }

    private static class InsertAuthorTask extends AsyncTask<Author, Void, Void> {
        @Override
        protected Void doInBackground(Author... authors) {
            if (authors != null && authors.length > 0) {
                database.authorDao().insertAuthor(authors[0]);
            }
            return null;
        }
    }


    private static class GetBookTask extends AsyncTask<Long, Void, Book> {
        @Override
        protected Book doInBackground(Long... longs) {
            if (longs != null && longs.length > 0) {
                return database.bookDao().getBookById(longs[0]);
            }
            return null;
        }
    }


    //метод для установки стартовых значений (для тестирования)
    public void startSetup() {
        List<Author> newAuthors = new ArrayList<>();
        List<Book> newBooks = new ArrayList<>();
        newAuthors.add(new Author(0, "Лев Толстой", "9 сентября 1828"));
        newAuthors.add(new Author(0, "Михаил Лермонтов", "15 октября 1814"));
        newAuthors.add(new Author(0, "Александр Пушкин", "26 мая 1799"));
        newAuthors.add(new Author(0, "Антон Чехов", "29 января 1860"));

        newBooks.add(new Book(0, 1, "Анна Каренина", "«Анна Каренина» — роман Льва Толстого о трагической любви замужней дамы Анны Карениной и блестящего офицера Алексея Вронского на фоне счастливой семейной жизни дворян Константина Лёвина и Кити Щербацкой."));
        newBooks.add(new Book(0, 1, "Детство", "«Детство» — первая повесть автобиографической трилогии Льва Толстого, впервые напечатана в 1852 году в журнале «Современник», № 9. Эта книга описывает психологические переживания, которые испытывают многие мальчики в детстве: первая влюблённость, чувство несправедливости, обида, стеснение."));
        newBooks.add(new Book(0, 1, "Кавказский пленник", "«Кавказский пленник» — рассказ Льва Толстого, повествующий о русском офицере в плену у горцев. Написан для «Азбуки», впервые опубликован в 1872 году в журнале «Заря». Одно из наиболее популярных произведений писателя, многократно переиздававшееся и входящее в школьную программу."));
        newBooks.add(new Book(0, 1, "Хаджи-Мурат", "«Хаджи-Мурат» —  повесть Льва Толстого, написанная в конце 1890-х — начале 1900-х и опубликованная в 1912 году, после смерти писателя. Главный герой повести — реальное историческое лицо, Хаджи-Мурат, наиб Шамиля, в 1851 году перешедший на сторону русских, а в следующем году погибший при попытке бежать в горы."));

        newBooks.add(new Book(0, 2, "Герой нашего времени", "«Герой нашего времени» —   первый в русской прозе социально-психологический роман, написанный Михаилом Юрьевичем Лермонтовым в 1838—1840 годах. Классика русской литературы. Впервые роман был издан в Санкт-Петербурге в типографии Ильи Глазунова и Кº в 1840 году в 2 книгах. Тираж — 1000 экземпляров."));
        newBooks.add(new Book(0, 2, "Демон", "«Демон» —  поэма Михаила Юрьевича Лермонтова, над которой поэт работал в течение десяти лет — с 1829 по 1839 год."));
        newBooks.add(new Book(0, 2, "Мцыри", "«Мцыри» —  повесть Льва Толстого, написанная в конце 1890-х — романтическая поэма М. Ю. Лермонтова, написанная в 1839 году и опубликованная в 1840 году в единственном прижизненном издании поэта — сборнике «Стихотворения М. Лермонтова»."));
        newBooks.add(new Book(0, 2, "Бородино", "«Бородино» — стихотворение поэта Михаила Юрьевича Лермонтова. Было написано в начале 1830-х годов. Опубликовано в журнале «Современник» в 1837 году. Посвящено Бородинскому сражению 7 сентября 1812 года, в котором русская армия сражалась против французского наполеоновского войска."));

        newBooks.add(new Book(0, 2, "Капитанская дочка", "«Капитанская дочка» —  исторический роман Александра Пушкина, действие которого происходит во время восстания Емельяна Пугачёва. Впервые опубликован без указания имени автора в 4-й книжке журнала «Современник», поступившей в продажу в последней декаде 1836 года.   "));
        newBooks.add(new Book(0, 2, "Пиковая дама", "«Пиковая дама» —   повесть Александра Сергеевича Пушкина с мистическими элементами, послужившая источником сюжета одноимённой оперы П. И. Чайковского. "));
        newBooks.add(new Book(0, 2, "Руслан и Людмила", "«Руслан и Людмила» —  первая законченная поэма Александра Сергеевича Пушкина; волшебная сказка, вдохновлённая древнерусскими былинами."));
        newBooks.add(new Book(0, 2, "Сказка о царе Салтане", "«Сказка о царе Салтане, о сыне его славном и могучем богатыре князе ́Гвидоне Салтановиче и о прекрасной царевне Лебеди» — сказка в стихах Александра Пушкина, написанная в 1831 году и впервые изданная в следующем году в собрании стихотворений"));

        newBooks.add(new Book(0, 3, "Хамелеон", "«Хамелеон» —  рассказ русского писателя Антона Павловича Чехова. Впервые опубликован в журнале «Осколки» № 36 от 8 сентября 1884 года под подписью «А. Чехонте». В 1886 году «Хамелеон» был также выпущен с мелкими изменениями «Осколками» в составе сборника Чехова «Пёстрые рассказы»."));
        newBooks.add(new Book(0, 3, "Толстый и тонкий", "«Толстый и тонкий» —  сатирический рассказ русского писателя Антона Павловича Чехова, созданный в 1883 году. Впервые был опубликован юмористическим журналом «Осколки» 1 октября 1883 года. "));
        newBooks.add(new Book(0, 3, "Дама с собачкой", "«Дама с собачкой» —  рассказ русского писателя и драматурга Антона Павловича Чехова, написанный в 1898 году. Впервые опубликован в журнале «Русская мысль» № 12 в 1899 году."));
        newBooks.add(new Book(0, 3, "Злоумышленник", "«Злоумышленник» —  рассказ русского писателя А. П. Чехова, впервые опубликованный в 1885 году."));

        for (int i = 0; i < newAuthors.size(); i++) {
            insertAuthor(newAuthors.get(i));
        }
        for (int i = 0; i < newBooks.size(); i++) {
            insertBook(newBooks.get(i));
        }
    }
}
