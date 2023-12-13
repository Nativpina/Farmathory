package com.naty.farmathory;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
}
