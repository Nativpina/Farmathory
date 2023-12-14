package com.naty.farmathory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.naty.farmathory.adapter.PillAdapter;

import java.util.ArrayList;
import java.util.List;

public class GetPillActivity extends AppCompatActivity implements PillController.OnGetPillListener {
    PillAdapter adPills;
    RecyclerView rvPills;

    PillController pillController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill);

        adPills = new PillAdapter();

        int numberOfColumns = 1;
        RecyclerView.LayoutManager lm_products = new GridLayoutManager(getApplicationContext(), numberOfColumns);

        rvPills = this.findViewById(R.id.rvPills);
        rvPills.setHasFixedSize(true);
        rvPills.setLayoutManager(lm_products);
        rvPills.setItemAnimator(new DefaultItemAnimator());
        rvPills.setAdapter(adPills);

        pillController = new PillController();
        pillController.setListener(this);

        // mDatabase = FirebaseDatabase.getInstance().getReference().child("pills");
        // getAllPills();
    }

    /*
    private void getAllPills() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Pill> pills = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Pill pill = snapshot.getValue(Pill.class);
                    if (pill != null) {
                        pills.add(pill);
                    }
                }

                // Manejar la lista de pastillas
                // handlePillsList(pills);
                adPills.setItems(pills);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showToast("Error al obtener las pastillas: " + databaseError.getMessage());
            }
        });
    }
    */

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetPillSuccess(List<Pill> pills) {
        adPills.setItems(pills);
    }

    @Override
    public void onGetPillFailure(String errorMessage) {
        showToast("Error al obtener las pastillas: " + errorMessage);
    }
}

