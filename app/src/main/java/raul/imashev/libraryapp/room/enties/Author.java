package raul.imashev.libraryapp.room.enties;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "author")
public class Author {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String birthDate;

    public Author(long id, String name, String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
