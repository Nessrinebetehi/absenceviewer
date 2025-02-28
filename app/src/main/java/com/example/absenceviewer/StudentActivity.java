package com.example.absenceviewer;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private SearchView searchView;
    private List<Student> studentList = new ArrayList<>();
    private HashMap<String, Integer> absenceCountMap = new HashMap<>();

    private static final Uri CONTENT_URI = Uri.parse("content://com.example.absencemanager.provider/absences");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        searchView = findViewById(R.id.searchView);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentAdapter = new StudentAdapter(studentList, absenceCountMap);
        recyclerView.setAdapter(studentAdapter);

        loadStudents("");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                loadStudents(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadStudents(newText);
                return false;
            }
        });
    }

    private void loadStudents(String query) {
        studentList.clear();
        absenceCountMap.clear();

        Cursor cursor = getContentResolver().query(CONTENT_URI, null, "student_name LIKE ?", new String[]{"%" + query + "%"}, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("student_name"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
                String subject = cursor.getString(cursor.getColumnIndexOrThrow("subject"));

                if (status.equalsIgnoreCase("Absent")) {
                    int count = absenceCountMap.getOrDefault(name, 0);
                    absenceCountMap.put(name, count + 1);
                }


                studentList.add(new Student(name, date, status, subject));
            }
            cursor.close();
        } else {
            Toast.makeText(this, "Aucune absence trouvée", Toast.LENGTH_SHORT).show();
        }

        studentAdapter.notifyDataSetChanged();
    }
}
