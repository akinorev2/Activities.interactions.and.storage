package com.example.activitiesinteractionsandstorage;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab555.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static List<Note> notes;
    private static ArrayAdapter<Note> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        registerForContextMenu(listView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_add_note) {
            startActivity(new Intent(this, AddNoteActivity.class));
            return true;
        } else if (itemId == R.id.menu_delete_note) {
            startActivity(new Intent(this, DeleteNoteActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // Method to get the list of notes
    public static List<Note> getNotes() {
        return notes;
    }

    // Method to delete a note at the specified position
    public static void deleteNoteAt(int position) {
        if (position >= 0 && position < notes.size()) {
            notes.remove(position);
            adapter.notifyDataSetChanged();
        }
    }

    // Method to add a note to the list
    public static void addNote(Note note) {
        notes.add(note);
        adapter.notifyDataSetChanged();
    }
}

