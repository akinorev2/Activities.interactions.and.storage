package com.example.activitiesinteractionsandstorage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import android.text.Editable;
import android.widget.EditText;
import android.widget.ListView;
import org.mockito.Mockito;
import org.robolectric.shadows.ShadowToast;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(RobolectricTestRunner.class)
public class MainActivityUnitTest {


    private MainActivity mainActivity;
    private AddNoteActivity addNoteActivity;
    private DeleteNoteActivity deleteNoteActivity;

    @Before
    public void setUp() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);
        addNoteActivity = Robolectric.setupActivity(AddNoteActivity.class);
        deleteNoteActivity = Robolectric.setupActivity(DeleteNoteActivity.class);
    }

    @Test
    public void testAddNote() {
        int initialSize = MainActivity.getNotes().size();
        Note testNote = new Note("TestNote", "TestContent");

        MainActivity.addNote(testNote);

        assertEquals(initialSize + 1, MainActivity.getNotes().size());
        assertTrue(MainActivity.getNotes().contains(testNote));
    }

    @Test
    public void testDeleteNote() {
        Note testNote = new Note("TestNote", "TestContent");
        MainActivity.addNote(testNote);

        int initialSize = MainActivity.getNotes().size();

        MainActivity.deleteNoteAt(0);

        assertEquals(initialSize - 1, MainActivity.getNotes().size());
        assertTrue(!MainActivity.getNotes().contains(testNote));
    }

    @Test
    public void testSaveButtonClickWithEmptyName() {
        addNoteActivity.onSaveButtonClick(null);

        assertEquals("Name should not be empty", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testSaveButtonClickWithValidInput() {
        AddNoteActivity spyActivity = Mockito.spy(addNoteActivity);
        EditText mockNameEditText = Mockito.mock(EditText.class);
        EditText mockContentEditText = Mockito.mock(EditText.class);

        Mockito.when(spyActivity.findViewById(R.id.edtNoteName)).thenReturn(mockNameEditText);
        Mockito.when(spyActivity.findViewById(R.id.edtNoteContent)).thenReturn(mockContentEditText);

        Mockito.when(mockNameEditText.getText()).thenReturn(Mockito.mock(Editable.class));
        Mockito.when(mockNameEditText.getText().toString()).thenReturn("TestName");

        Mockito.when(mockContentEditText.getText()).thenReturn(Mockito.mock(Editable.class));
        Mockito.when(mockContentEditText.getText().toString()).thenReturn("TestContent");

        spyActivity.onSaveButtonClick(null);

        assertEquals("Note saved", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testDeleteButtonClickWithNoSelection() {
        deleteNoteActivity.onDeleteButtonClick(null);

        assertEquals("No note selected", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void testDeleteButtonClickWithSelection() {
        DeleteNoteActivity spyActivity = Mockito.spy(deleteNoteActivity);
        ListView mockListView = Mockito.mock(ListView.class);

        Mockito.when(spyActivity.findViewById(R.id.listView)).thenReturn(mockListView);
        Mockito.when(mockListView.getCheckedItemPosition()).thenReturn(0);

        spyActivity.onDeleteButtonClick(null);

        assertEquals("Note deleted", ShadowToast.getTextOfLatestToast());
    }
}