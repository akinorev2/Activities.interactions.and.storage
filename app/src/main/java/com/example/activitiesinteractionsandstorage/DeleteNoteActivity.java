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






public class DeleteNoteActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_note_activity);


        listView = findViewById(R.id.listView);
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, MainActivity.getNotes()));
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void onDeleteButtonClick(View view) {
        int selectedPosition = listView.getCheckedItemPosition();

        if (selectedPosition != ListView.INVALID_POSITION) {
            MainActivity.deleteNoteAt(selectedPosition);

            finish();
        } else {
            showToast(getString(R.string.warning_no_note_selected));
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
