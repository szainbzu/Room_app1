package edu.cs.birzeit.room_app1.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Book {
    @ColumnInfo(name = "bookid")
    @PrimaryKey(autoGenerate = true)
    public int bookId;

    @ColumnInfo(name = "title")
    public String title;

    @ColumnInfo(name = "category")
    public String category;

    public Book(int bookId, String title, String category) {
        this.bookId = bookId;
        this.title = title;
        this.category = category;
    }
    @Ignore
    public Book(String title, String category) {
        this.title = title;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }
}
