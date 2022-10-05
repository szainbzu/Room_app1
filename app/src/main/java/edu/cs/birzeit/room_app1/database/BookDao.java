package edu.cs.birzeit.room_app1.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BookDao {
    @Query("SELECT * FROM book")
    List<Book> getAll();

    //@Query("SELECT * FROM book WHERE bookId IN (:bookIds)")
   // List<Book> loadAllByIds(int[] bookIds);

    @Query("SELECT * FROM book WHERE title LIKE :title")
    Book findByName(String title);

    @Insert
    void insertAll(Book... book);

    @Delete
    void delete(Book book);
}
