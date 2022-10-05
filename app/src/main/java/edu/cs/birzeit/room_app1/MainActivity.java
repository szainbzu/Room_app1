package edu.cs.birzeit.room_app1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.os.HandlerCompat;
import androidx.room.Room;
import edu.cs.birzeit.room_app1.database.AppDatabase;
import edu.cs.birzeit.room_app1.database.Book;
import edu.cs.birzeit.room_app1.database.BookDao;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private AppDatabase db;
    private EditText edtTitle;
    private EditText edtCat;
    private ListView lstBooks;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = HandlerCompat.createAsync(Looper.getMainLooper());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.getInstance(getApplicationContext());
        edtTitle = findViewById(R.id.edtTitle);
        edtCat = findViewById(R.id.edtCat);
        lstBooks = findViewById(R.id.lstBooks);
    }

    public void btnClick(View view) {
        Book book = new Book(edtTitle.getText().toString(),
                edtCat.getText().toString());
        saveBook(book);

        refreshList();




    }

    private void refreshList() {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                BookDao dao = db.bookDao();

                List<Book> books = dao.getAll();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Book[] arr = new Book[books.size()];
                        arr = books.toArray(arr);
                        ArrayAdapter<Book> adapter = new ArrayAdapter<Book>(MainActivity.this,
                                android.R.layout.simple_list_item_1, arr);

                        lstBooks.setAdapter(adapter);
                        edtTitle.setText("");
                        edtCat.setText("");
                    }
                });



            }
        });
    }

    private void saveBook(Book book) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                BookDao dao = db.bookDao();

                dao.insertAll(book);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Book added!", Toast.LENGTH_SHORT).show();
                    }
                });

            }

        });
    }
}