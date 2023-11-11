package com.example.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;

public class NoteDetails extends AppCompatActivity {

    private static final String TAG = "NoteDetails";

    EditText edTitle, edContent;
    ImageButton saveNoteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        edTitle = findViewById(R.id.edTitle);
        edContent = findViewById(R.id.edContent);
        saveNoteBtn = findViewById(R.id.btnSaveNote);

        saveNoteBtn.setOnClickListener((v) -> saveNote());
    }
    void saveNote() {
        String noteTitle = edTitle.getText().toString();
        String noteContent = edContent.getText().toString();

        if (noteTitle==null || noteTitle.isEmpty()) {
            edTitle.setError("Title cannot be empty!");
            return;
        }
        Note note = new Note();
        note.setTitle(noteTitle);
        note.setContent(noteContent);
        note.setTimestamp(Timestamp.now());

        saveNoteToFirebase(note);
    }

    void saveNoteToFirebase(Note note) {
        DocumentReference documentReference = Utility.getCollectionReferenceForNotes().document();
        documentReference.set(note).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Utility.showToast(NoteDetails.this, "Note added successfully");
                    Log.d(TAG, "Note added successfully");
                    finish();
                } else {
                    Utility.showToast(NoteDetails.this, "Failed to add note");
                    Log.e(TAG, "Error adding note to Firebase", task.getException());
                    finish();
                }
            }
        });
    }
}