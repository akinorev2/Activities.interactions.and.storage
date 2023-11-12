package com.example.activitiesinteractionsandstorage;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;




public class AddNoteActivity extends AppCompatActivity {

    private EditText edtNoteName;
    private EditText edtNoteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);

        edtNoteName = findViewById(R.id.edtNoteName);
        edtNoteContent = findViewById(R.id.edtNoteContent);
    }

    public void onSaveButtonClick(View view) {
        String name = edtNoteName.getText().toString().trim();
        String content = edtNoteContent.getText().toString().trim();

        if (name.isEmpty()) {
            showToast(getString(R.string.warning_empty_text));
        } else {
            showToast(getString(R.string.note_saved));

            MainActivity.addNote(new Note(name, content));

            finish();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}