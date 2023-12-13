package com.naty.farmathory;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PillController {
    private DatabaseReference mDatabase;

    public PillController() {
        mDatabase = FirebaseDatabase.getInstance().getReference().child("pills");
    }

    public void addPill(Pill pill) {
        String key = mDatabase.push().getKey();
        if (key != null) {
            mDatabase.child(key).setValue(pill);
        }
    }

    public void getPill(String pillKey, final OnGetPillListener listener) {
        mDatabase.child(pillKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Pill pill = dataSnapshot.getValue(Pill.class);
                    listener.onGetPillSuccess(pill);
                } else {
                    listener.onGetPillFailure("Pill not found");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onGetPillFailure(databaseError.getMessage());
            }
        });
    }

    public interface OnGetPillListener {
        void onGetPillSuccess(Pill pill);

        void onGetPillFailure(String errorMessage);
    }
}
