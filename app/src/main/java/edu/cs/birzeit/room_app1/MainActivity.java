package edu.cs.birzeit.room_app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import edu.cs.birzeit.room_app1.database.AppDatabase;
import edu.cs.birzeit.room_app1.database.Book;
import edu.cs.birzeit.room_app1.database.BookDao;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view) {
        AppDatabase db = AppDatabase.getInstance(getApplicationContext());
        BookDao dao = db.bookDao();
        List<Book> bks = new ArrayList<>();
        Book b1 = new Book("Java Development", "CS");
        bks.add(b1);
        Book b2 = new Book("C# Development", "CS");
        Book b3 = new Book("Php Development", "CS");
        bks.add(b2);
        bks.add(b3);
        for(Book b: bks){
            dao.insertAll(b);
        }


        String str = "";

        List<Book> books = dao.getAll();

        for(Book b : books){
            str+= b.getTitle() + "\n";
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();

        //clean
        bks = dao.getAll();
        for(Book b: bks){
            dao.delete(b);
        }
    }
}