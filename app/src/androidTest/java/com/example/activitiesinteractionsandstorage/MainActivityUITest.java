package com.example.activitiesinteractionsandstorage;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.contrib.RecyclerViewActions;


import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

@RunWith(AndroidJUnit4ClassRunner.class)
public class MainActivityUITest {


    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Rule
    public ActivityScenarioRule<AddNoteActivity> addNoteActivityRule = new ActivityScenarioRule<>(AddNoteActivity.class);

    @Rule
    public ActivityScenarioRule<DeleteNoteActivity> deleteNoteActivityRule = new ActivityScenarioRule<>(DeleteNoteActivity.class);

    @Test
    public void clickAddNoteButton_opensAddNoteActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.btnAddNote)).perform(click());
    }

    @Test
    public void clickDeleteNoteButton_opensDeleteNoteActivity() {
        Espresso.onView(ViewMatchers.withId(R.id.btnDeleteNote)).perform(click());
    }

    @Test
    public void addNoteAndDelete_itWorksTogether() {

        Espresso.onView(ViewMatchers.withId(R.id.edtNoteName)).perform(typeText("TestNoteName"));
        Espresso.onView(ViewMatchers.withId(R.id.edtNoteContent)).perform(typeText("TestNoteContent"), closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.btnSaveNote)).perform(click());


        Espresso.onView(ViewMatchers.withId(R.id.btnDeleteNote)).perform(click());

        // Delete the note in DeleteNoteActivity
        Espresso.onView(ViewMatchers.withId(R.id.listView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        Espresso.onView(ViewMatchers.withId(R.id.btnDelete)).perform(click());
    }

    @Test
    public void addMultipleNotesAndScroll_itWorksTogether() {

        for (int i = 0; i < 10; i++) {
            Espresso.onView(ViewMatchers.withId(R.id.btnAddNote)).perform(click());
            Espresso.onView(ViewMatchers.withId(R.id.edtNoteName)).perform(typeText("Note" + i));
            Espresso.onView(ViewMatchers.withId(R.id.edtNoteContent)).perform(typeText("Content" + i), closeSoftKeyboard());
            Espresso.onView(ViewMatchers.withId(R.id.btnSaveNote)).perform(click());
        }

        // Scroll the ListView in MainActivity
        Espresso.onView(ViewMatchers.withId(R.id.listView)).perform(RecyclerViewActions.scrollToPosition(5));
    }

    @Test
    public void deleteAllNotes_itWorksTogether() {
        // Add a note in AddNoteActivity
        Espresso.onView(ViewMatchers.withId(R.id.btnAddNote)).perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.edtNoteName)).perform(typeText("TestNoteName"));
        Espresso.onView(ViewMatchers.withId(R.id.edtNoteContent)).perform(typeText("TestNoteContent"), closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.btnSaveNote)).perform(click());


        Espresso.onView(ViewMatchers.withId(R.id.btnDeleteNote)).perform(click());


        while (true) {
            try {
                Espresso.onView(ViewMatchers.withId(R.id.listView)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
                Espresso.onView(ViewMatchers.withId(R.id.btnDelete)).perform(click());
            } catch (Exception e) {

                break;
            }
        }
    }
}

