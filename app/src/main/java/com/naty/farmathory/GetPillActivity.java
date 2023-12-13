package com.naty.farmathory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GetPillActivity extends AppCompatActivity  {

    private DatabaseReference mDatabase;

    private EditText pillNameEditText;
    private Button getButton;

    private PillController pillController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pillController = new PillController();
        getButton.setOnClickListener(view -> getAllPills());
        mDatabase = FirebaseDatabase.getInstance().getReference("pills");
        getAllPills();
    }

    private void getPill() {

        String pillName = pillNameEditText.getText().toString().trim();
        pillController.getPill(pillName, new PillController.OnGetPillListener() {
            @Override
            public void onGetPillSuccess(Pill pill) {
                // Manejar la pastilla obtenida exitosamente
            }

            @Override
            public void onGetPillFailure(String errorMessage) {
                // Manejar el fallo al obtener la pastilla
            }
        });
    }

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
                handlePillsList(pills);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                showToast("Error al obtener las pastillas: " + databaseError.getMessage());
            }
        });
    }

    private void handlePillsList(List<Pill> pills) {
        // Aquí puedes manejar la lista de pastillas como lo necesites
        for (Pill pill : pills) {
            showToast("Nombre: " + pill.getPillName() + ", Dosificación: " + pill.getDosage());
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

