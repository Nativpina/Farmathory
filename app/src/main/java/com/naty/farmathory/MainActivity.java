package com.naty.farmathory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button addPillButton;
    private Button allPillButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Inicializar Firebase
        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        addPillButton = findViewById(R.id.btnPill);
        addPillButton.setOnClickListener(view -> openAddPillActivity());

        allPillButton = findViewById(R.id.button12);
        allPillButton.setOnClickListener(view -> openAllPillActivity());
    }

    private void openAddPillActivity() {
        Intent intent = new Intent(MainActivity.this, AddPillActivity.class);
        startActivity(intent);
    }

    private void openAllPillActivity() {
        Intent intent = new Intent(MainActivity.this, GetPillActivity.class);
        startActivity(intent);
    }
}
