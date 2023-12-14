package com.naty.farmathory.controller;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.naty.farmathory.model.Pill;
import com.naty.farmathory.model.PillDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PillController {
    private DatabaseReference mDatabase;
    List<Pill> pills = new ArrayList<>();

    public PillController() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child(user.getUid());
    }

    public void addPill(Pill pill) {
        String key = mDatabase.push().getKey();
        if (key != null) {
            pill.setKey(key);
        }
        mDatabase.child(pill.getPillName()).setValue(pill);
    }

    public void editPill(Pill pill) {
        mDatabase.child(pill.getPillName()).setValue(pill);
    }

    public void deletePill(Pill pill) {
        mDatabase.child(pill.getPillName()).removeValue();
    }

    public void setListener(Context myContext) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pills = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Pill pill = snapshot.getValue(Pill.class);
                    if (pill != null) {
                        pills.add(pill);
                    }
                }
                ((OnGetPillListener) myContext).onGetPillSuccess(pills);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                ((OnGetPillListener) myContext).onGetPillFailure(databaseError.getMessage());
            }
        });
    }

    public interface OnGetPillListener {
        void onGetPillSuccess(List<Pill> pills);
        void onGetPillFailure(String errorMessage);
    }
}
