package com.naty.farmathory;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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
            mDatabase.child(key).setValue(pill);
        }
    }

    public void setListener(Context myContext) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pills.clear();
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
