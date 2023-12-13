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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inicializar Firebase
        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setContentView(R.layout.activity_menu);
        addPillButton = findViewById(R.id.btnPill);
        addPillButton.setOnClickListener(view -> openAddPillActivity());
    }

    private void openAddPillActivity() {
        Intent intent = new Intent(MainActivity.this, AddPillActivity.class);
        startActivity(intent);
    }

}
