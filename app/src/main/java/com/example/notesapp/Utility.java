package com.example.notesapp;
import android.content.Context;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class Utility {
    static void showToast(Context context, String message){
        Toast.makeText(context, message,Toast.LENGTH_SHORT).show();
    }

    static CollectionReference getCollectionReferenceForNotes() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser != null) {
            return FirebaseFirestore.getInstance().collection("users")
                    .document(currentUser.getUid())
                    .collection("notes");
        } else {
            // Handle the case where the user is not authenticated
            // You might want to redirect to the login screen or handle it appropriately
            return null;
        }
    }
//   static CollectionReference getCollectionReferenceForNotes(){
//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//       return FirebaseFirestore.getInstance().collection("notes").
//               document(currentUser.getUid()).collection("my_notes");
//   }


}