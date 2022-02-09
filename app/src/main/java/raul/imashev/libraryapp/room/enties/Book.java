package raul.imashev.libraryapp.room.enties;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "book", foreignKeys = {@ForeignKey(entity = Author.class, parentColumns = "id", childColumns = "authorId")})
public class Book {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private long authorId;
    private String name;
    private String description;

    public Book(long id, long authorId, String name, String description) {
        this.id = id;
        this.authorId = authorId;
        this.name = name;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
